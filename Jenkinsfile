pipeline {
    agent none  // Указывает, что пайплайн не должен использовать глобальный агент
    stages {
        stage('Prepare Environment') {
            agent {
                docker { image 'maven:3.8.1-openjdk-11' }  // Используем Docker-контейнер для этой стадии
            }
            steps {
                echo 'Installing Maven...'
                sh 'mvn --version'
            }
        }
        stage('Build') {
            agent {
                docker { image 'maven:3.8.1-openjdk-11' }
            }
            steps {
                echo 'Building the project...'
                sh 'mvn clean package'
            }
        }
        stage('Docker Build') {
            agent {
                docker { image 'docker:19.03.12-dind' }  // Используем Docker для создания образа
            }
            steps {
                script {
                    docker.build("myapp:latest")  // Создаём Docker-образ
                }
            }
        }
        stage('Run Docker') {
            agent {
                docker { image 'docker:19.03.12-dind' }
            }
            steps {
                script {
                    docker.image("myapp:latest").run("-p 8080:8080")  // Пробрасываем порт 8080
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
