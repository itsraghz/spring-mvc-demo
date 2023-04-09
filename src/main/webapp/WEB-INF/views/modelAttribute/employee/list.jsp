<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="com.raghsonline.phonebook.model.Employee"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Employee | View</title>
	</head>
	<body>
		<h1>ModelAttribute Demo - Employee List</h1>
		<h2>Common ModelAttribute</h2>
		<div>
			<b>Common Attribute : </b> ${commonAttribute}
		</div>	
		<h2>Employee Data</h2>
		<div>
			<%
				Map<Integer, Employee> employeeMap = null;
				Object employeeMapObj = request.getAttribute("employeeMap");
				int size = 0;
				
				if(null!=employeeMapObj)
				{
					employeeMap = (Map<Integer, Employee>) employeeMapObj;
					size = employeeMap.size();
				}
			%>
			EmployeeMap set in the request scope : <%= employeeMap %>, 
			with the size <b><%= size %></b>.
			<div>
				<h3>Employee Map Contents</h3>
				<ul>
					<%
						Set<Integer> keys = employeeMap.keySet();
						Iterator<Integer> iterator = keys.iterator();
						
						while(iterator.hasNext())
						{
							Integer key = iterator.next();
					%>
							<li>
								<%= employeeMap.get(key) %>
							</li>
					<% 
						}
					%>
				</ul>
			</div>
			<h3>Operations</h3>
			<div>
				<Ul>
					<li>
						Click <a href="/spring-mvc-demo/modelAttributeDemo/">here</a> to go to the Index Page!
					</li>
					<li>
						Click <a href="/spring-mvc-demo/modelAttributeDemo/addEmployee">here</a> to add another Employee!
					</li>
					<li>
						Click <a href="/spring-mvc-demo/modelAttributeDemo/showEmployees">here</a> to list all the employees
					</li>
				</ul>
			</div>
		</div>			
	</body>
</html>