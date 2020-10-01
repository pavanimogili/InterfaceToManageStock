<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
    Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
<title>Stock Manager</title>
</head>
<%@ include file="../views/menu.jsp" %>
<body>
<div align="center">
    <h2>DailyProdutUpdates Manager</h2>
        
        <form method="get" action="/InterfaceToManageStock/searchByProduct">
        <label for="productName: ">Product Name: </label>
        <input type="text" name="keyword"  id="productName"/> &nbsp;
        <input type="submit" value="Search" />
    </form>
        
    <h3><a href="#" onclick="addProduct()">New Product</a> &nbsp;&nbsp;<a href="/InterfaceToManageStock/dailyProdutUpdates">List All ProductDetails</a></h3>
     <form:form action="saveProductRecord" method="post" modelAttribute="dailyproductdetails">
    <table border="1" cellpadding="5" id="productsData"  class="order-list" style="table-layout:fixed;">
    <thead>
        <tr>
        <th>select</th>
            <th>ID</th>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Purchase Price</th>
            <th>Selling price</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listDetails}" var="product" varStatus="loop">
        <tr>
         <td><input type='checkbox' name='selected' value="${loop.index}" ></td>
            <td><input type="text" style="width:50px" name="id${loop.index}" value="${product.id}" readonly /></td>
       <!--  <td><input type="text" style="width:350px" name="productName${loop.index}" value="${product.productName}"  />-->
       <td> <select class="dropdown" name="productName${loop.index}">
        <c:forEach items="${products}" var="record">
           <option value="${record.productName}" <c:if test="${record.productName == product.productName}"> selected </c:if>>
            
            ${record.productName}</option>
        </c:forEach>
    </select ></td>
        <td><input type="text" style="width:50px" name="quantity${loop.index}" value="${product.quantity != null ? product.quantity : 0 }" required />
        <td><input type="text" style="width:50px" name="purchasePrice${loop.index}" value="${product.purchasePrice != null ? product.purchasePrice: 0}" required />
        <td><input type="text" style="width:50px" name="sellingprice${loop.index}" value="${product.sellingprice != null ? product.sellingprice:0}" required/>
        <td>${fn:substring(product.date, 0, 10)}</td>
        
            <td>
                
                <a href="/InterfaceToManageStock/deleteProductRecord?id=${product.id}">Delete</a>
            </td>
        </tr>
        </c:forEach>
        
        </tbody>
    </table>
    <input type="button" name='edit' value='edit'>
    <input type="submit" name='save' value='Save'>
    </form:form>
</div>   
</body>
<script>
$(document).ready(function () {

	var error = "${error}";
	if(error != ""){
		alert("entered data is not saved ,As it is having duplicate entry ");
		}
	
	 $("form input[type=text],form input[type=checkbox]").prop("disabled",true);
	 $( ".dropdown" ).prop("disabled",true);
	$("#productName").removeAttr("disabled");
     $("input[name=edit]").on("click",function(){
    	 $("input[type=text],input[type=checkbox],select").removeAttr("disabled");
    	 $( ".dropdown" ).removeAttr("disabled");
     })

     $("input[name=save]").on("click",function(event){
    	 var count = $('input[type="checkbox"]:checked').length;
    	 if(count<1){
        	 alert("edit any field and select respective check box before clicking on save");
    		 event.preventDefault();
        	 }
    	 else{
        $(this).closest("td").find("input[type=text],input[type=checkbox],select").prop("disabled",true);
    	 }
     })
    var counter = 0;
    addProduct =  function () {
        counter = $('#productsData tr').length - 2;

        var newRow = $("<tr>");
        var cols = "";
        cols += '<td><input type="checkbox" name="selected" value= "'+counter+'x" checked = "checked"></td>';
        cols += '<td></td>';
        cols += ' <td> <select name="productName' + counter + 'x"><c:forEach items="${products}" var="record"><option value="${record.productName}" <c:if test="${record.productName eq product.productName}">selected="selected"</c:if>>${record.productName}</option></c:forEach></select></td>';
        cols += '<td><input type="text" style="width:50px" name="quantity' + counter + 'x" value="${product.quantity != null ? product.quantity : 0 }"  required/></td>';
        cols += '<td><input type="text" style="width:50px" name="purchasePrice' + counter + 'x" value="${product.purchasePrice!= null ? product.purchasePrice : 0 }"  required /></td>';
        cols += '<td><input type="text" style="width:50px" name="sellingprice' + counter + 'x" value="${product.sellingprice != null ? product.sellingprice : 0}" required /></td>';

        cols += '<td><input type="button" class="ibtnDel"  value="Delete"></td>';
        newRow.append(cols);
        $("table.order-list").append(newRow);
        counter++;
    }

    $("table.order-list").on("click", ".ibtnDel", function (event) {
        $(this).closest("tr").remove();
        
        counter -= 1
        $('#addrow').attr('disabled', false).prop('value', "Add Row");
    });


});

</script>
</html>
