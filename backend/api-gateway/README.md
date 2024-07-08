## Setup:

For local development:
- create a .env file with the following
    - SERVICES_URL_PRODUCT=http://localhost:8080
    - SERVICES_URL_ORDER=http://localhost:8081
    - SERVICES_URL_INVENTORY=http://localhost:8082
    - SERVICES_URL_FRONTEND=http://localhost:4200
    - MYSQL_ROOT_PASSWORD=root
    - MYSQL_DATABASE=keycloak
    - MYSQL_USER=keycloak
    - MYSQL_PASSWORD=password
    - KEYCLOAK_DB_VENDOR=MYSQL
    - KEYCLOAK_DB_ADDRESS=mysql
    - KEYCLOAK_DATABASE=keycloak
    - KEYCLOAK_USER=keycloak
    - KEYCLOAK_PASSWORD=password
    - KEYCLOAK_ADMIN_USER=admin
    - KEYCLOAK_ADMIN_PASSWORD=admin
    - KEYCLOAK_PORT=8181
    - LOKI_PORT=3100
    - PROMETHEUS_PORT=9090
    - TEMPO_PORT=3110
    - ZIPKIN_PORT=9411
    - GRAFANA_PORT=3000
