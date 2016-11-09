package loginSpring.po;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 11/8/2016.
 */
public class Cart {

    private User user;
    //the list of item names
    private List<String> itemList;
    //the key is the item name; the value is quantity
    private Map<Product, Integer> items;

    public List<String> getItemList() {
        return itemList;
    }

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }

    public Cart(User user, List<String> itemList, Map<Product, Integer> items) {
        this.user = user;

        this.itemList = itemList;
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Product, Integer> items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((items == null) ? 0 : items.hashCode());

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
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
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
        stringBuilder.append(user.toString());
        for(Product product : items.keySet()){
            stringBuilder.append(product.toString());
            stringBuilder.append(" Quantity: " + items.get(product));
        }
        return stringBuilder.toString();
    }
}
