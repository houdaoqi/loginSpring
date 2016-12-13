package loginSpring.service.impl;

import loginSpring.dao.ICartDao;
import loginSpring.po.Cart;
import loginSpring.po.OrderedItem;
import loginSpring.service.ICartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lenovo on 11/9/2016.
 */
@Service
public class CartServiceImpl implements ICartService {

    @Resource(name="cartDaoSimpleImpl")
    private ICartDao cartDao;

    public void setCartDao(ICartDao cartDao) {
        this.cartDao = cartDao;
    }

    /**
     * find cart by user name
     * @param userName the user name to be searched
     * @return the cart
     */
    @Override
    public Cart findCartByUser(String userName) {
        return cartDao.selectCart(userName);
    }

    /**
     * save the car for the user
     * @param userName the user name
     * @param cart the cart to be saved
     */
    @Override
    public void saveCart(String userName, Cart cart) {
        cartDao.saveCart(userName, cart);
    }

    /**
     * check if the user has the car
     * @param userName the user name of the user
     * @return true means the user has the cart
     */
    @Override
    public boolean isCartExist(String userName) {
        Cart cart = cartDao.selectCart(userName);
        return cart != null;
    }

    /**
     * retrieve the ordered item based on product name in some cart
     * @param cart the cart
     * @param productName the product name to be searched
     * @return retrieved ordered item
     */
    @Override
    public OrderedItem findItem(Cart cart, String productName) {
        return cartDao.findItem(cart, productName);
    }

    /**
     * add the item to some cart
     * @param cart the cart
     * @param orderedItem the ordered item to be added
     */
    @Override
    public void addItem(Cart cart, OrderedItem orderedItem) {
        cartDao.addItem(cart,orderedItem);
    }

    /**
     * delete the item form some cart based on product name
     * @param cart the cart
     * @param productName the product name to be searched
     */
    @Override
    public void deleteItem(Cart cart, String productName) {
        cartDao.deleteItem(cart, productName);
    }

    /**
     * update the item in some cart based on product name with the new quantity
     * @param cart the cart
     * @param productName the product name to be searched
     * @param newQuantity the new quantity to update
     */
    @Override
    public void updateItem(Cart cart, String productName, int newQuantity) {
        cartDao.updateItem(cart, productName, newQuantity);
    }

    /**
     * check if the item exist in the cart
     * @param cart the cart
     * @param productName the product name to be searched
     * @return true means the item exists in the cart
     */
    @Override
    public boolean isItemExist(Cart cart, String productName){
        return cartDao.isItemExist(cart, productName);
    }
}
