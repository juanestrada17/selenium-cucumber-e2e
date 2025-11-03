pipeline {
    agent any

    tools {
       jdk 'JDK21'
       maven 'Maven3.9.11'
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

        stage('Build') {
            steps {
                echo 'Building...'
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo 'Testing...'
                bat 'mvn test'
            }
        }

        stage('Publish Cucumber Report'){
            steps {
                cucumber buildStatus: 'UNSTABLE',
                fileIncludePattern: '**/target/cucumber-reports/*.json',
                sortingMethod: 'ALPHABETICAL',
                trendsLimit: 10,
                classifications: [
                    [key: 'Platform', value: 'Windows'],
                    [key: 'Browser', value: 'Chrome'],
                ]
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
    }
    failure {
        mail to: 'juanestrada17@outlook.com',
             subject: "Build Failed: ${currentBuild.fullDisplayName}",
             body: "The build has failed. Please check the Jenkins job for details: ${env.BUILD_URL}"
    }
}