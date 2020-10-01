<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
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
    <h2>Area Manager</h2>
        
        <form method="get" action="/InterfaceToManageStock/searchArea">
        <label for="areaName: ">Area Name: </label>
        <input type="text" name="keyword"  id="areaName"/> &nbsp;
        <input type="submit" value="Search" />
    </form>
        
    <h3><a href="#" onclick="addArea()">New Area</a> &nbsp;&nbsp;<a href="/InterfaceToManageStock/ListAreas">List All Areas</a></h3>
     <form:form action="saveArea" method="post" modelAttribute="area">
    <table border="1" cellpadding="5" id="areasData"  class="order-list" style="table-layout:fixed;">
    <thead>
        <tr>
            <th>Area ID</th>
            <th>Area Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listArea}" var="area">
        <tr>
            <td>${area.areaId}</td>
        <td>${area.areaName}</td>
            <td>
                <a href="/InterfaceToManageStock/editArea?id=${area.areaId}">Edit</a>
                &nbsp;&nbsp;&nbsp;
                <a href="/InterfaceToManageStock/deleteArea?id=${area.areaId}">Delete</a>
            </td>
        </tr>
        </c:forEach>
        
        </tbody>
    </table>
    <input type="submit" name ="save" value="Save">
    </form:form>
</div>   
</body>
<script>
$(document).ready(function () {
	var error = "${error}";
	if(error != ""){
		alert("entered data is not saved ,As it is having duplicate entry ");
		}

    var counter = 0;
    addArea =  function () {

        counter = $('#areasData tr').length - 2;

        var newRow = $("<tr>");
        var cols = "";

        cols += '<td><input type="checkbox" name="selected" value="area.areaName' + counter + '" checked = "checked"></td>';
        cols += '<td><input type="text" style="width:350px" name="area.areaName' + counter + '" value="${area.areaName}" required /></td>';

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


$("input[name=save]").on("click",function(event) {
		
		var count = $('input[type="checkbox"]:checked').length;
		if (count < 1) {
			alert("edit any field and select respective check box before clicking on save");
			event.preventDefault();
		} 
	}) 

});

</script>
</html>
