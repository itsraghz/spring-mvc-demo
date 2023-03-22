<%@page import ="java.util.List, com.raghsonline.phonebook.model.Contact, 
		org.springframework.ui.ModelMap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@include file="inc/header.jsp"%>

<h1 style="color:Blue;">PhoneBook - Contacts</h1>
		<%
			Object messageObj = request.getAttribute("message");
		
			String message = (null!=messageObj) ? (String) messageObj : null;
			
			if(null!=message) 
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
		%>	
				<p>
					Please find all the contacts in your Phonebook.
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
			<table class="table table-striped table-hover table-bordered 
						table-responsive caption-top">
				<caption style="color:Red;">View All Contacts</caption>
				<thead>
					<tr style="color:#8B008B;">	
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
						<tr style="color:blue;">
							<td>${contact.id}</td>
							<td>${contact.firstName}</td>
							<td>${contact.lastName}</td>
							<td>${contact.dob}</td>
							<td>${contact.contactNo}</td>
							<td>${contact.email}</td>
							<td>${contact.notes}</td>
							<td>${contact.tag}</td>
							<td>
								<a href="contact?id=${contact.id}" class="btn btn-info" role="button">View</a> &nbsp; | &nbsp;
								<a href="update-contact?id=${contact.id}" class="btn btn-info" role="button">Update</a> &nbsp; | &nbsp;
								<a href="delete-contact?id=${contact.id}" class="btn btn-danger" role="button">Delete</a>
							</td>
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
				<a href="add-contact" class="btn btn-info" role="button">Add Contact</a> 
			</div>
<%@include file="inc/footer.jsp" %>