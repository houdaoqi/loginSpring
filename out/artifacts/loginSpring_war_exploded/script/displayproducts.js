/**
 * Created by lenovo on 11/15/2016.
 */
// $(document).ready(function() {
    $.ajax({
        url: "http://localhost:8081/product/all"
    }).then(function(data) {
        // $('.greeting-id').append(data.id);
        // $('.greeting-content').append(data.content);
        // var table = '<table><thead><th>Product ID</th><th>Product Name</th><th>Price</th></thead><tbody>';
        // var table;
        var table=$("#dataTable");
        var obj = data;
            $.each(obj, function() {
                table.append('<tr><td>' + this['productID'] + '</td><td id="name_'+this['productID']+'">' + this['productName'] + '</td><td id="price_'+this['productID']+'">' + this['price'] +'</td><td><input id="quantity_'+this['productID']+'" value="1" /></td><td><button onclick="cart('+this['productID']+')">add to cart</button></td></tr>');
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
function cart(id)
{
    //retrieve item info from table
    var name = $("#name_"+id).text();
    var price = $("#price_"+id).text();
    var quantity = $("#quantity_"+id).val();
    console.log(id);
    console.log(name);
    console.log(price);
    console.log(quantity);
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
        contentType : 'application/json'
    });
    $.post(
        "http://localhost:8081/cart/" + userName,
        JSON.stringify({"product" : {"productID" : id,"productName" : name,"price" : price},"quantity" : quantity}),
        function() {
            show_cart(name);
            console.log("the item has been added to cart");
        }
    );
    // show_cart();
}

function show_cart(name)
{
    //retrieve user name from cookie
    var userName = Cookies.get("userName");
    $.ajax({
        url:'http://localhost:8081/cart/'+userName+'/'+name,
    }).then(function(data) {
        var table=$("#cartItemTable");
        table.append('<tr><td>' + data['product'].productID + '</td><td>' + data['product'].productName + '</td><td>' + data['product'].price + '</td><td><input id="quantity_' + data['productID'] + '" value='+data['quantity']+' /></td><td><button onclick="cart('+data['productID']+')">update cart</button></td></tr>');
    });
}

// })