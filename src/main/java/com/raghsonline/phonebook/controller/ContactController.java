package com.raghsonline.phonebook.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.raghsonline.phonebook.exception.BusinessException;
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
@SessionAttributes("name")
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
							"2003-01-01", "1234567891", 
							"databinding@springmvc.com", "Spring MVC DataBinding", 
							"Java, Spring");
		
		/* Purposefully added this to demonstrate the validation failure */
		//contact = new Contact();
		
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
	public String addContact(@Valid Contact contact, BindingResult result, 
				RedirectAttributes redirectAttrs)
	{
		logger.info("addContact() invoked");
		
		/* Validation on the Duplicate contact */

		
		if(result.hasErrors())
		{
			logger.error("Errors while binding the data values");
			
			logger.error("Printing all the ObjectErrros in hasErrors()");
			logger.error("---------------------------------------------");
			result.getAllErrors().stream().forEach(logger::error);
			logger.error("---------------------------------------------");
			
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
		/* Q: But, what will be the value of the id field? */
		//Contact contact = new Contact(0, firstName, lastName, dob, contactNo, email, notes, tag);
		
		logger.info("Contact obj prepared from the REQ parameters via Spring MVC : " + contact);
		
		int id = -1;
		
		try {
			id = contactService.addContact(contact);
		} catch (BusinessException businessException) {
			handleBusinessException(contact, result, businessException);
			return "phonebook/addContact";
		}
		
		logger.info("ID of the newly added Contact Object : " + id);
		
		/* 
		 * Problem:
		 * ------- 
		 * Simply adding this message into `model` gets this appended as a Query String on the URL
		 * when it is redirecting the control flow and NOT really getting captured in the target JSP
		 * 
		 * Ex: http://localhost:8080/spring-mvc-demo/contacts?message=Contact+added+successfully%21
		 */
		//model.addAttribute("message", "Contact added successfully!");
		
		/*
		 * Solution: 
		 * ---------
		 * Use org.springframework.web.servlet.mvc.support.RedirectAttributes which indeed extends
		 * the Model but designed explicitly for the redirect scenarios.
		 * 
		 * Reference :
		 * ---------
		 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/mvc/support/RedirectAttributes.html
		 */
		//redirectAttrs.addAttribute("id", contact.getId()).addFlashAttribute("message", "Contact added successfully!");
		redirectAttrs.addFlashAttribute("message", "Contact added successfully!");
		
		return "redirect:/contacts";
	}
	
	/**
	 * <p>
	 * A method to receive each attribute of a Control individually from the HTML Form
	 * and add a Contact into the List.
	 * </p>
	 * <p>
	 * Note: Not very up to date, as the focus has been given on the other variant of 
	 * the addContact() method which receives the Contact object as a <tt>CommandBean</tt>
	 * to receive all the attributes in a single Object, that is most preferred and convenient.
	 * </p>
	 * @param firstName
	 * @param lastName
	 * @param dob
	 * @param contactNo
	 * @param email
	 * @param notes
	 * @param tag
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "add-contact-separate-params", method = RequestMethod.POST)
	public String addContact(@RequestParam(required = true) String firstName, @RequestParam(required = true)  String lastName, 
				@RequestParam(required = true)  String dob, @RequestParam(required = true)  String contactNo, 
				@RequestParam(required = true)  String email, @RequestParam(required = false)  String notes,
				@RequestParam(required = false)  String tag, ModelMap model)
	{
		logger.info("addContact() invoked");
		
		Contact contact = new Contact(0, firstName, lastName, dob, contactNo, email, notes, tag);
		
		logger.info("Contact Object prepared from the request parameters : " + contact);
		
		int id = -1;
		
		try {
			id = contactService.addContact(contact);
		} catch (BusinessException businessException) {
			logger.error("BusinessException occurred while adding a Contact");
			logger.error("Error Messsage : " + businessException.getMessage());
			//TODO:	Should make this conditional depends on the AppMode flag when we have it.
			businessException.printStackTrace();
			
			//result.addError(new ObjectError("contactNo", businessException.getMessage()));
			return "phonebook/addContact";
		}
		
		logger.info("ID of the newly added Contact Object : " + id);
		
		/* The below steps are the repetition of what is present in the getAllContacts() method */
		/*model.addAttribute("contacts", contactService.getAllContacts());
		return "phonebook/contacts";*/
		
		return "redirect:/contacts";
	}
	
	@RequestMapping(value = "contact", method = RequestMethod.GET)
	public String getContact(@RequestParam int id, ModelMap model)
	{
		logger.info("getContact() invoked, with the requestParam ID : " + id);
		
		Optional<Contact> contact = contactService.getContactById(id);
		
		if(contact.isPresent())
		{
			model.addAttribute("contact", contact.get());
		} else {
			logger.error("No matching contact available");
		}
		
		return "phonebook/viewContact";
	}
	
	@RequestMapping(value = "contact/{id}", method = RequestMethod.GET)
	public String getContactPathParam(@PathVariable(value = "id") int id, ModelMap model)
	{
		logger.info("getContactPathParam() invoked, with the pathParam ID : " + id);
		
		Optional<Contact> contact = contactService.getContactById(id);
		
		if(contact.isPresent())
		{
			model.addAttribute("contact", contact.get());
		}
		
		return "phonebook/viewContact";
	}
	
	@RequestMapping(value = "update-contact", method=RequestMethod.GET)
	public String updateContact(@RequestParam int id , ModelMap model)
	{
		logger.info("updateContact() invoked, with the requestParam ID : " + id);
		
		Optional<Contact> contact = contactService.getContactById(id);
		
		if(contact.isPresent())
		{
			model.addAttribute("contact", contact.get());
		}
		
		/* We reuse the same addContact.jsp page here */
		return "phonebook/addContact";
	}

	@RequestMapping(value = "update-contact", method=RequestMethod.POST)
	public String updateContact(@Valid Contact contact, BindingResult result, ModelMap model)
	{
		logger.info("updateContact() POST invoked, with the requestParam contact : " + contact);
		
		if(result.hasErrors())
		{
			return "phonebook/addContact"; 
		}
		
		try {
			contactService.updateContact(contact);
		} catch (BusinessException businessException) {
			handleBusinessException(contact, result, businessException);
			return "phonebook/addContact";
		}
		
		
		model.clear();
		model.addAttribute("contact", contact);
		
		/* 
		 * Here we can use model itself to add a flash message, since we are 
		 * NOT using a redirection to a view (redirect:/), rather we are 
		 * returning the logical view name as a String value. 
		 */
		model.addAttribute("message", "Contact updated successfully!");
	
		return "phonebook/viewContact";
	}

	/**
	 * <p>
	 * A method to handle the <tt>BusinessException</tt> when occurs
	 * while performing Create or Update of a Contact.
	 * </p>
	 * @param contact the contact attempted to be added
	 * @param result the BindingResult object to throw the validation error
	 * @param businessException the actual exception that occurs
	 */
	private void handleBusinessException(Contact contact, 
			BindingResult result, 
			BusinessException businessException) 
	{
		logger.error("BusinessException occurred while adding/updating a Contact");
		logger.error("Error Messsage : " + businessException.getMessage());
		//TODO:	Should make this conditional depends on the AppMode flag when we have it.
		businessException.printStackTrace();
		
		logger.error("Printing all the ObjectErrros before adding a BusinessException");
		logger.error("--------------------------------------------------------------");
		result.getAllErrors().stream().forEach(logger::error);
		logger.error("--------------------------------------------------------------");
		
		/* ============================================= */
		/* Trial and Errors to display the error message */
		/* ==========================-================== */
		//result.addError(new ObjectError("contactNo", businessException.getMessage()));
		
		/*result.addError(new ObjectError("contact", 
						new String[] {"contactNoObject"}, 
						new Object[] {businessException.getMessage()},
						null));*/

		//result.addError(new FieldError("contact", "contactNo", businessException.getMessage()));
		/* ================================== */
		
		/** 
		 * Successful - as we are able to retain the input value by passing the `rejectedValue` 
		 */
		result.addError(new FieldError("contact", "contactNo", 
				contact.getContactNo(), true, null, 
				null, businessException.getMessage()));
		
		
		logger.error("Printing all the ObjectErrros after adding a BusinessException");
		logger.error("--------------------------------------------------------------");
		result.getAllErrors().stream().forEach(logger::error);
		logger.error("--------------------------------------------------------------");
	}
	
	@RequestMapping(value = "delete-contact", method=RequestMethod.GET)
	public String deleteContact(@RequestParam int id, RedirectAttributes redirectAttrs)
	{
		logger.info("deleteContact() - GET Request invoked");
		
		logger.info("Parameter Id :  " + id);
		
		boolean status = contactService.deleteContact(id);
		
		String message = "Contact was " + (status ? "" : "NOT") + " successfully deleted";
		
		/* if(status) {
			message = "Contact was successfully deleted";
		}else {
			message = "Contact was NOT successfully deleted";
		}*/
		
		redirectAttrs.addFlashAttribute("message", message);
		
		return "redirect:/contacts";
	}
	
}
