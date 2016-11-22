/**
 * Created by lenovo on 11/15/2016.
 */
// $(document).ready(function() {
$.ajax({
    url: "http://localhost:8081/product/all"
}).then(function (data) {
    // $('.greeting-id').append(data.id);
    // $('.greeting-content').append(data.content);
    // var table = '<table><thead><th>Product ID</th><th>Product Name</th><th>Price</th></thead><tbody>';
    // var table;
    var table = $("#dataTable");
    var obj = data;
    $.each(obj, function () {
        table.append('<tr><td>' + this['productID'] + '</td><td id="name_' + this['productID'] + '">' + this['productName'] + '</td><td id="price_' + this['productID'] + '">' + this['price'] + '</td><td><input id="quantity_' + this['productID'] + '" value="1" /></td><td><button onclick="cart(' + this['productID'] + ')">Add to cart</button></td></tr>');
        // table += '<tr><td>' + this['productID'] + '</td><td>' + this['productName'] + '</td><td>' + this['price'] +'</td></tr>';
        // table += '<div class="products" id=' + this['productID'] + '>'
        //     + '<input type="button" value = "Add to Cart" onclick="cart(' + this['producID'] + ')' + '">'
        //     + '<p>Product ID:' + this['productID'] + '</p>'
        //     + '<p>Product Name:' + this['productName'] + '</p>'
        //     + '<p>Product Price:' + this['price'] + '</p>';
    });
    // table += '</tbody></table>';
    // document.getElementById("datalist").innerHTML = table;
});
function cart(id) {
    //retrieve item info from table
    var name = $("#name_" + id).text();
    var price = $("#price_" + id).text();
    var quantity = $("#quantity_" + id).val();
    // console.log(id);
    // console.log(name);
    // console.log(price);
    // console.log(quantity);
    //retrieve user name from cookie
    var userName = Cookies.get("userName");
    console.log(userName);
    // $.ajax({
    //     type:'post',
    //     url:'http://localhost:8081/cart/' + userName,
    //     data:JSON.stringify({"product" : {"productID" : id,"productName" : name,"price" : price},"quantity" : quantity}),
    //     contentType: 'application/json; charset=utf-8',
    //     dataType: 'application/json',
    //     success:function(response) {
    //         // document.getElementById("total_items").value=response;
    //         console.log(response);
    //         console.log("the item has been added to cart");
    //         show_cart();
    //     },
    //     failure: function(errMsg){
    //         alert(errMsg);
    //     }
    // });
    $.ajaxSetup({
        contentType: 'application/json'
    });
    $.post(
        "http://localhost:8081/cart/" + userName,
        JSON.stringify({"product": {"productID": id, "productName": name, "price": price}, "quantity": quantity}),
        function () {
            show_cart();
            console.log("the item has been added to cart");
        }
    );
    // show_cart();
}

function show_cart() {

    console.log("in show_cart........................");
    //retrieve user name from cookie
    var userName = Cookies.get("userName");
    // $("#cartItemTable tr:gt(0)").remove();
    // $("#cartItemTable").find("tr:gt(0)").remove();
    $.ajax({
        url: 'http://localhost:8081/cart/' + userName + '/all',
    }).then(function (data) {
        var totalPrice = 0;
        var table = $("#cartItemTable");
        var tbody = $("#cartItemTable tbody");
        tbody.empty();
        $.each(data, function () {
            table.append('<tr><td>' + this['product'].productID + '</td><td id="nameInCart_' + this['product'].productID + '">' + this['product'].productName + '</td><td id="priceInCart_' + this['product'].productID + '">' + this['product'].price + '</td><td><input id="quantityInCart_' + this['product'].productID + '" value=' + this['quantity'] + ' /></td><td><button onclick="update_cart(' + this['product'].productID + ')">update cart</button></td></tr>');
            totalPrice += this['product'].price*this['quantity'];
        });
        $("#totalPrice").html(totalPrice);
    });
}

function update_cart(id) {
    var name = $("#nameInCart_" + id).text();
    var price = $("#priceInCart_" + id).text();
    var quantity = $("#quantityInCart_" + id).val();
    var userName = Cookies.get("userName");
    console.log("in update_cart ------------------------------------");
    $.ajax({
        type:'put',
        url:'http://localhost:8081/cart/' + userName,
        data:JSON.stringify({"product" : {"productID" : id,"productName" : name,"price" : price},"quantity" : quantity}),
        contentType: 'application/json; charset=utf-8',
        // dataType: 'application/json'
    }).then(function(){
        show_cart();
        console.log("the item has been updated in cart");
    });
}

// })