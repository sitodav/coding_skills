Summary 
Create a Spring based application that allows us to manage the orders of the "pilotes" through some 
API. Pilotes of the great Miquel Montoro are a Majorcan recipe that consisting of a meatball stew. 
The following operations must be implemented: 
- Create a pilotes order, choosing between 5, 10 or 15 pilotes. 
- Update  a  pilotes  order.
During the  5  minutes  following  the  creation  of  the  order  it  will  be  allowed to update the order data; after that time it will not be possible to modify any data of  the order because Miquel will be occupied cooking the pilotes. 
- Search orders by customer data. Allow partial searches: e.g., all orders of customers whose  name contains an “a” in their name. 
All types of data used must be validated. For example, if you use an email when placing the order, you must validate that the format of the email is valid. 
The search operation must be secured, you must check that the user is allowed to use the search. All other operations are public and should not be secured. 
 
Technical requirements 
- The base project uses Lombok, so you must install it. You can use the following guide https://www.baeldung.com/lombok-ide 
- The API must follow REST standard 
- Use an in-memory database, such as H2 or similar. 
- Write tests 
 
Recommended 
- Improve the project (update the versions of the dependencies, Java JDK, gitignore, etc...) 
- Add Docker Compose for local development 
- Test coverage 80% or above 
- Meaningful project description and instructions (README) 
- API documentation (Swagger or similar) 
 
Considerations 
The data model to be used is completely free, but as a minimum it must contain the following data: 
- Basic Client info: 
o First and last name 
o Telephone  number 
- Basic Order info: 
o Order number (server side generated) 
o Delivery address 
o Number of pilotes 
o Order total (server side generated, consider 1.33 € as price for a single pilotes) 
 
Test evaluation 
In this test, Quality is just as important as the correct implementation of specs. Take your time to 
produce a project that shows your best and respects the best practices. You could take inspiration 
from the most famous Open Source projects. 
Compliance with specs and technical requirements are mandatory to pass the test. 
The recommendations aren’t mandatory but concur to the final evaluation to bring you to the top of 
seniority. 
 
Test delivery 
Use git and send us the repository using a bundle (as email attachment). You can use the following 
command to create the bundle: 
git bundle create <yourname>.bundle --all --branches 
Please, don’t share this exercise with anyone. 
 
 
Good luck! 



----------------------------------
DEVELOPMENT

 
	-The application uses spring security basic authentication , with session.
	 (basic authentication requires base 64encoding for both username and password to be send at least one time, if session is used, like in this case.
	 It should be advised to use POSTMAN instead of swagger-ui to try challenging the API with different users to see different results, because
	 swagger-ui doesn't support fully custom exception mapping with messages, but it's a swagger-ui shortcoming, not of the application).
	 
	-The username/password must match a user saved in the H2 database.
	-When the application starts, two users are inserted in the H2 database.
	-There are two types of roles ADMI and CUSTOMER.
	-When the application starts one of the inserted user is ADMIN (admin/admin ) the other
		is customer (d.sito/d.sito)
	-Some API are allower to both CUSTOMER and ADMIN, some others only for ADMIN.
	-Some of the API allowed to CUSTOMER, are allower only on his own data (for example
	 a user with only CUSTOMER role can query for its own user account info, but cannot do it for different user accounts)
	-The APIs authorities are written as comments on the APIs controllers

	-The order can be in several status. A scheduled component is used to simulate the switch between CREATED to IN_PREPARATION status
	When a order is IN_PREPARATION it cannot be updated anymore from the user (but it can be by the admin)
	The scheduled component for the switch of the status has a frequency that is configured in the application.properties because (and not on the db like the business parameters, because it's a non functional requisite)
	while the time to switch from CREATED -> IN_PREPARATION (which is the maximum time window the client can change his mind for the quantities or delete the order) is configured in the db (because it's a business parameter)

	-Even the others parameters used to validate the order (fixed quantities, valid update window for order, price for pilotes etc)
	are configured in the db.


NB: you don't need the docker compose.
You can produce a running docker image using the native maven plugin BuilderPack with the following command:

mvn spring-boot:build-image -Dspring-boot.build-image.imageName=pilotes

The image for the application (pilotes) will be sent do your running docker instance.
----------------------------------

EXAMPLE FOR ORDER CREATION
 
POST: /order
json payload request : 
	{
  
	"deliveryAddress": "hello",
	"numberOfPilotes": 5 
	
	}

This will create the order associated to the user whose username/password we are sending the first time (and it will remain in session)
And will return it

{
  "orderNumber": "113a2139-8214-41b7-9622-57a45ad14ea2",
  "deliveryAddress": "hello",
  "numberOfPilotes": 5,
  "totalCost": 6.65,
  "creationDate": "25/04/2023 13:08:43",
  "orderStatus": "CREATED",
  "user": {
    "userNumber": "77264f34-8f8f-469b-9a4d-2663c6a0cb6e",
    "username": "admin",
    "firstName": "Giorgio",
    "secondName": "Molinari",
    "telephone": "+393423334232",
    "orders": []
  }
}

----------------------------------
EXAMPLE FOR ORDER DETAIL
GET: /order/{orderNumber}    
for example http://localhost:8082/pilotes/order/113a2139-8214-41b7-9622-57a45ad14ea2
will return 

{
  "orderNumber": "113a2139-8214-41b7-9622-57a45ad14ea2",
  "deliveryAddress": "hello",
  "numberOfPilotes": 5,
  "totalCost": 6.65,
  "creationDate": "25/04/2023 13:08:43",
  "orderStatus": "CREATED",
  "user": {
    "userNumber": "77264f34-8f8f-469b-9a4d-2663c6a0cb6e",
    "username": "admin",
    "firstName": "Giorgio",
    "secondName": "Molinari",
    "telephone": "+393423334232",
    "orders": []
  }
}


-------------------------------
BONUS&EXTRA
If we try to get order info (and the requesting user is not ADMIN) from the order of another user we will be blocked.
Same thing if we try to get infos of another user and we are not ADMIN.


Bonus points I was not able to produce because of the given week I had to work on the project, I only had 2 halves of a day, so I had to leave out :
Unit Testing and coverage (pretty straighforward)
I would have liked to create different configuration/application files for different execution environment (local and kubernetes)
I would have proceeded in this way:
  using a single common application.properties for common environment properties
  a application-loc.properties for local environment (to be launched from local using spring.active.profiles=loc )
  a application-k8s.properties for cloud/kubernetes environment.
   In this I could have specified a different database configuration (instead of h2 I could have configured a datasource connector to a mysql)
   using injected parameters with ${} from the docker container environment, taken from a config map in the kubernetes environment.
   On top of that I would have used database configuration (for the transaction manager classes) enabled with the @Profile annotation, so that they would have been
   used only when in k8s environment.
   The use of spring profile 'k8s' would have passed to the execution for the jvm in the container specification (the .yml deployment file for the application)
   to use in conjunction with the docker image produced using the mvn BuilderPack plugin.