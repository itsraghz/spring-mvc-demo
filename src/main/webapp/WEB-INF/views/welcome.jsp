<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@include file="/WEB-INF/views/phonebook/inc/header.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="icon" type="image/x-icon" href="<%=request.getContextPath() %>/images/favicon.ico">
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
		<title>Home Page</title>
	</head>
	<body>
		<h1 style=color:#00BFFF;>Home Page</h1>
		<div id="greeting">
			Welcome <b>${name}!</b>
		</div>
		<p style=color:#6B8E23;>
			You may please access all the avialable contacts by clicking <a href="contacts">here</a>.
		</p>
	</body>
</html>
<%@include file="/WEB-INF/views/phonebook/inc/footer.jsp" %>