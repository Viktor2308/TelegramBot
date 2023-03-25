# Name: Telegram Bot

## Author: Viktor Derkachev

### Author-email: 3952324@gmail.com

> Java 17
> Spring boot 3.0.2
> Pengrad bot API 5.7.0
> Postgres
> lombok

Telegram bot, which can receive messages from the User in the format:

02/02/2023 18:00 Do homework

and send the User a message with the text:

Do homework - at 18:00 on February 2, 2023. :+1:

## Configure Applications
To initialize the project, you need to add the bot token to the application.properties file:

telegram.bot.token=

## Database connection
Enter data in the application.properties for connecting to the database Postgres

spring.datasource.url=jdbc:postgresql://localhost:5432/
spring.datasource.username=
spring.datasource.password=
