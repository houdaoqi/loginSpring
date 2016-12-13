/**
 * Created by lenovo on 12/2/2016.
 */
var shoppingModule = angular.module('shoppingService', ['ngMaterial', 'ngMessages', 'material.svgAssetsCache']);
// angular.module('shoppingService',[])
// .factory('getProducts', ['$http', function($http, call_back){
shoppingModule.factory('shopping', ['$http', function($http){
    var getProducts = function(call_back) {
        $http({
            method: 'GET',
            url: "http://localhost:8081/product/all"
        }).then(
            function successCallback(response) {
                call_back(response.data);
                // console.log("success call back");
                //$scope.productDetails = response.data;
                // getAllOrderedItems();
            },
            function errorCallback(response) {
                console.log(response.statusText);
            }
        );
    }

    var getOrderedItems = function(call_back){
        console.log("getting all ordered items from your cart...");
        var userName = Cookies.get("userName");
        $http({
            method : 'GET',
            url : 'http://localhost:8081/cart/' + userName + '/all'
        }).then(
        function successCallback(response) {
            console.log("getAllOrderedItems() called successfully");
            console.log(response);
            call_back(response.data);
            // $scope.itemDetails = response.data;
        },
        function errorCallback(response) {
            console.log(response.statusText);
        });
    }

    var addItem2Cart = function(productDetail, call_back){
        console.log("adding item to cart...");
        // console.log("test...");
        var userName = Cookies.get("userName");
        // var data = JSON.stringify({"product": {"productID": productDetail.productID, "productName": productDetail.productName, "price": productDetail.price}, "quantity": productDetail.quantity});
        var data = angular.toJson({"product": {"productID": productDetail.productID, "productName": productDetail.productName, "price": productDetail.price}, "quantity": productDetail.quantity});

        $http({
            method : 'POST',
            url : "http://localhost:8081/cart/" + userName,
            data : data
        }).then(
            function successCallback(response) {
                console.log("the item has been added to cart");
                call_back(response);
            },
            function errorCallback(response) {
                console.log(response.statusText);
            }
        )
    }

    var updateItem2Cart = function(itemDetail, call_back){
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
                call_back(response);
                console.log("the item has been updated to cart");

            },
            function errorCallback(response) {
                console.log(response.statusText);
            }
        )
    }

    var placeOrder = function(order, call_back){
        $http({
            method : 'POST',
            url : "http://localhost:8081/order",
            data : order
        }).then(
            function successCallback(response) {
                call_back(response);
                console.log("the order has been placed");
            },
            function errorCallback(response) {
                console.log(response.statusText);
            }
        )
    }

    var showOrder = function($mdDialog, $event, order) {
        // Appending dialog to document.body to cover sidenav in docs app
        // Modal dialogs should fully cover application
        // to prevent interaction outside of dialog
        $mdDialog.show(
            $mdDialog.alert()
                .parent(angular.element(document.querySelector('#popupContainer')))
                .clickOutsideToClose(true)
                .title('Order details')
                .textContent(angular.toJson(order, 2))
                .ok('Got it!')
                .targetEvent($event)
        );

    };
    return {
        getProducts: getProducts,
        getOrderedItems : getOrderedItems,
        addItem2Cart : addItem2Cart,
        updateItem2Cart : updateItem2Cart,
        placeOrder : placeOrder,
        showOrder : showOrder
    }
}]);
