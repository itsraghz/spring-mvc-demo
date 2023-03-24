<%@page import="com.raghsonline.phonebook.model.Contact"%>
<%@include file="../inc/header.jsp"%>
			<h1 style="color:Blue;">Phonebook - View Contact</h1>
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
					<table class="table table-striped table-hover table-bordered 
						table-responsive caption-top">
						<caption style="color:grey;">View Contact</caption>
						<thead style="color:#8B008B;">
							<tr>	
								<th>Field</th>
								<th>Value</th>
							</tr>
						</thead>
						<tbody class="table-group-divider" style="color:#0202ff;">
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
<%@include file="../inc/footer.jsp" %>