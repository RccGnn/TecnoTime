<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REGISTRATION PAGE</title>
</head>
<body>
	<form action = "RegistrationPage" method="post">
	 Name:<input type="text" name="firstName" required placeholder="Your name">
	<br>
	Surname:<input type="text" name="lastName" required placeholder="Your surname">
	<br>
	<input type="submit" value="Add"><input type="reset">	
	
	<% String error = (String) request.getAttribute("error");
	 String message= (String) request.getAttribute("message");
	  if (error != null && !error.isEmpty()) {
		  %>
		      <div style="color:red;">
		        <%= error %>
		      </div>
		  <%
		    }
	  if (message!=null && !message.isEmpty()){
		  %>
		  <div style="color:blue;">
		        <%= message %>
		      </div>
		  <%
	  }
		  %>
</form>
</body>
</html>