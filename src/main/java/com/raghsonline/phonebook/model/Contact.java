package com.raghsonline.phonebook.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
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
	
	@Size(min = 4, message = "First Name should have a min of 4 characters")
	private String firstName;
	
	@Size(min = 4, message = "Last Name should have a min of 4 characters")
	private String lastName;
	
	/**
	 * <p>
	 * To keep it simple, we can store the DOB 
	 * as a String in YYYY-MM-DD format.
	 * </p>
	 */
	@NotBlank(message = "Date of Birth should NOT be blank")
	private String dob;
	
	@NotBlank(message = "Contact No. should NOT be blank")
	private String contactNo;
	
	@NotBlank(message = "Email should NOT be blank")
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
