pipeline {
	agent any
	tools {
    maven 'maven-3'
    jdk 'java'
  }
  
	stages {
		stage('SCM Checkout'){
			steps{
        	git credentialsId: 'gitPwd', url: 'https://github.com/KorbiO/auth-service'
        		}
    }
		stage('Compile') {
			steps {
			withMaven(maven : 'maven-3'){
				
				bat './mvnw clean compile'
				
				
			}	
				  }
		}
		stage('Package') {
			steps {
			withMaven(maven : 'maven-3'){
				
				
				bat './mvnw package -DskipTests'
				
			}	
				  }
		}
		stage('Build Docker Image'){
			steps{
        		bat 'docker build -t omarkorbi/auth-service:latest .'
        		}
    }
    stage('Push Docker Image'){
     	steps{
	        bat 'docker login -u omarkorbi -p gotktpas123'
	  		bat 'docker tag omarkorbi/auth-service:latest omarkorbi/auth-service '
	  		bat 'docker push omarkorbi/auth-service'
	  		}
    }
     stage('Run Kubernetes'){
    	steps{
    		
    		bat 'kubectl --kubeconfig ./config apply -f mysqldb-root-credentials.yaml'
    		bat 'kubectl --kubeconfig ./config apply -f mysqldb-credentials.yaml'
    		bat 'kubectl --kubeconfig ./config apply -f mysql-configmap.yaml'
   	   		bat 'kubectl --kubeconfig ./config apply -f mysql-deployment.yaml'
   	   		bat 'kubectl --kubeconfig ./config apply -f deployment.yaml'
   	   		}
    }
     
    
		
	}
}