I tested the endpoints using Postman to ensure their functionality. For managing my database, I utilize PostgreSQL and have configured it via the `docker-compose.yml` file. 
This file contains the startup configuration for the Docker container, including the definition of environment variables such as POSTGRES_USER, POSTGRES_PASSWORD and POSTGRES_DB. 
Additionally, I've specified the startup port as 5432.

In the `application.properties` file, I store crucial information such as the username, password, and URL to establish a connection with the database.
