<%@page import="com.raghsonline.phonebook.model.Contact"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Phonebook | Contact - View</title>
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div id="contacts" class="container">
			<h1>Phonebook - View Contact</h1>
			<div id="greeting">
				Welcome, <b>${name}</b>
			</div>			
			<%
				Object contactObj = request.getAttribute("contact");
				Object messageObj = request.getAttribute("message");
				
				String message = (null!=messageObj) ? (String) messageObj : null;
			
				if(null==contactObj)
				{
			%>
					<font color=red>No contact available with the Id you supplied!</font>
			<%
				} else {
		
					Contact contact = null;
				
					if(null!=contactObj)
					{
						contact = (Contact) contactObj;
					}
					
					if(null!=message) 
					{
			%>
						<span style="background-color: teal; color: white;">
							${message}
						</span>
			<% 	
					}
			%>										
					<table class="table table-striped table-hover table-bordered 
						table-responsive caption-top">
						<caption>View Contact</caption>
						<thead>
							<tr>	
								<th>Field</th>
								<th>Value</th>
							</tr>
						</thead>
						<tbody class="table-group-divider">
							<tr>
								<td>Id</td>
								<td>${contact.id}</td>
							</tr>
							<tr>
								<td>First Name</td>
								<td>${contact.firstName}</td>
							</tr>
							<tr>
								<td>Date of Birth</td>
								<td>${contact.dob}</td>
							</tr>							
							<tr>
								<td>Last Name</td>
								<td>${contact.lastName}</td>
							</tr>
							<tr>
								<td>Contact No</td>
								<td>${contact.contactNo}</td>
							</tr>
							<tr>
								<td>Email Address</td>
								<td>${contact.email}</td>
							</tr>
							<tr>
								<td>Notes</td>
								<td>${contact.notes}</td>
							</tr>
							<tr>
								<td>Tag</td>
								<td>${contact.tag}</td>
							</tr>																																										
						</tbody>
					</table>
			<%
				}
			%>
			<a href="contacts" class="btn btn-primary">View All Contacts</a>
		</div>
		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</body>
</html>