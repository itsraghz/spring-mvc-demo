package com.raghsonline.phonebook.service;

import java.util.List;
import java.util.Optional;

import com.raghsonline.phonebook.exception.BusinessException;
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
	 * @throws BusinessException in case of any business validation failures
	 */
	public long addContact(Contact contact) throws BusinessException;
	
	/**
	 * <p>
	 * A method to get a contact by its Id.
	 * </p>
	 * @param id the id attribute of the Contact
	 * @return an Optional contact if exists matching with the id.
	 */
	public Optional<Contact> getContactById(int id);
	
	/**
	 * <p>
	 * A method to update the attributes of a Contact.
	 * </p>
	 * @param contact the contact to be updated
	 * @throws BusinessException in case of any business validation failures
	 */
	public long updateContact(Contact contact) throws BusinessException;
	
	/**
	 * <p>
	 * A method to delete a Contact by its Id.
	 * </p>
	 * @param id the sequence number (Id) of the Contact to be deleted
	 * @return boolean true if there was a successful deletion, false otherwise.
	 */
	public long deleteContact(int id);
}
