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
		<%
		String name = (String) request.getAttribute("name");
		if (null != name) {
		%>
		<h2>
			<span style="color: #008000">Welcome ${name}!</span>
		</h2>
		<%
		}
		%>
		<h1>
			<span style="color: #43302e"><b> PhoneBook - Contacts </b></span>
		</h1>
		<%
		Object messageObj = request.getAttribute("message");

		String message = (null != messageObj) ? (String) messageObj : null;

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

		<p>
			<span style="color: #43302e"><b>Please find all the
					contacts in your Phonebook.</b> </span>
		</p>
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
					<th class="text-center"><b>Id</b></th>
					<th class="text-center"><b>FirstName</b></th>
					<th class="text-center"><b>LastName</b></th>
					<th class="text-center"><b>DOB</b></th>
					<th class="text-center"><b>Contact No</b></th>
					<th class="text-center"><b>Email</b></th>
					<th class="text-center"><b>Notes</b></th>
					<th class="text-center"><b>Tag</b></th>
					<th class="text-center"><b>Action</b></th>
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
			<a href="add-contact" class="btn btn-primary" role="button">Add
				Contact</a>
		</div>
	</div>
	<%@include file="/WEB-INF/views/phonebook/inc/footer.jsp"%>
</body>
</html>