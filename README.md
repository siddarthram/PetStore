# PetStore
Spring Boot pet store app

This app is built using Spring Boot, JPA, H2 and Mockito

The app is mainly divided into Service, Repository and Controller layers

The intial flow of the application starts from the PetStoreApplication class

The Pet entity class contains all the neccessary attributes such as id, name, type and price

The services such as getPets, getPetById, updatePet etc, are present in the PetService class

All the necessary Rest API mapping is present in the PetController class

**Run application using Maven**
1) Go to the root of the application where pom.xml (contains all the neccessary dependencies) is available
2) Run the command - mvn spring-boot:run

**Deploy Spring boot application using following commands:** 
1) mvn clean install
2) java -jar .\target\demo-0.0.1-SNAPSHOT.jar (jar file will be created in the target folder)

This will start the application on the Tomcat port **8080** as shown below −

And you can use the link - http://localhost:8080/pets to test the application either on your browser or POSTMAN
![Browser-Output](https://github.com/siddarthram/PetStore/blob/master/src/main/resources/images/Browser.png)

**Test Sping Boot API with Postman**
Now, it is the time to run the application and test our Spring Boot API using Postman! 
Once started, the application will be available at http://localhost:8000

Retrieving all Products using **GET request**: http://localhost:8080/pets

![GET-Output](https://github.com/siddarthram/PetStore/blob/master/src/main/resources/images/GetAll.png)

Retrieving a pet by ID using **GET request**: http://localhost:8080/pets/{id}

![GETById-Output](https://github.com/siddarthram/PetStore/blob/master/src/main/resources/images/GetById.png)
```
Inserting a new Pet:
1) Select POST as request type.
2) URL as http://localhost:8080/pets
3) Select Body and provide user data
4) Specify content type as JSON.
5) Click Send
```
![POST-Output](https://github.com/siddarthram/PetStore/blob/master/src/main/resources/images/Create.png)
```
Updating an exsisting Pet:
1) Select PUT as request type.
2) URL as http://localhost:8080/pets/{id}
3) Select Body and provide user data
4) Specify content type as JSON.
5) Click Send
```
![POST-Output](https://github.com/siddarthram/PetStore/blob/master/src/main/resources/images/Update.png)

Deleting an specific pet using the **DELETE request**: http://localhost:8080/pets/{id}

![POST-Output](https://github.com/siddarthram/PetStore/blob/master/src/main/resources/images/Delete.png)

For this application we are using the in memory database **H2**

And the configuration details are present in the **application.properties file**

Here is a screenshot of the **H2-Console**, which can be accessed by http://localhost:8080/h2-console/

![h2-Output](https://github.com/siddarthram/PetStore/blob/master/src/main/resources/images/h2-console.png)

Whenever the application loads it is intiliazed by the following data 

![SQL-Output](https://github.com/siddarthram/PetStore/blob/master/src/main/resources/images/SQL.png)

**Testing:**

The test cases are present in the test folder and were written using Mockito, MockMvc, MockBean and ObjectMapper

Test cases have been implemented for the controller and service classes

![Test-Controller](https://github.com/siddarthram/PetStore/blob/master/src/main/resources/images/Test-Controller.png)

![Test-Service](https://github.com/siddarthram/PetStore/blob/master/src/main/resources/images/TestService.png)

