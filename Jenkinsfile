pipeline {
    agent any

    tools {
        maven 'Maven_Latest_3.9.12'  // Maven name from jenkins
        jdk 'JDK_File_21'           //jdk name from jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/mussama1/PracticalExercise.git'
            }
        }

        stage('Install Dependencies') {
            steps {
                bat "mvn clean install -DskipTests"
            }
        }

        stage('Run Tests') {
            steps {
                bat "mvn test"
            }
        }
    }

    post {
        always {
            junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
        }
    }
}