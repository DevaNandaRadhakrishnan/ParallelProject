pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'openJDK'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        git url: 'https://github.com/DevaNandaRadhakrishnan/ParallelProject.git', branch: 'main'
                    }
                }
            }
        }

        stage('Pre-Build') {
            steps {
                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        bat 'docker-compose down || true'

                        def services = ['api-gateway', 'service-registry', 'auth-service', 'author-service', 'book-service']

                        for (service in services) {
                            bat 'docker rmi -f ${service}:latest || true'

                        }
                    }
                }
            }
        }

        stage('Build') {
            steps {
                script {
                     def services = ['api-gateway', 'service-registry', 'auth-service', 'author-service', 'book-service']
                    for (service in services) {
                        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                            dir(service) {
                                bat 'mvn clean package -DskipTests'
                            }
                        }
                    }
                }
            }
        }

        stage('Run Docker Compose Up') {
            steps {
                script {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        bat 'docker-compose up -d'
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed!'
        }
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline had errors, but execution continued.'
        }
    }
}

def getPort(service) {
    switch (service) {
        case 'api-gateway':
            return '7078'
        case 'service-registry':
            return '8761'
        case 'auth-service':
            return '7079'
        case 'author-service':
            return '7070'
        case 'book-service':
            return '7071'
        default:
            return '8080'
    }
}