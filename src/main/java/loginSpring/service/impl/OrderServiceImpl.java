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
    @Override
    public void placeOrder(Order order) {
        orderDao.insertOrder(order);
    }

    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> findOrderByUserID(long userID) {
        return orderDao.selectOrderByUserID(userID);
    }

    @Override
    public Order findOrderByOderID(long orderID) {
        return orderDao.selectOrderByOrderID(orderID);
    }

    @Override
    public void revokeOrder(long orderID) {
        orderDao.deleteOrder(orderID);
    }

    @Override
    public boolean isOrderExist(long orderID) {
        return findOrderByOderID(orderID) != null;
    }
}
