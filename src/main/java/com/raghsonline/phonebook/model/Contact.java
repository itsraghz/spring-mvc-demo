package com.raghsonline.phonebook.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * <p>
 * A Domain class to represent a Contact in a Phonebook.
 * </p>
 * 
 * @author raghavan.muthu
 *
 */
public class Contact 
{
	/**
	 * <p>An unique identifier to track the contact</p>
	 */
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	/**
	 * <p>
	 * To keep it simple, we can store the DOB 
	 * as a String in YYYY-MM-DD format.
	 * </p>
	 */
	private String dob;
	
	private String contactNo;
	
	private String email;
	
	/**
	 * <p>A short notes associated with a contact. </p>
	 */
	private String notes;

	/**
	 * <p>
	 * A tag used to classify the contact. 
	 * Example: Office, Family, Relatives, College etc., 
	 * </p>
	 */
	private String tag;
}
