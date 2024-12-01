pipeline {
    agent {
        docker {
            image 'jenkins/jenkins:lts-jdk17'
            args '-u root'
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
                echo 'Building the project...'
                sh 'mvn clean package'
            }
        }
        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'
                sh 'docker build -t myapp:latest .'
            }
        }
        stage('Run Docker') {
            steps {
                echo 'Running Docker container...'
                sh 'docker run -d -p 8081:8081 --name myapp myapp:latest'
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
