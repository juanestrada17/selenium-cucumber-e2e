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
        // Checkout stage to pull the latest code from SCM
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build And Test') {
            steps {
                echo 'Building...'
                bat 'mvn clean install'
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
                             [key: 'Browser', value: 'Chrome'],
                         ]

                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Archive Artifacts') {
            steps {
                echo 'Archiving artifacts...'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }

    post {
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }

        always {
            allure includeProperties:
            false,
            jdk: '',
            results: [[path: 'target/allure-results']]
        }

        success {
            echo 'Build succeeded!'
        }

        failure {
            echo 'Build failed!'
        }
    }
}