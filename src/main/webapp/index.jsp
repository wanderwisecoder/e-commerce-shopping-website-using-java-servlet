 <%@ page import="com.packages.connections.DbCon" %>
 
 
 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="com.packages.models.*" %>
  <% 
    User auth = (User) request.getSession().getAttribute("auth"); 
    if(auth!= null)
    {
    request.setAttribute("auth",auth);
    }
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Shopping Cart</title>

<%@include file="bootstrap-files/head.jsp" %>
</head>
<body>


 <%@include file="bootstrap-files/navbar.jsp" %>

<% out.print(DbCon.getConnection()); %>

<%@include file="bootstrap-files/js-cdn-link.jsp" %>
</body>

</html>