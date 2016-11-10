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

    @Override
    public Cart findCartByUser(String userName) {
        return cartDao.selectCart(userName);
    }

    @Override
    public void saveCart(String userName, Cart cart) {
        cartDao.saveCart(userName, cart);
    }

    @Override
    public boolean isCartExist(String userName) {
        Cart cart = cartDao.selectCart(userName);
        return cart != null;
    }

    @Override
    public OrderedItem findItem(Cart cart, String productName) {
        return cartDao.findItem(cart, productName);
    }

    @Override
    public void addItem(Cart cart, OrderedItem orderedItem) {
        cartDao.addItem(cart,orderedItem);
    }

    @Override
    public void deleteItem(Cart cart, String productName) {
        cartDao.deleteItem(cart, productName);
    }

    @Override
    public void updateItem(Cart cart, String productName, int newQuantity) {
        cartDao.updateItem(cart, productName, newQuantity);
    }
    @Override
    public boolean isItemExist(Cart cart, String productName){
        return cartDao.isItemExist(cart, productName);
    }
}
