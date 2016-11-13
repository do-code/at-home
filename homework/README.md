my homework
===================
Based on Source: <https://github.com/jboss-developer/jboss-wfk-quickstarts/tree/2.7.x/helloworld-html5>

 this work in this repro is based on Apache License Version 2.0


Simple rest-Service to store Person-Entitys (see:`/homework1/src/main/java/de/doCode/homework/model/Person.java`)

** usage **
(see: `/homework1/src/main/java/de/doCode/homework/rest/Service.java`)
 * GET: `http://localhost:8080/rest-service/PersonService/person/1` 
	Reads a Person if found
 * POST: `http://localhost:8080/rest-service/PersonService/person`
PUT and POST are both unsafe methods. However, PUT is idempotent, while POST is not. So POST creates and returns JSON e.g.:
<code>
{"id":3,"name":"Hans"}
</code>
 * PUT: `http://localhost:8080/rest-service/PersonService/person`
 e.g.: with Content-Type:application/x-www-form-urlencoded e.g.: `id=3&name=Klaus`
 PUT updates and returns updated person as JSON, e.g.:
<code>{"id":3,"name":"Klaus"}</code>
 * DELETE: `http://localhost:8080/rest-service/PersonService/person/1`
Deletes a Person if found and returns deleted person as JSON

** status **

 * works with: wildfly-8.2.0.Final and the default Example Datasource `java:jboss/datasources/ExampleDS`
 * Arquillian-tests work with managed wildfly - change:
	`"jbossHome"` and `"modulePath"`
	in `/homework1/src/test/resources/arquillian.xml` to your local installation
	
 * my pom.xml is not a valid example for dependency-lessons - more a 'mergehell' -> but this is work for later
 
 -------------------------
  
 * if you run mvn test, something like `/homework1/mvnTest.out.txt` should be printed
 * if you run this in eclipse (like Red Hat JBoss-Developer Studio) something like `/homework1/eclipseWithWildFly8.2.out.txt` should be printed
 

