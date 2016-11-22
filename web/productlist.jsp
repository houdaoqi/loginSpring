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
    <style>
        #product-accordion{font-size: 14px;}
    </style>
    <link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="script/js.cookie.js"></script>
    <script src="script/displayProducts.js"></script>
    <script src="script/handleOrder.js"></script>
    <script>
        $(function() {
            $( "#product-accordion" ).accordion({
                heightStyle: "content",
                collapsible: true
            });
            $(":button").button();
            $( "#order-dialog" ).dialog({
                autoOpen: false
            });
        });
//        $(function() {
//            $( "#order-dialog" ).dialog({
//                autoOpen: false
//            });
//        });
    </script>
</head>
<body>
<h1>Welcome to Daoqi's computer store!</h1>
<div id="product-accordion">
    <h3>Product List</h3>
<div id="datalist">
    <table id="dataTable">
        <thead><th>Product ID</th><th>Product Name</th><th>Price</th><th>Quantity</th><th>Operation</th></thead><tbody>
    </table>
</div>
<h3>Cart</h3>
<div id="cartItemList">
    <table id="cartItemTable">
        <thead><th>Product ID</th><th>Product Name</th><th>Price</th><th>Quantity</th><th>Operation</th></thead><tbody>
    </table>
</div>
<h3>Order</h3>
<div id="orderSection">
    <table id="orderTable">
        <thead><th>Mail Address</th><th>Credit Card Number</th><th>Total Price</th><th>Operation</th></thead>
        <tr><td><input id="address"/></td><td><input id="creditNumber"/></td><td id="totalPrice"></td><td><button id="placeOrder" onclick="place_order()">Place Order</button></td></tr>
    </table>
    <pre id="order-dialog" title="Here is your order details"></pre>
</div>
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
