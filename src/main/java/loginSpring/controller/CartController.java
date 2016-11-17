package loginSpring.controller;

import loginSpring.common.LoginException;
import loginSpring.common.ResultCode;
import loginSpring.po.Cart;
import loginSpring.po.OrderedItem;
import loginSpring.service.ICartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 11/9/2016.
 */
@RestController
public class CartController {

    @Autowired
    private ICartService cartService;

    private final Logger logger = LoggerFactory.getLogger(CartController.class);

    //show items in cart
    @RequestMapping(value = "/cart/{userName}/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, OrderedItem>> getItems(@PathVariable("userName") String userName, HttpServletRequest request) {
        HttpSession session = request.getSession();
//Get the logged in user name
//        String loggedUserName = (String)session.getAttribute("userName");
//        if(loggedUserName == null || !loggedUserName.equals(userName)){
//            logger.error(userName + " has not log in");
//            return new ResponseEntity<Map<String, OrderedItem>>(HttpStatus.UNAUTHORIZED);
//        }
//        userName = loggedUserName;
        System.out.println("Fetching all items in " + userName + "'s cart ");
        Cart cart = cartService.findCartByUser(userName);
        Map<String, OrderedItem> itemsInCart = null;
        if(cart != null) itemsInCart = cart.getItems();
        if (itemsInCart == null) {
            System.out.println("No product found");
            return new ResponseEntity<Map<String, OrderedItem>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Map<String, OrderedItem>>(itemsInCart, HttpStatus.OK);
    }

    //show single item in cart
    @RequestMapping(value = "/cart/{userName}/{productName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderedItem> getItems(@PathVariable("userName") String userName,@PathVariable("productName") String productName, HttpServletRequest request) {
        HttpSession session = request.getSession();
//Get the logged in user name
//        String loggedUserName = (String)session.getAttribute("userName");
//        if(loggedUserName == null || !loggedUserName.equals(userName)){
//            logger.error(userName + " has not log in");
//            return new ResponseEntity<Map<String, OrderedItem>>(HttpStatus.UNAUTHORIZED);
//        }
//        userName = loggedUserName;
        System.out.println("Fetching single item" + productName + " in " + userName + "'s cart ");
        Cart cart = cartService.findCartByUser(userName);
        OrderedItem item = null;
        if(cart != null) item = cartService.findItem(cart, productName);
        if (item == null) {
            System.out.println("No product found");
            return new ResponseEntity<OrderedItem>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<OrderedItem>(item, HttpStatus.OK);
    }

    //add item to cart
    @RequestMapping(value="/cart/{userName}",method= RequestMethod.POST)
    public ResponseEntity<OrderedItem> addToCart(@PathVariable("userName") String userName, HttpServletRequest request, HttpServletResponse response, @RequestBody OrderedItem orderedItem, UriComponentsBuilder ucBuilder)
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
//Get the logged in user name
//        String loggedUserName = (String)session.getAttribute("userName");
//        if(loggedUserName == null || !loggedUserName.equals(userName)){
////            throw new LoginException(userName + " has not log in", ResultCode.NOT_LOGIN_YET);
//            logger.error(userName + " has not log in");
//            return new ResponseEntity<OrderedItem>(HttpStatus.UNAUTHORIZED);
//        }
//        userName = loggedUserName;
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
            //return new ResponseEntity<OrderedItem>(HttpStatus.CONFLICT);
            //if exist update quantity = old value + new value
            int oldQuantity = cartService.findItem(cart, productName).getQuantity();
            int newQuantity = orderedItem.getQuantity() + oldQuantity;
            cartService.updateItem(cart, productName, newQuantity);
        }else
            cartService.addItem(cart, orderedItem);
        System.out.println(orderedItem);

// Now display the cart and allow the user to check out or order more items.
//        ModelAndView model = new ModelAndView("ShowCartAfterAdd");
//        return model;
//        response.sendRedirect(response.encodeRedirectURL(
//                "/shoppingcart/ShowCartAfterAdd.jsp"));
        HttpHeaders headers = new HttpHeaders();
        List<String> expandedURI = new ArrayList<>();
        expandedURI.add(userName);
        expandedURI.add(orderedItem.getProduct().getProductName());
        //headers.setLocation(ucBuilder.path("/cart/{userName}/{productName}").buildAndExpand(expandedURI).toUri());
//        return new ResponseEntity<OrderedItem>(orderedItem, headers, HttpStatus.CREATED);
        return new ResponseEntity<OrderedItem>(orderedItem, HttpStatus.CREATED);
    }

    //update items in cart
    @RequestMapping(value="/cart/{userName}",method= RequestMethod.PUT)
    public ResponseEntity<OrderedItem> updateCart(@PathVariable("userName") String userName, HttpServletRequest request, HttpServletResponse response, @RequestBody OrderedItem orderedItem, UriComponentsBuilder ucBuilder)
            throws IOException, ServletException
    {
        HttpSession session = request.getSession();
//Get the logged in user name
//        String loggedUserName = (String)session.getAttribute("userName");
//        if(loggedUserName == null || !loggedUserName.equals(userName)){
//            logger.error(userName + " has not log in");
//            return new ResponseEntity<OrderedItem>(HttpStatus.UNAUTHORIZED);
//        }
//        userName = loggedUserName;

// Get the cart.
        Cart cart = (Cart) session.
                getAttribute("ShoppingCart");

// If there is no shopping cart, create one.
        if (cart == null)
        {
            cart = new Cart(userName);
            session.setAttribute("ShoppingCart", cart);
            cartService.saveCart(userName, cart);
        }
        String productName = orderedItem.getProduct().getProductName();
        if (!cartService.isItemExist(cart, productName)) {
            System.out.println("A item with name " + productName + " does not exist");
            return new ResponseEntity<OrderedItem>(HttpStatus.CONFLICT);
        }
        cartService.updateItem(cart, orderedItem.getProduct().getProductName(), orderedItem.getQuantity());
        System.out.println(orderedItem);

        HttpHeaders headers = new HttpHeaders();
        List<String> expandedURI = new ArrayList<>();
        expandedURI.add(userName);
        expandedURI.add(orderedItem.getProduct().getProductName());
//        headers.setLocation(ucBuilder.path("/cart/{userName}/{product}").buildAndExpand(expandedURI).toUri());
//        return new ResponseEntity<OrderedItem>(orderedItem, headers, HttpStatus.CREATED);
        return new ResponseEntity<OrderedItem>(orderedItem, HttpStatus.CREATED);
    }
}