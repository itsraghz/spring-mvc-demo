package com.raghsonline.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PathTestController 
{
	@RequestMapping(value = "/pathTest", method = RequestMethod.GET)
	public String pathTest(@RequestParam(required = false, defaultValue = "employee") String path)
	{
		System.out.println("PathTestController - pathTest GET method invoked");
		
		if(null!=path && path.equalsIgnoreCase("manager")) {
			return "manager/home"; /*Path -> /WEB-INF/views/manager/home.jsp */
		} else {
			return "employee/home";/*Path -> /WEB-INF/views/employee/home.jsp */
		}
	}

}
