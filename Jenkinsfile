pipeline {
    agent any
    stages {
        stage("Setup permissions") {
            steps {
                chmod +x ./gradlew
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
    }
}