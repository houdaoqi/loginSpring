/**
 * Created by lenovo on 11/29/2016.
 */
var app = angular.module("myapp", []);
app.controller("ListController", function($scope, $http) {
    $scope.productDetails = [];
    $scope.addItem2Cart = addItem2Cart;
    $scope.updateItem2Cart = updateItem2Cart;
    $scope.place_order = place_order;
    getAllProducts();

    //HTTP GET- get all products collection
    function getAllProducts() {
        $http({
            method : 'GET',
            url : "http://localhost:8081/product/all"
        }).then(
            function successCallback(response) {
                $scope.productDetails = response.data;
                // getAllOrderedItems();
            },
            function errorCallback(response) {
                console.log(response.statusText);
            }
        );
    }
    //HTTP GET- get all ordered items from cart collection
    function getAllOrderedItems() {
        console.log("getting all ordered items from your cart...");
        var userName = Cookies.get("userName");
        $http({
            method : 'GET',
            url : 'http://localhost:8081/cart/' + userName + '/all'
        }).then(
            function successCallback(response) {
                console.log("getAllOrderedItems() called successfully");
                console.log(response);
                $scope.itemDetails = response.data;

                //calculate total price
                var price = function(){
                    var total = 0;
                    console.log("itemDetails------------");
                    console.log($scope.itemDetails);
                    for(var key in $scope.itemDetails){
                        var item = $scope.itemDetails[key];
                        total += (item.product.price * item.quantity);
                    }
                    console.log("total--------------------");
                    console.log(total);
                    return total;
                };
                $scope.totalPrice = price();
            },
            function errorCallback(response) {
                console.log(response.statusText);
            }
        );
    }
    //HTTP POST- add item to cart
    function addItem2Cart(productDetail){
        console.log("adding item to cart...");
        // console.log("test...");
        var userName = Cookies.get("userName");
        // var data = JSON.stringify({"product": {"productID": productDetail.productID, "productName": productDetail.productName, "price": productDetail.price}, "quantity": productDetail.quantity});
        var data = angular.toJson({"product": {"productID": productDetail.productID, "productName": productDetail.productName, "price": productDetail.price}, "quantity": productDetail.quantity});
        // console.log(userName);
        // console.log("data will be posted to cart is:--------------------");
        // console.log(data);
        // console.log("productDetail is ----------------------");
        // console.log(productDetail.productID);
        // console.log(productDetail.productName);
        // console.log(productDetail.price);
        // console.log(productDetail.quantity);
        $http({
            method : 'POST',
            url : "http://localhost:8081/cart/" + userName,
            data : data
        }).then(
            function successCallback(response) {
                console.log(response);
                console.log("the item has been added to cart");
                getAllOrderedItems();
            },
            function errorCallback(response) {
                console.log(response.statusText);
            }
        )
    }
    //HTTP POST- update item in cart
    function updateItem2Cart(itemDetail){
        console.log("updating item to cart...");
        // console.log("test...");
        var userName = Cookies.get("userName");
        // var data = JSON.stringify({"product": {"productID": productDetail.productID, "productName": productDetail.productName, "price": productDetail.price}, "quantity": productDetail.quantity});
        var data = angular.toJson({"product": {"productID": itemDetail.product.productID, "productName": itemDetail.product.productName, "price": itemDetail.product.price}, "quantity": itemDetail.quantity});

        $http({
            method : 'PUT',
            url : "http://localhost:8081/cart/" + userName,
            data : data
        }).then(
            function successCallback(response) {
                console.log(response);
                console.log("the item has been updated to cart");
                getAllOrderedItems();
            },
            function errorCallback(response) {
                console.log(response.statusText);
            }
        )
    }
    //HTTP POST- place order
    function place_order(itemDetails){
        console.log("placing order...");
        var mailAddress = $scope.address;
        var credit = $scope.creditNumber;
        var userName = Cookies.get("userName");
        var orderedItemArray = [];
        var userID = 0;
        var orderID = generate_id();
        angular.forEach(itemDetails, function(itemDetail){
            itemDetail.id=generate_id();
            orderedItemArray.push(itemDetail);
        });
        $http({
            method : 'GET',
            url : 'http://localhost:8081/user/' + userName
        }).then(
            function successCallback(response) {
                console.log("get user response:------------------")
                console.log(response);
                userID = response.data.id;
                console.log(userID);
                var order = {
                    "orderID" : orderID,
                    "orderedItemList" : orderedItemArray,
                    "totalPrice" : $scope.totalPrice,
                    "address" : mailAddress,
                    "creditNumber" : credit,
                    "userID" : userID
                };
                var data = angular.toJson(order);
                $http({
                    method : 'POST',
                    url : "http://localhost:8081/order",
                    data : data
                }).then(
                    function successCallback(response) {
                        console.log(response);
                        console.log("the order has been placed");
                        getAllOrderedItems();
                    },
                    function errorCallback(response) {
                        console.log(response.statusText);
                    }
                )
            },
            function errorCallback(response) {
                console.log(response.statusText);
            }
        );
    }
    //generate random id
    function generate_id() {
        var id = Math.floor((Math.random() * 1000) + 1) + Date.now();
        return id;
    }

});