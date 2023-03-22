<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="inc/header.jsp"%>
			<h1 style="color:#8B008B;">Phonebook - Add Contact</h1>
			<form:form method="POST" commandName="contact">
			  <div class="form-group row">
			    <form:label path="firstName" for="fName" class="col-sm-2 col-form-label" style="color:#00008B;">
			    	First Name <span class="text-warning" style="color:red">*</span>
			    </form:label>			    
			    <div class="col-sm-10">
			      	<form:input path="firstName" type="text" id="fName" name="firstName" 
						size="20" required="required" style="color:#006400"/>
					<form:errors path="firstName" cssClass="text-warning" style="color:red"></form:errors>
			    </div>
			  </div>
			  <div class="form-group row">
			    <form:label path="lastName" for="lName" class="col-sm-2 col-form-label" style="color:#00008B;">
			    	Last Name <span class="text-warning" style="color:red">*</span> 
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="lastName" type="text" id="lName" name="lastName" 
						size="20" required="required" style="color:#006400"/>
					<form:errors path="lastName" cssClass="text-warning" style="color:red"></form:errors>
			    </div>
			  </div>			  		
				<div class="form-group row">
			    <form:label path="dob" for="dob" class="col-sm-2 col-form-label" style="color:#00008B;">
			    	Date Of Birth <span class="text-warning" style="color:red">*</span>
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="dob" type="text" id="dob" name="dob" 
						size="20" required="required" style="color:#006400"/>
					<form:errors path="dob" cssClass="text-warning" style="color:red"></form:errors>
			    </div>
			  </div>
				<div class="form-group row">
			    <form:label path="contactNo" for="contactNo" class="col-sm-2 col-form-label" style="color:#00008B;">
			    	Contact Number <span class="text-warning" style="color:red">*</span>
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="contactNo" type="text" id="contactNo" name="contactNo" 
						size="20" required="required" style="color:#006400"/>
					<form:errors path="contactNo" cssClass="text-warning" style="color:red"></form:errors>
			    </div>
			  </div>
				<div class="form-group row">
			    <form:label path="email" for="email" class="col-sm-2 col-form-label" style="color:#00008B;">
			    	Email Id 
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="email" type="text" id="email" name="email" 
						size="25" required="required" style="color:#006400"/>
					<form:errors path="email" cssClass="text-warning" style="color:red"></form:errors>
			    </div>
			  </div>
				<div class="form-group row">
			    <form:label path="notes" for="notes" class="col-sm-2 col-form-label" style="color:#00008B;">
			    	Notes 
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="notes" type="text" id="notes" name="notes" 
						size="20" style="color:#006400"/>
					<form:errors path="notes" cssClass="text-warning" style="color:red"></form:errors>
			    </div>
			  </div>
				<div class="form-group row">
			    <form:label path="tag" for="tag" class="col-sm-2 col-form-label" style="color:#00008B;">
			    	Tag 
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="tag" type="text" id="tag" name="tag" 
						size="20" style="color:#006400"/>
					<form:errors path="tag" cssClass="text-warning" style="color:red"></form:errors>
			    </div>
			  </div>
				<!-- <button type="button" class="btn btn-primary">Add</button>	-->	
				<input class="btn btn-primary" type="submit" value="Add"/>																	
			</form:form>
<%@include file="inc/footer.jsp" %>