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
    <h2>Area Monthly Expense</h2>
        
        <form method="get" action="/InterfaceToManageStock/areaMonthlyExpense">
        
        <div align="center">
				<table>
					<tr>
						<td><label for="areaName: ">Area Name: </label> <select
							id="selectedArea" name="selectedArea">
								<c:forEach items="${listAreas}" var="area">
									<option value="${area.areaName}" <c:if test="${area.areaName == selectedArea}"> selected </c:if>>${area.areaName}</option>
								</c:forEach>
						</select></td>
					</tr>
				</table>
			</div>
			<input type="submit" value="Search" />
    </form>
        
    <h3><a href="#" onclick="addArea()">New Area</a> &nbsp;&nbsp;<a href="/InterfaceToManageStock/areaMonthlyExpense">List All Areas Monthly Expenses</a></h3>
     <form:form action="saveAreaMonthlyExpense" method="post" >
    <table border="1" cellpadding="5" id="areaData"  class="order-list" style="table-layout:fixed;">
    <thead>
        <tr>
        <th>select</th>
            <th>ID</th>
            <th>Area Name</th>
            <th>Monthly Transport Expenses</th>
            <th>Monthly Man Power Expenses</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listDetails}" var="areaDetails" varStatus="loop">
        <tr>
         <td><input type='checkbox' name='selected' value="${loop.index}" ></td>
            <td><input type="text"style="width:50px"  name="id${loop.index}" value="${areaDetails.id}" readonly /></td>
       <td> <select class="dropdown" name="areaName${loop.index}">
        <c:forEach items="${listAreas}" var="record">
           <option value="${record.areaName}" <c:if test="${record.areaName == areaDetails.areaName}"> selected </c:if>>
            ${record.areaName}</option>
        </c:forEach>
    </select ></td>
    <td><input type="text" style="width:350px" name="trans_exp_mnthly${loop.index}" value="${areaDetails.trans_exp_mnthly}" required/></td>
        <td><input type="text" style="width:350px" name="man_pwr_mnthly${loop.index}" value="${areaDetails.man_pwr_mnthly}" required/></td>
        <td>${fn:substring(areaDetails.date, 0, 10)}</td>
        
            <td>
                <a href="/InterfaceToManageStock/deleteAreaMnthlyExpnsRecord?id=${areaDetails.id}">Delete</a>
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
	
	var a = "${listAreas}";
	
	 $("form input[type=text],form input[type=checkbox]").prop("disabled",true);
	 $( ".dropdown" ).prop("disabled",true);
	$("#selectedArea").removeAttr("disabled");
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
    addArea =  function () {
        counter = $('#areaData tr').length - 2;

        var newRow = $("<tr>");
        var cols = "";
        cols += '<td><input type="checkbox" name="selected" value= "'+counter+'x" checked = "checked"></td>';
        cols += '<td></td>';
        cols += ' <td> <select name="areaName' + counter + 'x"><c:forEach items="${listAreas}" var="record"><option value="${record.areaName}" <c:if test="${record.areaName eq  areaDetails.areaName}">selected="selected"</c:if>>${record.areaName}</option></c:forEach></select></td>';
        cols += '<td><input type="text" style="width:350px" name="trans_exp_mnthly' + counter + 'x" value="${areaDetails.trans_exp_mnthly}" required="required"/></td>';
        cols += '<td><input type="text" style="width:350px" name="man_pwr_mnthly' + counter + 'x" value="${areaDetails.man_pwr_mnthly}"required="required" /></td>';

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
