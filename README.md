# COVID-19-TRACKER

John Hopkins stopped reporting daily active and recovered due to the ambiguity within 
the measurements and failure to report from some areas leading to inaccurate data.

# How to Run
1. Clone the repo
2. In a terminal, run "mvn clean install" to get all dependencies
3. To start the application, in a terminal run "./mvnw spring-boot:run". On default, this should be the local link: http://localhost:8080/
It will take a while to run due to how many rows are in the csv.

# Decision Making
- Spring Boot: Springboot allows easy creation of microservices. It has many useful annotations to manage REST endpoints, remove boiler plate code, and access a database.
- MySQL: SQL databases provide many benefits. I chose MySQL for the maintenance of ACID properties, a rigid schema, cascading changes, and the joining of tables.
- Thymeleaf and Materialize CSS: Materialize had prebuilt components to use from. To create an MVC application, I passed in a model for thymeleaf to use. Thymeleaf is a natural template that allows me to manipulate the data passed from the model.
# Changes I would Make:
- Testing: I would use JUnit to add unit tests to make sure the endpoints are working correctly and the service calls are doing expected business functionality.
- For the backend, I think Spring Boot is still a good choice. One thing I would change is organizational structure. I would create a requests folder that models the required fields for each endpoint request. I would also create a response folder that models the expected response each endpoint returns. Separating the service, repository, entity, and controller into separate folders is also something I would do. 
- For the front-end: Instead of utilizing thumeleaf and materialize css, I would use a front-end framework such as React or Angular. Doing so would help this push towards a scalable application. With the utilization of components in front end frameworks, it would allow easier changes for business needs and expanding users. Moreover, you can also use state management with Redux/NGRx. I would then host backend on docker/aws/heroku instead locally. In Redux/NGRx, I would shoot off an action everytime I want to change call a backend endpoint which the effects will do. I would also use a library such as BootStrap or PrimeNG to help create the UI.
- Database: Instead of MySQL, I would choose a NoSQL database such as MongoDB. The reasoning for this is because the functionality of this application is to simply display data. I don't need cascade updating, ACID properties, or joining any tables. A NoSQL database would be better because it allows for horizontal scaling instead of vertical scaling. Moreover, NoSQL access times are faster since the data is not normalized.
