<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Phonebook | Home Page</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="icon" type="image/x-icon" href="<%=request.getContextPath() %>/images/favicon.ico">
		<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/inc/sticky-footer-navbar.css"/>
	</head>
	<body class="d-flex flex-column h-100">
		<header>
   			<%@include file="menu.jsp"%>
   		</header>
   		<div id="contacts" class="container">
   		<style>
   			.text-warning{color:red}
   		</style>