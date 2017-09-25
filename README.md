# SurveyProject
Technology Stack: 
Java, Spring Boot, Junit, Apache Derby.
About:
Survey project is an implementation of REST API with CRUD operations for “Survey” developed using Spring Boot and has an in-memory database Apache Derby. The project has been tested using Junit. The repository has a file “Survey-Project_Collection.postman_collection” which contains all REST API urls tested on postman. In this implementation, a Survey can have multiple categories. Each category can have multiple questions and each question can have different choice of answers, e.g. yes/no, multiple choice, and free form text. Also, each question can have one level deep sub questions and their corresponding answer choices.
Installation
1.	Clone the repo to your local machine.
2.	Import as maven project in eclipse.
3.	Build project with maven goals: clean install.
4.	Finally Test the application.
