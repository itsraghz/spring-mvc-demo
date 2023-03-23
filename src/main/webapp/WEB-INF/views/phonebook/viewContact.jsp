<%@page import="com.raghsonline.phonebook.model.Contact"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/phonebook/inc/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Phonebook | Contact - View</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div id="contacts" class="container">
		<h1>
			<span style="color: #43302e"><b>Phonebook - View Contact </b></span>
		</h1>
		<%
		Object contactObj = request.getAttribute("contact");
		Object messageObj = request.getAttribute("message");

		String message = (null != messageObj) ? (String) messageObj : null;

		if (null == contactObj) {
		%>
		<font color=red>No contact available with the Id you supplied!</font>
		<%
		} else {

		Contact contact = null;

		if (null != contactObj) {
			contact = (Contact) contactObj;
		}

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
		<table
			class="table table-striped table-hover table-bordered 
						table-responsive caption-top">
			<caption style="color: #43302e">
				<b>View Contact</b>
			</caption>
			<thead>
				<tr style="color: #43302e">
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
		<a href="update-contact?id=${contact.id}" class="btn btn-info"
			role="button">Update</a>
		<a href="delete-contact?id=${contact.id}" class="btn btn-danger"
			role="button">Delete</a>
			 <a href="contacts" class="btn btn-primary">View
			All Contacts</a>
	</div>
	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<%@include file="/WEB-INF/views/phonebook/inc/footer.jsp"%>
</body>
</html>