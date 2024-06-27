# Order Service

### Setup local dev
- Run `./gradlew clean build`
- create a .env file with the following
    - MYSQL_PASSWORD=mysql
    - MYSQL_DB=order_service
    - MYSQL_USERNAME=root
    - MYSQL_HOST=localhost
    - MYSQL_PORT=3306
    - SERVICE_INVENTORY_URL=http://localhost:8082
    - KAFKA_PORT=9092
    - KAFKA_PORT_LISTENER=29092
    - KAFKA_BOOTSTRAP_SERVERS=localhost:9092
    - KAFKA_TOPIC=order-placed
- Run `docker-compose up -d` to start mySQL DB
- Run `docker-compose down -v` to stop mySQL DB and remove volumes