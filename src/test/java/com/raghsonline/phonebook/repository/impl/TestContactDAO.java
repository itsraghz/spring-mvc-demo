package com.raghsonline.phonebook.repository.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.raghsonline.phonebook.config.AppConfig;
import com.raghsonline.phonebook.exception.BusinessException;
import com.raghsonline.phonebook.model.Contact;
import com.raghsonline.phonebook.repository.DAO;
import com.raghsonline.phonebook.service.ContactService;

public class TestContactDAO 
{
	
	static Logger logger = Logger.getLogger(TestContactDAO.class);
	
	//@Autowired
	static ContactService contactService;
	
	static ContactDAO contactDAO;
	
	static AnnotationConfigApplicationContext context;
	
	@BeforeAll
	public static void init()
	{
		logger.info("init() method invoked");
		
		//Needed to load the Spring Context, otherwise, it will NOT be able to 
		//inject the depedency contactService.
		context = new AnnotationConfigApplicationContext(AppConfig.class);
		logger.info("Spring Context :: " + context);
		
		assertNotNull(context);
		
		contactService = context.getBean(ContactService.class);
		logger.info("contactService :: " + contactService);
		assertNotNull(contactService);
		
		/*DAO<Contact> dao = context.getBean(ContactDAO.class);
		logger.info("dao :: " + dao);
		assertNotNull(dao);
		
		contactDAO = (ContactDAO) dao;
		contactDAO.cleanup();*/
	}
	
	@AfterAll
	public static void cleanup()
	{
		logger.info("cleanup() method invoked");
		
		//contactDAO.reset();
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
		
		Contact contact = new Contact(-1, "Spring JDBC", "Unit Testing", 
				"2003-01-01", "3691024685", 
				"spring@jdbc.com", "Spring JDBC Unit Testing", 
				"Java, Spring, JDBC");
		
		int newlyInsertedId = contactService.addContact(contact);
		
		logger.info("newlyInsertedId : " + newlyInsertedId);
		
		assertTrue(newlyInsertedId>0);
	}
}
