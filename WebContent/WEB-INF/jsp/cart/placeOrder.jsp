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
<h3><font color="GREEN">Place Your Order</font></h3>
<h4>Product    Price</h4>
         <table>
            <tr>
            <th>Product Name<th>Product Color<th>Product Size in Stock<th>Product Cost
            
            <c:forEach var="l" items="${list}">
				<tr><td>${l.name}<td>${l.color}<td>${l.sizeInStock}<td>${l.price}
			</c:forEach>
         </table>
			
		<h3>Total Price:-</h3>
		<span>${totalAmount}</span>
		<br>
		<br>
		<form:form method="post" action="/SpringOnlineShoppingCart/addMoreProducts">
	  	  <input type="submit" value="Add More Products to the Cart">
		</form:form>
		<br><br>
		<%@include file="../logout/logoutButton.jsp" %>
</center>
</body>
</html>