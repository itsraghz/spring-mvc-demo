<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PhoneBook | Login Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/images/favicon.ico">
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">

</head>
<body class="d-flex flex-column h-100">
	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">
			<!--  Container Div Start -->
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
			} else {
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
			}
			%>
			<div class="d-flex justify-content-center">
				<div class="row">
					<div class="col-12">
						<h2>Login</h2>
					</div>
					<div class="col-12">
						<form id="loginForm" name="LoginForm" method="post" action="login">
							<table
								class="table table-striped table-hover table-bordered 
											table-responsive caption-top">
								<tbody>
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
										<td colspan="2" align="center"><input
											class="btn btn-primary" type="submit" value="Login" /> <input
											class="btn btn-danger" type="reset" value="Reset" /></td>
									</tr>

								</tbody>
							</table>
						</form>
					</div>
				</div>
			</div>
			<%@include file="/WEB-INF/views/phonebook/inc/footer.jsp"%>