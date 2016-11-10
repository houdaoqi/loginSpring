package loginSpring.dao;

import loginSpring.po.Cart;
import loginSpring.po.OrderedItem;

/**
 * Created by lenovo on 11/9/2016.
 */
public interface ICartDao {

    void saveCart(String userName, Cart cart);

    int updateCart(String userName, Cart cart);

    void insertCart(String userName, Cart cart);

    Cart selectCart(String userName);

    OrderedItem findItem(Cart cart, String productName);

    void addItem(Cart cart, OrderedItem orderedItem);

    void deleteItem(Cart cart, String productName);

    void updateItem(Cart cart, String productName, int newQuantity);

    boolean isItemExist(Cart cart, String productName);

}
