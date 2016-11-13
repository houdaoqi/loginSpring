package loginSpring.dao;

import loginSpring.po.Order;

import java.util.List;

/**
 * Created by lenovo on 11/10/2016.
 */
public interface IOrderDao {

    void insertOrder(Order order);

    List<Order> selectOrderByUserID(long userID);

    Order selectOrderByOrderID(long orderID);

    void deleteOrder(long orderID);
}
