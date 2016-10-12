# sample-app project
A sample project using spring-boot and AngularJS by Meltem Yesiltas. 

It will be packaged as two deployable WAR files:
+ sample-app/sample-app-client/target/sample-app-client.war
+ sample-app/sample-app-cust/target/sample-app-cust.war

## project structure
The project is configured to be a multi module maven project. sample-app/ is the parent pom module.

+ sample-app/
  +sample-app-client/
  +sample-app-common/
  +sample-app-common-model/
  +sample-app-cust/
  +sample-app-cust-model/

## prerequisites
Following are required to build and run the executables

+ java 8
+ maven 3.3+
+ mysql 5.7+ 

## configuration

You should configure sample-app-client/ and sample-app-cust/ projects.


### sample-app-cust/

You can have a look at "/sample-app/sample-app-cust/src/main/resources" for all the configuration options.
But the only configuration you have to change before proceeding is database configuration.

Determine a Spring profile name of you want for DB. Lets say you named it "mysql-test". 
Just create a Spring application configuration file with tihs name: application-{mysql-test}.properties.
Put necessary connection parameters in it, a sample content:

spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/sampleDB?profileSQL=false&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456

Your file must be in classpath root. You can put it in "/sample-app/sample-app-cust/src/main/resources". 
In the pom.xml, find maven profile called "dev", under it find the property called "mysql.spring.profile", set your profilename (mysql-test)
as the value.

That is all for sample-app-cust/ project.

### sample-app-client/
You can have a look at "/sample-app/sample-app-client/src/main/resources" for all the configuration options.
But the only configuration you have to change before proceeding is URL configurations of both sample-app-client and sample-app-cust applications.
You need to know this information beforehand.

In the pom.xml, find maven profile called "dev", under it find the property called "base.url", set the runtime URL for sample-app-client application as the value.
In the pom.xml, find maven profile called "dev", under it find the property called "cust.api.url", set the runtime URL for sample-app-cust application as the value.

That is all for sample-app-cust/ project.

## packaging

Run the following maven command in sample-app/ folder:

clean package -Dsample-app-client.skip.tests=true -P dev

The tests under sample-app-client will be skipped with above command, because they are integration tests and require sampl-app-cust up and running.
When you have sampl-app-cust, you can run these tests.

## deploying

Just deploy the following two WAR files to any JEE server of your favorite.
They are tested on Tomcat both locally and on AWS ElasticBeanstalk. They don't have to run on same server, they are independent.

+ sample-app/sample-app-client/target/sample-app-client.war
+ sample-app/sample-app-cust/target/sample-app-cust.war

If you have any questions, you can contact meltem@yesiltas.net. 
