# auronia-interview
Practical of Auronia Comapany for Interview Process.


# STEPS

## Installation

The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies

## Database configuration

Create a MongoDB database with the name AuroniaInterview and add the credentials to /resources/application.properties.
The default ones are :

spring.data.mongodb.host=localhost

spring.data.mongodb.port=27017

spring.data.mongodb.database=AuroniaInterview

spring.main.allow-bean-definition-overriding=true

server.port=1999

## Usage

Run the project through the IDE and head out to http://localhost:1999

## GET All Orders :

Mapping : GET Mapping.

Parameters : Not Required.

Return : List of all orders.

Url : http://localhost:1999/orderAPI

![alt text](https://github.com/umang4846/auronia-interview/blob/master/getAllOrders.PNG)


## GET Order by Id :

Mapping : GET Mapping.

Parameters : id

Return : order of given Id.

Url : http://localhost:1999/orderAPI/orderById/5d43202659f4ef18f42722db

![alt text](https://github.com/umang4846/auronia-interview/blob/master/getOrderById.PNG)

## DELETE Order by Id :

Mapping : DELETE Mapping.

Parameters : id

Return : response message.

Url : http://localhost:1999/orderAPI/deleteOrder/5d433b724075e512c0b75273

![alt text](https://github.com/umang4846/auronia-interview/blob/master/deleteById.PNG)

## POST Order (Add new Order):

Mapping : POST Mapping.

Parameters : Need to pass the Order Model as a Response body.

Return : added order details with response message.

Url : http://localhost:1999/orderAPI/addOrder

**json file for POST body request**
```json
{  
    "customerEmail": "umang4846@gmail.com",
    "firstName": "Umang",
    "lastName": "Patel",
    "shippingAddress": {
        "firstName": "Umang",
        "lastName": "Patel",
        "email": "umang4846@gmail.com",
        "city": "Ahmedabad",
        "address1": "Cross road , highway",
        "address2": "Sardarpatel Stadium",
        "zipPostalCode": "384021",
        "phoneNumber": "XXX25XX22"
    },
       "orderItems" : [ 
        {
            "quantity" : 3,
            "product":{
            	"name":"Mobile",
            	"price":"31000"
            }
        },
        {
            "quantity" : 2,
            "product":{
            	"name":"Earphone",
            	"price":"1500"
            }
        }
        ,
         {
            "quantity" : 1,
            "product":{
            	"name":"Powerbank",
            	"price":"2100"
            }
        }
    ]
}
```

![alt text](https://github.com/umang4846/auronia-interview/blob/master/addOrder.PNG)

