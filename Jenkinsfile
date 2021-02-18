pipeline {
    agent any
    environment {
        registry = "registry.merilairon.com"
        dockerImage = 'calculator'
    }
    stages {
        stage("Setup permissions") {
            steps {
                sh "chmod +x ./gradlew"
            }
        }
        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }
        stage("Unit test") {
            steps {
                sh "./gradlew test"
            }
        }
        stage("Code coverage") {
            steps {
                sh "./gradlew jacocoTestReport"
                publishHTML (
                    target: [
                    reportDir: 'build/reports/jacoco/test/html',
                    reportFiles: 'index.html',
                    reportName: "JaCoCo Report"
                ])
                sh "./gradlew jacocoTestCoverageVerification"
            }
        }
        stage("Static code analysis") {
            steps {
                sh "./gradlew checkstyleMain"
                publishHTML (target: [
                    reportDir: 'build/reports/checkstyle/',
                    reportFiles: 'main.html',
                    reportName: "Checkstyle Report"
                ])
            }
        }

        stage("Package") {
            steps {
                sh "./gradlew build"
           }
        }

        stage("Docker build") {
            steps {
                sh "sudo docker build -t $registry/$dockerImage:$BUILD_NUMBER ."
            }
        }

        stage("Docker push") {
            steps {
                script {
                    sh "sudo docker push $registry/$dockerImage:$BUILD_NUMBER"
                }
            }
        }

        stage('Cleaning up') {
            steps {
                sh "sudo docker rmi $registry/$dockerImage:$BUILD_NUMBER"
            }
        }
    }
}