/**
 * Created by lenovo on 11/17/2016.
 */
function place_order() {
    console.log("the order is placing...");
    var mailAddress = $("#address").val();
    var credit = $("#creditNumber").val();
    var userName = Cookies.get("userName");
    var userID = 1;
    var totalPrice = $("#totalPrice").text();
    console.log(mailAddress);
    console.log(credit);
    console.log("totalPrice: " + totalPrice);
    $.ajax({
        url: 'http://localhost:8081/user/' + userName,
    }).then(function (data) {
        userID = data['id'];
        console.log(userID);
    });
    var orderID = generate_id();
    console.log(orderID);
    var orderedItemArray = [];
    $.ajax({
        url: 'http://localhost:8081/cart/' + userName + '/all',
    }).then(function (data) {
        $.each(data, function () {
            var orderedItemID = generate_id();
            orderedItemArray.push({
                "id" : orderedItemID,
                "product": {
                    "productID" : this['product'].productID,
                    "productName" : this['product'].productName,
                    "price" : this['product'].price,
                },
                "quantity" : this['quantity']
            });
            // table.append('<tr><td>' + this['product'].productID + '</td><td id="nameInCart_' + this['product'].productID + '">' + this['product'].productName + '</td><td id="priceInCart_' + this['product'].productID + '">' + this['product'].price + '</td><td><input id="quantityInCart_' + this['product'].productID + '" value=' + this['quantity'] + ' /></td><td><button onclick="update_cart(' + this['product'].productID + ')">update cart</button></td></tr>');

        });
        console.log('ordered item array-----------------------------');
        console.log(orderedItemArray);

        var order = {
            "orderID" : orderID,
            "orderedItemList" : orderedItemArray,
            "totalPrice" : totalPrice,
            "address" : mailAddress,
            "creditNumber" : credit,
            "userID" : userID
        };

        $.post(
            "http://localhost:8081/order",
            JSON.stringify(order),
            // order,
            function () {
                show_cart();
                console.log("the order has been placed");
                $("#order-dialog").html(JSON.stringify(order, null, 2));
                $("#order-dialog").dialog("open");
                // $.ajax({
                //     url: 'http://localhost:8081/order/' + orderID ,
                // }).then(function (data) {
                //     console.log("the placed order info------------------");
                //     console.log(data);
                //     console.log("------------------");
                //     // $("#order-dialog").html(data);
                //     $("#order-dialog").text(data);
                //     $("#order-dialog").dialog("open");
                // });
            }
        );
    });




}

function generate_id() {
    var id = Math.floor((Math.random() * 1000) + 1) + Date.now();
    return id;
}