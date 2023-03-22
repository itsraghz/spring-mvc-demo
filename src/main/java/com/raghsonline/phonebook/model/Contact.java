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
	
	@Size(min = 4, message = "First Name should have a min of 4 characters")
	private String firstName;
	
	@Size(min = 2, message = "Last Name should have a min of 2 characters")
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
	@Size(min = 10, max = 10, message = "Contact Number must have exactly 10 digits")
	private String contactNo;
	
	//@NotBlank(message = "Email should NOT be blank")
	@Email(message= "Please enter a valid email address")
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
