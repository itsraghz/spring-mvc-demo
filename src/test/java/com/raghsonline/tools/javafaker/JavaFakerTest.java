package com.raghsonline.tools.javafaker;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.github.javafaker.Faker;
import com.raghsonline.phonebook.config.AppConfig;
import com.raghsonline.phonebook.exception.BusinessException;
import com.raghsonline.phonebook.model.Contact;
import com.raghsonline.phonebook.service.ContactService;

public class JavaFakerTest 
{
	static Logger logger = Logger.getLogger(JavaFakerTest.class);
	
	static AnnotationConfigApplicationContext context;
	
	static ContactService contactService;
	
	/*public JavaFakerTest(ContactService contactService)
	{
		logger.info("JavaFakerTest() instantiated");
		logger.info("contactService : " + contactService);
	}*/
	
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
	}
	
	@Test
	@DisplayName("Java Faker should return the valid values")
	public void javaFakerSimpleTest()
	{
		Faker faker = new Faker(new Locale("en-IND"));
		
		String firstName = faker.name().firstName();
		logger.info("Faker First Name : " + firstName);
		assertNotNull(firstName);
		
		String lastName = faker.name().lastName();
		logger.info("Faker Last Name : " + lastName);
		assertNotNull(lastName);
	}
	
	@Test
	@DisplayName("Contact object filled with the values from Java Faker")
	public void javaFakerContactTest()
	{
		getFakerContact();
	}

	private Contact getFakerContact() 
	{
		Faker faker = new Faker(new Locale("en-IND"));
		
		return getFakerContact(faker);
	}
	
	private Contact getFakerContact(Faker faker) 
	{
		String firstName, lastName, dob, contactNo, email, notes, tag;
		int id;
		
		/* Fill the values with the Java Faker */
		firstName = faker.name().firstName();
		lastName = faker.name().lastName();
		
		//dob = faker.date().birthday().toString();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		dob = sdf.format(faker.date().birthday());
		
		contactNo = faker.phoneNumber().phoneNumber().substring(0,  10);
		email = faker.internet().emailAddress();
		notes = "JavaFaker Test - " + faker.funnyName().name();
		tag = "Java, Faker, " + faker.name().username();
		id = faker.number().numberBetween(0, 9999); /* It returns a number between 0-9 */
		
		Contact contact = new Contact(id, firstName, lastName, dob, contactNo, email, notes, tag);
		
		logger.info("contact object filled with Faker : " + contact);
		
		assertNotNull(contact);
		assertNotNull(contact.getFirstName());
		assertNotNull(contact.getLastName());
		assertNotNull(contact.getDob());
		assertNotNull(contact.getContactNo());
		assertNotNull(contact.getEmail());
		assertNotNull(contact.getNotes());
		assertNotNull(contact.getTag());
		assertTrue(contact.getId() >= 0);
		assertTrue(contact.getId() <= 9999); 
		
		return contact;
	}
	
	//@Test
	@DisplayName("A Contact object from Java Faker should be added into DB successfully")
	public void addFakerContact()
	{
		logger.info("addFakerContact() invoked!");
		
		Contact contact = getFakerContact();
		long newlyInsertedId = 0;
		
		try {
			newlyInsertedId = contactService.addContact(contact);
			logger.info("newlyInsertedId :: " + newlyInsertedId);
			assertTrue(newlyInsertedId > 0);
		} catch (BusinessException businessException) {
			logger.error("BusinessException occurred while adding a Contact");
			logger.error("Error Message : " + businessException.getMessage());
			businessException.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Add multiple contacts from Java Faker")
	public void addMultipleContactsToDb() throws BusinessException
	{
		logger.info("addMultipleContactsToDb() invoked!");
		
		/* Flavor #1 */ 
		List<Contact> contactList1 = List.of(getFakerContact(), getFakerContact(), getFakerContact());
		
		logger.info("contactList1 size : " + contactList1.size());
		contactList1.stream().forEach(x -> logger.info(x));
		
		/* Flavor #2 */
		List<Contact> contactList2 = new ArrayList<>();
		
		for(int i=0; i < 3; i++)
		{
			contactList2.add(getFakerContact());
		}
		
		logger.info("contactList2 size : " + contactList2.size());
		contactList2.stream().forEach(x -> logger.info(x));
		
		addContactListToDB(contactList1);
		addContactListToDB(contactList2);		
	}

	private void addContactListToDB(List<Contact> contactList) 
	{
		contactList.stream().forEach(x -> {
			try {
				final long newlyInsertedId = contactService.addContact(x);
				logger.info("Contact [" + x + "] has been added to DB, with the ID : "
							+ newlyInsertedId);
			} catch (BusinessException businessException) {
				logger.error("Exception occurred while adding a Contact to DB");
				logger.error("Error Message : " + businessException.getMessage());
				businessException.printStackTrace();
			}
		});
	}
}
