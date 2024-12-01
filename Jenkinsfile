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
                script {
                    docker.build("myapp:latest")  // Создаём Docker-образ
                }
            }
        }
        stage('Run Docker') {
            steps {
                script {
                    docker.image("myapp:latest").run("-p 8081:8081")  // Пробрасываем порт 8080
                }
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
