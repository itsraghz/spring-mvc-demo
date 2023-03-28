package com.raghsonline.phonebook.repository;

import java.util.Optional;



public interface UserDAO<T> {
	
	public long getCount();
	
	public Optional<T> verifyUser(String s1, String s2);

}
