<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	    $("#items").change(function(){
		var itemSelected = $(this).val();
		$.ajax({
	        url : "ajaxShowProductDetails",
	        type: "GET",
	        data : {item:itemSelected},
	        success : function(data) {
	            $("#result").html(data);	            
	        }
	    });
	});
});
function addToCart(){
	var item = $("#items").val();
	$.ajax({
        url : "addToCart",
        type: "POST",
        data : {item:item},
        success : function(data) {
            alert("done");	            
        }
    });
}
function showMyCart(){
	$.ajax({
        url : "showMyCart",
        type: "GET",
        success : function(data) {
            alert("shown");	            
        }
    });
}
function removeFromCart(){
	$.ajax({
        url : "removeFromCart",
        type: "GET",
        success : function(data) {
            alert("shown");	            
        }
    });
}
</script> 
</head>
<body>
Welcome <h2><font color="red">${message}</font></h2>
<h4><font color="green">${addedToCartOrNot}</font></h4>
<h2>${addToCartError}</h2>
<center>
<fieldset>
  <legend><font size="5" color="green">Shop Here</font></legend>
  <br>
	<form:form commandName="ProductList" method="post" action="/SpringOnlineShoppingCart/showMyCart">
		<form:select path="selectedItem" id="items" name="items">
			<form:option value="select">Select</form:option>
			<form:option value="Car">Car</form:option>
			<form:option value="Mobile">Mobile</form:option>
			<form:option value="Watch">Watch</form:option>
			<form:option value="Bentley">Bentley</form:option>
		</form:select>
		<div id="result"></div>
		
		<input type="button" onclick="addToCart();" value="Add To Cart" />
		<input type="submit" value="Show My Cart" />
	</form:form>
</fieldset>
</center>
<%@include file="logoutButton.jsp" %>
</body>
</html>
