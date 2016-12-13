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
    <!-- Angular Material requires Angular.js Libraries -->
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-animate.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-aria.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular-messages.min.js"></script>
    <script src="http://ngmaterial.assets.s3.amazonaws.com/svg-assets-cache.js"></script>
    <!-- Angular Material Library -->
    <script src="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.1/angular-material.min.js"></script>

    <script src="script/js.cookie.js"></script>
    <script src="script/displayProductsController.js"></script>
    <script src="script/shoppingService.js"></script>
    <link rel="stylesheet" href="../css/dialog.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/angular-material/1.1.1/angular-material.css">

</head>
<body ng-app="myapp" ng-controller="ListController">
<h1>Welcome to Daoqi's computer store!</h1>
<div id="productList">
    <h3>Product List:</h3>
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
                {{productDetail.productID}}
            </td>
            <td>
                {{productDetail.productName}}
            </td>
            <td>
                {{productDetail.price}}
            </td>
            <td>
                <input class="form-control" ng-init="productDetail.quantity=1" ng-model="productDetail.quantity"
                       required/></td>
            <td>
                <button ng-click="addItem2Cart(productDetail)">Add to Cart</button>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<div id="cartItemList">
    <h3>Cart:</h3>
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
                {{itemDetail.product.productID}}
            </td>
            <td>
                {{itemDetail.product.productName}}
            </td>
            <td>
                {{itemDetail.product.price}}
            </td>
            <td>
                <input class="form-control" ng-init="itemDetail.quantity=itemDetail.quantity"
                       ng-model="itemDetail.quantity" required/></td>
            <td>
                <button ng-click="updateItem2Cart(itemDetail)">Update Cart</button>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<div id="orderSection">
    <h3>Order:</h3>
    <table id="orderTable">
        <thead>
        <th>Mail Address</th>
        <th>Credit Card Number</th>
        <th>Total Price</th>
        <th>Operation</th>
        </thead>
        <tr>
            <td><input ng-init="address='xxxxxx@yyyy.zzz'" ng-model="address"/></td>
            <td><input ng-init="creditNumber='888888888888'" ng-model="creditNumber"/></td>
            <td id="totalPrice" ng-init="totalPrice=0">{{totalPrice}}</td>
            <td>
                <button id="placeOrder" ng-click="place_order(itemDetails, $event)">Place Order</button>
            </td>
        </tr>
    </table>
</div>
<div class="md-padding dialogdemoBasicUsage" id="myTest" ng-cloak="">
    <div class="dialog-demo-content" layout="row" layout-wrap="" layout-margin="" layout-align="center">
    </div>
</div>
</body>
</html>
