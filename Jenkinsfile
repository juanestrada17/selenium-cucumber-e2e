pipeline {
    agent any

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
                bat 'mvn clean test'
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
                cucumber buildStatus: 'UNSTABLE',
                         fileIncludePattern: 'Report/*.json',
                         sortingMethod: 'ALPHABETICAL',
                         trendsLimit: 10,
                         classifications: [
                             [key: 'Platform', value: 'Windows'],
                             [key: 'Browser', value: 'Chrome']
                         ]
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
