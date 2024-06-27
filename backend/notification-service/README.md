# Notification Service

### Setup local dev
- Run `./gradlew clean build`
- create a .env file with the following
    - KAFKA_BOOTSTRAP_SERVERS=localhost:9092
    - KAFKA_GROUP_ID=notificationService

- Create an account with https://mailtrap.io/ (free) 
  - Under Email Testing Inboxes Tab
    - Create a new inbox (or use the default 'My inbox')
    - Under integration SMTP tab:
      - Get the following details to add to the .env file
          - MAIL_HOST=
          - MAIL_PORT=
          - MAIL_USERNAME=
          - MAIL_PASSWORD=