pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Push image') {
            docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-creds') {
                app.push("${env.BUILD_NUMBER}")
                app.push("latest")
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }
    }
}
