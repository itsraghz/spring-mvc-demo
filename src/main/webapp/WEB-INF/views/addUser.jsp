<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Add Login Page</title>
	</head>
	<body>
	<h1 style="color:#8B008B;">New Login</h1>
			<form:form method="POST" commandName="userLogin">
			  <div class="form-group row">
			    <form:label path="userName" for="userName" class="col-sm-2 col-form-label" style="color:#00008B;">
			    	User Name <span class="text-warning" style="color:red">*</span>
			    </form:label>			    
			    <div class="col-sm-10">
			      	<form:input path="userName" type="text" id="userName" name="userName" 
						size="20" required="required" style="color:#006400"/>
					<form:errors path="userName" cssClass="text-warning" style="color:red"></form:errors>
			    </div>
			  </div>
			  <div class="form-group row">
			    <form:label path="password" for="password" class="col-sm-2 col-form-label" style="color:#00008B;">
			    	Password <span class="text-warning" style="color:red">*</span> 
			    </form:label>			    
			    <div class="col-sm-10">			      
			      	<form:input path="password" type="password" id="password" name="password" 
						size="20" required="required" style="color:#006400"/>
					<form:errors path="password" cssClass="text-warning" style="color:red"></form:errors>
			    </div>
			  </div>
			  <input class="btn btn-primary" type="submit" value="Add"/>	
			 </form:form>	
	</body>
</html>