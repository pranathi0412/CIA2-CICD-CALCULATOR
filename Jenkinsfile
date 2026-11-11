pipeline {
    agent any

    environment {
        PATH = "/opt/homebrew/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin"
        KUBECONFIG = "/Users/pranathib/.kube/config"
        ACR_NAME = "cia2registry2026"
        ACR_SERVER = "cia2registry2026.azurecr.io"
        IMAGE_NAME = "calculator-app"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Package Application') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Azure Registry Login') {
            steps {
                sh 'az acr login --name $ACR_NAME'
            }
        }

        stage('Docker Build and Push') {
            steps {
                sh '''
                    docker buildx build \
                      --platform linux/amd64 \
                      -t $ACR_SERVER/$IMAGE_NAME:$BUILD_NUMBER \
                      --push .
                '''
            }
        }

        stage('Deploy to AKS using Ansible') {
            steps {
                withEnv(["IMAGE_TAG=${BUILD_NUMBER}"]) {
                    sh 'ansible-playbook -i ansible/inventory.ini ansible/deploy.yml'
                }
            }
        }

        stage('Verify Deployment') {
            steps {
                sh 'kubectl get pods,services'
            }
        }
    }

    post {
        success {
            echo 'CI/CD completed: image pushed to ACR and deployed to AKS!'
        }
        failure {
            echo 'Pipeline failed. Check the Console Output.'
        }
    }
}
