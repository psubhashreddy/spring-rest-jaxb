# spring-rest-jaxb
Simple spring boot rest apivwith jaxb and flyway implementation

Its a small spring boot application with a rest end point - /api/v1/person/id/{id}  

Import the project into any one of IDE and run it as a spring boot application, which creates a datasource in H2 database and inserts some of the records
as part of flyway script into Person table.

Now you can fire the rest end point and get the output as XML using browser or Postman.
