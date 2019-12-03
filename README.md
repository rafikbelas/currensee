# Currsnee - API Documentation

###Table of Contents
1. [Introduction](#introduction)
2. [Requirements](#requirements)
3. [Running the application](#running)
3.1 [Build - test - package from the source code](#source)
3.2 [Run using docker-compose](#compose)
3.3 [Run the Docker image from DockerHub](#hub)

<a name="introduction"><a/>
###1. Introduction

This project is a microservice that serves the following API endpoints:
- Output the current time (GMT+1).
- Validate a VAT input and return corresponding country code.
- Convert currency input. **Note:** The free plan of the [Currency Layer API][currencylayer] only allows to [get real-time rates from USD to desired currencies](currencylayer-api). Therefore, the `source` currency of the conversion feature is always USD.

<a name="requirements"></a>
###2. Requirements:
- Source code that you can review.
- A micro-service that you can build, test & run yourselves.
- Documentation, where relevant.
- Incorporation of software engineering best-practices and design-patters where relevant.
- Test coverage to ensure code-quality.
   
<a name="running"><a/>
###3. Running the application:

You can either run the application by building, testing and running the project from the source code, or use the Docker image provided on DockerHub to run a container.

<a name="source"><a/>
####3.1. Build - test - package from the source code.

- Download souce code or clone it using `git clone https://github.com/rafikbelas/currensee.git`
- Go to the project folder `cd currensee`
- Run the following command `mvn clean`
- Test the application by running `mvn test`
- Run the application by running: `mvn spring-boot:run`. This will start the application on port 8081 http://localhost:8081
- To test the API endpoints, head to the auto-generated [Swagger documentation][currensee-docs]

<a name="compose"><a/>
####3.2. Run using docker-compose

<a name="hub"><a/>
####3.3. Run the Docker image from DockerHub
- Run the following command 
`docker run -dp 8081:8081 rafikbelas/currensee`. This will download the `currensee` image from the DockerHub and run it.
- To test the API endpoints, head to the auto-generated [Swagger documentation][currensee-docs]

[currencylayer]: https://currencylayer.com
[currencylayer-api]: https://currencylayer.com/documentation#real_time_rates
[currensee-docs]: http://localhost:8081/api/swagger-ui.html
