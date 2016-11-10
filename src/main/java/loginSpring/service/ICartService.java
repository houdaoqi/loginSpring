package loginSpring.service;

import loginSpring.po.Cart;
import loginSpring.po.OrderedItem;
import loginSpring.po.Product;

/**
 * Created by lenovo on 11/9/2016.
 */
public interface ICartService {

    Cart findCartByUser(String userName);

    void saveCart(String userName, Cart cart);

    boolean isCartExist(String userName);

    OrderedItem findItem(Cart cart, String productName);

    void addItem(Cart cart, OrderedItem orderedItem);

    void deleteItem(Cart cart, String productName);

    void updateItem(Cart cart, String productName, int newQuantity);

    boolean isItemExist(Cart cart, String productName);
}
