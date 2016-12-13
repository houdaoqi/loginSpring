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

    /**
     * create the cart for the user if the cart not existed, ortherwise update the cart
     * @param userName the user who owns the cart
     * @param cart the new cart to be saved
     */
    @Override
    public void saveCart(String userName, Cart cart){
        if(updateCart(userName, cart) == 0){
            insertCart(userName, cart);
        }
    }

    /**
     * update the existing cart for the user
     * @param userName the user who owns the cart
     * @param cart the new cart value
     * @return
     */
    @Override
    public int updateCart(String userName, Cart cart) {
        if(map.containsKey(userName)){
            insertCart(userName, cart);
            return 1;
        }else return 0;
    }

    /**
     * create the cart for the user if user does not have a cart yet
     * @param userName the user who owns the cart
     * @param cart the cart to be created
     */
    @Override
    public void insertCart(String userName, Cart cart) {
        if(!map.containsKey(userName)) {
            map.put(userName, cart);
            System.out.printf("cart[%s] is inserted...\n", cart);
        }
    }

    /**
     * retrieve the cart based on the user name
     * @param userName user name to be searched
     * @return the cart
     */
    @Override
    public Cart selectCart(String userName) {
        return map.get(userName);
    }

    /**
     * retrieve the ordered item based on cart and product name
     * @param cart the cart to be searched
     * @param productName the product name to be searched
     * @return the ordered item
     */
    @Override
    public OrderedItem findItem(Cart cart, String productName) {
        return cart.getItems().get(productName);
    }

    /**
     * add the ordered item into the cart
     * @param cart the cart
     * @param orderedItem the ordered item to be added
     */
    @Override
    public void addItem(Cart cart, OrderedItem orderedItem) {
        if(orderedItem.getQuantity()<=0) return;
        if(!cart.getItems().containsKey(orderedItem.getProduct().getProductName())){
            cart.getItems().put(orderedItem.getProduct().getProductName(), orderedItem);
        }
    }

    /**
     * delete the ordered item from the cart
     * @param cart the cart
     * @param productName the product name of the ordered item to be deleted
     */
    @Override
    public void deleteItem(Cart cart, String productName) {
        cart.getItems().remove(productName);
    }

    /**
     * update the quantity of the ordered item in the cart
     * @param cart the cart
     * @param productName the product name of the ordered item
     * @param newQuantity the new quantity
     */
    @Override
    public void updateItem(Cart cart, String productName, int newQuantity) {
        if(cart.getItems().containsKey(productName)){
            if(newQuantity == 0){
                deleteItem(cart, productName);
            }else if(newQuantity > 0)
                cart.getItems().get(productName).setQuantity(newQuantity);
        }
    }

    /**
     * check if the cart contains some product
     * @param cart the cart
     * @param productName the product name to be searched
     * @return true means exist, false means not exist
     */
    @Override
    public boolean isItemExist(Cart cart, String productName) {
        return cart.getItems().containsKey(productName);
    }
}
