package com.raghsonline.phonebook.controller;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	@ResponseBody()
	public List<Contact> getAllContacts()
	{
		logger.info("/api/contacts - invoked");
		//response.setContentType("application/json");
		return contactService.getAllContacts();
	}
	
	@GetMapping(value = "/api/contacts/Optional/{id}")//, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody()
	public Optional<Contact> getContactByIdOptional(@PathVariable long id)
	{
		logger.info("/api/contacts/Optional/{id} - invoked, id="+id);
		//response.setContentType("application/json");
		return contactService.getContactById(id);
	}
	
	@GetMapping(value = "/api/contacts/{id}")//, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody()
	public Contact getContactById(@PathVariable long id)
	{
		logger.info("/api/contacts/{id} - invoked, id="+id);
		//response.setContentType("application/json");
		return contactService.getContactById(id).get();
	}
	
}
