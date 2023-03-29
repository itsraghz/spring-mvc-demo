package com.raghsonline.phonebook.repository.impl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.raghsonline.phonebook.model.User;
import com.raghsonline.phonebook.repository.UserDAO;
public class TestUserDAO {
	static Logger logger = Logger.getLogger(TestUserDAO.class);

	static UserDAO<User> userDAO;

	@Test
    @DisplayName("Test getUserByUsernameAndPassword method")
    public void testGetUserByUsernameAndPassword() {

		logger.info("getUserByUsernameAndPassword method() invoked");
		
		Optional<User> optionalUser = userDAO.verifyUser("TestJava","TestSpring");
        logger.info("optionalUser is "+optionalUser);
		boolean isValidUser = false;
		if(optionalUser.isPresent())
		{
			User user = optionalUser.get();
			logger.info("User object from database" + user);
			assertNotNull(user);
			assertEquals("TestJava", user.getUserName());
			assertEquals("TestSpring", user.getPassword());
			isValidUser = true;
		}
}
}
