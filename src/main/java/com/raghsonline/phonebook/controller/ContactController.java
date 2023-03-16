package com.raghsonline.phonebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.raghsonline.phonebook.service.impl.ContactServiceImpl;

/**
 * <p>
 * A (web) Controller class acting as a central component
 * to handle all the requests associated with a Contact
 * in a Phonebook Application.
 * </p>
 * @author raghavan.muthu
 * @since 2023-16-16
 *
 */
@Controller
public class ContactController 
{
	/** For the time being, we can create an instance a new operator */
	//ContactServiceImpl contactService = new ContactServiceImpl();
	
	@Autowired
	ContactServiceImpl contactService;
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public String getAllContacts(ModelMap model)
	{
		/* Using the CRUD Method Overridden from the Service Interface */
		model.addAttribute("contacts", contactService.getAllContacts());
		
		return "phonebook/contacts";
	}
}
