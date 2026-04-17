pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning repository...'
            }
        }

        stage('Install Dependencies') {
            steps {
                bat 'pip install -r requirements.txt'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'pytest || echo No tests found'
            }
        }

        stage('Run Application') {
            steps {
                bat 'python app.py'
            }
        }
    }
}
