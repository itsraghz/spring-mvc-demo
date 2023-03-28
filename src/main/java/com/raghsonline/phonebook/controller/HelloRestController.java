package com.raghsonline.phonebook.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.raghsonline.phonebook.model.Contact;

@RestController
public class HelloRestController 
{
	Logger logger = Logger.getLogger(HelloRestController.class);
	
	List<String> weekdaysList = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
	
	@RequestMapping(value = "/api/hello", method = RequestMethod.GET)
	//@ResponseBody //optional
	public String sayHello()
	{
		logger.info("/api/hello - invoked!");
		return "Hello Spring REST - via @RequestMapping with RequestMethod.GET";
	}
	
	@GetMapping("/api/hello2")
	//@ResponseBody
	public String sayHello2()
	{
		logger.info("[@GetMapping] /api/hello2 - invoked!");
		return "Hello, @GetMapping - easy alternative!";
	}
	
	@GetMapping("/api/hello2/")
	public String sayHello2Slash()
	{
		logger.info("[@GetMapping] /api/hello2/ - invoked - a slash at the end, pls note!");
		return "Hello, @GetMapping - easy alternative, but pay attention on the ending slash!!!";
	}
	
	@GetMapping("/api/hello/{name}")
	public String sayHelloName(@PathVariable String name)
	{
		logger.info("/api/hello/{name} - invoked, name="+name);
		return String.format("Hello, %s", name);
	}
	
	@GetMapping("/api/hello/weekdays")
	public List<String> getWeekdays()
	{
		logger.info("/api/hello/weekdays - invoked");
		return weekdaysList;
	}
	
	@GetMapping("/api/hello/contact")
	public Contact getContact()
	{
		logger.info("/api/hello/weekdays - invoked");
		
		Contact contact = new Contact(-1, "Spring REST", "Sample Contact", 
				"2003-01-01", "1234567891", 
				"getmapping@springrest.com", "Spring Rest @GetMapping", 
				"Java, Spring, Rest");
		
		return contact;
	}
	
	/*@GetMapping(value = "/api/hello/error-weekdays", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getListOfWeekdays()
	{
		logger.info("/api/hello/error-weekdays - invoked");
		//response.setContentType("application/json");
		return weekdaysList;
	}
	
	@GetMapping(value = "/api/hello/1/weekdays", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getWeekdaysJson()
	{
		logger.info("/api/hello/weekdays - invoked");
		return new ResponseEntity<List<String>>(weekdaysList, HttpStatus.OK);
		//return ResponseEntity.created(location).header("MyResponseHeader", "MyValue").body("Hello World");
		//return ResponseEntity.created(new URI("/api/hello/weekdays")).header("Content-Type", "application/json").body(weekdaysList);

	}*/
}
