package com.raghsonline.phonebook.model;

import java.util.Objects;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
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
	
	@NotBlank(message = "First Name should NOT be blank")
	@Size(min = 4, max=30, message = "First Name should have a min of 4 characters and "
			+ "max of 30 characters")
	private String firstName;
	
	@NotBlank(message = "Last Name should NOT be blank")
	@Size(min = 2, max =30, message = "Last Name should have a min of 2 characters"
			+ "and of max 30 characters")
	private String lastName;
	
	/**
	 * <p>
	 * To keep it simple, we can store the DOB 
	 * as a String in YYYY-MM-DD format.
	 * </p>
	 */
	@Size(max =10, message="Please enter your Date Of Birth in YYYY-MM-DD format")
	private String dob;
	
	@NotBlank(message = "Contact No. should NOT be blank")
	@Size(min = 10, max = 10, message = "Contact Number must exactly have 10 digits")
	private String contactNo;
	

	@Email(message = "Please provide a valid Email address")
	@Size(max =30, message="Email can have a maximum of 30 characters")
	private String email;
	
	/**
	 * <p>A short notes associated with a contact. </p>
	 */
	@Size(max =50, message="Notes can have a maximum of 50 characters")
	private String notes;

	/**
	 * <p>
	 * A tag used to classify the contact. 
	 * Example: Office, Family, Relatives, College etc., 
	 * </p>
	 */
	@Size(max =50, message="Tag can have a maximum of 50 characters")
	private String tag;
	
	@Override
	public int hashCode() 
	{
		System.out.println("Contact - hashCode() invoked, hashCode " + Objects.hash(id));
		//return Objects.hash(id);
		return this.id; /* simplest way */
	}

	@Override
	public boolean equals(Object obj)
	{
		System.out.println("Contact - equals invoked");
		System.out.println("Object : " + obj);
		
		if (this == obj) {
			System.out.println("....this==obj, returning true");
			return true;
		}
		if (obj == null) {
			System.out.println("....obj==null, returning false");
			return false;
		}
		if (getClass() != obj.getClass()) {
			System.out.println("....getClass()!=obj.getClass(), returning false");
			return false;
		}
		
		Contact other = (Contact) obj;
		System.out.println("....id==other.id ? " + (id==other.id));
		return id == other.id;
	}
}
