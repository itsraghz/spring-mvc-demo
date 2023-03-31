<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login Page</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	    <!-- Custom styles for this template -->
    	<link rel="stylesheet" href="<%=request.getContextPath()%>/inc/sticky-footer-navbar.css"/>
	</head>
	<body class="d-flex flex-column h-100">
		<!-- Begin page content -->
		<main class="flex-shrink-0">
			<div class="container">
		<%
			String errorMessage = (String) request.getAttribute("errorMessage");
	    	String message = (String) request.getAttribute("message");

			if(null!=errorMessage)
			{
		%>
			<div class="row">
				<div class="col-12" align="center">
					<div class="alert alert-danger" role="alert">
						<%= errorMessage %>
					</div>
				</div>
			</div>	
		<%
			} else {
				if (null != message) 
			 	{
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
				<form name="loginFom" method="post" action="login">
					<table class="table table-striped table-hover table-bordered 
								  table-responsive caption-top">
						<tbody>
							<tr>
								<td>UserName</td>
								<td>
									<input type="text" class="form-control" 
										id="userName" name="userName" size="10"
										value="Spring"
										required size="20" >
								</td>
							</tr>
							<tr>
								<td>Password</td>
								<td>
									<input type="password" class="form-control" 
										id="password" name="password" size="15" 
										placeholder="password" maxlength="15"
										value="Spring">
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input class="btn btn-primary" type="submit" value="Login"/>
									<input class="btn btn-outline-danger" type="reset" value="Reset"/>																									
								</td>
							</tr>
						</tbody>
					</table>
					<b>Server Date and Time : </b> <%=  LocalDateTime.now() %>
				</form>
			</div>
		</div>
		click on <a href="add-user" class="btn btn-info" role="button">NewLogin</a> for registration.
	</div>

<%@include file="inc/footer.jsp" %>