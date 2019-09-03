# spring-rest-jaxb
Simple spring boot rest apivwith jaxb and flyway implementation

Steps to run the spring boot application
a.	Unzip the file
b.	Import it from any of IDE (IntelliJ or Eclipse)
c.	Run the application as Spring Boot Application.
d.	When the server is up – Open http://localhost:8080/h2 to get into the console of h2 database and make sure JDBC Url : ‘jdbc:h2:~/springjaxb’ and then connect with username:sa and no password.
e.	You will see person table created and some of the records inserted into the table already with flyway sql script.
f.	Hit the endpoint http://localhost:8080/api/v1/person/id/{} to see the data in xml format. 
