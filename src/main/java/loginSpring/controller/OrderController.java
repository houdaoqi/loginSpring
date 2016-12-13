package loginSpring.controller;

import loginSpring.po.Order;
import loginSpring.po.Product;
import loginSpring.service.IOrderService;
import loginSpring.service.IProductService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by lenovo on 11/11/2016.
 */
@RestController
public class OrderController {

    @Autowired
    IOrderService orderService;

    /**
     * Retrieve single order based on order Id
     * @param orderID the oder Id to be searched
     * @return response which includes the retrieved order
     */
    @RequestMapping(value = "/order/{orderID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getProduct(@PathVariable("orderID") long orderID) {
        System.out.println("Fetching order with orderID " + orderID);
        Order order = orderService.findOrderByOderID(orderID);
        if (order == null) {
            System.out.println("Order with orderID " + orderID + " not found");
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    /**
     * save a order into database
     * @param order the order to be placed
     * @param ucBuilder build path for added order
     * @return http response which includes the added order
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<Order> createProduct(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
        System.out.println("Placing order " + order.getOrderID());

        if (orderService.isOrderExist(order.getOrderID())) {
            System.out.println("A order with OrderID " + order.getOrderID() + " already exist");
            return new ResponseEntity<Order>(HttpStatus.CONFLICT);
        }

        orderService.placeOrder(order);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/order/{orderID}").buildAndExpand(order.getOrderID()).toUri());
        return new ResponseEntity<Order>(order, headers, HttpStatus.CREATED);
    }
}
