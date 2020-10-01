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
    <h2>Area wise Product Updates</h2>

		<form method="get" action="/InterfaceToManageStock/areaWisedetails"
			id="searchForm">
			<div align="center">
				<table>
					<tr>
						<td><label for="areaName: ">Area Name: </label> <select
							id="selectedArea" name="selectedArea">
								<c:forEach items="${Area}" var="area">
									<option value="${area.areaName}" <c:if test="${area.areaName == selectedArea}"> selected </c:if>>${area.areaName}</option>
								</c:forEach>
						</select></td>

						<td><label for="productName: ">Product Name: </label> <input
							type="text" name="keyword" id="productName" value="${selectedProduct}"/> &nbsp;</td>
					</tr>
				</table>
			</div>

			<input type="submit" value="Search" />
		</form>
        <div  id="areaNameH">
      
 </div>
         
		<h3><a href="#" onclick="addProduct()">New Product</a> </h3>
		<form:form action="saveAreaProductRecord" method="post"
			id="saveAreaProductRecord">
			<table border="1" cellpadding="5" id="areaData" class="order-list"
				style="table-layout: fixed;">
				<thead>
					<tr>
						<th>select</th>
						<th>ID</th>
						<th>Product Name</th>
						<th>Quantity</th>
						<th>Purchase Price</th>
						<th>Selling price</th>
						<th>Received Amount</th>
						<th>returnedQuantity</th>
						<th>ReasonFor Return</th>
						<th>Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${areaWisedetailsList}" var="area"
						varStatus="loop">
						<tr>
							<td><input type='checkbox' name='selected'
								value="${loop.index}"></td>
							<td><input type="text"style="width:50px" name="id${loop.index}"
								value="${area.id}" readonly /></td>
							<td><select class="dropdown" name="productName${loop.index}">
									<c:forEach items="${products}" var="record">
										<option value="${record}"
											<c:if test="${record == area.productName}"> selected </c:if>>

											${record}</option>
									</c:forEach>
							</select></td>
							<td><input type="text" style="width: 50px"
								name="quantity${loop.index}" value="${area.quantity !=null ? area.quantity : 0}" required /></td>
							<td><input type="text" style="width: 50px"
								name="purchasePrice${loop.index}" value="${area.purchasePrice}" required/></td>
							<td><input type="text" style="width: 50px"
								name="sellingprice${loop.index}" value="${area.sellingprice}" required /></td>
								 <%-- <td><input type="text" style="width: 50px"
								name="receivedAmount${loop.index}" value="${area.receivedAmount}" /> --%>
								<td><input type="text" style="width: 50px"
								name="receivedAmount${loop.index}" value="${area.receivedAmount != null ? area.receivedAmount : 0}" required /></td>
								
								<td><input type="text" style="width: 50px"
								name="returnedQuantity${loop.index}" value="${area.returnedQuantity != null ? area.returnedQuantity : 0}" required /></td>
								<td><input type="text" style="width: 50px"
								name="reasonForReturn${loop.index}" value="${area.reasonForReturn != null ? area.reasonForReturn : NA}" /> </td>
							<td>${fn:substring(area.date, 0, 10)}</td>

							<td><a
								href="/InterfaceToManageStock/deleteAreaProductRecord?id=${area.id}">Delete</a>
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
						
						var area = $("#selectedArea").val();
						$("#areaNameH").html("<h3>Viewing <span style='color: #ff0000'>"+area+"</span> Area Product Detail</h3><a href=''/</a>");

						$("#selectedArea").change(function() {
							var area = $("#selectedArea").val();
							$("#searchForm").submit();
						});

						$("form input[type=text],form input[type=checkbox]")
								.prop("disabled", true);
						$(".dropdown").prop("disabled", true);
						$("#productName").removeAttr("disabled");

						$("input[name=edit]")
								.on(
										"click",
										function() {
											$(
													"input[type=text],input[type=checkbox],select")
													.removeAttr("disabled");
											$(".dropdown").removeAttr(
													"disabled");
										})

						$("input[name=save]").on("click",function(event) {
											var area = $("#selectedArea").val();
											$('#saveAreaProductRecord').append(
															'<input type="hidden" name="selectedArea" value="'+area+'" />');
											var count = $('input[type="checkbox"]:checked').length;
											if (count < 1) {
												alert("edit any field and select respective check box before clicking on save");
												event.preventDefault();
											} else {
												$(this)
														.closest("td")
														.find(
																"input[type=text],input[type=checkbox],select")
														.prop("disabled", true);
											}
										})
						var counter = 0;
						addProduct = function() {
							counter = $('#areaData tr').length - 2;

							var newRow = $("<tr>");
							var cols = "";
							cols += '<td><input type="checkbox" name="selected" value= "'+counter+'x" checked = "checked"></td>';
							cols += '<td></td>';
							cols += ' <td> <select class="dropdown" name="productName' + counter + 'x"><c:forEach items="${products}" var="record"><option value="${record}" <c:if test="${record eq area.productName}">selected="selected"</c:if>>${record}</option></c:forEach></select></td>';
							cols += '<td><input type="text" style="width:50px" name="quantity' + counter + 'x" value="${area.quantity != null? area.quantity :0}"  required/></td>';
							cols += '<td><input type="text" style="width:50px" name="purchasePrice' + counter + 'x" value="${area.purchasePrice}" required/></td>';
							cols += '<td><input type="text" style="width:50px" name="sellingprice' + counter + 'x" value="${area.sellingprice}" required /></td>';
							cols += '<td><input type="text" style="width:50px" name="receivedAmount' + counter + 'x" value="${area.receivedAmount != null ? area.receivedAmount : 0 }" required /></td>';
							cols += '<td><input type="text" style="width:50px" name="returnedQuantity' + counter + 'x" value="${area.returnedQuantity != null ? area.returnedQuantity : 0}" required/></td>';
							cols += '<td><input type="text" style="width:50px" name="reasonForReturn' + counter + 'x" value="${area.reasonForReturn != null ? area.reasonForReturn : "NA" }" /></td>';

							cols += '<td><input type="button" class="ibtnDel"  value="Delete"></td>';
							newRow.append(cols);
							$("table.order-list").append(newRow);
							setValue("${products[0]}",counter);
							counter++;
						}

						 setValue = function(name1,counter) {

							 var arr = "${areaWisedetailsList}";

								<c:forEach var="list" items="${areaWisedetailsList}">
								var name = "${list.productName}";
								if(name == name1 ){
									
									$('input[name="purchasePrice'+counter+'x"]').val("${list.purchasePrice}")
									$('input[name="sellingprice'+counter+'x"]').val("${list.sellingprice}")
									}
								</c:forEach>
							}

						$("table.order-list").on(
								"click",
								".ibtnDel",
								function(event) {
									$(this).closest("tr").remove();

									counter -= 1
									$('#addrow').attr('disabled', false).prop(
											'value', "Add Row");
								});

						$(".dropdown").change(function() {
							var selectedValue = this.value;
							var selectedValueName = this.name;
							var index = selectedValueName.substr( 8 );


							<c:forEach var="list" items="${areaWisedetailsList2}">
							var name = "${list.productName}";
							if(name == selectedValue ){
								$('input[name="purchasePrice'+index+'"]').val("${list.purchasePrice}")
								$('input[name="sellingprice'+index+'"]').val("${list.sellingprice}")
								}
							</c:forEach>
						});


						$("table.order-list").on("change",".dropdown",function(event) {

							var selectedValue = this.value;
							var selectedValueName = this.name;
							var index = selectedValueName.substr( 11 );


							<c:forEach var="list" items="${areaWisedetailsList2}">
							var name = "${list.productName}";
							if(name == selectedValue ){
								$('input[name="purchasePrice'+index+'"]').val("${list.purchasePrice}")
								$('input[name="sellingprice'+index+'"]').val("${list.sellingprice}")
								}
							</c:forEach>

							});
						

					});
</script>
</html>
