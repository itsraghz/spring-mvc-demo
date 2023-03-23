<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@include file="/WEB-INF/views/phonebook/inc/header.jsp" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Phonebook | Contact - Add</title>
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<div id="contacts" class="container">
			<h1 style=color:Black;>Phonebook - Add Contact</h1>
			<form:form method="POST" commandName="contact">
			  <div class="form-group row">
			    <form:label path="firstName" for="fName" class="col-sm-2 col-form-label">
			    	First Name <span style=color:red; class="required">*</span>
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="firstName" type="text" id="fName" name="firstName" 
						size="20" required="required"/>
					<form:errors path="firstName" cssClass="text-warning"></form:errors>
			    </div>
			  </div>
			  <div class="form-group row">
			    <form:label path="lastName" for="lName" class="col-sm-2 col-form-label">
			    	Last Name <span style=color:red; class="required">*</span>
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="lastName" type="text" id="lName" name="lastName" 
						size="20" required="required"/>
					<form:errors path="lastName" cssClass="text-warning"></form:errors>
			    </div>
			 </div>
			  <div class="form-group row">
			    <form:label path="dob" for="dob" class="col-sm-2 col-form-label">
			    	Date of Birth  <span style=color:red; class="required">*</span>
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="dob" type="text" id="dob" name="dob" 
						size="10" required="required"/>
					<form:errors path="dob" cssClass="text-warning"></form:errors>
			    </div>
			     </div>
			    <div class="form-group row">
			    <form:label path="contactNo" for="contactNo" class="col-sm-2 col-form-label">
			    	Contact No  <span style=color:red; class="required">*</span>
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="contactNo" type="text" id="contactNo" name="contactNo" 
						size="10" required="required"/>
					<form:errors path="contactNo" cssClass="text-warning"></form:errors>
			    </div>
			 </div>
			    <div class="form-group row">
			    <form:label path="email" for="email" class="col-sm-2 col-form-label">
			    	Email Address
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="email" type="text" id="email" name="email" 
						size="20" required="required"/>
					<form:errors path="email" cssClass="text-warning"></form:errors>
			    </div>
				 </div>
			    <div class="form-group row">
			    <form:label path="email" for="email" class="col-sm-2 col-form-label">
			    	Notes
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="notes" type="text" id="notes" name="notes" 
						size="40" required="required"/>
					<form:errors path="notes" cssClass="text-warning"></form:errors>
			    </div>
				 </div>
			    <div class="form-group row">
			    <form:label path="tag" for="tag" class="col-sm-2 col-form-label">
			    	Tag
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="tag" type="text" id="tag" name="tag" 
						size="20" required="required"/>
					<form:errors path="tag" cssClass="text-warning"></form:errors>
			    </div>
			    </div>
				<!-- <button type="button" class="btn btn-primary">Add</button>	-->	
				<input class="btn btn-primary" type="submit" value="Add"/>																	
			</form:form>
		</div>
		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
		<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</body>
</html>
<%@include file="/WEB-INF/views/phonebook/inc/footer.jsp" %>

