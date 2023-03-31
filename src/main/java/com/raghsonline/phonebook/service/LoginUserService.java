package com.raghsonline.phonebook.service;

import com.raghsonline.phonebook.exception.BusinessException;
import com.raghsonline.phonebook.model.UserLogin;

public interface LoginUserService 
{
	
	public long addUser(UserLogin login) throws BusinessException;
	
	public boolean isValidUser(String userName, String password);
}
