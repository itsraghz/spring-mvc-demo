package com.raghsonline.phonebook.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.raghsonline.phonebook.model.Contact;
import com.raghsonline.phonebook.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService 
{
	/**
	 * <p>
	 * A class level logger instance
	 * </p>
	 */
	static Logger logger = Logger.getLogger(ContactServiceImpl.class);
	
	/**
	 * <p>
	 * A class level (static) counter to track the running 
	 * sequence number of the entries being added into the 
	 * List.
	 * </p>
	 */
	static int counter = 0;
	
	public ContactServiceImpl()
	{
		logger.info("ContactServiceImpl instantiated....");
	}

	/**
	 * <p>
	 * To begin with, we would have a static list that will act as
	 * a repository to hold all the contacts, and we will perform the
	 * necessary CRUD operation in this list! 
	 * </p>
	 * <p>
	 * Note: It is a volatile list! 
	 * Whenever we restart the Server/Application, the data what
	 * was stored in the list will be lost, and we will get the initial
	 * values what we configure to be available.
	 * </p>
	 * <p>
	 * TODO: Later, when we build all our Web interfaces (UI, Controller, Model
	 * with MVC), we can switch this to a Database repository than the
	 * static List.
	 * </p>
	 */
	private static List<Contact> contactList = new ArrayList<>();
	
	/**
	 * <p>
	 * This static initializer gets called when the .class gets loaded,
	 * and useful for initializing a class level data that is common
	 * across all the instances/objects of this class.
	 * </p> 
	 */
	static 
	{
		logger.info("ContactServiceImpl() - static initializer block");
		
		/* Building a contact list of Arun */
		contactList.add(new Contact(++counter, "Arun", "Prasad", "1988-04-05", "1234567890", "arun.prasad@gmail.com", "Self", "Self"));
		contactList.add(new Contact(++counter, "Thejaswini", "RN", "1998-08-01", "8740112311", "theju.rn@outlook.com", "Milvik Java Training Batchmate", "Milvik"));
		contactList.add(new Contact(++counter, "Rama", "Vemulapalli", "1995-06-30", "6789012345", "rama.vemulapalli@gmail.com", "Milvik Java Training Batchmate", "Milvik"));
		contactList.add(new Contact(++counter, "Karthik", "HK", "2000-02-30", "9012345678", "hk.karthik@rediffmail.com", "Milvik Java Training Batchmate", "Milvik"));
	}


	@Override
	public List<Contact> getAllContacts() 
	{
		return contactList;
	}

	@Override
	public int addContact(Contact contact) 
	{
		logger.debug("addContact() invoked");
		
		logger.info("Contact Object received : " + contact);
		
		/* We receive the contact but it MAY NOT have
		 * the actual running sequence, which is ONLY
		 * known to the Service class/layer (this class).
		 * 
		 * We should ideally replace / supply the actual
		 * sequence to the "id" attribute of the received
		 * Contact object which is an input argument to this
		 * method, before we actually add it (to the list - Phase 1).
		 */
		contact.setId(++counter);
		
		logger.info("Contact object supplied with the actual counter : " + contact);

		contactList.add(contact);
		
		return contact.getId();
	}
	
	/* ================================================ */
	/* 				Getters and Setters					*/
	/* ================================================ */
	
	/**
	 * @return the contactList
	 */
	public static List<Contact> getContactList() {
		return contactList;
	}

	/**
	 * @param contactList the contactList to set
	 */
	public static void setContactList(List<Contact> contactList) {
		ContactServiceImpl.contactList = contactList;
	}

}
