package loginSpring.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 11/10/2016.
 */
public class Order implements Serializable{

    private long orderID;

    private List<OrderedItem> orderedItemList;

    private double totalPrice;

    private String address;

    private String creditNumber;

    private long userID;

    public Order() {
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public List<OrderedItem> getOrderedItemList() {
        return orderedItemList;
    }

    public void setOrderedItemList(List<OrderedItem> orderedItemList) {
        this.orderedItemList = orderedItemList;
        totalPrice=0;
        for(OrderedItem orderedItem : orderedItemList){
            totalPrice+=(orderedItem.getQuantity()*orderedItem.getProduct().getPrice());
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(String creditNumber) {
        this.creditNumber = creditNumber;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
