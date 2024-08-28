pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'candy', url: 'https://github.com/werwerTrain/werwerHotel.git'
            }
        }
        
        stage('delete old image in k8s'){
            steps{
                 bat '''
                kubectl delete -f k8s/wwHotel-deployment.yaml || true
                kubectl delete -f k8s/wwHotel-service.yaml || true
                '''
            }
        }
        stage('Build new image') {
            steps {
                script {
                    powershell '''
                    $containers = docker ps -q --filter "ancestor=qiuer0121/wwhotel:latest"
                    foreach ($container in $containers) {
                        Write-Output "Stopping container $container"
                        docker stop $container
                    }
    
                    $allContainers = docker ps -a -q --filter "ancestor=qiuer0121/wwhotel:latest"
                    foreach ($container in $allContainers) {
                        Write-Output "Removing container $container"
                        docker rm $container
                    }
                    '''
                    bat 'docker rmi -f qiuer0121/wwhotel:latest || true'
                    bat '''
                    docker build -t qiuer0121/wwhotel .
                    '''
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                        bat '''
                        echo 040723Lyl| docker login -u 3181577132 --password-stdin
                        docker push 3181577132/wwhotel:latest
                        '''
                }
            }
        }


        stage('deploy to k8s'){
            steps{
                bat '''
                kubectl apply -f k8s/wwHotel-deployment.yaml
                kubectl apply -f k8s/wwHotel-service.yaml
                kubectl apply -f k8s/wwHotel-hpa.yaml
                '''
                echo '部署成功'
            }
        }
    }

    post {
        always {
            bat 'docker system prune -f'
        }
        success {
            echo 'Build and deployment succeeded!'
        }
        failure {
            echo 'Build or deployment failed.'
        }
    }
}
