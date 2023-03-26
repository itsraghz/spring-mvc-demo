# Game Plan 

## Objective

We are building a Phonebook Application using Spring MVC.

### Phase 1

We use a static data (List) in the Service Impl, instead of the Database

## Phase 2

We will use a Database to manipulate the data, and we use JDBC to interact.

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
	* Reusable fragments - header, footer and Menu. #Assignment | Menus - Home, Contacts
	* Add a Bootstrap button to the View
	* Add the alignment for all the colums in the `addContact.jsp` page and do the necessary validations
	* Checkout the person specific branch created in the GitHub Repo  `https://github.com/itsraghz/spring-mvc-demo/tree/Assignment1-UI-21Mar2023-Rama` and push your code in to the corresponding branch (Remote Feaure Branch). 
	* The one scores high, will get merged with the PR (Pull Request) into the remote master/main branch.

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

## Use Case - R

* getById() completed linked with Controller and Test Method.


## Pending Things

* @Order does not work as expected in TestContactDAO. Not sure whether Spring Context being loaded in the class has some influence. Need to check.

### Assignment 

* Pending Use cases using Spring JDBC
	* Update
	* Delete 
* check into the Github Repo as always. Will create a new branch for this `Assignment2-SpringJDBC-26Mar23-<Name>`
* Raghavan should 
	* rectify the file names of Snagit Images which was preventing the git pull due to the very long files names and the team uses Windows.
	* create a new branch for each for this new Assignment - Assignment 2.
	