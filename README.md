# Application

My application is built using Springboot, Thymeleaf, Hibernate, Spring MVC, and Docker. It follows the Service, Controller, Dao architecture pattern. The application provides a homepage where users can login with their existing credentials or register a new account. If anyone wants to use the application, they can pull the source code and run it with 

`docker-compose up`.

## Technologies Used

* Springboot
* Thymeleaf
* Hibernate
* Spring MVC
* Docker

## Architecture Pattern

The application follows the Service, Controller, Dao architecture pattern, which separates the application into three layers:

* Service - Contains business logic and acts as an intermediary between the Controller and Dao layers.
* Controller - Handles requests from the user interface and passes them to the appropriate Service layer.
* Dao - Handles the persistence of data to the database.

## User Interface

The application provides a homepage where users can login with their existing credentials or register a new account.

## Usage

If anyone wants to use the application, you can pull the source code and run it with `docker-compose up`.

