<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 11/14/2016
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
    <style type="text/css">
        table {
            border: 1px solid #777;
            border-collapse: collapse;
        }

        table tr th,
        table tr td {
            border: 1px solid #777;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="script/js.cookie.js"></script>
    <script src="script/displayproducts.js"></script>
</head>
<body>
Welcome to Daoqi's computer store!
<div id="datalist">
    <table id="dataTable">
        <thead><th>Product ID</th><th>Product Name</th><th>Price</th><th>Quantity</th><th>Operation</th></thead><tbody>
    </table>
</div>
Here is your cart:
<div id="cartItemList">
    <table id="cartItemTable">
        <thead><th>Product ID</th><th>Product Name</th><th>Price</th><th>Quantity</th><th>Operation</th></thead><tbody>
    </table>
</div>
<%--<input type="hidden" id="hdnSession" data-value="@Request.RequestContext.HttpContext.Session["userName"]" />--%>

<%--<script>--%>
    <%--$(document).ready(function() {--%>
        <%--var data = '[{ "firstName": "John", "lastName": "Smith" }, { "firstName": "Peter", "lastName": "Jason" }, { "firstName": "Alice", "lastName": "Ray" }]';--%>
        <%--var table = '<table><thead><th>First Name</th><th>Last Name</th></thead><tbody>';--%>
        <%--var obj = $.parseJSON(data);--%>
        <%--$.each(obj, function() {--%>
            <%--table += '<tr><td>' + this['firstName'] + '</td><td>' + this['lastName'] + '</td></tr>';--%>
        <%--});--%>
        <%--table += '</tbody></table>';--%>
        <%--document.getElementById("datalist").innerHTML = table;--%>
    <%--});--%>
<%--</script>--%>
<%--<script>--%>
    <%--$(document).ready(function() {--%>
        <%--$.ajax({--%>
            <%--url: "http://localhost:8081/product/all"--%>
        <%--}).then(function(data) {--%>
            <%--// $('.greeting-id').append(data.id);--%>
            <%--// $('.greeting-content').append(data.content);--%>
            <%--var table = '<table><thead><th>Product ID</th><th>Product Name</th><th>Price</th></thead><tbody>';--%>
            <%--var obj = data;--%>
            <%--$.each(obj, function() {--%>
                <%--table += '<tr><td>' + this['productID'] + '</td><td>' + this['productName'] + '</td><td>' + this['price'] +'</td></tr>';--%>
            <%--});--%>
            <%--table += '</tbody></table>';--%>
            <%--document.getElementById("datalist").innerHTML = table;--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>
<%--<script>--%>
    <%--$(document).ready(function() {--%>
        <%--displayProducts();--%>
    <%--});--%>
<%--</script>--%>
</body>
</html>
