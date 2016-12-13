package loginSpring.service;

import loginSpring.po.Order;

import java.util.List;

/**
 * Created by lenovo on 11/10/2016.
 */
public interface IOrderService {

    void placeOrder(Order order);

    List<Order> findOrderByUserID(long userID);

    Order findOrderByOderID(long orderID);

    void revokeOrder(long orderID);

    boolean isOrderExist(long orderID);
}
