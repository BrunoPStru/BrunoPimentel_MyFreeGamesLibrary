# My Free Games Library

## About the project

My Free Games Library is a Spring Boot REST API, that provides endpoints and consumes endpoints from another external API.

Through the application endpoints, I search for a free game from the external API from the given ID, and save this game in My free games library, in addition, I can list the games in my library, search for a specific game in my library, list the genres of games that have been added to the library, and I can delete a game from my library.

The external API used is the "Free-To-Play Games Database API", available at https://www.freetogame.com/api-doc.

The database used was MySQL, through the image "mysql:8" in Docker.

## Instructions

- Run `mvn package` command to package the project in a jar file.
- Run the `bash` command in wsl2 environment, then execute the `docker compose up` command to create and start the docker container.

### Author
Bruno Pimentel Struminski