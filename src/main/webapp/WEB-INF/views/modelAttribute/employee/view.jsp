<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Employee | View</title>
	</head>
	<body>
		<h1>ModelAttribute Demo - Employee View</h1>
		<h2>Employee Data</h2>
		<div>
			<ul>
				<li>
					<b>Name : </b> ${name}
				</li>
				<li>
					<b>Id : </b> ${id}
				</li>
			</ul>
			<h3>Add Employee</h3>
			Click <a href="/spring-mvc-demo/modelAttributeDemo/addEmployee">here</a> to add another Employee!
			<h3>List All Employees</h3>
			Click <a href="/spring-mvc-demo/modelAttributeDemo/showEmployees">here</a> to list all the employees
		</div>
		<h3>Common ModelAttribute</h3>
		<div>
			<b>Common Attribute : </b> ${commonAttribute}
		</div>		
	</body>
</html>