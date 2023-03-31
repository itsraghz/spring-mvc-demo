# Game Plan 

## Objective

We are building a Phonebook Application using Spring MVC.

### Phase 1

We use a static data (List) in the Service Impl, instead of the Database

## Phase 2

We will use a Database to manipulate the data, and we use JDBC to interact.

## Phase 3

We will expose the services as a Rest API

## Tech Stack

### Phase 1 

* Java 8
* Spring MVC
* Spring Core
* JUnit 5
* Bootstrap
* JSTL
* Lombok
* Log4J
* Maven
* Tomcat
* Maven Tomcat Plugin (tomcat7)

### Phase 2

* JDBC 
* Spring JDBC
* ORM / Hibernate (if time permits)

### Phase 3

* Spring Rest

## Features / Services

* Contacts
	* Create 
	* List 
	* Read / Get
	* Update
	* Delete 
	
* Other Features

## Game Plan 

For any feature/module/service, we follow the steps.

### Unit 1 (Service Layer, and Unit Tested) 

	* JUnit Test - preferred for *TDD* (Test Driven Development)
		* Write a test method
		* Make it fail 
		* Write the actual implementation
		* Make the test cases pass
	* Service Interface
	* Service Implementation

### Unit 2 (Web Layer)

	* Controller 
	* UI Pages
	* Unit Testing on the UI 
		* Manual 
			* Hitting the URL on the browser
			* Validate the output in the browser
			* See/Verify the Application logs in the Console if any
		* _Automation_ - using *Selenium* (we will see later if time permits)
	
### Testing the E2E Flow

	* End to end testing by following 
		* Start from the Login 
		* Test every other scenario / feature by hitting the respsective URLs
		* Verify the output in the UI/Browser for the corresponding scenario
		* Verify the Application logs in the Console, if any , on demand
	
## Use Cases

### Contact - Add

* JUnit
* Service
* Controller
* UI

#### Improvements

* [DONE] &rarr; `redirect:/contacts` where we don't duplicate the code but invoke the existing method with the url pattern '/contacts' to list all the contacts.
* Validation
   * [DONE] No null/empty values - kind of taken care in the UI with the `required` attribute in the `input` tag/element
   * Server Side Validation - at the Controller level
     * [DONE] Missing or empty values - through `@Valid` annotation 
     * [DONE] Duplicate Values 
* [DONE] Reduce the repeating `@RequestParam` for each of the parameters passed via the Request

* addContacts.jsp Page 
	* Add a red color star to indicate that a  particular field is mandatory (whichever is really required)
	* Add the necessary back end validation for all the applicable fields - except the firstName and lastName which we have done it so far.
	* [WIP] Alignment of the columns - fields and values for each input
	
	
### Contact - Read

* JUnit
* Service
* Controller
* UI

### Contact - Update

* JUnit
* Service
* Controller
* UI

#### Troubleshooting

* Issue : The update was not properly happening
* Solution : 
	* Domain - manualy provide the `hashCode()` and `equals()` method, than the one given by Lombok.
	* Reduced the width of the @Size attribute for the lastName field - from 4 to 2.
* Issue : The contact being attempted with the existing contact #. Because it is a hard coded list we manage in Phase 1 and we execute in two steps. First, we remove the item first and then adding the list later to the list. Here the removal fo an item is successful but the addition gets failed due to the validaiton for Duplicate based on the Contact #. Hence, the operation is partially completed! :( It is NOT the desired way.  Either we should do it ALL or NONE - honoring the ACID style (Atomicity, Consistency, Isolation and Durability).
* Solution : Use Transaction Management - either manually using the JTA (Java Transaction API) (old style), OR let the framework like Spring manage it for us, as that was one of the primary goals of such frameworks.

#### Enhancements 

* Redirecting to 'contacts.jsp' page after addContact.
* Add RedirectAttributes to add a flash message, than using the Model or the ModelMap.


### Contact - Delete

* JUnit
* Service
* Controller
* UI

## TODO

* UI
	* [DONE] Reusable fragments - header, footer and Menu. #Assignment | Menus - Home, Contacts
	* [DONE] Add a Bootstrap button to the View
	* [DONE] Add the alignment for all the colums in the `addContact.jsp` page and do the necessary validations
	* [DONE] Checkout the person specific branch created in the GitHub Repo  `https://github.com/itsraghz/spring-mvc-demo/tree/Assignment1-UI-21Mar2023-Rama` and push your code in to the corresponding branch (Remote Feaure Branch). 
	* [PENDNIG] The one scores high, will get merged with the PR (Pull Request) into the remote master/main branch.

* Pending /TBD Later
	* Add a favico - `<link rel="icon" type="image/x-icon" href="/images/favicon.ico">`
* Transaction - UoW (Unit of Work)
	*  All steps involved in the business acitivty should be completed and `commit`ted, in case of any failures in the middle, the entire operation should be `rolled back`. 

# Spring JDBC

* Plain old Java with JDBC
	- List of steps involved
	- Most of the steps like Obaining the Connection, Creating the statement, Dealing with the SQLException, Closing the resources (ResultSet, Statement(OR PreparedStmt), Connection) - are called as Ceremonies OR also the boiler plate code
		-  The Reason being no matter what business logic you have, you must have all these without fail.
		-  And it does not add any direct value to your busines. 
	- Savior / Rescue - Spring JDBC
> Note: Motto of the Spring Framework : - To Simplify Application Development

## How does Spring JDBC Help?

 * It covers / wraps the boiler plate code under the hood, so that the Devleoper can just focus on the actual business logic OR the lines that really matters to him /his business. 
 
## Reference 

We use Spring Framework V 4.3.30, and hence we refer this (link)[https://docs.spring.io/spring-framework/docs/4.3.30.RELEASE/spring-framework-reference/html/jdbc.html#jdbc-introduction] for Spring JDBC.

## How do I make use of Spring JDBC ?

* Find out the compatible version of *Spring JDBC* from the URL https://mvnrepository.com/artifact/org.springframework/spring-jdbc to be added in the `pom.xml` file of our project. For our case, we choose '4.3.30.RELEASE' version that is matching with our Spring Core (spring-context).

* We should declare a `DataSource` of `java.sql`, as a `@Bean` in the `@Configuration` class
* We can have an Interface for the CRUD operations based on the Domain object.
* This Datasource bean should be injected to the `Service` layers.	
* JDBCTemplate method - `execute` or `queryXXx`
* Implementation of the `RowMapper` interface to map the data on a per-row basis, by implementing the `mapRow() method declared in the interface.
* Before executing the Test class, ensure that we have the actual Database and Table available in the Database. :) 
* Add the DB specific JDBC connector in the `pom.xml`. Ex, *MySQL JDBC Connector* for the MySQL Database
	```xml
		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.29</version>
		</dependency>
	```

## What will be the data flow in the Application involving Spring JDBC ?

### With Web (MVC)

View/Client <--> Controller <--> Service <--> Repository <--> [JDBC <-->] Database (Actual Database)

> *Note*: Service layer should have Datasource injected, which is declared as a `@Bean` in a `@Configuration` class.

### Without Web (MVC)

Client <--> [Service] <--> [Repository] <--> [JDBC <-->] Database (Actual Database)

* Any or both of the layers can be kept optional. But at least you need layer in the Backend. 

> *Note*: If you keep both of them optiona, then you must have one layer as a Controller (a central layer to get all requests from the Client and regulate the flow).

### How do I invoke the layers from a Test class? 

Typically in a Test class, we may NOT have the Controller available. In that case, we can skip that, and
direclty invoke the Service/Repository (depends on what you configured in the Application) and invoke the flow.

> *Note*: Whenever we say Service/ Repository for injecting the dependencies, we really speak about the Implementation class at the respective layer, and certainly NOT the Interface.

## Spring JDBC - Use Cases

### C (Create) Contact 

* Test Class
* Service
* Repository
* AppConfig
	* jdbcTemplate
	* DataSource
* DI (Dependency Injection)
	- TestContactDAO - we injected ContactService (Reference Type/Interface)
	- ContactServiceImpl - we injected DAO<Contact> (Reference Type / Interface)
	- ContactDAO (Implementation Class) - we injected JdbcTemplate (which indeed has got the DataSource Reference

* Accomplishments
	* We are able to create a new entry in Contact Table successfully
	
* Pending / Todo
	* The validations - Boundary checks and also the Business Validations
	* Boundary Validations - min/max size, not null etc., 
	* Business Exceptions - Duplicate Contact 
		- We do have a method `isContactDuplicate()` in the `ContactServiceImpl` class, but it operates on the static hardcoded list of data - `contactList`
		- Solution 
			- #1. We can modify this list from hardcoded to Database. [getAll() method can return the List<Contact>]
				- Challenge: What if the Database has got more than a Lakh records?
			- #2. We can query from the Database Table with a WHERE clause to find a potential match for duplicate. If so we stop it
				- ```sql
					SELECT * FROM CONTACT WHERE CONTACTNO= ? -- the number supplied from the User.
				   ```sql
			- #3. The best bet is to add a UNIQUE constraint on the Contact Table and let the Database handle the scenario and throw a SQLException whenver an attempt is made to store a contact whose contact no is already present in the table. 
		
** Accomplishments 
	- Added a new contract `getByContactNo(String contactNo)` in the `DAO<T>`.
	- Added the implementation for the contract method in the `ContactDAO` class.
	- Tested the same via a `@Test` method in `TestContactDAO` class.
	- Modified the link in `ContactServiceImpl` class in the `isContactDuplicate()` method 
		- Commented the code for the iteration of the hard coded list
		- Added the new logic to invoke the `contactDAO.getByContactNo` and determine based on `optionalContact.isPresent()`.	
* [DONE] Error while checking the `getContactByContacNo(String contactNo)` for the duplicate validation check as the `jdbcTemplate.query` or the `queryForObject()` method indeed calling the `requireSingleResult()` which indeed throws an `EmptyResultDataAccessException` if the resultset is empty (meaning when there are no matching rows for the contactNo being passed).
* [DONE] The ID parameter was NOT populated in the `RowMapper` Implementation class - on the `getAll()` method for the URL `/contacts`.
* [DONE] The autogenerated ID was not assigned/displayed in the success message. We instead retrieved the `rowsAffected` - the direct return value of the `jdbcTemplate.update(sql)` method. `rowsAffected` will indicate the number of rows affected by executing the DML statement passed to the `update` method of `jdbcTemplate` object. For an INSERT, it is NOT the same as the auto generated the sequence number or the Primary Key.

## Use Case - R, U, and D

* getById() completed linked with Controller and Test Method.

### Assignment 

* Pending Use cases using Spring JDBC
	* Update
	* Delete 
* check into the Github Repo as always. Will create a new branch for this `Assignment2-SpringJDBC-26Mar23-<Name>`
* Raghavan should 
	* [DONE] rectify the file names of Snagit Images which was preventing the git pull due to the very long files names and the team uses Windows.
	* [DONE] create a new branch for each for this new Assignment - Assignment 2.
	
## Pending Things

* @Order does not work as expected in TestContactDAO. Not sure whether Spring Context being loaded in the class has some influence. Need to check.
* [DONE] Properties for DataSource to be injected from the properties (`jdbc.properties`) file
	- Flavor #1 - Config class with `@Bean` method with `@PropertySource` and `@Autowired` `Environment` class, and use `env.getProperty(key)`. 
	- Flavor #2 - define a `<bean>` tag in the `registratiion-servlet.xml` along with the `<context:property-placeholder location='classpath:/jdbc.properties'/>`.
	
	*pom.xml*
	```xml
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>4.3.30.RELEASE</version>
		</dependency>
	```
	*registration-servlet.xml*
	```xml
	    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
	        <property name="driverClassName" value="${jdbc.driverClassName}"/>
	        <property name="url" value="${jdbc.url}"/>
	        <property name="username" value="${jdbc.username}"/>
	        <property name="password" value="${jdbc.password}"/>
	    </bean>
    
    		<context:property-placeholder location="classpath:/jdbc.properties"/>
	```
	> *Note*: If theer is a bean declared in the XML file that will take the preference than the one declared in the Java class via `@Bean` annotation.
* Assignment #3 - Login using Spring JDBC and Spring MVC to have the login credentials stored and verified from the Database table than the one hardcoded.
	* I will create a separate github repo name for this assignment. *Name*: `Assignment3-SpringJDBC-Login-27Mar2023-Karthik
		* https://github.com/itsraghz/spring-mvc-demo/tree/Assignment3-SpringJDBC-Login-27Mar2023-Arun
* Spring MVC Rest
	* Dependency on Jackson API
	```xml
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.5.3</version>
		</dependency>
	```
* Tools
	* Browser - direct URL in the address bar
	* Command Line tools - HTTPie, cURL
	* Dedicated Rest Clients	
		- Installable Apps - Postman, Insomnia REST
		- Browser  Addons - RESTClient for Firefox
* `JavaFaker` to have the random data added.
	* Search Capability to make it more meaningful
* Spring Security
* I18N (Internationlization) capabilities

## Spring Rest 

* *Resource* - most important component, anything and everything we speak here is in terms of a Resource
* Example: An Image, an URL, an entity, a document etc., - anything that can be referred in the URL - is a URI
* URI - Uniform Resource Indicator. 
* URL - Uniform Resource Locator. 
* URL Vs URI -> Locator tells you where the entity being looked for is present. 
	* http://localhost:8080/spring-mvc-demo/images/logo.png - specify that the file 'logo.png' is present inside the 'images' directory 
	* http://localhost:8080/spring-mvc/demo/api/contacts, with HTTP method GET. It just specifies that the user is interested in getting all the contacts, not really bothered about the location where they are stored in the System.

## `@Controller` Vs `@RestController`

* Controller is used for Web (B2C), and returns the logical view name as a String - in Spring MVC
* RestController is used for non-web (B2B), and it returns the complete response as a `@ResponseBody`.

> *Note*: As a convention, the URL to the Rest Controller methods will have a prefix of `/api` to indicate that they belong the Rest API. But not a RULE to be followed!
	
## Rest Controller Flow with other layers

Client <--> RestController <--> Service <--> Repository <--> [JDBC <-->] Database (Actual Database)

> *Note*: The Client here is typically a *Rest Client*, like _cURL_, _HTTPie_, _Postman_, _Insomnia REST_ etc., 
> Sometimes, the Browser can also act as a Rest Client (for GET requests).

## Method Names

* Typically in a Java method, we use _nouns_ for the member variables and _verbs_ for the methods indicating the actions a method can perform on an Object. Example, *doGet()*, *sayHello()*, _run()_, _sleep()_, _writeToFile()_ etc., 
* But in *REST*, we only talk in terms of *Resources* - meaning _nouns_ and *NOT* _Verbs_. What should be the method names ? 
	* `/api/contacts`, `/api/users`. 
* We do *NOT* say, `api/getContact`, `/api/addContact`, `/api/updateContact` etc.,

> *Note*: It is more or less like a RULE to be followed. Though the Java compiler will NOT complain you against these norms, but the Java Developer Community will accuse and abuse you if you dont', as these are the general best practices widely adopted and strictly followed without any excuses!!!!

> *Note*: The URL patterns genrally will be of lower case, until we need a camelCase in a single word. 
> `/api/Contacts` is NOT recommended. (Capital case C, and it is a single word!!!)
	
### Rest API Method Vs CRUD Operations

Q: How do we distinguish on the action for CRUD methods we perform on the Resources? 
A: Ultimately, we use two things in conjunction to determine the operations being performed on the resource. 

Factors :

 * HTTP Method - `GET`, `POST`, etc., 
 * Actual method name / the URL pattern - `/api/contacts`. 

#### Example URL Mappings 

| URL | HTTP Method | Domain Object | CRUD Operation | Remarks |
| --- | ----------- | ------------- | -------------- | ------- |
| `/api/contacts` | `GET` | `Contact.java` | `R` - Read | Get All Contacts (list of Contacts) |
| `/api/contacts` | `POST` | `Contact.java` | `C` - Create | Create a new Contact |
| `/api/contacts/{id}` | `GET` | `Contact.java` | `R` - Read | Get the Contact By Id, if any |
| `/api/contacts` | `PUT` | `Contact.java` | `U` - Update | Update the Contact (By Id) - we don't say it explicitly like GET |
| `/api/contacts` | `DELETE` | `Contact.java` | `D` - Delete | Delete the Contact (By Id) - we don't say it explicitly like GET |
| `/api/events` | `GET` | `Event.java` | `R` - Read | Get All Events (list of Events) |
| `/api/events` | `POST` | `Event.java` | `C` - Create | Create a new Event |
| `/api/events/{id}` | `GET` | `Event.java` | `R` - Read | Get the Event By Id, if any |
| `/api/events` | `PUT` | `Event.java` | `U` - Update | Update the Event (By Id) - we don't say it explicitly like GET |
| `/api/events` | `DELETE` | `Event.java` | `D` - Delete | Delete the Event (By Id) - we don't say it explicitly like GET |

> *Note*: The URL Pattern and the HTTP Method should be unique. The Spring MVC framework will throw an exception while getting started and it is preparing its internal Map (URL Pattern with the RequestHandlerMapping), if it is otherwise.

## Rest API Vs Exception Handling

* Default - blows up the stack trace
* Flavor #1 - Throw a `RuntimeException` - No big difference
* Flavor #2 - Return a generic `Object` - Better but status code is inappropriate.
* Flavor #3 - Use a `ResponseEntity<T>` which is a wrapper object that carries 
	- Actual Response Data
	- Custom Response Headers
	- Http Status Code

### Assignment

* Make the HTTP Post/Put/Delete requests via `cURL`, as we have demonstrated the same using *Insomnia REST Client* in the class.
* Go through the list of HTTP Status code and get an understanding of each category (1XX to 5XX) and read about the famous status code in each category, especially 2XX, 4XX and 5XX. [Wikipedia Link - https://en.wikipedia.org/wiki/List_of_HTTP_status_codes](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)

### Pending Things

* [DONE] Exception Handling on the Rest API Methods
* See the `@Valid` annotation in Action for the validation rules
* Put Vs Patch method in Rest API
* Java 8 Streams - on all the business logic where applicable
* Log4J to have a rolling file appender
* Merge the assignment branches to master via the PR (Pull Request)