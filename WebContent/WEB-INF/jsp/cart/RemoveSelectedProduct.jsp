<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script> 

</head>
<body>
<h3><font color="brown">Delete product from your cart</font></h3>
<h4><font color="green">${deleted}</font></h4>

        <form:form commandName="ProductList" action="/SpringOnlineShoppingCart/RemoveSelectedProduct" method="post">
		<form:select path="selectedItem" id="items" name="items" >
	 		<c:forEach var="l" items="${list}">
				<form:option value="${l.name}">${l.name}</form:option>
			</c:forEach>
	  	  </form:select>
	  	  <input type="submit" value="Remove this item" />
	  	  
		</form:form>
		<%@include file="../logout/logoutButton.jsp" %>
</body>
</html>
