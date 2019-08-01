# auronia-interview
Practical of Auronia Comapany for Interview Process.


# STEPS

# Installation

The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies

# Database configuration

Create a MongoDB database with the name AuroniaInterview and add the credentials to /resources/application.properties.
The default ones are :

spring.data.mongodb.host=localhost

spring.data.mongodb.port=27017

spring.data.mongodb.database=AuroniaInterview

spring.main.allow-bean-definition-overriding=true

server.port=1999

# Usage

Run the project through the IDE and head out to http://localhost:1999

# GET All Orders :

Mapping : GET Mapping.
Parameters : Not Required.
Return : List of all orders.

Url : http://localhost:1999/orderAPI

![alt text](https://github.com/umang4846/auronia-interview/blob/master/getAllOrders.PNG)


# GET Order by Id :

Mapping : GET Mapping.
Parameters : id
Return : order of given Id.

Url : http://localhost:1999/orderAPI/orderById/5d43202659f4ef18f42722db

![alt text](https://github.com/umang4846/auronia-interview/blob/master/getOrderById.PNG)

# DELETE Order by Id :

Mapping : DELETE Mapping.
Parameters : id
Return : response message.

Url : localhost:1999/orderAPI/deleteOrder/5d433b724075e512c0b75273

![alt text](https://github.com/umang4846/auronia-interview/blob/master/deleteById.PNG)



