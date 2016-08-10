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

<center>
   <h3><font color="brown">Your Cart</font></h3>
        <ul>
			<c:forEach var="l" items="${list}">
				<li>${l.name}</li>
			</c:forEach>
		</ul>
		
		<form:form method="get" action="/SpringOnlineShoppingCart/RemoveSelectedProduct">
		   <input type="submit" value="Remove From Cart" />
		</form:form>
		
		
		<form:form method="post" action="/SpringOnlineShoppingCart/placeOrder">
	  	  <input type="submit" value="Place Order">
		</form:form>
		
		<%@include file="../logout/logoutButton.jsp" %>
</center>
</body>
</html>