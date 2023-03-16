package com.raghsonline.phonebook.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.raghsonline.phonebook.model.Contact;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestContactServiceImpl 
{
	/**
	 * <p>
	 * A class level logger instance.
	 * </p>
	 */
	Logger logger = Logger.getLogger(TestContactServiceImpl.class);
	
	/**
	 * <p>
	 * An instance of ContactService whose data will be operated upon
	 * in this Test class.
	 * </p>
	 * <p>
	 * TODO: For now, we will have a new instance created by ourselves, later
	 * we will bring in @Autowired of Spring Container/Context. 
	 * </p>
	 */
	ContactServiceImpl contactService = new ContactServiceImpl();
	
	@Test
	@DisplayName("GetAllContacts should return min 4 contacts")
	public void getAllContacts()
	{
		List<Contact> contactList = contactService.getAllContacts();
		
		assertNotNull(contactList);
		assertEquals(4, contactList.size());
		
		logger.info("Contact List - size : " + contactList.size());
		logger.info("Contact List - Contents : " + contactList);
		//contactList.stream().forEach(System.out::println);
		contactList.stream().forEach(logger::info);
	}

}
