<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login Page</title>
	</head>
	<body>
		<h1>Login Page</h1>
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
		<form name="loginFom" method="post" action="login">
			<table border=1>
				<tr>
					<th>Field</th>
					<th>Value</th>
				</tr>
				<tr>
					<td>UserName</td>
					<td>
						<input type="text" name="userName" id="userName" size=20 
							value="Java"/>
					</td>
				</tr>
				<tr>
					<td>Password</td>
					<td>
						<input type="password" name="password" id="password" 
							size=20 value="Spring"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="Submit" name="Login" value="Login"/>
						<input type="reset" name="Reset" value="Cancel"/>
					</td>
				</tr>			
			</table>
		</form>
	</body>
</html>