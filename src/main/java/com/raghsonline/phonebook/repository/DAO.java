package com.raghsonline.phonebook.repository;

import java.util.Optional;

/**
 * <p>
 * A common interface for all the Data Access Operations (DAO)
 * in the Phonebook Application.
 * <p>
 * <p>
 * Any feature/module can implement this DAO by supplying the Type, 
 * for example, <tt>ContactDAO</tt>, <tt>EventDAO</tt> etc., 
 * </p>
 * @author raghavan.muthu
 * @param <T> any valid Type (Java class) which is the Domain Object
 * @since 2023-03-23
 * @version 1.0
 */
public interface DAO<T> 
{
	/* Methods to define the contract for the CRUD funtionality. */
	/**
	 * <p>
	 * A method to create a new entry in the Database mapped to the attributes
	 * of the Domain Object being passed <tt>T</tt>.
	 * </p>
	 * 
	 * @param t the type of the object , instance of type <tt>T</tt>
	 * @return an integer representing the newly created sequence number (auto-generated)
	 */
	public int create(T t); // [C]
	
	/**
	 * <p>
	 * A method to get the matching instance of the Domain object of type <tt>T</tt>,
	 * based on the <tt>Id</tt> value passed, which is the unique key in the 
	 * Database Table / Repository (in general).
	 * </p>
	 * @param id the id of the row for finding a match
	 * @return the matching domain object if available, empty otherwise.
	 */
	public Optional<T> getById(int id); // [R] (get == getById, ID is the PK)
	
	public void update(T t); // [U]
	
	public boolean deleteById(int id); //[D] - most popular
	
	public boolean delete(T t); // [D] - other flavor of Delete

}
