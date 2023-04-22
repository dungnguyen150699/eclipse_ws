# k8s-deployment

#1 create deployment for auth-server
kubectl apply -f auth-server-deployment.yaml

#2 create service for auth-server
kubectl apply -f auth-server-service.yaml

#3 create config map for service module
kubectl apply -f service-config-map.yaml

#4 create secret for service module
kubectl apply -f service-secret.yaml

#5 create deployment for service-server
kubectl apply -f service-server-deployment.yaml

#6 create service for service-server
kubectl apply -f service-server-service.yaml
