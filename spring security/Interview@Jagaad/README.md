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
	
