package com.raghsonline.phonebook.service;

import java.util.List;

import com.raghsonline.phonebook.model.Contact;

/**
 * An interface to define the contract for the 
 * CRUD (Create, Read, Update and Delete) 
 * functionalities of a Contact.
 */
public interface ContactService 
{
	/**
	 * <p>
	 * A method to return all the available contacts in the Phonebook.
	 * </p>
	 * 
	 * @return an instance of <tt>java.util.List</tt> containing 
	 * 			all the contacts.
	 */
	public List<Contact> getAllContacts();
	
	/**
	 * <p>
	 * A method to add a Contact to the PhoneBook
	 * and return the unique Id of the contact being added.
	 * </p>
	 * 
	 * @param contact the contact to be added
	 * @return an integer representing the sequence number 
	 * 			of the newly added contact
	 */
	public int addContact(Contact contact);
}
