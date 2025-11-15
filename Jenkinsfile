pipeline {
    agent any

    environment {
        PROJECT_ID = "onyx-segment-477302-c1"
        REGION = "northamerica-northeast2"
        REPO = "selenium-tests"
        IMAGE = "selenium-tests"
        TAG = "latest"
        PATH = "/var/lib/jenkins/google-cloud-sdk/bin:${env.PATH}"

        GCP_KEY_FILE = credentials('artifact-registry-key')

        // GKE service account JSON
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

        stage('Build and Test') {
            steps {
                echo 'Building and running tests...'
                script {
                    if (isUnix()) {
                        sh 'mvn clean test'
                    } else {
                        bat 'mvn clean test'
                    }
                }
            }
            post {
                always {
                    allure(
                        includeProperties: false,
                        results: [[path: 'target/allure-results']]
                    )
                }
            }
        }

        stage('Publish Cucumber Report') {
            steps {
                script {
                    def platform = isUnix() ? 'Linux' : 'Windows'
                    cucumber buildStatus: 'UNSTABLE',
                             fileIncludePattern: 'Report/*.json',
                             sortingMethod: 'ALPHABETICAL',
                             trendsLimit: 10,
                             classifications: [
                                 [key: 'Platform', value: platform],
                                 [key: 'Browser', value: 'Chrome']
                             ]
                }
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Archive Artifacts') {
            steps {
                echo 'Archiving test reports...'
                archiveArtifacts artifacts: 'Report/*.html', fingerprint: true
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

       stage('Deploy to GKE') {
           steps {
               sh '''
               gcloud auth activate-service-account --key-file="$GKE_KEY_FILE"
               gcloud config set project $PROJECT_ID
               gcloud container clusters get-credentials $CLUSTER_NAME --region $CLUSTER_REGION

               export IMAGE_URL="${REGION}-docker.pkg.dev/${PROJECT_ID}/${REPO}/${IMAGE}:${TAG}"

               # Render YAML with env variables and apply it
               envsubst < k8s/selenium-job.yaml | kubectl apply -f -

               # Wait for job completion
               kubectl wait --for=condition=complete job/selenium-test-job --timeout=600s

               # Show logs
               kubectl logs job/selenium-test-job
               '''
           }
       }


        stage('Cleanup Workspace') {
            steps {
                echo 'Cleaning workspace...'
                cleanWs()
            }
        }


    }


    post {
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
