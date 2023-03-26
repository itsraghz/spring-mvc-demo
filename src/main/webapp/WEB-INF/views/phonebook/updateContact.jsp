<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Phonebook | Contact - Update</title>
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div id="contacts" class="container">
			<h1>Phonebook - Update Contact</h1>
			<div id="greeting">
				Welcome, <b>${name}</b>
			</div>			
			<form:form method="POST" commandName="contact">
			  <div class="form-group row">
			    <form:label path="firstName" for="fName" class="col-sm-2 col-form-label">
			    	First Name
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="firstName" type="text" id="fName" name="firstName" 
						size="20" required="required"/>
					<form:errors path="firstName" cssClass="text-warning"></form:errors>
			    </div>
			  </div>
			  <div class="form-group row">
			    <form:label path="lastName" for="lName" class="col-sm-2 col-form-label">
			    	Last Name 
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="lastName" type="text" id="lName" name="lastName" 
						size="20" required="required"/>
					<form:errors path="lastName" cssClass="text-warning"></form:errors>
			    </div>
			  </div>			  		
				<fieldset id="dobField" name="dobField">
					<form:label path="dob" for="dob">Date of Birth : </form:label>
					<form:input path="dob" type="text" id="dob" name="dob" 
						size="10" required="required"/>	
					<form:errors path="dob" cssClass="text-warning"></form:errors>									
				</fieldset>	
				<fieldset id="contactNoField" name="contactNoField">
					<form:label path="contactNo" for="contactNo">Contact No : </form:label>
					<form:input path="contactNo" type="text" id="contactNo" name="contactNo" 
						size="10" required="required"/>
					<form:errors path="contactNo" cssClass="text-warning"></form:errors>				
				</fieldset>	
				<fieldset id="emailField" name="emailField">
					<form:label path="email" for="email">Email Address : </form:label>
					<form:input path="email" type="text" id="email" name="email" 
						size="20"/>	
					<form:errors path="email" cssClass="text-warning"></form:errors>			
				</fieldset>	
				<fieldset id="notesField" name="notesField">
					<form:label path="notes" for="notes">Notes : </form:label>
					<form:input path="notes" type="text" id="notes" name="notes" size="40" 
						placeholder="Any short notes to be added for the Contact"/>				
				</fieldset>	
				<fieldset id="tagField" name="tagField">
					<form:label path="tag" for="tag">Tag : </form:label>
					<form:input path="tag" type="text" id="tag" name="tag" size="20" 
						placeholder="Any meaningful short name to group/classify this Contact"/>				
				</fieldset>
				<!-- <button type="button" class="btn btn-primary">Add</button>	-->	
				<input class="btn btn-primary" type="submit" value="Add"/>																	
			</form:form>
		</div>
		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</body>
</html>