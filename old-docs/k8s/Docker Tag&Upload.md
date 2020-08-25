# Tag and Uplaod your docker image.
The address of my docker registry is 10.176.122.20:5000.    
You could replace all 10.176.122.20:5000 with you own docker registry address.    

## Part 1:
docker tag codewisdom/ts-admin-basic-info-service 10.176.122.20:5000/ts-admin-basic-info-service    
docker tag codewisdom/ts-admin-order-service 10.176.122.20:5000/ts-admin-order-service    
docker tag codewisdom/ts-admin-route-service 10.176.122.20:5000/ts-admin-route-service    
docker tag codewisdom/ts-admin-travel-service 10.176.122.20:5000/ts-admin-travel-service    
docker tag codewisdom/ts-admin-user-service 10.176.122.20:5000/ts-admin-user-service    
docker tag codewisdom/ts-assurance-service 10.176.122.20:5000/ts-assurance-service    
docker tag codewisdom/ts-basic-service 10.176.122.20:5000/ts-basic-service    
docker tag codewisdom/ts-cancel-service 10.176.122.20:5000/ts-cancel-service    
docker tag codewisdom/ts-config-service 10.176.122.20:5000/ts-config-service    
docker tag codewisdom/ts-consign-price-service 10.176.122.20:5000/ts-consign-price-service    
docker tag codewisdom/ts-consign-service 10.176.122.20:5000/ts-consign-service    
docker tag codewisdom/ts-contacts-service 10.176.122.20:5000/ts-contacts-service    
docker tag codewisdom/ts-execute-service 10.176.122.20:5000/ts-execute-service    
docker tag codewisdom/ts-food-map-service 10.176.122.20:5000/ts-food-map-service    
docker tag codewisdom/ts-food-service 10.176.122.20:5000/ts-food-service    
docker tag codewisdom/ts-inside-payment-service 10.176.122.20:5000/ts-inside-payment-service    
docker tag codewisdom/ts-login-service 10.176.122.20:5000/ts-login-service    
docker tag codewisdom/ts-news-service 10.176.122.20:5000/ts-news-service    
docker tag codewisdom/ts-notification-service 10.176.122.20:5000/ts-notification-service    
docker tag codewisdom/ts-order-other-service 10.176.122.20:5000/ts-order-other-service    
docker tag codewisdom/ts-order-service 10.176.122.20:5000/ts-order-service    
docker tag codewisdom/ts-payment-service 10.176.122.20:5000/ts-payment-service    
docker tag codewisdom/ts-preserve-other-service 10.176.122.20:5000/ts-preserve-other-service    
docker tag codewisdom/ts-preserve-service 10.176.122.20:5000/ts-preserve-service    
docker tag codewisdom/ts-price-service 10.176.122.20:5000/ts-price-service    
docker tag codewisdom/ts-rebook-service 10.176.122.20:5000/ts-rebook-service    
docker tag codewisdom/ts-register-service 10.176.122.20:5000/ts-register-service    
docker tag codewisdom/ts-route-plan-service 10.176.122.20:5000/ts-route-plan-service    
docker tag codewisdom/ts-route-service 10.176.122.20:5000/ts-route-service    
docker tag codewisdom/ts-seat-service 10.176.122.20:5000/ts-seat-service    
docker tag codewisdom/ts-security-service 10.176.122.20:5000/ts-security-service    
docker tag codewisdom/ts-sso-service 10.176.122.20:5000/ts-sso-service    
docker tag codewisdom/ts-station-service 10.176.122.20:5000/ts-station-service    
docker tag codewisdom/ts-ticket-office-service 10.176.122.20:5000/ts-ticket-office-service    
docker tag codewisdom/ts-ticketinfo-service 10.176.122.20:5000/ts-ticketinfo-service    
docker tag codewisdom/ts-train-service 10.176.122.20:5000/ts-train-service    
docker tag codewisdom/ts-travel2-service 10.176.122.20:5000/ts-travel2-service    
docker tag codewisdom/ts-travel-service 10.176.122.20:5000/ts-travel-service    
docker tag codewisdom/ts-travel-plan-service 10.176.122.20:5000/ts-travel-plan-service    
docker tag codewisdom/ts-ui-dashboard 10.176.122.20:5000/ts-ui-dashboard    
docker tag codewisdom/ts-verification-code-service 10.176.122.20:5000/ts-verification-code-service    
docker tag codewisdom/ts-voucher-service 10.176.122.20:5000/ts-voucher-service    
docker tag mongo 10.176.122.20:5000/ts-mongo    
docker tag mysql 10.176.122.20:5000/ts-mysql    
docker tag rabbitmq:management 10.176.122.20:5000/ts-rabbitmq-management    
docker tag redis 10.176.122.20:5000/ts-redis    
docker tag openzipkin/zipkin 10.176.122.20:5000/ts-openzipkin-zipkin    

## Part2
docker push 10.176.122.20:5000/ts-admin-basic-info-service    
docker push 10.176.122.20:5000/ts-admin-order-service    
docker push 10.176.122.20:5000/ts-admin-route-service    
docker push 10.176.122.20:5000/ts-admin-travel-service    
docker push 10.176.122.20:5000/ts-admin-user-service    
docker push 10.176.122.20:5000/ts-assurance-service    
docker push 10.176.122.20:5000/ts-basic-service    
docker push 10.176.122.20:5000/ts-cancel-service    
docker push 10.176.122.20:5000/ts-config-service    
docker push 10.176.122.20:5000/ts-consign-price-service    
docker push 10.176.122.20:5000/ts-consign-service    
docker push 10.176.122.20:5000/ts-contacts-service    
docker push 10.176.122.20:5000/ts-execute-service    
docker push 10.176.122.20:5000/ts-food-map-service    
docker push 10.176.122.20:5000/ts-food-service    
docker push 10.176.122.20:5000/ts-inside-payment-service    
docker push 10.176.122.20:5000/ts-login-service    
docker push 10.176.122.20:5000/ts-news-service    
docker push 10.176.122.20:5000/ts-notification-service    
docker push 10.176.122.20:5000/ts-order-other-service    
docker push 10.176.122.20:5000/ts-order-service    
docker push 10.176.122.20:5000/ts-payment-service    
docker push 10.176.122.20:5000/ts-preserve-other-service    
docker push 10.176.122.20:5000/ts-preserve-service    
docker push 10.176.122.20:5000/ts-price-service    
docker push 10.176.122.20:5000/ts-rebook-service    
docker push 10.176.122.20:5000/ts-register-service    
docker push 10.176.122.20:5000/ts-route-plan-service    
docker push 10.176.122.20:5000/ts-route-service    
docker push 10.176.122.20:5000/ts-seat-service    
docker push 10.176.122.20:5000/ts-security-service    
docker push 10.176.122.20:5000/ts-sso-service    
docker push 10.176.122.20:5000/ts-station-service    
docker push 10.176.122.20:5000/ts-ticket-office-service    
docker push 10.176.122.20:5000/ts-ticketinfo-service    
docker push 10.176.122.20:5000/ts-train-service    
docker push 10.176.122.20:5000/ts-travel2-service    
docker push 10.176.122.20:5000/ts-travel-service     
docker push 10.176.122.20:5000/ts-travel-plan-service    
docker push 10.176.122.20:5000/ts-ui-dashboard    
docker push 10.176.122.20:5000/ts-verification-code-service    
docker push 10.176.122.20:5000/ts-voucher-service    
docker push 10.176.122.20:5000/ts-mongo    
docker push 10.176.122.20:5000/ts-mysql    
docker push 10.176.122.20:5000/ts-rabbitmq-management    
docker push 10.176.122.20:5000/ts-redis    
docker push 10.176.122.20:5000/ts-openzipkin-zipkin    