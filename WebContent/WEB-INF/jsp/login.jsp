<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>
  <font color="red">${message}</font>
</h2>
<center>
    <h1>User Login</h1>
    <form:form commandName="CustomerLogin" method="post" action="/SpringOnlineShoppingCart/showProductList">
        <font size="5">User Name:</font>
        <form:input path="uname" />
        <form:errors path="uname"></form:errors>
        <font size="5">Password:</font>
        <form:password path="password" />
        <form:errors path="password"></form:errors>
        <input type="submit" value="Submit">
    </form:form>
</center>

</body>
</html>

