package com.raghsonline.phonebook.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.raghsonline.phonebook.model.Contact;
import com.raghsonline.phonebook.service.ContactService;

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
	/**
	 * <p>
	 * A logger instance to log the statements for troubleshooting
	 * </p>
	 */
	Logger logger = Logger.getLogger(ContactController.class);
	
	/** For the time being, we can create an instance a new operator */
	//ContactService contactService = new ContactServiceImpl();
	
	@Autowired
	ContactService contactService;
	
	@RequestMapping(value = "add-contact", method = RequestMethod.GET)
	public String showAddContactPage(ModelMap model )
	{
		logger.info("showAddContact() invoked...");
		
		/* 
		 * We will create a Contact object with some data, 
		 * and see how this object gets displayed in the UI page
		 */
		Contact contact = new Contact(-1, "Spring", "2 way Data Binding", 
							"2003-01-01", "76749123131", 
							"databinding@springmvc.com", "Sprnig MVC DataBiding", 
							"Java, Spring");
		model.addAttribute("contact", contact);
		
		return "phonebook/addContact";
	}
	
	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public String getAllContacts(ModelMap model)
	{
		logger.info("getAllContacts() invoked");
		
		/* Using the CRUD Method Overridden from the Service Interface */
		model.addAttribute("contacts", contactService.getAllContacts());
		
		return "phonebook/contacts";
	}
	
	/* 
	 * By passing the Domain object as a Parameter, we would request / expect
	 * Spring MVC to bind the request parameters supplied by the User
	 * (typically in a HTML form) to the corresponding attributes of this 
	 * Domain POJO class - Contact in our case.
	 * 
	 * But, how does Spring Framework get to know which input field in the
	 * HTML form to be bound to which attribute / field of the Java class? 
	 * 
	 * (1) Is it with the data type? (String firstName) - here 'String' 
	 * 			Not really. Because there may be many fields declared with the same data type.
	 * 			Ex. String firstName, String lastName etc., so Confusion! 
	 * 
	 * (2) Is it with the name? (<input name="firstName">) - here 'firstName'
	 * 			Yes, the best choice. 
	 * 			The user is enforced to declare the HTML inputs with the exact name 
	 * 			of the Java class attribute.
	 * 
	 * 			What if the user wants to have a different field name in the UI? 
	 * 			Nope. If so, he will not be able to get the value bound with the attribute.
	 * 			
	 * Solution: Spring has a form tag (form: - as prefix) and we use an attribute called 
	 * 			'path' which should have the same name as that of the Java field/attribute
	 * 			with which the Spring framework can easily bind the values.
	 */
	@RequestMapping(value = "add-contact", method = RequestMethod.POST)
	public String addContact(@Valid Contact contact, BindingResult result, ModelMap model)
	{
		logger.info("addContact() invoked");
		
		if(result.hasErrors())
		{
			logger.error("Errors while binding the data values");
			result.getAllErrors().stream().forEach(logger::error);
			
			/* We should return it to the same addContact page */
			/* 
			 * Q: Should/can we redirect to the URL /add-contact with GET! ? 
			 * A: Nope we shouldn't, because the add-contact creates a brand new Contact object,
			 * and we want the values supplied by the users to be retained when we send back
			 * the errors! Hence, we should just be returning to the same JSP page, than redirecting!
			 */
			return "phonebook/addContact";
		}

		
		/* Not needed, as Spring does the work for me :) */
		/* But, what will be the value of the id field? */
		//Contact contact = new Contact(0, firstName, lastName, dob, contactNo, email, notes, tag);
		
		logger.info("Contact obj prepared from the REQ parameters via Spring MVC : " + contact);
		
		int id = contactService.addContact(contact);
		
		logger.info("ID of the newly added Contact Object : " + id);
		
		return "redirect:/contacts";
	}
	
	@RequestMapping(value = "add-contact-separate-params", method = RequestMethod.POST)
	public String addContact(@RequestParam(required = true) String firstName, @RequestParam(required = true)  String lastName, 
				@RequestParam(required = true)  String dob, @RequestParam(required = true)  String contactNo, 
				@RequestParam(required = true)  String email, @RequestParam(required = false)  String notes,
				@RequestParam(required = false)  String tag, ModelMap model)
	{
		logger.info("addContact() invoked");
		
		Contact contact = new Contact(0, firstName, lastName, dob, contactNo, email, notes, tag);
		
		logger.info("Contact Object prepared from the request parameters : " + contact);
		
		int id = contactService.addContact(contact);
		
		logger.info("ID of the newly added Contact Object : " + id);
		
		/* The below steps are the repetition of what is present in the getAllContacts() method */
		/*model.addAttribute("contacts", contactService.getAllContacts());
		return "phonebook/contacts";*/
		
		return "redirect:/contacts";
	}
}
