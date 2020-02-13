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
- JDK Java 11
- Maven 3.6+
```

### Installing

Clone or download the project from: https://github.com/victorwardi/WaesScalableWeb

Go to the source folder with your preferred command line tool and type:

```
mvn clean install
```

All dependencies will be downloaded, tests will be made and your project will be ready to run.

## Testing

In case you need to re-run some tests

### Running unit tests

```
mvn test
```

### Running integration tests:
```
mvn failsafe:integration-test   
```

## Running

To run the application

```
mvn spring-boot:run
```

### The app API will be available at:

http://localhost:8080

## API

Documentation is provided at:   

http://localhost:8080/swagger-ui.html

To post data to a side: 

#### POST /v1/diff/{id}/left     

```
Example: /v1/diff/1/left     
 
{"encodedData" : "MTIzNDU72NwVX=="}
```       

#### POST /v1/diff/{id}/left
```  
Example: /v1/diff/1/left  

{"encodedData" : "M21IzNDU1Jw23=="}
```

#### GET diff

- GET /v1/diff/{id}
```
Response:

{
    "id": "1",
    "result": "Contents have same size but different contents.",
    "details": [
        {
            "offset": 1,
            "length": 9,
            "left": "TIzNDU72N",
            "right": "21IzNDU1J"
        },
        {
            "offset": 11,
            "length": 2,
            "left": "VX",
            "right": "23"
        }
    ]
}
```

## Online APP
It is possible to test this app online: 

[WEAS App](https://waes-victor-api.herokuapp.com)
[Documentation](https://waes-victor-api.herokuapp.com/swagger-ui.html)


## Built With

* [Spring Boot](https://spring.io/projects/spring-boot/) - Spring Boot makes it easy to create stand-alone apps
* [H2](https://www.h2database.com/html/main.html) - In memory database
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Fox](http://springfox.github.io/springfox/docs/current/) - Automated JSON API documentation for API's built with Spring


## Improvements

- Put the app in a docker container
- Add of real database, not an in memory one.
- Add profiles such as local, dev, hom, prod and test.
- Internationalization.

