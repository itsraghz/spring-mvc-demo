package com.raghsonline.phonebook.repository.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.Order;

import com.raghsonline.phonebook.config.AppConfig;
import com.raghsonline.phonebook.exception.BusinessException;
import com.raghsonline.phonebook.model.Contact;
import com.raghsonline.phonebook.repository.DAO;
import com.raghsonline.phonebook.service.ContactService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestContactDAO 
{
	
	static Logger logger = Logger.getLogger(TestContactDAO.class);
	
	//@Autowired
	static ContactService contactService;
	
	static DAO<Contact> contactDAO;
	
	static AnnotationConfigApplicationContext context;
	
	static String contactNo;
	
	static long count;
	
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
		
		contactDAO = context.getBean(ContactDAO.class);
		logger.info("dao :: " + contactDAO);
		assertNotNull(contactDAO);
		
		contactNo = "3691024685";
		
		/* 
		 * Here the contactDAO is of type Interface DAO<T>, which does NOT have the
		 * cleanup() and reset() methods. Hence, a Typecasting to ContactDAO is necessary!
		 */
		((ContactDAO)contactDAO).cleanup();
	}
	
	@BeforeEach
	public void beforeEach() throws BusinessException
	{
		logger.info("beforeEach() invoked - !!!! DOING NOTHING!!!!");
		
		//createContact();
	}
	
	@AfterAll
	public static void cleanup()
	{
		logger.info("cleanup() method invoked");
		
		((ContactDAO)contactDAO).reset();
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
	@DisplayName("Get the count of rows from Contact Table")
	@Order(1)
	public void getCount()
	{
		logger.info("getCount() invoked!");
		
		count = contactDAO.getCount();
		
		logger.info("count :: " + count);
	}
	
	//@Test
	@DisplayName("Creating a Contact should successfully return an auto-generated sequence number")
	@Order(2)
	public void createContact() throws BusinessException 
	{
		logger.info("createContact() invoked");
		
		Contact contact = new Contact(0, "Spring JDBC", "Unit Testing", 
				"2003-01-01", contactNo, 
				"spring@jdbc.com", "Spring JDBC Unit Testing", 
				"Java, Spring, JDBC");
		
		Contact contact2 = new Contact(0, "Spring JDBC2", "Unit Testing", 
				"2003-01-02", "2222222222", 
				"spring@jdbc2.com", "Spring JDBC Unit Testing", 
				"Java, Spring, JDBC2");
		
		Contact contact3 = new Contact(0, "Spring JDBC3", "Unit Testing", 
				"2003-01-03", "3333333333", 
				"spring@jdbc3.com", "Spring JDBC Unit Testing", 
				"Java, Spring, JDBC3");
		
		long newlyInsertedId = addContact(contact);
		
		assertTrue(newlyInsertedId>0);
		
		newlyInsertedId = addContact(contact2);
		
		assertTrue(newlyInsertedId>0);
		
		newlyInsertedId = addContact(contact3);
		
		assertTrue(newlyInsertedId>0);
	}

	private long addContact(Contact contact) throws BusinessException 
	{
		long newlyInsertedId = contactService.addContact(contact);
		
		logger.info("newlyInsertedId : " + newlyInsertedId);
		return newlyInsertedId;
	}
	
	@Test
	@DisplayName("GetByContactNo should fetch the matching Contact object")
	@Order(3)
	public void getByContactNo() 
	{
		logger.info("getByContactNo() invoked!");
		
		logger.info("contactDAO :: " + contactDAO);
		
		/* Here I dont want to go via ContactService - for now, hence using the contactDAO directly */
		Optional<Contact> optionalContact = contactDAO.getByContactNo(contactNo);
		
		logger.info(optionalContact);
		
		if(optionalContact.isPresent()) {
			assertNotNull(optionalContact.get());
		}	
	}
	
	@Test
	@DisplayName("Creating a Duplicate Contact should throw a BusinessException")
	@Order(4)
	public void createDuplicateContact()
	{
		logger.info("createDuplicateContact() invoked");
		
		Contact contact = new Contact(-1, "Spring JDBC", "Unit Testing", 
				"2003-01-01", contactNo, 
				"spring@jdbc.com", "Spring JDBC Unit Testing", 
				"Java, Spring, JDBC");
		
		
		Exception thrown = Assertions.assertThrows(BusinessException.class, () -> {
			long newlyInsertedIdLocal = contactService.addContact(contact);
			logger.info("newlyInsertedId : " + newlyInsertedIdLocal);
			assertTrue(newlyInsertedIdLocal>0);
		});
		logger.error(thrown.getMessage());

		Assertions.assertEquals("ContactNo already exists!", thrown.getMessage());
	}
	
	@Test
	@DisplayName("GetAll should retrieve all the available contacts")
	@Order(5)
	public void getAll()
	{
		logger.info("getAll() invoked");
		
		List<Contact> contactList = contactDAO.getAll();
		
		logger.info("contactList :: " + contactList);

		assertNotNull(contactList);
		
		contactList.stream().forEach(logger::info);
	}
	
	@Test
	@DisplayName("Get Contact by Id should return the matching Contact instance")
	@Order(6)
	public void getContactById()
	{
		logger.info("getContactById() invoked");
		
		Optional<Contact> optionalContact = contactDAO.getById(1);
		
		logger.info("optionalContact :: " + optionalContact);
		
		/* 
		 * For an Optional value, the assertNotNull does NOT really add a weightage,
		 * because mostly we would be returning an Optional.empty().
		 */
		assertNotNull(optionalContact);
		
		if(optionalContact.isPresent())
		{
			assertNotNull(optionalContact.get());
		}
	}
	
	@Test
	@DisplayName("Update contact by Id and return rowsaffected")
	@Order(7)
	public void updateContact()
	{
		logger.info("----------- updateContact() - Invoked ----------");
		
		Contact t =new Contact();
		
		t.setFirstName("Update By Junit");
		t.setLastName("JUnit");
		t.setDob("2000-09-07");
		t.setContactNo("0989089082");
		t.setEmail("JUnit@gmail.com");
		t.setNotes("Contact Upadted Through JUnit");		
		t.setTag("JUnit Test");		
		t.setId(1);
		
		long rowsAffected = contactDAO.update(t);
		logger.info(rowsAffected);
		if(rowsAffected>0) {
			assertTrue(rowsAffected>0);	
		}
		else {
			logger.info("No Data is avalibale to Edit");
		}
			
	}
	
	@Test
	@DisplayName("Delete contact by Id and return rowsaffected")
	@Order(8)
	public void deleteContact()
	{
		logger.info("----------- deleteContact() - Invoked ----------");
		
		int id = 3;
		
		long rowsAffected = contactDAO.deleteById(id);
		logger.info(rowsAffected);
		assertTrue(rowsAffected>=1);
		
	}
}
