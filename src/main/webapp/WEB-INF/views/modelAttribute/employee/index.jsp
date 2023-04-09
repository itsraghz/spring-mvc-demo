<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ModelAttribute Demo</title>
	</head>
	<body>
		<h1>ModelAttribute - Demo</h1>
		<h2>Common ModelAttribute</h2>
		<div>
			<b>Common Attribute : </b> ${commonAttribute}
		</div>
		<h2>Operations</h2>
		<div>
			<ul>
				<li>
					Click <a href="/spring-mvc-demo/modelAttributeDemo/addEmployee">here</a> to add an Employee
				</li>
				<li>
					Click <a href="/spring-mvc-demo/modelAttributeDemo/showEmployees">here</a> to list all the Employee
				</li>								
			</ul>
		</div>
	</body>
</html>