<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 11/29/2016
  Time: 10:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script src="script/js.cookie.js"></script>
    <script src="script/displayProductsAGJS.js"></script>
</head>
<body ng-app="myapp" ng-controller="ListController">
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <%--<form ng-submit="addNew()">--%>
                    <div id = "productList">
                        <table class="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th>Product ID</th>
                                <th>Product Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Operation</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="productDetail in productDetails">
                                <td>
                                    {{productDetail.productID}}</td>
                                <td>
                                    {{productDetail.productName}}</td>
                                <td>
                                    {{productDetail.price}}</td>
                                <td>
                                    <input class="form-control" ng-init="productDetail.quantity=1" ng-model="productDetail.quantity" required/></td>
                                <td>
                                    <button ng-click="addItem2Cart(productDetail)">Add to Cart </button></td>
                            </tr>
                            </tbody>
                        </table>
                        <%--<div class="form-group">--%>
                            <%--<input ng-hide="!personalDetails.length" type="button" class="btn btn-danger pull-right" ng-click="remove()" value="Remove">--%>
                            <%--<input type="submit" class="btn btn-primary addnew pull-right" value="Add New">--%>
                        <%--</div>--%>
                    <%--</form>--%>
                    </div>
                    <div id="cartItemList">
                        <table class="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th>Product ID</th>
                                <th>Product Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Operation</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="itemDetail in itemDetails">
                                <td>
                                    {{itemDetail.product.productID}}</td>
                                <td>
                                    {{itemDetail.product.productName}}</td>
                                <td>
                                    {{itemDetail.product.price}}</td>
                                <td>
                                    <input class="form-control" ng-init="itemDetail.quantity=itemDetail.quantity" ng-model="itemDetail.quantity" required/></td>
                                <td>
                                    <button ng-click="updateItem2Cart(itemDetail)">Update Cart </button></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div id="orderSection">
                        <table id="orderTable">
                            <thead><th>Mail Address</th><th>Credit Card Number</th><th>Total Price</th><th>Operation</th></thead>
                            <tr><td><input ng-init="address='xxxxxx@yyyy.zzz'" ng-model="address"/></td><td><input ng-init="creditNumber='888888888888'" ng-model="creditNumber"/></td><td id="totalPrice" ng-init="totalPrice=0">{{totalPrice}}</td><td><button id="placeOrder" ng-click="place_order(itemDetails)">Place Order</button></td></tr>
                        </table>
                        <pre id="order-dialog" title="Here is your order details"></pre>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
