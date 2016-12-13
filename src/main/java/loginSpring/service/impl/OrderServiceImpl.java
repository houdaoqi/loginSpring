package loginSpring.service.impl;

import loginSpring.dao.IOrderDao;
import loginSpring.po.Order;
import loginSpring.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lenovo on 11/10/2016.
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Resource(name="orderDaoSqliteImpl")
    private IOrderDao orderDao;

    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * save the order into database
     * @param order the order to be inserted
     */
    @Override
    public void placeOrder(Order order) {
        orderDao.insertOrder(order);
    }

    /**
     * retrieve the orders based on user ID
     * @param userID the user Id to be searched
     * @return retrieved list of orders
     */
    @Override
    public List<Order> findOrderByUserID(long userID) {
        return orderDao.selectOrderByUserID(userID);
    }

    /**
     * retrieve the order based on the oder Id
     * @param orderID the order Id to be searched
     * @return the retrieved order
     */
    @Override
    public Order findOrderByOderID(long orderID) {
        return orderDao.selectOrderByOrderID(orderID);
    }

    /**
     * delete the order based on order Id
     * @param orderID the order Id
     */
    @Override
    public void revokeOrder(long orderID) {
        orderDao.deleteOrder(orderID);
    }

    /**
     * check if the order exist in database based on the order Id
     * @param orderID the order Id to be searched
     * @return true means the oder exists
     */
    @Override
    public boolean isOrderExist(long orderID) {
        return findOrderByOderID(orderID) != null;
    }
}
