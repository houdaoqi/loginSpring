/**
 * Created by lenovo on 11/29/2016.
 */
var app = angular.module("myapp", ['shoppingService', 'ngMaterial', 'ngMessages', 'material.svgAssetsCache']);
app.controller("ListController", ['$scope', '$mdDialog', '$http', 'shopping', function($scope, $mdDialog, $http, shopping) {
    $scope.productDetails = [];
    $scope.addItem2Cart = addItem2Cart;
    $scope.updateItem2Cart = updateItem2Cart;
    $scope.place_order = place_order;
    getAllProducts();

    //HTTP GET- get all products collection
    function getAllProducts() {
        shopping.getProducts(function(data){
            $scope.productDetails = data;
        });
    };
    //HTTP GET- get all ordered items from cart collection
    function getAllOrderedItems() {
        console.log("getting all ordered items from your cart...");
        shopping.getOrderedItems(function(data){
            $scope.itemDetails = data;
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
        })
    };
    //HTTP POST- add item to cart
    function addItem2Cart(productDetail){
        shopping.addItem2Cart(productDetail, function(data){
            console.log(data);
            getAllOrderedItems();
        })
    };
    //HTTP POST- update item in cart
    function updateItem2Cart(itemDetail){
        shopping.updateItem2Cart(itemDetail, function(data){
            console.log(data);
            getAllOrderedItems();
        })
    };
    //HTTP POST- place order
    function place_order(itemDetails, $event){
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
                shopping.placeOrder(data, function(response){

                    shopping.showOrder($mdDialog, $event, order);
                    getAllOrderedItems();
                })
            },
            function errorCallback(response) {
                console.log(response.statusText);
            }
        );
    };
    //generate random id
    function generate_id() {
        var id = Math.floor((Math.random() * 1000) + 1) + Date.now();
        return id;
    };

}]);