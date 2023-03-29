package com.raghsonline.springmvc;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.raghsonline.phonebook.model.User;
import com.raghsonline.phonebook.model.Contact;
import com.raghsonline.phonebook.repository.DAO;
import com.raghsonline.phonebook.repository.UserDAO;


@Service
public class LoginService 
{
	@Autowired
	DAO<Contact> contactDAO;

	@Autowired
	UserDAO<User> userDAOImpl;
	
	Logger logger = Logger.getLogger(LoginService.class);
	
	/* Add a no-arg constructor to verify the Spring Container 
	 * instantiates this class */
	public LoginService()
	{
		
		//System.out.println("LoginService instantiated...");
		logger.info("LoginService instantiated...");
	}
	
	public boolean isValidUser(String userName, String password)
	{
		//Just for the sake of testing, but this is NOT a recommended approach
		//In real time, we should be testing the credentials against the DB.
		//return userName.equals("Java") && password.equals("Spring");
		logger.info("isValidUser() invoked.");
		boolean isValidUser = false;
		Optional<User> optionalUser = Optional.empty();
		optionalUser = userDAOImpl.verifyUser(userName,password);
		if(optionalUser.isPresent())
		{
			isValidUser = true;
		}

		return isValidUser;
	}
	}

