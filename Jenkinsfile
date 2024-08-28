pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'sxq', url: 'https://github.com/werwerTrain/werwerHotel.git'
            }
        }
        
        // stage('delete old image in k8s'){
        //     steps{
        //          bat '''
        //         kubectl delete -f k8s/wwhotel-deployment.yaml
        //         kubectl delete -f k8s/wwhotel-service.yaml
        //         '''
        //     }
        // }
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
                    bat 'docker rmi -f qiuer0121/wwhotel:latest'
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
                        echo 20050121Rabbit| docker login -u qiuer0121 --password-stdin
                        docker push qiuer0121/wwhotel:latest
                        '''
                }
            }
        }


        stage('deploy to k8s'){
            steps{
                bat '''
                kubectl apply -f k8s/wwhotel-deployment.yaml
                kubectl apply -f k8s/wwhotel-service.yaml
                kubectl apply -f k8s/wwhotel-hpa.yaml
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
