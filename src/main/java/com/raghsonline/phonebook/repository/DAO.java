package com.raghsonline.phonebook.repository;

import java.util.List;
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
	 * @return a long value representing the newly created sequence number (auto-generated)
	 */
	public long create(T t); // [C]
	
	/**
	 * <p>
	 * A method to get the matching instance of the Domain object of type <tt>T</tt>,
	 * based on the <tt>Id</tt> value passed, which is the primary key in the 
	 * Database Table / Repository (in general).
	 * </p>
	 * @param id the id of the row for finding a match
	 * @return the matching domain object if available, empty otherwise.
	 */
	public Optional<T> getById(long id); // [R] (get == getById, ID is the PK)
	
	/**
	 * <p>
	 * A method to get the matching instance of the Domain object of type <tt>T</tt>,
	 * based on the <tt>ContactNo</tt> value passed, which is the unique key in the 
	 * Database Table / Repository (in general).
	 * </p>
	 * @param contactNo the contactNo of the row for finding a match
	 * @return the matching domain object if available, empty otherwise.
	 */
	public Optional<T> getByContactNo(String contactNo); // [R]
	
	public long update(T t); // [U]
	
	public long deleteById(int id); //[D] - most popular
	
	public boolean delete(T t); // [D] - other flavor of Delete
	
	/**
	 * <p>
	 * A method to return the total number of entries present in the
	 * Table.
	 * </p>
	 * @return a long value indicating the count 
	 */
	public long getCount();
	
	/**
	 * <p>
	 * A method to fetch/retrieve all the available entries from the Database of type <tt>T</tt>
	 * </p>
	 * @return an instance of <tt>java.util.List</tt> containing all the entries.
	 */
	public List<T> getAll();

}
