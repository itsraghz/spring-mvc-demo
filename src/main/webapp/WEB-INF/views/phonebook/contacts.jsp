<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="java.util.List, com.raghsonline.phonebook.model.Contact, 
		org.springframework.ui.ModelMap"%>
<%@include file="/WEB-INF/views/phonebook/inc/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Phonebook | Contacts</title>
<link rel="icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/images/favicon.ico">
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div id="contacts" class="container">
		<h1>PhoneBook - Contacts</h1>
		<div id="greeting">
			<span style="color: #43302e"> Welcome, <b>${name}</b>. Your
				last successful login was at : <b>${lastSuccessfulLogin}</b></span>
		</div>

		<%
		Object messageObj = request.getAttribute("message");

		String message = (null != messageObj) ? (String) messageObj : null;

		if (null != message) {
		%>
		<br /> <br />
		<div class="row">
			<div class="col-12" align="center">
				<div class="alert alert-success" role="alert">
					<%=message%>
				</div>
			</div>
		</div>
		<%
		}

		/* See if the contacts is hving a valid data */
		Object contacts = request.getAttribute("contacts");
		%>
		<!--  For Debugging  -->
		<!--<c:out value="${contacts}"/>-->

		<%
		List<Contact> contactList = (ArrayList<Contact>) contacts;

		if (contactList.size() <= 0) {
		%>
		<!--<c:out value="There are NO records available to display"/> -->
		<br /> <br />
		<div id="message">
			<span style="background-color: yellow; color: red;"> There are
				NO records available to display. </span>
		</div>
		<br /> <br />
		<div>
			Please click <a href="add-contact">here</a> to add a new contact.
		</div>
		<%
		} else {
		%>
		<span style="color: #43302e"><b>Please find all the
				contacts in your Phonebook.</b> </span>
		<!--  Equivalent to out.println("Hello, JSTL") -->
		<!--<c:out value="Hello, JSTL"></c:out> -->
		<%
		/*List<Contact> contactList = (List<Contact>) request.getAttribute("contacts");

		out.println("contactList size : " + contactList.size());
		out.println("<br/>");
		out.println("===========================");
		out.println("<br/>");
		for(Contact contact : contactList)
		{
			out.println(contact);
			out.println("<br/>");
		}
		out.println("<br/>");
		out.println("===========================");
		*/
		%>
		<table
			class="table table-striped table-hover table-bordered 
							table-responsive caption-top">
			<caption style="color: #43302e">
				<b>View All Contacts</b>
			</caption>
			<thead>
				<tr style="color: #43302e">
					<th class="text-center">Id</th>
					<th class="text-center">FirstName</th>
					<th class="text-center">LastName</th>
					<th class="text-center">DOB</th>
					<th class="text-center">Contact No</th>
					<th class="text-center">Email</th>
					<th class="text-center">Notes</th>
					<th class="text-center">Tag</th>
					<th class="text-center">Action</th>
				</tr>
			</thead>
			<tbody class="table-group-divider">
				<c:forEach var="contact" items="${contacts}">
					<tr>
						<td>${contact.id}</td>
						<td>${contact.firstName}</td>
						<td>${contact.lastName}</td>
						<td>${contact.dob}</td>
						<td>${contact.contactNo}</td>
						<td>${contact.email}</td>
						<td>${contact.notes}</td>
						<td>${contact.tag}</td>
						<td><a href="contact?id=${contact.id}"
							class="btn btn-primary" role="button">View</a> <a
							href="update-contact?id=${contact.id}" class="btn btn-info"
							role="button">Update</a> <a
							href="delete-contact?id=${contact.id}" class="btn btn-danger"
							role="button">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<!-- Not a recommended approach. JSP to JSP it forwards -->
			<!--  Click <a href="addContact.jsp">here</a> to add a new Contact.-->

			<!--  
							Recommended Approach, we need the request to go the Server and
							it will redirect to the right JSP Page (MVC Approach). 
							
							See the value in href - it has the urlPattern than the actual .jsp file
					 -->
			<!-- Click <a href="add-contact">here</a> to add a new Contact.
					<button type="submit" class="btn btn-primary">Add</button> -->
			<a href="add-contact" class="btn btn-info" role="button">Add
				Contact</a>
		</div>
		<%
		}
		%>
	</div>
	<%@include file="/WEB-INF/views/phonebook/inc/footer.jsp"%>
</body>
</html>