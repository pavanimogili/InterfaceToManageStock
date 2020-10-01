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
    <h2>Categories List</h2>
        
        <form method="get" action="/InterfaceToManageStock/ListCategories">
        <label for="categoryName: ">Category Name: </label>
        <input type="text" name="keyword"  id="categoryName"/> &nbsp;
        <input type="submit" value="Search" />
    </form>
        
    <h3><a href="#" onclick="addArea()">New Category</a> &nbsp;&nbsp;<a href="/InterfaceToManageStock/ListCategories">List All Categories</a></h3>
     <form:form action="saveCategories" method="post" >
    <table border="1" cellpadding="5" id="categoryData"  class="order-list" style="table-layout:fixed;">
    <thead>
        <tr>
        <th>select</th>
            <th>ID</th>
            <th>Category Name</th>
            <th>Category Code</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listCategories}" var="category" varStatus="loop">
        <tr>
         <td><input type='checkbox' name='selected' value="${loop.index}" ></td>
            <td><input type="text"style="width:50px"  name="id${loop.index}" value="${category.id}" readonly /></td>
    <td><input type="text" style="width:350px" name="categoryName${loop.index}" value="${category.categoryName}" required/></td>
        <td><input type="text" style="width:350px" name="categoryCode${loop.index}" value="${category.categoryCode}" required/></td>
        
            <td>
                <a href="/InterfaceToManageStock/deleteCategoryRecord?id=${category.id}">Delete</a>
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
	$("#categoryName").removeAttr("disabled");
     $("input[name=edit]").on("click",function(){
    	 $("input[type=text],input[type=checkbox],select").removeAttr("disabled");
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
    addArea =  function () {
        counter = $('#categoryData tr').length - 2;

        var newRow = $("<tr>");
        var cols = "";
        cols += '<td><input type="checkbox" name="selected" value= "'+counter+'x" checked = "checked"></td>';
        cols += '<td></td>';
        cols += '<td><input type="text" style="width:350px" name="categoryName' + counter + 'x" value="${category.categoryName}" required="required"/></td>';
        cols += '<td><input type="text" style="width:350px" name="categoryCode' + counter + 'x" value="${category.categoryCode}"required="required" /></td>';

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
