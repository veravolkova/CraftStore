## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
A full-stack app created within Server Programming with Spring Boot Course, Haaga-Helia.
An example of an online craftstore with different types of premium subscriptuions. 
The higher the tier of the subscription is - the more ads and products a user can add.
There is also admin user who can delete ads and products. 

<img src="https://drive.google.com/uc?export=view&id=1yv7N1-FQoJhr5SM-FP7q8E88q_Mpu0gt" alt="Craftstore Screenshot1" width="750" height="340">
<img src="https://drive.google.com/uc?export=view&id=1UFqeAaskCH5rBF7N902NiPFRLmFnXzEw" alt="Craftstore Screenshot2" width="930" height="400">
<img src="https://drive.google.com/uc?export=view&id=1B3UafPRwx9rNVTdu8LEuQDqtGAqMP6EX" alt="Craftstore Screenshot3" width="930" height="400">
<img src="https://drive.google.com/uc?export=view&id=1aAEDHKrSA3VtesbSha_o9sJqoBt5Nu4j" alt="Craftstore Screenshot4" width="750" height="340">

## Technologies/ functionality
* Java8, Spring Boot
* MariaDB, H2 databases
* Security
* Testing 
* REST
* Thymeleaf, Bootstrap
	
## Setup
To install this project:
```
Download, inzip, import as Maven project.

```

## To run this project:
```
Unfortunately, the original database is no longer available.

More likely, versions updates in pom.xml will be needed as some time have passed.

Delete the "test scope" of h2 database in pom.xml

Change the contents of application.properties to:
spring.jpa.properties.hibernate.format_sql = true
spring.data.rest.basePath=/api
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

Run CraftstoreApplication.java

```

