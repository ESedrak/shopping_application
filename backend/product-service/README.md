# Product Service

### Setup local dev
- Run `./gradlew clean build`
- create a .env file with the following
    - MONGO_DB_USERNAME=root
    - MONGO_DB_PASSWORD=password
    - MONGO_DB=product-service
    - MONGO_HOST=localhost
    - MONGO_PORT=27017
- Run `docker-compose up -d` 
- Run `docker-compose down -v` to stop and remove volumes