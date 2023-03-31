package com.raghsonline.phonebook.repository;

import java.util.Optional;

/**
 * <p>
 * A common interface for all the Data Access Operations (DAO)
 * in the Phonebook Application for Login.
 * <p>
 */

public interface LoginDAO<L>
{
	/**
	 * <p>
	 * A method to create a new entry in the Database mapped to the attributes
	 * of the Domain Object being passed <tt>L</tt>.
	 * </p> 
	 * @param l the type of the object , instance of type <tt>L</tt>
	 * @return a long value representing the newly created sequence number (auto-generated)
	 */
	public long create(L l);
	
	/**
	 * <p>
	 * A method to get the matching instance of the Domain object of type <tt>L</tt>,
	 * based on the <tt>UserName</tt> value passed, which is the unique key in the 
	 * Database Table / Repository (in general).
	 * </p>
	 * @param userName the userName of the row for finding a match
	 * @return the matching domain object if available, empty otherwise.
	 */
	public Optional<L> verifyUser(String userName , String Password);
	
	public Optional<L> getByUserName(String userName);

}
