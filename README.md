# GameClub
Spring Boot application that demonstrates a game renting system.
It is written using Java 17 and the services are exposed with a REST API.

## Getting Started
In order to build and run this application locally, you need to have a PostgreSQL database set up and [jdk 17](https://www.oracle.com/java/technologies/downloads).
If you haven't done this before, [here is](https://www.geeksforgeeks.org/how-to-set-java-path-in-windows-and-linux/) a quick guide to follow after installing a new Java version.

### Database 
This application uses a [PostgreSQL](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) db. Once you have it installed and created the database, you may run the queries found in this [script](https://github.com/dinos217/game-club/files/8305260/postgres-gameclub-init-script.txt) to build your schema and tables.

*ΝΟΤΕ: Since this is an application which is built to run locally you can always use whatever database credentials you like and then configure accordingly* `spring.datasource.username` & `spring.datasource.password` *in* `src/main/resources/application.properties` *file*.

### Get the application up & running 
Once the application is successfully loaded in your IDE you can:
- `spring-boot:run` from maven plugins or
- run the main method in `com.project.gameclub.GameclubApplication`

Now the application listens to your `localhost:8080`.

## Understanding and Testing the application
The documentation and the endpoints collection were exported from Postman. 
- Here you may find the [Documentation](https://documenter.getpostman.com/view/7555836/UVsEUoz9)
- and the [Postman Collection](https://github.com/dinos217/game-club/files/8200686/GameClub.Endpoints.postman_collection.zip).
