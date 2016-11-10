package loginSpring.dao.impl;

import loginSpring.dao.ICartDao;
import loginSpring.po.Cart;
import loginSpring.po.OrderedItem;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 11/9/2016.
 */
@Repository
public class CartDaoSimpleImpl implements ICartDao{
    //the key is the userName and the value is Cart
    private Map<String, Cart> map;

    public CartDaoSimpleImpl() {
        this.map = new HashMap<>();
    }

    @Override
    public void saveCart(String userName, Cart cart){
        if(updateCart(userName, cart) == 0){
            insertCart(userName, cart);
        }
    }

    @Override
    public int updateCart(String userName, Cart cart) {
        if(map.containsKey(userName)){
            insertCart(userName, cart);
            return 1;
        }else return 0;
    }

    @Override
    public void insertCart(String userName, Cart cart) {
        if(!map.containsKey(userName)) {
            map.put(userName, cart);
            System.out.printf("cart[%s] is inserted...\n", cart);
        }
    }

    @Override
    public Cart selectCart(String userName) {
        return map.get(userName);
    }

    @Override
    public OrderedItem findItem(Cart cart, String productName) {
        return cart.getItems().get(productName);
    }

    @Override
    public void addItem(Cart cart, OrderedItem orderedItem) {
        if(orderedItem.getQuantity()<=0) return;
        if(!cart.getItems().containsKey(orderedItem.getProduct().getProductName())){
            cart.getItems().put(orderedItem.getProduct().getProductName(), orderedItem);
        }
    }

    @Override
    public void deleteItem(Cart cart, String productName) {
        cart.getItems().remove(productName);
    }

    @Override
    public void updateItem(Cart cart, String productName, int newQuantity) {
        if(cart.getItems().containsKey(productName)){
            if(newQuantity == 0){
                deleteItem(cart, productName);
            }else if(newQuantity > 0)
                cart.getItems().get(productName).setQuantity(newQuantity);
        }
    }

    @Override
    public boolean isItemExist(Cart cart, String productName) {
        return cart.getItems().containsKey(productName);
    }
}
