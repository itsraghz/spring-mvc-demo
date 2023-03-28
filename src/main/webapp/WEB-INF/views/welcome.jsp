<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<h1>Home Page</h1>
	<div id="greeting">
		Welcome <b>${name}!</b>
	</div>
	<p>
		You may please access all the available contacts in your <b>PhoneBook</b>
		by clicking <a href="contacts">here</a>.
	</p>
</body>
</html>