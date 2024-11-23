pipeline {
    agent {
        docker {
            image 'jenkins/jenkins:lts-jdk17'
            args '-u root' // Используем root для установки Maven
        }
    }

    stages {
        stage('Prepare Environment') {
            steps {
                echo 'Installing Maven...'
                sh '''
                    apt-get update
                    apt-get install -y maven
                    mvn --version
                '''
            }
        }
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }
        stage('Build') {
            steps {
                echo 'Building project with Maven...'
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed.'
        }
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
