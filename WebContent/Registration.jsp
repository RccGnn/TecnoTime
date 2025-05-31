<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REGISTRATION JSP</title>
</head>
<body>
	<form action = "RegistrationPage" method="post">
	<input type="text" name="firstName" required placeholder="Your name">
	<br>
	<input type="text" name="lastName" required placeholder="Your surname">
	<br>
	<input type="submit" value="Add"><input type="reset">	
	
	<% String error = (String) request.getAttribute("error");
	  if (error != null && !error.isEmpty()) {
		  %>
		      <div style="color:red;">
		        <%= error %>
		      </div>
		  <%
		    }
		  %>
</form>
</body>
</html>