package com.raghsonline.phonebook.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.raghsonline.phonebook.exception.BusinessException;
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
	public int addContact(Contact contact) throws BusinessException 
	{
		logger.debug("addContact() invoked");
		
		logger.info("Contact Object received : " + contact);
		
		/* It is time to do the validation on the duplicate check */
		// All the boundary level validations are already performed at the Controller level */
		if(isContactDuplicate(contact)) 
		{
			String errorMsg = "Contact Number already exists!";
			logger.error(errorMsg);
			throw new BusinessException(errorMsg);
		}
		
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
	
	/**
	 * <p>
	 * A reusable utility method to find out whether a contact
	 * is already present in the list.
	 * </p>
	 * @param contactParam An instance of the Contact the contact to be added
	 * @return	a boolean flag indicating a true/false depending on the status
	 */
	public boolean isContactDuplicate(Contact contactParam)
	{
		logger.info("isContactDuplicate() invoked");
		
		boolean isDuplicate = false;
		
		for (Contact contact : contactList) 
		{
			/* Logic: TFor now we will verify the Contact No alone */
			if(contact.getContactNo().equals(contactParam.getContactNo())) 
			{
				logger.error("Contact Number already present in the list!");
				isDuplicate = true;
				break;
			}
		}
		
		logger.info("isDuplicate ? " + isDuplicate);
		
		return isDuplicate;
		
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

	@Override
	public Optional<Contact> getContactById(int id) 
	{	
		logger.info("getContactById() invoked with id - " + id);
		
		Optional<Contact> optionalContact = Optional.empty();
		
		/* 
		 * Q: Why we don't use the contactList.get(id)?
		 * A: Because it is the index and NOT the id ! 
		 */
		//return contactList.get(id);
		
		/* The recommended way is to loop through the list and find for a match */
		for (Contact contact : contactList) 
		{
			if(contact.getId()==id)
			{
				optionalContact = Optional.of(contact);
				break;
			}
		}
		
		return optionalContact;
	}

	@Override
	public void updateContact(Contact contact) throws BusinessException 
	{
		System.out.println("updateContact invoked!");
		
		/**
		 * Because we use a hardcoded list of data now,
		 * we can remove the existing object/item from the list matching with the Id, 
		 * and then add it further into the same list.	
		 */
		Optional<Contact> optionalContact = getContactById(contact.getId());
		
		if(optionalContact.isEmpty())
		{
			System.out.println("Contact is not available!");
			return;
		}
		
		System.out.println("contact matching : " + optionalContact.get());
		
		System.out.println("size of the contactList before removal : " + contactList.size());
		contactList.remove(contact);
		
		System.out.println("size of the contactList after removal : " + contactList.size());
		contactList.stream().forEach(System.out::println);
		
		//contactList.add(contact);
		addContact(contact);
		
		System.out.println("size of the contactList after addition : " + contactList.size());
		contactList.stream().forEach(System.out::println);
	}

	@Override
	public boolean deleteContact(int id)
	{
		logger.info("deleteContact() invoked with id - " + id);
		
		/* Trouble: You pass it as ID but the JDK/JVM treats it for Index */
		//Contact contactRemoved = contactList.remove(id);
		
		/* 
		 * Solution : We can solve it in two different ways
		 * 
		 *  1. Using the remove(Object o) method and pass the actual Object instance for deletion
		 *  	than remove(int index).
		 *  	Note:  remove(int index) returns the Object which was present before removal
		 *  		   remove(Object o) returns a boolean status for the removal operation.
		 *  
		 *  2. TODO: Assignment (21 Mar 2023)Iterate the list and find out the index of the matching object, 
		 *  	then pass it to the remove(int index) method.
		 * 
		 */

		//Contact contactRemoved = null;
		boolean removalStatus = false;
		Contact contactToBeDeleted = null;
		
		Optional<Contact> optionalContact = getContactById(id);
		
		if(optionalContact.isPresent()) {
			contactToBeDeleted = optionalContact.get();
			removalStatus = contactList.remove(contactToBeDeleted);
		}
		
		logger.info("removalStatus :: " + removalStatus);
		
		return removalStatus;
	}

}
