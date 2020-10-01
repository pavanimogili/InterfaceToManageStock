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
    <h2>Generate Area wise Dashboard</h2>
        
        <form method="get" action="/InterfaceToManageStock/dailyProfitDashboard">
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
        
  <h3> <a href="#" id="listAll">List All Area Details</a></h3>
     <form:form action="saveDailyExpensesRecord"  >
    <table border="1" cellpadding="5" id="areaData"  class="order-list" style="table-layout:fixed;">
    <thead>
        <tr>
            <th>ID</th>
            <th>Area Name</th>
            <th>Revenue</th>
            <th>Returned Revenue</th>
            <th>Gross Margin</th>
            <th>Total Expense</th>
            <th>Profit Or Loss</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${dailyProfitList}" var="details" varStatus="loop">
        <tr>
            <td>${details.id}</td>
            <td>${details.areaName}</td>
        <td>${details.revenue} </td>
        <td>${details.return_revenue}</td>
        <td>${details.gross_margin} </td>
         <td>${details.total_expense}</td>
         <td>${details.dailyProfit}</td>
        <td>${fn:substring(details.date, 0, 10)}</td>
        
        </tr>
        </c:forEach>
        
        </tbody>
    </table>
    </form:form>
</div>   
</body>
<script>
$(document).ready(function () {


	 $("listAll").on("click",function(event){
		 $("#selectedArea").val('');
		 $("#searchForm").submit();
	 });


});

</script>
</html>
