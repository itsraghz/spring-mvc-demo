package com.raghsonline.phonebook.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.raghsonline.phonebook.exception.BusinessException;
import com.raghsonline.phonebook.model.Contact;
import com.raghsonline.phonebook.service.ContactService;

@RestController
public class ContactRestController 
{
	Logger logger = Logger.getLogger(ContactRestController.class);
	
	ContactService contactService;
	
	@Autowired
	public ContactRestController(ContactService contactService)
	{
		logger.info("ContactRestController(contactService) instantiated, contactService="+ contactService);
		this.contactService = contactService;
	}
	
	@GetMapping(value = "/api/contacts")//, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody() //Optional, but its presence does not do any harm! 
	public List<Contact> getAllContacts()
	{
		logger.info("/api/contacts - invoked");
		//response.setContentType("application/json");
		return contactService.getAllContacts();
	}
	
	@GetMapping(value = "/api/contacts/Optional/{id}")
	public Optional<Contact> getContactByIdOptional(@PathVariable long id)
	{
		logger.info("/api/contacts/Optional/{id} - invoked, id="+id);
		//response.setContentType("application/json");
		return contactService.getContactById(id);
	}
	
	@GetMapping(value = "/api/contacts/{id}")
	public Contact getContactById(@PathVariable long id)
	{
		logger.info("/api/contacts/{id} - invoked, id="+id);
		//response.setContentType("application/json");
		return contactService.getContactById(id).get();
	}
	
	/* 
	 * POST - to submit a form/ request data for creating an entity
	 * Entry point to carry the data - No UI/Web, and NO HTML Form.
	 * Carrier - JSON Object 
	 */
	//@RequestMapping(value = "/api/contact", method=RequestMethod.POST) 
	//@PostMapping(value = "/api/contact")//shortcut Mapping
	@PostMapping("/api/contacts")//even more shorter version - skip the value attribute if it is the only value
	public String addContact(@RequestBody @Valid Contact contact)
	{
		logger.info("POST - /api/contacts request received");
		logger.info("RequestBody :: " + contact);
		
		/* 
		 * It is time to really act on the contact object. We need to make a few changes to the method
		 *  
		 *  1. Invoke the Serice method to add the Contact Object
		 *  	(indeed it hits the Repository/DAO layer and Database via Spring JDBC here!)
		 *  2. Handle the BusinessException if any being thrown while adding a Contact
		 *  3. Modify the return type from 'void' to a String here with a meaningul message to the User
		 *  	similar to what we had in the @Controller with Web (ContactController)
		 */
		/* Now you understand, why we have a Service object reference here! */
		long newlyInsertedId = 0;
		String message = null;
		try {
			newlyInsertedId = contactService.addContact(contact);
			logger.info("newlyInsertedId ::: " + newlyInsertedId);
			message = "Contact successfully added with the Id : " + newlyInsertedId;
		} catch (BusinessException businessException) {
			String errorMsg = businessException.getMessage();
			logger.error("BusinessException occurred while adding a Contact");
			logger.error("Error Message : " + errorMsg);
			message = "Error while adding a Contact. Reason : " + errorMsg;
			//TODO: Make it conditioally printed based on the 'AppDevMode' flag if any.
			businessException.printStackTrace();
		}
		
		logger.info("Message :: " + message);
		
		return message;
	}
	
	@PutMapping("/api/contacts")
	public void updateContact(@RequestBody @Valid Contact contact)
	{
		logger.info("PUT - /api/contacts request received");
		logger.info("RequestBody :: " + contact);
		
		try {
			contactService.updateContact(contact);
			logger.info("An attempt to update the Contact successfully!");
		} catch (BusinessException businessException) {
			String errorMsg = businessException.getMessage();
			logger.error("BusinessException occurred while updating a Contact");
			logger.error("Error Message : " + errorMsg);
			//TODO: Make it conditioally printed based on the 'AppDevMode' flag if any.
			businessException.printStackTrace();
		}
	}
	
	@DeleteMapping("/api/contacts/{id}")
	public boolean deleteContact(@PathVariable long id)
	{
		logger.info("DELETE - /api/contacts/{id} request received");
		logger.info("ID parameter :: " + id);
		
		boolean status = contactService.deleteContact(id);
		
		logger.info("deletion status ? " + status);
		
		return status;
	}
	
	/**
	 * <p>
	 * This method conditionally return the Contact object if one exists
	 * with the matching Id supplied as an input parameter. Else it throws
	 * a RuntimeException.
	 * </p>
	 * <p>
	 * Nevertheless, it is not a recommended behavior to throw a <tt>RuntimeException</tt>
	 * as the caller may NOT be aware and/or prepared to handle the scenario, as opposed to
	 * the Checked or Compile Time exceptions. We are just doing this for the learning purposes.
	 * </p>
	 * @param id
	 * @return
	 * @throws RuntimeException
	 */
	@GetMapping(value = "/api/contacts/try/RTE/{id}")
	public Contact getContactById2(@PathVariable long id)
	{
		logger.info("/api/contacts/try/RTE/{id} - invoked, id="+id);
		
		Optional<Contact> optionalContact =  contactService.getContactById(id);
		
		if(optionalContact.isPresent()) {
			return optionalContact.get();
		} else {
			throw new RuntimeException("Contact not present with the given Id : " + id);
		}
	}
	
	@GetMapping(value = "/api/contacts/try/Object/{id}")
	public Object getContactById3(@PathVariable long id)
	{
		logger.info("/api/contacts/try/Object/{id} - invoked, id="+id);
		
		Optional<Contact> optionalContact =  contactService.getContactById(id);
		
		if(optionalContact.isPresent()) {
			return optionalContact.get();
		} else {
			return new String("Contact not present with the given Id : " + id);
		}
	}
	
	@GetMapping(value = "/api/contacts/try/RE/{id}")
	public ResponseEntity<Object> getContactByIdResponseEntity(@PathVariable long id)
	{
		logger.info("/api/contacts/try/RE/{id} - invoked, id="+id);
		
		Optional<Contact> optionalContact =  contactService.getContactById(id);
		
		ResponseEntity<Object> responseEntity = null;
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("responseTime", LocalDateTime.now().toString());
		
		if(optionalContact.isPresent()) {
			/* ------------------------------------------------------------ */
			/* Straight forward and simple way */
			/* ------------------------------------------------------------ */
			////responseEntity = ResponseEntity.ok(optionalContact.get());
			
			/* ------------------------------------------------------------ */
			/* Extended version with a custom Header - may be the timestamp */
			/* ------------------------------------------------------------ */
			responseEntity = ResponseEntity
								.status(HttpStatus.OK)
								.headers(headers)
								.body(optionalContact.get());
								
		} else {
			String errorMsg = "Contact Not Found with the Id supplied - " + id;
			/**
			 * Dilemma: HTTP 500 Vs 404? 
			 * 
			 * 500 - Internal Server Error, when the Server (Spring MVC) has no idea to handle the situation
			 * 	Missing Piece - Data Not Available, Server has no idea of handling the situation, hence HTTP 500.
			 * 
			 * 404 - We have a control (Developer), and we know how to handle the situation.
			 * 	Missing Piece - Data Not Available, we inform the Server how to handle the situation (via ResponseEntity)
			 */
			/* ------------------------------------------------------------ */
			// Straight forward and simple way 
			/* ------------------------------------------------------------ */
			//[NOT NEEDED] - return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMsg);
			//responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMsg);
			
			/* ------------------------------------------------------------ */
			/* Extended version with a custom Header - may be the timestamp */
			/* ------------------------------------------------------------ */
			responseEntity = ResponseEntity
								.status(HttpStatus.NOT_FOUND)
								.headers(headers)
								.body(errorMsg);
		}
		
		logger.info("responseEntity :: " + responseEntity);
		
		return responseEntity;
	}
}
