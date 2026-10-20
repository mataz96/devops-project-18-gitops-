pipeline {
    agent any

    environment {
        // اسم السيرفر المعرف في جنكينز
        SONAR_SERVER_NAME = 'SonarQubeServer'
    }

    stages {
        stage('1. Checkout Code') {
            steps {
                echo 'Pulling application source code...'
                // هنا يسحب الكود (محلياً أو من Git)
                sh 'rm -rf app && cp -r /var/jenkins_home/workspace-source/app . 2>/dev/null || true'
            }
        }

        stage('2. Maven Compile & Test') {
            steps {
                dir('app') {
                    echo 'Compiling Java code and running tests...'
                    sh 'mvn clean compile package -DskipTests=false'
                }
            }
        }

        stage('3. SonarQube Analysis') {
            steps {
                dir('app') {
                    echo 'Scanning code security and quality...'
                    withSonarQubeEnv("${SONAR_SERVER_NAME}") {
                        sh 'mvn sonar:sonar -Dsonar.projectKey=devops-demo-app -Dsonar.host.url=http://sonarqube:9000 -Dsonar.java.binaries=target/classes'
                    }
                }
            }
        }

        stage('4. Docker Build Image') {
            steps {
                dir('app') {
                    echo 'Building container image automatically...'
                    sh 'docker build -t matazalassiry/devops-demo-app:v1.0.0 .'
                }
            }
        }
    }

    post {
        success {
            echo '🎉 All stages passed! The code is secure and Docker image is built.'
        }
        failure {
            echo '❌ Pipeline failed! Deployment aborted.'
        }
    }
}