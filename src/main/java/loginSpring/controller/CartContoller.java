package loginSpring.controller;

import loginSpring.po.Cart;
import loginSpring.po.OrderedItem;
import loginSpring.po.Product;
import loginSpring.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 11/9/2016.
 */
@RestController
public class CartContoller {

    @Autowired
    private ICartService cartService;
    //show items in cart
    @RequestMapping(value = "/cart/{userName}/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, OrderedItem>> getItems(@PathVariable("userName") String userName) {
        System.out.println("Fetching all items in " + userName + "'s cart ");
        Map<String, OrderedItem> itemsInCart = cartService.findCartByUser(userName).getItems();
        if (itemsInCart == null) {
            System.out.println("No product found");
            return new ResponseEntity<Map<String, OrderedItem>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Map<String, OrderedItem>>(itemsInCart, HttpStatus.OK);
    }

    //add item to cart
    @RequestMapping(value="/cart/{userName}",method= RequestMethod.POST)
    public ResponseEntity<Void> addToCart(@PathVariable("userName") String userName, HttpServletRequest request, HttpServletResponse response, @RequestBody OrderedItem orderedItem, UriComponentsBuilder ucBuilder)
            throws IOException, ServletException
    {

// First get the item values from the request.
//        long productID = Long.parseLong(request.getParameter("productID"));
//        String productName = request.getParameter("productName");
//        int quantity = Integer.parseInt(
//                request.getParameter("quantity"));
//        double price = Double.parseDouble(
//                request.getParameter("price"));

// Now create an item to add to the cart.
//        Product product = new Product(productID, productName, price);
//        OrderedItem item = new OrderedItem(product, quantity);

        HttpSession session = request.getSession();

// Get the cart.
        Cart cart = (Cart) session.
                getAttribute("ShoppingCart");

// If there is no shopping cart, create one.
        if (cart == null)
        {
//            String userName = (String) session.getAttribute("userName");
//            String userName = "hou";//temporary use hou as the userName
            cart = new Cart(userName);
            session.setAttribute("ShoppingCart", cart);
            cartService.saveCart(userName, cart);
        }
        String productName = orderedItem.getProduct().getProductName();
        if (cartService.isItemExist(cart, productName)) {
            System.out.println("A item with name " + productName + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        cartService.addItem(cart, orderedItem);
        System.out.println(orderedItem);

// Now display the cart and allow the user to check out or order more items.
//        ModelAndView model = new ModelAndView("ShowCartAfterAdd");
//        return model;
//        response.sendRedirect(response.encodeRedirectURL(
//                "/shoppingcart/ShowCartAfterAdd.jsp"));
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/cart/{userName}").buildAndExpand(orderedItem.getProduct().getProductName()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
