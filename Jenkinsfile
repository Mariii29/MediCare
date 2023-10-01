pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                script {
                    // Build your Spring Boot application with Gradle
                    sh './gradlew bootBuildImage --imageName=public.ecr.aws/p8x2g8s1/marisia:medicare'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Push the Docker image to a AWS ECR
                    sh 'docker push my-spring-boot-app:latest'
                }
            }
        }
    }
}
