<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Product</title>
</head>
<body>
    <div align="center">
        <h2>Edit Product</h2>
        <form:form action="/InterfaceToManageStock/saveEditedProduct" method="post" modelAttribute="product">
            <table border="0" cellpadding="5">
                <tr>
                    <td>Product ID: </td>
                    <td>${product.productId}
                        <form:hidden path="productId"/>
                    </td>
                </tr>        
                <tr>
                    <td>Product Name: </td>
                     <td><form:input path="productName"  required="required"/></td>
                </tr>
                  
                <tr>
                    <td colspan="2"><input type="submit" value="Save"></td>
                </tr>                    
            </table>
        </form:form>
    </div>
</body>
</html>