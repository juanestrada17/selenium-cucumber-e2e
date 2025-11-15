pipeline {
    agent any

    environment {
        PROJECT_ID = "onyx-segment-477302-c1"
        REGION = "northamerica-northeast2"
        REPO = "selenium-tests"
        IMAGE = "selenium-tests"
        TAG = "latest"

        PATH = "/var/lib/jenkins/google-cloud-sdk/bin:${env.PATH}"

        // Artifact Registry auth
        GCP_KEY_FILE = credentials('artifact-registry-key')

        // GKE service account used to deploy + run job
        GKE_KEY_FILE = credentials('gke-admin-key')
        CLUSTER_NAME = 'gke-cluster'
        CLUSTER_REGION = 'northamerica-northeast2'
    }

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    options {
        timestamps()
        buildDiscarder(logRotator(daysToKeepStr: '30', numToKeepStr: '10'))
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build (no tests)') {
            steps {
                echo 'Building project...'
                sh 'mvn -Dmaven.test.skip=true clean package'
            }
        }

        stage('Authenticate to Google Cloud') {
            steps {
                sh '''
                gcloud auth activate-service-account --key-file="$GCP_KEY_FILE"
                gcloud config set project $PROJECT_ID
                gcloud auth configure-docker ${REGION}-docker.pkg.dev --quiet
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                docker build -t ${REGION}-docker.pkg.dev/${PROJECT_ID}/${REPO}/${IMAGE}:${TAG} .
                '''
            }
        }

        stage('Push to Artifact Registry') {
            steps {
                sh '''
                docker push ${REGION}-docker.pkg.dev/${PROJECT_ID}/${REPO}/${IMAGE}:${TAG}
                '''
            }
        }

        stage('Run Selenium Tests in GKE') {
            steps {
                sh '''
                gcloud auth activate-service-account --key-file="$GKE_KEY_FILE"
                gcloud config set project $PROJECT_ID
                gcloud container clusters get-credentials $CLUSTER_NAME --region $CLUSTER_REGION

                export IMAGE_URL="${REGION}-docker.pkg.dev/${PROJECT_ID}/${REPO}/${IMAGE}:${TAG}"

                # apply the job
                envsubst < k8s/selenium-job.yaml | kubectl apply -f -

                # wait for job completion
                kubectl wait --for=condition=complete job/selenium-test-job --timeout=600s

                # fetch logs
                kubectl logs job/selenium-test-job
                '''
            }
        }

        stage('Cleanup Workspace') {
            steps {
                cleanWs()
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs above.'
        }
    }
}
