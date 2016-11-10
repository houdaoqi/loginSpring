package loginSpring.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 11/8/2016.
 */
public class Cart implements Serializable {

    private String userName;
    //the list of item ID
//    private List<Long> itemList;
    //the key is the item name; the value is quantity
    private Map<String, OrderedItem> items;

//    public List<Long> getItemList() {
//        return itemList;
//    }

//    public void setItemList(List<Long> itemList) {
//        this.itemList = itemList;
//    }

    public Cart(String userName) {
        this.userName = userName;
//        this.itemList = new ArrayList<>();
        this.items = new HashMap<>();
    }

    public Cart(String userName, Map<String, OrderedItem> items) {
        this.userName = userName;
//        this.itemList = itemList;
        this.items = items;
    }

    public String getUser() {
        return userName;
    }

    public void setUser(String userName) {
        this.userName = userName;
    }

    public Map<String, OrderedItem> getItems() {
        return items;
    }

    public void setItems(Map<String, OrderedItem> items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((items == null) ? 0 : items.hashCode());
//        result = prime * result + ((itemList == null) ? 0 : itemList.hashCode());
        result = prime * result;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cart other = (Cart) obj;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (items == null) {
            if (other.items != null)
                return false;
        } else if (!items.equals(other.items))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(userName.toString());
        for(String productName : items.keySet()){
            OrderedItem orderedItem = items.get(productName);
            stringBuilder.append(orderedItem.getProduct());
            stringBuilder.append(" Quantity: " + orderedItem.getQuantity());
        }
        return stringBuilder.toString();
    }
}

