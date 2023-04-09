<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ModelAttribute Demo - Employee</title>
	</head>
	<body>
		<h1>ModelAttribute Demo - Employee Form</h1>
		<form:form name="modelAttributeDemo" modelAttribute="employee" 
				action="addEmployee">
			<table>
				<tr>
					<td>
						<form:label path="name">Name : </form:label>
					</td>
					<td>
						<form:input path="name"/>
						<form:errors path="name" cssClass="text-warning"></form:errors>
					</td>
				</tr>
				<tr>
					<td>
						<form:label path="id">Id : </form:label>
					</td>
					<td>
						<form:input path="id"/>
						<form:errors path="id" cssClass="text-warning"></form:errors>
					</td>
				</tr>	
				<tr>
					<td colspan="2">
						<input type="submit" name="Submit"/>
					</td>
				</tr>			
			</table>
		</form:form>
	</body>
</html>