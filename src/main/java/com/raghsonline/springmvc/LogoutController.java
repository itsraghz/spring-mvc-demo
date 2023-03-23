package com.raghsonline.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;


@Controller
public class LogoutController {

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model)
	{
		System.out.println("LogoutController, /logout GET method invoked");
		model.addAttribute("message", "You have been logged out successfully!");
		return "login";
	}
}
