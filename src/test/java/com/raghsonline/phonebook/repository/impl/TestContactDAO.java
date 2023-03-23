package com.raghsonline.phonebook.repository.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.raghsonline.phonebook.config.AppConfig;
import com.raghsonline.phonebook.exception.BusinessException;
import com.raghsonline.phonebook.model.Contact;
import com.raghsonline.phonebook.service.ContactService;

public class TestContactDAO 
{
	
	static Logger logger = Logger.getLogger(TestContactDAO.class);
	
	//@Autowired
	static ContactService contactService;
	
	static AnnotationConfigApplicationContext context;
	
	@BeforeAll
	public static void init()
	{
		logger.info("init() method invoked");
		
		//Needed to load the Spring Context, otherwise, it will NOT be able to 
		//inject the depedency contactService.
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		logger.info("Spring Context :: " + context);
		
		contactService = context.getBean(ContactService.class);
		logger.info("contactService :: " + contactService);
	}
	
	/**
	 * A single arg constructor with the dependency, can be 
	 * automagicaly wired, and the `@Autowired` annotation is
	 * optional - at all levels - field or the Constructor.
	 * 
	 * @param contactDAO
	 */
	/*public TestContactDAO(ContactService contactService)
	{
		logger.info("TestContactDAO instantiated, contactService : " + contactService);
		
		this.contactService = contactService;
	}*/
	
	@Test
	@DisplayName("Creating a Contact should successfully return an auto-generated sequence number")
	public void createContact() throws BusinessException
	{
		logger.info("createContact() invoked");
		
		Contact contact = new Contact(-1, "Spring", "2 way Data Binding", 
				"2003-01-01", "1234567891", 
				"databinding@springmvc.com", "Spring MVC DataBinding", 
				"Java, Spring");
		
		int newlyInsertedId = contactService.addContact(contact);
		
		logger.info("newlyInsertedId : " + newlyInsertedId);
		
		assertTrue(newlyInsertedId>0);
	}
}
