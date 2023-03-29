<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login Page</title>
		<link rel="icon" type="image/x-icon" href="<%=request.getContextPath() %>/images/favicon.ico">
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body class="d-flex flex-column h-100">
	<main class="flex-shrink-0">
	<div  class="container">
		<h1 style=color:#00BFFF;>Login Page</h1>
		<b>Server Date and Time : </b> <%=  LocalDateTime.now() %>
		<%
			String errorMessage = (String) request.getAttribute("errorMessage");
		
			if(null!=errorMessage)
			{
		%>
				<font color=red>${errorMessage}</font>
		<%
			}
		%>
		<div class="d-flex justify-content-center">	
		<div class="col-12">
		<form name="loginFom" method="post" action="login">
			<table
				class="table table-striped table-hover table-bordered 
					table-responsive caption-top">
				<tbody>
				<tr style=color:#6B8E23;>
					<th>Field</th>
					<th>Value</th>
				</tr>
				<tr>
					<td style=color:#6B8E23;>UserName</td>
					<td>
						<input type="text" name="userName" id="userName" size=20 
							value="Java"/>
					</td>
				</tr>
				<tr>
					<td style=color:#6B8E23;>Password</td>
					<td>
						<input type="password" name="password" id="password" 
							size=20 value="Spring"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input class="btn btn-primary" type="Submit" name="Login" value="Login"/>
						<input  class="btn btn-secondary" type="reset" name="Reset" value="Cancel"/>
					</td>
				</tr>			
			</table>
		</form>
		</div>
		</div>
	</div>
	</main>
		
	</body>
</html>