pipeline {
    agent {
            kubernetes {
                label 'selenium-agent'
                defaultContainer 'selenium'
                yaml """
    apiVersion: v1
    kind: Pod
    metadata:
      labels:
        app: selenium-agent
    spec:
      containers:
      - name: selenium
        image: your-dockerhub-user/selenium-tests:latest
        command:
        - cat
        tty: true
        resources:
          requests:
            memory: "2Gi"
            cpu: "1000m"
          limits:
            memory: "3Gi"
            cpu: "2"
        volumeMounts:
        - name: dshm
          mountPath: /dev/shm
      volumes:
      - name: dshm
        emptyDir:
          medium: Memory
    """
            }
        }

    environment {
        MAVEN_OPTS = '-Dmaven.test.failure.ignore=true -Dwebdriver.chrome.silentOutput=true'
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
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
