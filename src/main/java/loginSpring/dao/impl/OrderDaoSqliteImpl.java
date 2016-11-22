package loginSpring.dao.impl;

import loginSpring.dao.IOrderDao;
import loginSpring.po.Order;
import loginSpring.po.OrderedItem;
import loginSpring.po.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 11/10/2016.
 */
@Repository
public class OrderDaoSqliteImpl implements IOrderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public final void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertOrder(Order order) {
        System.out.println("The insertOrder function in OrderDaoSqliteImpl class is called");
        String sql = "INSERT INTO orders " +
                "(orderId, totalPrice, address, creditNumber, userID) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, new Object[]{order.getOrderID(), order.getTotalPrice(),
                order.getAddress(), order.getCreditNumber(), order.getUserID()
        });
        insertOrderedItems(order.getOrderID(), order.getOrderedItemList());
    }

    public void insertOrderedItems(long orderID, List<OrderedItem> orderedItemList) {
        for (OrderedItem orderedItem : orderedItemList) {
            String sql = "INSERT INTO orderedItem " +
                    "(id, orderID, productID, productName, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, new Object[]{orderedItem.getId(), orderID,
                    orderedItem.getProduct().getProductID(), orderedItem.getProduct().getProductName(),
                    orderedItem.getProduct().getPrice(), orderedItem.getQuantity()
            });
        }
    }

    @Override
    public List<Order> selectOrderByUserID(long userID) {
        return null;
    }

    @Override
    public Order selectOrderByOrderID(long orderID) {
        System.out.println("The selectOrderByOrderID function in OrderDaoSqliteImpl class is called");
        String sql = "select orderId, address, creditNumber, userID" +
                " from orders where orderID = ?";
        Object[] param = new Object[]{orderID};
        int[] types = {Types.INTEGER};
        Order result = null;
        System.out.println("Order ID is: " + orderID);
        try {
            result = jdbcTemplate.queryForObject(sql, param, types, new RowMapper<Order>() {
                @Override
                public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                    Order order = new Order();
//                    order.setOrderID(resultSet.getInt(1));
                    order.setOrderID(resultSet.getLong(1));
                    order.setAddress(resultSet.getString(2));
                    order.setCreditNumber(resultSet.getString(3));
//                    order.setUserID(resultSet.getInt(4));
                    order.setUserID(resultSet.getLong(4));
                    return order;
                }
            });
            selectOrderedItemsByOrderID(orderID, result);
        } catch (Throwable e) {
//            e.printStackTrace(System.err);
//            logger.error("query Order error ",e);
            System.out.println("The exception is: " + e.getMessage());
            result=null;
        }

        return result;
    }

    void selectOrderedItemsByOrderID(long orderID, Order order) {
        System.out.println("The selectOrderedItemsByOrderID function in OrderDaoSqliteImpl class is called");
        String sql = "SELECT id, productID, productName, price, quantity FROM orderedItem WHERE orderID=?";
        Object[] param = new Object[]{orderID};
        int[] types = {Types.INTEGER};
        List<OrderedItem> orderedItems = new ArrayList<>();
//        List<OrderedItem> rows = jdbcTemplate.queryForList(sql, param, types,OrderedItem.class);
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, param, types);
        for (Map row : rows) {
            OrderedItem item = new OrderedItem();
//            item.setId((int)row.get("id"));
            item.setId((long)row.get("id"));
            item.setQuantity((int)row.get("quantity"));
            Product product = new Product();
//            product.setProductID((int)row.get("productID"));
            Integer productIDI = (int)row.get("productID");
            Long productIDL = productIDI.longValue();
            product.setProductID(productIDL);
            product.setProductName((String)row.get("productName"));
            product.setPrice((double)row.get("price"));
            item.setProduct(product);
            orderedItems.add(item);
        }
        order.setOrderedItemList(orderedItems);
//        List<OrderedItem> items  = jdbcTemplate.query(sql, param, types,
//                new BeanPropertyRowMapper(OrderedItem.class));
//        order.setOrderedItemList(items);
    }

    @Override
    public void deleteOrder(long orderID) {

    }
}
