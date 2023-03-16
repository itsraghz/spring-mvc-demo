<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import ="java.util.List, com.raghsonline.phonebook.model.Contact, 
		org.springframework.ui.ModelMap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<link href="webjars/bootstrap/4.6.2/css/bootstrap.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Phonebook | Contacts</title>
	</head>
	<body>
		<h1>PhoneBook - Contacts</h1>
		Please find all the contacts in your Phonebook.<br/>
		<!--  Equivalent to out.println("Hello, JSTL") -->
		<c:out value="Hello, JSTL"></c:out>
		
		<div id="contacts" class="container">
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
			<table class="table table-striped table-hover table-bordered 
						table-responsive caption-top">
				<caption>View All Contacts</caption>
				<thead>
					<tr>	
						<th>Id</th>
						<th>FirstName</th>
						<th>LastName</th>
						<th>DOB</th>
						<th>Contact No</th>
						<th>Email</th>
						<th>Notes</th>
						<th>Tag</th>
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
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>