package com.raghsonline.phonebook.repository.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import com.raghsonline.phonebook.model.User;
import com.raghsonline.phonebook.repository.UserDAO;

public class TestUserDAOImpl {
	
	static Logger logger = Logger.getLogger(TestUserDAOImpl.class);
	
	static UserDAO<User> userDAO;
	
	@Test
	@DisplayName("A method to verify Login credentials from DB")
	public void verifyUser()
	{
		
		Optional<User> optionalUser = userDAO.verifyUser("Java","Spring");
		logger.info("optionalUser is "+optionalUser);
		
		boolean isValidUser = false;
		if(optionalUser.isPresent())
		{
			User user = optionalUser.get();
			logger.info("User object from database" + user);
			assertNotNull(user);
			isValidUser = true;
		}
		
		assertTrue(isValidUser);
		
	}

}
