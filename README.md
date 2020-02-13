#  WAES RECRUITMENT CHALLENGE
  
[![CircleCI](https://circleci.com/gh/victorwardi/WaesScalableWeb.svg?style=svg)](https://circleci.com/gh/victorwardi/WaesScalableWeb)
  
The main goal of this project is to show my coding skills as a Java Software Developer.

## Assumptions
- POST Endpoints will send a Json with some encode data on base64 (text, images, files etc).
- Difference check will be applied on the the encoded data.
- Each ID has a SIDE and one encoded data, but data will be update if submitted to the same SIDE and ID.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the project

```
- Java 11
- Maven
```

### Installing

Clone or download the project from: https://github.com/victorwardi/WaesScalableWeb

On the source folder open your preferred command line tool and type:

    mvn clean install

All dependecies will be downloaded and the project will be com
And repeat


    mvn spring-boot:run


## The rest API will be available at:

    http://localhost:8080

## Running the tests

    mvn test
    
## Running integration tests:

    mvn verify   
    
    
## API

Post contents (Base64 encoded JSON)

    POST /v1/diff/{id}/left
    POST /v1/diff/{id}/right

Get the diff

    GET /v1/diff/{id}

## Online APP

[Heroku App](https://waes-victor-api.herokuapp.com)

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot/) - Spring Boot makes it easy to create stand-alone apps
* [H2](https://www.h2database.com/html/main.html) - In memory database
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Fox](http://springfox.github.io/springfox/docs/current/) - Automated JSON API documentation for API's built with Spring


## Improvements

* Hat tip to anyone whose code was used
* Inspiration
* etc
