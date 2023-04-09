package com.raghsonline.springmvc;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.raghsonline.phonebook.model.Employee;

@Controller
/* 
 * @ControllerAdvice - Needed to have a common ModelAttribute across all the models
 */
@ControllerAdvice 
@RequestMapping("/modelAttributeDemo")
public class ModelAttributeDemoController 
{
	Logger logger = Logger.getLogger(ModelAttributeDemoController.class);
	
	Map<Integer, Employee> employeeMap = new HashMap<>();
	
	@ModelAttribute
	public void addAttribute(Model model)
	{
		logger.info("@ModelAttribute - addAttribute()' invoked");
		System.out.println("@ModelAttribute - addAttribute()' invoked");
		
		model.addAttribute("commonAttribute", 
						"Common value added via @ModelAttribute at " 
						+ LocalDateTime.now().toString());
	}
	
	@RequestMapping
	public String showIndex()
	{
		logger.info("GET - '/modelAttributeDemo/' - showIndex() invoked");
		
		return "modelAttribute/employee/index"; 
	}
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	public String showAddEmployee(Model model)
	{
		logger.info("GET - '/modelAttributeDemo/addEmployee' invoked");
		
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		
		return "modelAttribute/employee/add";
	}
	
	@RequestMapping(value = "/addEmployee", method=RequestMethod.POST)
	public String addEmployee(@ModelAttribute("employee") Employee employee,
			BindingResult bindingResult, ModelMap modelMap)
	{
		logger.info("POST - '/modelAttributeDemo/addEmployee' invoked");
		
		if(bindingResult.hasErrors())
		{
			System.err.println("Validation Errors");
			return "modelAttribute/employee/add"; 
		}
		
		logger.info("ModelAttribute - Employee Object : " + employee);
		
		modelMap.addAttribute("name", employee.getName());
		modelMap.addAttribute("id", employee.getId());
		
		employeeMap.put(employee.getId(), employee);
		
		return "modelAttribute/employee/view";
	}
	
	@RequestMapping(value= "/showEmployees", method=RequestMethod.GET)
	public String showEmployees(ModelMap modelMap)
	{
		logger.info("GET - '/modelAttributeDemo/showEmployees' invoked");
		
		modelMap.addAttribute("employeeMap", employeeMap);
		
		return "modelAttribute/employee/list";
	}
}
