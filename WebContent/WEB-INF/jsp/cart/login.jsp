<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h4>
  <font color="red">${error}</font>
</h4>
<center>
    <h1>User Login</h1>
    <form:form commandName="CustomerLogin" method="post" action="/SpringOnlineShoppingCart/showProductList">
        <font size="5">User Name:</font>
        <form:input path="uname" />
        <br><br>
        <font color="red">
           <form:errors path="uname"></form:errors>
        </font>
        <br>
        <font size="5">Password:</font>
        <form:password path="password" />
        <br><br>
        <font color="red">
            <form:errors path="password"></form:errors>
        </font>
        <br>
        <input type="submit" value="Submit">
    </form:form>
</center>
</body>
</html>

