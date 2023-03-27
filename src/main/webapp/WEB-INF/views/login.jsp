<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="d-flex flex-column h-100">
	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">
			<h1>Login Page</h1>
			<b>Server Date and Time : </b>
			<%=LocalDateTime.now()%>
			<%
			String errorMessage = (String) request.getAttribute("errorMessage");
			String message = (String) request.getAttribute("message");

			if (null != errorMessage) {
			%>
			<div class="row">
				<div class="col-12" align="center">
					<div class="alert alert-danger" role="alert">
						<%=errorMessage%>
					</div>
				</div>
			</div>
			<%
			}
			%>

			<%
			if (null != message) {
			%>
			<div class="row">
				<div class="col-12" align="center">
					<div class="alert alert-success" role="alert">
						<%=message%>
					</div>
				</div>
			</div>

			<%
			}
			%>
			<div class="d-flex justify-content-center">
				<div class="row">
					<div class="col-12">
						<form name="loginFom" method="post" action="login">
							<table
								class="table table-striped table-hover table-bordered 
						table-responsive caption-top">
								<caption style="color: #43302e">
									<b> Please fill in the details to login</b>
								</caption>
								<tr>
									<th>Field</th>
									<th>Value</th>
								</tr>
								<tr>
									<td>UserName</td>
									<td><input type="text" name="userName" id="userName"
										size=20 value="Java" /></td>
								</tr>
								<tr>
									<td>Password</td>
									<td><input type="password" name="password" id="password"
										size=20 value="Spring" /></td>
								</tr>
								<tr>
									<td colspan="2"><input
										class="btn btn-primary" type="submit" value="Login" /> <input
										class="btn btn-danger" type="reset" value="Reset" /></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
</html>