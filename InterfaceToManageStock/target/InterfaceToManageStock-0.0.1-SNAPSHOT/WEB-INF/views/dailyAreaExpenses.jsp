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
    <h2>Area Expenses Manager</h2>
        
        <form method="get" action="/InterfaceToManageStock/dailyAreaExpenses"
			>
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
        
    <h3><a href="#" onclick="addArea()">New Product</a> &nbsp;&nbsp;<a href="/InterfaceToManageStock/dailyAreaExpenses">List All AreaDetails</a></h3>
     <form:form action="saveDailyExpensesRecord" method="post" >
    <table border="1" cellpadding="5" id="areaData"  class="order-list" style="table-layout:fixed;">
    <thead>
        <tr>
        <th>select</th>
            <th>ID</th>
            <th>Area Name</th>
            <th>Transport Expense daily</th>
            <th>Manpower Expense daily</th>
            <th>Other Expense</th>
            <th>Name other Expense</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${expensesList}" var="expense" varStatus="loop">
        <tr>
         <td><input type='checkbox' name='selected' value="${loop.index}" ></td>
            <td><input type="text" style="width:50px" name="id${loop.index}" value="${expense.id}" readonly /></td>
       <td> <select class="dropdown" name="areaName${loop.index}">
        <c:forEach items="${listAreas}" var="record">
           <option value="${record.areaName}" <c:if test="${record.areaName == expense.areaName}"> selected </c:if>>
            
            ${record.areaName}</option>
        </c:forEach>
    </select ></td>
        <td><input type="text" style="width:200px" name="trans_exp_daily${loop.index}" value="${expense.trans_exp_daily}" required />
        <td><input type="text" style="width:200px" name="man_pwr_daily${loop.index}" value="${expense.man_pwr_daily}" required />
        <td><input type="text" style="width:150px" name="othr_exp${loop.index}" value="${expense.othr_exp != null ? expense.othr_exp : 0}" required/>
         <td><input type="text" style="width:250px" name="name_othr_exp${loop.index}" value="${expense.name_othr_exp}" />
        <td>${fn:substring(expense.date, 0, 10)}</td>
        
            <td>
                
                <a href="/InterfaceToManageStock/deleteDailyExpensesRecord?id=${expense.id}">Delete</a>
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


	$(document).ready(function() {

						var error = "${error}";
						if(error != ""){
							alert("entered data is not saved ,As it is having duplicate entry ");
							}

						$("form input[type=text],form input[type=checkbox]").prop("disabled", true);
						$(".dropdown").prop("disabled", true);
						
						$("#selectedArea").change(function() {
							var area = $("#selectedArea").val();
							$("#searchForm").submit();
						});

						$(".dropdown").prop("disabled", true);
						$("input[name=edit]").on("click",function() {
											$("input[type=text],input[type=checkbox],select").removeAttr("disabled");
											$(".dropdown").removeAttr("disabled");
										})

						$("input[name=save]").on("click",function(event) {
											var count = $('input[type="checkbox"]:checked').length;
											if (count < 1) {
												alert("edit any field and select respective check box before clicking on save");
												event.preventDefault();
											} else {
												$(this).closest("td").find("input[type=text],input[type=checkbox],select").prop("disabled", true);
											}
										})
						var counter = 0;
						addArea = function() {
							counter = $('#areaData tr').length - 2;

							var newRow = $("<tr>");
							var cols = "";
							cols += '<td><input type="checkbox" name="selected" value= "'+counter+'x" checked = "checked"></td>';
							cols += '<td></td>';
							cols += ' <td> <select id="dropdown" class="dropdown" name="areaName' + counter + 'x"><c:forEach items="${listAreas}" var="record"><option value="${record.areaName}" <c:if test="${record.areaName eq expense.areaName}">selected="selected"</c:if>>${record.areaName}</option></c:forEach></select></td>';
							cols += '<td><input type="text" style="width:200px" name="trans_exp_daily' + counter + 'x" value="${expense.trans_exp_daily}" required/>${expense.trans_exp_daily}</td>';
							cols += '<td><input type="text" style="width:200px" name="man_pwr_daily' + counter + 'x" value="${expense.man_pwr_daily}" required/>${expense.man_pwr_daily} </td>';
							cols += '<td><input type="text" style="width:150px" name="othr_exp' + counter + 'x" value="${expense.othr_exp != null ? expense.othr_exp : 0}" required/></td>';
							cols += '<td><input type="text" style="width:250px" name="name_othr_exp' + counter + 'x" value="${name_othr_exp}" /></td>';

							cols += '<td><input type="button" class="ibtnDel"  value="Delete"></td>';
							newRow.append(cols);
							$("table.order-list").append(newRow);
							setValue("${listAreas[0].areaName}",counter);
							counter++;
						}

						$("table.order-list").on("click",".ibtnDel",function(event) {
									$(this).closest("tr").remove();

									counter -= 1
									$('#addrow').attr('disabled', false).prop('value', "Add Row");
								});                              

						 setValue = function(name1,counter) {


								<c:forEach var="list" items="${expensesList}">
								var name = "${list.areaName}";
								if(name == name1 ){
									
									$('input[name="trans_exp_daily'+counter+'x"]').val("${list.trans_exp_daily}")
									$('input[name="man_pwr_daily'+counter+'x"]').val("${list.man_pwr_daily}")
									}
								</c:forEach>
							}

						$(".dropdown").change(function() {
							var selectedValue = this.value;
							var selectedValueName = this.name;
							var index = selectedValueName.substr( 8 );


							<c:forEach var="list" items="${expensesList2}">
							var name = "${list.areaName}";
							if(name == selectedValue ){
								$('input[name="trans_exp_daily'+index+'"]').val("${list.trans_exp_daily}")
								$('input[name="man_pwr_daily'+index+'"]').val("${list.man_pwr_daily}")
								}
							</c:forEach>
						});

						$("table.order-list").on("change",".dropdown",function(event) {

							var selectedValue = this.value;
							var selectedValueName = this.name;
							var index = selectedValueName.substr( 8 );


							<c:forEach var="list" items="${expensesList2}">
							var name = "${list.areaName}";
							if(name == selectedValue ){
								$('input[name="trans_exp_daily'+index+'"]').val("${list.trans_exp_daily}")
								$('input[name="man_pwr_daily'+index+'"]').val("${list.man_pwr_daily}")
								}
							</c:forEach>

							});

						

					});
</script>
</html>
