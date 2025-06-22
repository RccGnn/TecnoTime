<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
   <%
   HttpSession currentSession = request.getSession(false);
    if (currentSession == null || currentSession.getAttribute("user") == null) {
        response.sendRedirect("loginPage.jsp");
        return;
    }
%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>