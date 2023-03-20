package com.raghsonline.jee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/LoginServlet" })
public class LoginServlet extends HttpServlet 

{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) 
	throws ServletException, IOException 
	{
		System.out.println("LoginServlet - doGet() invoked");
		
		request.getRequestDispatcher("/WEB-INF/views/login.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
				HttpServletResponse response) 
	throws ServletException, IOException 
	{
		System.out.println("LoginServlet - doPost() invoked");
		
		/* Just for the initial sanity */
		//PrintWriter out = response.getWriter();
		//out.println("Login request received!");
		
		String userName = (String) request.getParameter("userName");
		String password = (String) request.getParameter("password");
		
		if(userName.equals("Java") && password.equals("Spring"))
		{
			request.setAttribute("name", userName);
			request.getRequestDispatcher("/WEB-INF/views/welcome.jsp")
				.forward(request, response);
		}
		else
		{
			request.setAttribute("errorMessage", "Invalid Credentials");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp")
				.forward(request, response);
		}
	}

}
