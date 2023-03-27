
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/phonebook/inc/header.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Phonebook | Update - Contact</title>
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div id="contacts" class="container">

		<h1>
			<span style="color: #43302e"><b>Phonebook | Update -
					Contact</b></span>
		</h1>
		<div id="greeting">
			<span style="color: #43302e"> Welcome, <b>${name}</b></span>
		</div>
		<form:form method="POST" commandName="contact">
			<div class="form-group row">
				<form:label path="firstName" for="fName"
					class="col-sm-2 col-form-label">
					<span style="color: #43302e">First Name</span>
					<span class="required" style="color: red">*</span>
				</form:label>
				<div class="col-sm-10">
					<form:input path="firstName" type="text" id="fName"
						name="firstName" size="20" required="required" />
					<form:errors path="firstName" cssClass="text-warning"></form:errors>
				</div>
			</div>

			<div class="form-group row">
				<form:label path="lastName" for="lName"
					class="col-sm-2 col-form-label">
					<span style="color: #43302e">Last Name</span>
					<span class="required" style="color: red">*</span>
				</form:label>
				<div class="col-sm-10">
					<form:input path="lastName" type="text" id="lName" name="lastName"
						size="20" required="required" />
					<form:errors path="lastName" cssClass="text-warning"></form:errors>
				</div>
			</div>

			<div class="form-group row">
				<form:label path="dob" for="dob" class="col-sm-2 col-form-label">
					<span style="color: #43302e"> Date of Birth </span>
				</form:label>
				<div class="col-sm-10">
					<form:input path="dob" type="text" id="dob" name="dob" size="10"
					/>
					<form:errors path="dob" cssClass="text-warning"></form:errors>
				</div>
			</div>

			<div class="form-group row">
				<form:label path="contactNo" for="contactNo"
					class="col-sm-2 col-form-label">
					<span style="color: #43302e"> Contact No </span>
					<span class="required" style="color: red">*</span>
				</form:label>
				<div class="col-sm-10">
					<form:input path="contactNo" type="text" id="contactNo"
						name="contactNo" size="10" required="required" />
					<form:errors path="contactNo" cssClass="text-warning"></form:errors>
				</div>
			</div>

			<div class="form-group row">
				<form:label path="email" for="email" class="col-sm-2 col-form-label">
					<span style="color: #43302e"> Email Address </span>
				</form:label>
				<div class="col-sm-10">
					<form:input path="email" type="text" id="email" name="email"
						size="20" />
					<form:errors path="email" cssClass="text-warning"></form:errors>
				</div>
			</div>

			<div class="form-group row">
				<form:label path="notes" for="notes" class="col-sm-2 col-form-label">
					<span style="color: #43302e"> Notes </span>
				</form:label>
				<div class="col-sm-10">
					<form:input path="notes" type="text" id="notes" name="notes"
						size="40"
						placeholder="Any short notes to be added for the Contact" />
				</div>
			</div>

			<div class="form-group row">
				<form:label path="tag" for="tag" class="col-sm-2 col-form-label">
					<span style="color: #43302e"> Tag </span>
				</form:label>
				<div class="col-sm-10">
					<form:input path="tag" type="text" id="tag" name="tag" size="20"
						placeholder="Any meaningful short name to group/classify this Contact" />
				</div>
			</div>
			<input class="btn btn-primary" type="submit" value="Update Contact" />
		</form:form>
	</div>
	<br />
	<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<%@include file="/WEB-INF/views/phonebook/inc/footer.jsp"%>
</body>
</html>