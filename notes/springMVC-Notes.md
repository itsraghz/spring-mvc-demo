# Spring MVC

## Dependency - Maven

```xml
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-webmvc</artifactId>
		<version>4.2.2.RELEASE</version>
	</dependency>
```
> Note: Pickup the version of your choice in the mvnrepository at https://mvnrepository.com/artifact/org.springframework/spring-webmvc

## Key Concepts

| Entity | Package | Remarks/ Description |
| ------ | ------- | -------------------- |
| `DispatcherServlet` | `org.springframework.web.servlet.DispatcherServlet` | A Front Controller that gets all the requests at first, before it delegates to the other layers. |
| Handlers | N/A | Individual Controllers annotated with `@Controller`, that has got the methods to process the request |
| HandlerMapping | N/A | A Map maintained by Spring MVC Framework for each Controller (handler) with its URL pattern |
| `ViewResolver` | `org.springframework.web.servlet.view.InternalResourceViewResolver` | A Class that helps resolving the view patterns with a *prefix* and *suffix*, that helps the Controller methods just return the logical view name, instead of the full path. |
| `Model` | `org.springframework.ui.Model` | An Interface that defines a holder for model attributes. Method, `addAttribute(key, value)` for adding a value to the Model, which can be passed from Controller to the View. |
| `ModelMap` | `org.springframework.ui.ModelMap` | An implementation class of Map (precisely a `LinkedHashMap` for building model data for use with the UI. It supports chained calls and generation of model attribute names. It has the methods `addAttribute(key, value)`, and also `put(key,value)` - remember, it is from `Map` Interface Implementation. *Note*: ModelMap mandates the `name` attribute while adding the attribute. | 

## Key Annotations

* `@Controller` - Annotate a class and inform Spring Framework that it is a Controller and has the mapping associated with the methods to process the requests
* `@RequestMapping` - Annotation at a method level to facilitate/associate an URL pattern within the Controller class
	- `@RequestMapping(value = "/url", method = RequestMethod.GET)` - to restrict the flow for only the HTTP Get
	- `@RequestMapping(value = "/login")` - will now handle all types of requests - both GET and POST, and other HTTP methods as well if any injected via tools like Postman. Note: The absence of `method` attribute will facilitate this.
* `@ResponseBody` - to let the Controller method directly return a response back to the Client
* `@RequestParam` - to associate a parameter from the Http Request, which in the traditional Servlets, we used to get it from `request.getParameter("name")`. 
	- `public String handle(@RequestParam String userId)` - the parameter `userId` is same as in Http Request and also the local variable inside the `handle()` method
	- `public String handle(@RequestParam("id") String userId)` - the Http Request Paramater `id` is now retrieved via a local variable `userId` in the `handle()` method


## Debug Info with Spring MVC 5.X onwards when the Server is run in DEBUG mode

We should run the Tomcat Server in debug mode by passing the argument `-X` in the Command Line Argument when starting it inside the Eclipse (Right Click Project -> Run As -> Maven build (4th item) -> Command Line Arguments : - `tomcat7:run -X`)

```java
[DEBUG] GET "/spring-mvc-demo/login", parameters={}
[DEBUG] Mapped to com.raghsonline.springmvc.LoginController#showLogin()
LoginController, /login GET method invoked
[DEBUG] View name 'login', model {}
[DEBUG] Forwarding to [/WEB-INF/views/login.jsp]
[DEBUG] Completed 200 OK
```
> Note : Please pay attention to the following 
>   * Line #1 : It shows the URL what was hit with the parameters being supplied if any.
>   * Line #2 : It shows which Controller and which method has been identified as a match for the urlPattern
>       The Internal Mapping of Spring MVC for all such contollers and @RequestMapping methods
>   * Line #3 : Our own System.out.println for a debugging information
>   * Line #4 : The logical view name is what being obtained from the request mapping method (String)
>   * Line #5: The complete path name to the file is computed by InternalViewResolver. 
>   * Line #6: The HTTP status is confirmed as 200, meaning the ultimate final view is found out and the contents were able to be successfully rendered to the Client. 

### Successful form being posted and handled 

```java
[DEBUG] POST "/spring-mvc-demo/login", parameters={masked}
[DEBUG] Mapped to com.raghsonline.springmvc.LoginController#handleLogin(String, String, Model)
[DEBUG] View name 'welcome', model {name=Java}
[DEBUG] Forwarding to [/WEB-INF/views/welcome.jsp]
[DEBUG] Completed 200 OK
```

> Pay attention to the following.
>  * The parameters are `masked` for security reasons
>  * The model object what it carries from Controller to view (what we add into the Model) is printed for a reference
>  * It also prints the HTTP Status code 200 - meaning it was succesfully able to find out the view and render the contents to the Client without any issues. That is what a HTTP 200 typically means.

### Error Scenario

When we have an error in the input while submitting the form and the validation fails,

```java
[DEBUG] POST "/spring-mvc-demo/login", parameters={masked}
[DEBUG] Mapped to com.raghsonline.springmvc.LoginController#handleLogin(String, String, Model)
[DEBUG] View name 'login', model {errorMessage=Invalid Credentials}
[DEBUG] Forwarding to [/WEB-INF/views/login.jsp]
[DEBUG] Completed 200 OK
```

> Note : Pay attention to the `model`, view name and the `200 OK`

# ModelAttribute

The *ModelAttribute* is one of the most important Annotations in the *Spring MVC*. It is defined in the package (`org.springframework.web.bind.annotation`).

`@ModelAttribute is an annotation that binds a method parameter or method return value to a named model attribute, and then exposes it to a web view.` 

*Reference:* [https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation](https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation)

## Different Forms of `ModelAttribute`

We can use the `ModelAttribute` in two differen flavors/forms.

* At a Method level 
* At a Method Parameter level

### `ModelAttribute` at a Method level 

When we use the annotation at the method level, it indicates the purpose of the method is to add one or more model attributes. Such methods support the same argument types as @RequestMapping methods, but _they can't be mapped directly to requests._

*Example*:

```java
@ModelAttribute
public void addAttributes(Model model) {
    model.addAttribute("msg", "Welcome to the ModelAttribute Demo!");
}
```

In the above example, the method adds an attribute named `msg` to *all* the models defined in the controller class.

In general, Spring MVC will always make a call to that method first, before it calls any request handler methods. Basically, *`@ModelAttribute` methods are invoked before the controller methods annotated with `@RequestMapping` are invoked*. This is because the model object has to be created before any processing starts inside the controller methods.

It's also important that we annotate the respective class as `@ControllerAdvice`. Thus, we can add values in Model that'll be identified as *global*. This actually means that for every request, a default value exists for every method in the response.

### `ModelAttribute` at a Method level 

When we use the annotation as a method argument, it indicates _to retrieve the argument from the model._ 

When the annotation isn't present, it should first be instantiated, and then added to the model. Once present in the model, the arguments fields should populate from all request parameters that have matching names.

In the following code snippet, we'll populate the employee model attribute with data from a form submitted to the `addEmployee` endpoint. Spring MVC does this behind the scenes *before* invoking the _submit method:_

```java
@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
public String submit(@ModelAttribute("employee") Employee employee) {
    // Code that uses the employee object

    return "employeeView";
}
```

In Spring MVC, we refer to this as *data binding*, a common mechanism that _saves us from having to parse each form field individually._

## `modelAttribute` Vs `commandName`

The attribute in the `<form>` tag can be of either `commandName` or the `modelAttribute`. They both actually refer to the same backing bean in the `spring-form.tld`'s backed up Java class and they both refer to the same `ModelAttribute`. 

However the `commandName` is still retained for the historical reasons, and we should prefer using the `modelAttribute` in all the latest application development.

*Reference*: [https://stackoverflow.com/questions/21495616/difference-between-modelattribute-and-commandname-attributes-in-form-tag-in-spri](https://stackoverflow.com/questions/21495616/difference-between-modelattribute-and-commandname-attributes-in-form-tag-in-spri)
  * Direct Response : [https://stackoverflow.com/a/21500148/1001242](https://stackoverflow.com/a/21500148/1001242)
  
> *Note* : the class is just called <tag-name>Tag. For the fully qualified class name, open the library (.jar) containing the tag, spring-web in this case. Under META-INF, you'll find spring-form.tld. It'll have a <tag> entry for form with a <tag-class> of org.springframework.web.servlet.tags.form.FormTag

```java 
/**
 * Set the name of the form attribute in the model.
 * <p>May be a runtime expression.
 */
public void setModelAttribute(String modelAttribute) {
    this.modelAttribute = modelAttribute;
}

/**
 * Get the name of the form attribute in the model.
 */
protected String getModelAttribute() {
    return this.modelAttribute;
}

/**
 * Set the name of the form attribute in the model.
 * <p>May be a runtime expression.
 * @see #setModelAttribute
 */
public void setCommandName(String commandName) {
    this.modelAttribute = commandName;
}

/**
 * Get the name of the form attribute in the model.
 * @see #getModelAttribute
 */
protected String getCommandName() {
    return this.modelAttribute;
}
```
