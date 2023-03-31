package com.raghsonline.phonebook.service.impl;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raghsonline.phonebook.exception.BusinessException;
import com.raghsonline.phonebook.model.UserLogin;
import com.raghsonline.phonebook.repository.LoginDAO;
import com.raghsonline.phonebook.service.LoginUserService;

@Service
public class LoginUserServiceImpl implements LoginUserService
{
	static Logger logger = Logger.getLogger(LoginUserServiceImpl.class);
	
	@Autowired
	LoginDAO<UserLogin> loginDAOImpl;
	
	public LoginUserServiceImpl()
	{
		//System.out.println("LoginService1 instantiated...");
		logger.info("LoginUserServiceImpl instantiated...");
	}
	
	public boolean isValidUser(String userName, String password)
	{
		
		logger.info("isValidUser() invoked...");
		boolean isValidUser = false;
		Optional<UserLogin> user = Optional.empty();
		user = loginDAOImpl.verifyUser(userName,password);
		if(user.isPresent())
		{
			isValidUser = true;
		}

		return isValidUser;
	}
	
	@Override
	public long addUser(UserLogin userLogin) throws BusinessException 
	{
		logger.debug("UserLogin() invoked");
		
		logger.info("User Object received : " + userLogin);
		
		if(isUserDuplicate(userLogin)) 
		{
			String errorMsg = "UserName already exists! Try With Different UserName.";
			logger.error(errorMsg);
			throw new BusinessException(errorMsg);
		}
		
		long newlyInsertedId = loginDAOImpl.create(userLogin);
		logger.info("newlyInseredId : " + newlyInsertedId);
		return newlyInsertedId;
	}
	
	
	public boolean isUserDuplicate(UserLogin userLogin)
	{
		logger.info("isUserDuplicate() invoked, user=" + userLogin);
		
		boolean isDuplicate = false;
		
		Optional<UserLogin> optionalUser = loginDAOImpl.getByUserName(userLogin.getUserName());
		isDuplicate = optionalUser.isPresent();
		
		logger.info("isDuplicate ? " + isDuplicate);
		
		return isDuplicate;
		
	}
}
