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
     * Missing or empty values - through `@Valid` annotation 
     * Duplicate Values 
* [DONE] Reduce the repeating `@RequestParam` for each of the parameters passed via the Request