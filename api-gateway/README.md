## Setup:

For local development:
- create a .env file with the following
    - PRODUCT_SERVICE_BASEPATH=http://localhost:8080
    - ORDER_SERVICE_BASEPATH=http://localhost:8081
    - INVENTORY_SERVICE_BASEPATH=http://localhost:8082
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