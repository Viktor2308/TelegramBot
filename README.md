# Name: Telegram Bot
## Author: Viktor Derkachev
### Author-email: 3952324@gmail.com

> Java 17 <br/>
> Spring boot 3.0.2 <br/>
> Pengrad bot API 5.7.0 <br/>
> Postgres <br/>
> Liquibase <br/>
> lombok <br/>

Telegram bot, which can receive messages from the User in the format:<br/>
02/02/2023 18:00 Do homework<br/>
and send the User a message with the text:<br/>
Do homework - at 18:00 on February 2, 2023. :+1:<br/>

### Configure Applications
To initialize the project, you need to add the bot token to the application.properties file:

telegram.bot.token=

### Database connection
Enter data in the application.properties for connecting to the database Postgres

> spring.datasource.url=<br/>
> spring.datasource.username=<br/>
> spring.datasource.password=<br/>
