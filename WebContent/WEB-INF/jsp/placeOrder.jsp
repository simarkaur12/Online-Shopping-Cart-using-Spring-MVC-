<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<h4>Product    Price</h4>
<ul>
			<c:forEach var="l" items="${list}">
				<li>${l.name}</li>-->${l.price}
			</c:forEach>
		</ul>
		
		<h3>Total Price:-</h3>
		<h5>${totalAmount}</h5>
		<%@include file="logoutButton.jsp" %>
</body>
</html>