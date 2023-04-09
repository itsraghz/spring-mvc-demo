<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home Page</title>
	</head>
	<body>
		<h1>Home Page</h1>
		<div id="greeting">
			Welcome <b>${name}!</b>
		</div>
		<p>
			You may please access all the avialable contacts by clicking <a href="contacts">here</a>.
		</p>
		<h2>Contacts</h2>
		<ul>
			<li>
				Click <a href="/spring-mvc-demo/add-contact">here</a> to add a Contact!
			</li>
			<li>
				Click <a href="/spring-mvc-demo/contacts">here</a> to list all the Contacts!
			</li>
		</ul>
		<h2>ModelAttribute Demo</h2>
		<ul>
			<li>
				Click <a href="/spring-mvc-demo/modelAttributeDemo/addEmployee">here</a> to add an Employee!
			</li>
			<li>
				Click <a href="/spring-mvc-demo/modelAttributeDemo/showEmployees">here</a> to list all the Employees!
			</li>
		</ul>
	</body>
</html>