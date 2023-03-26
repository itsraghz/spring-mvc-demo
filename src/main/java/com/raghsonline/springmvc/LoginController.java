package com.raghsonline.springmvc;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

//Through @Controller we indicate Spring that this class
//will now act as a receiving end (component) for all the
//associated requests (for login).
// Equivalent to a HttpServlet class.
@Controller
@SessionAttributes(names = {"name", "lastSuccessfulLogin"})
public class LoginController 
{
	/* 
	 * Create an instance of the LoginService at the Controller class level,
	 * so that it can be reused across all the other methods in the Controller
	 */
	//LoginService service = new LoginService();
	
	@Autowired //this will pick up an instance of LoginService and assign it
	LoginService service;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
	public String sayHello()
	{
		return "Hello Spring MVC";
	}
	
	/*
	 * A typical method equivalent to doGet() in a Servlet class.
	 * In a Servlet, the urlPattern was associated with a Class/Servlet level.
	 * Here in SpringMVC, the urlPattern can be associated with a method as well.
	 * You can have multiple methods in a Controller class each with a varying 
	 * urlPattern and method combination.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	//@ResponseBody //this method will send the response by itself directly 
	//equivalent to response.getWriter().println("..")
	public String showLogin()
	{
		System.out.println("LoginController, /login GET method invoked");
		//The absence of the @ResponseBody will treat this return value
		// as a view name, but the ViewResolver component of Spring MVC
		//will find and form the actual view name (file) to be rendered
		
		//Servlets - we do the following to redirect to a JSP page
		//1 - request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(req,resp);
		//2 - request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
		//We can separate out the common aspects of the path
		// "/WEB-INF/views/" -- prefix 
		// ".jsp" - suffix (used for the file extension typically)
		// what was unique ? - "welcome", "login" - the file name!
		
		// ViewResolver is a Bean that is configured and we can specify these values
		// prefix and suffix
		
		//where do we define this Bean? 
		//registration-servlet.xml file - contextConfigLocation
		return "login";
	}
	
	/*
	 * A right and better solution is to write a new method in the Controller
	 * that handles the same URL pattern (if at all), and associate it with 
	 * the  HTTP Post Method, to avoid the error 
	 * HTTP Status 405 - Request method 'POST' not supported
	 */
	// As Good as a doPost() method in the Servlet
	// but here it is a normal Java class annotated with @Controller 
	// instead of a subclass of HttpServlet
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleLogin(@RequestParam String userName, 
					@RequestParam String password, 
					Model model)
	{
		/*
		 * In Servlet, we use either "request.setAttribute(key, value)"
		 * OR "session.setAttribute(key, value)" to send the value
		 * to a JSP page (or a view). Preferably request.setAttribute for
		 * the one time data transfer. Session is preferred for the data
		 * to be used across many different views/JSP pages through out the
		 * Session window or duration.
		 */
		/*
		 * In Spring MVC, we use something called a Model object that is
		 * nothing but a Map to store the key,value pairs from the 
		 * Controller to the View(s). But we must receive a Map parameter
		 * as a Request in the Method, only then Spring MVC will supply one.
		 */
		
		/*if(userName.equals("Java") && password.equals("Spring"))
		{
			model.addAttribute("name", userName);
			return "welcome";
		} else {
			model.addAttribute("errorMessage", "Invalid Credentials");
			return "login";
		}*/
		
		/* Now it is a reusable logic , than being hard coded inside a method */
		boolean isValidUser = service.isValidUser(userName, password);
		
		if(isValidUser)
		{
			model.addAttribute("name", userName);
			model.addAttribute("lastSuccessfulLogin", new Date());
			return "../../index";
		} else {
			model.addAttribute("errorMessage", "Invalid Credentials");
			return "login";
		}
		
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model)
	{
		System.out.println("LogoutController Invoked...!");
		model.addAttribute("message", "You have been logged out from the System");
		return "login";
	}
}
