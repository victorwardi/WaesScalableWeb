# WAES RECRUITMENT CHALLENGE

[![CircleCI](https://circleci.com/gh/victorwardi/WaesScalableWeb.svg?style=svg)](https://circleci.com/gh/victorwardi/WaesScalableWeb)

The main goal of this project is to show my coding skills as a Java Software Developer .

## Dependencies

- Java 11
- Maven


## Runnning application:

    mvn spring-boot:run

## The rest API will be available at:

    http://localhost:8080

## Running unit tests:

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