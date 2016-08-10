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

function removeProduct(){
    var item = $("#items").val();
	$.ajax({
        url : "removeProduct",
        type: "POST",
        data : { item: item },
        success : function(data) {
            alert("done");
        }
    });
}