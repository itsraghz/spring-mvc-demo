	package com.raghsonline.phonebook.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * A Domain class to represent a Login for a Phonebook Application.
 * </p>
 * 
 * @author arun.gubbala
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLogin 
{
	private int id;
	
	@NotBlank(message = "UserName should NOT be blank")
	@Size(min=6, message = "UserName Should Contain Minimum of 6 Characters.")
	private String userName;
	
	@NotBlank(message = "Password should NOT be blank")
	@Size(min=6, message = "Password Should Contain Minimum of 6 Characters.")
	private String password;

}
