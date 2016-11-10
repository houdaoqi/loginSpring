package loginSpring.po;

import java.io.Serializable;

/**
 * Created by lenovo on 11/9/2016.
 */
public class OrderedItem implements Serializable {
    private Product product;
    private int quantity;

    public OrderedItem() {
    }

    public OrderedItem(Product product) {
        this.product = product;
        quantity = 1;
    }

    public OrderedItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
