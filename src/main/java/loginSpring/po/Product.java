package loginSpring.po;

import java.io.Serializable;

/**
 * Created by lenovo on 11/8/2016.
 */
public class Product implements Serializable {

    private long productID;

    private String productName;

    private double price;

    public Product() {
    }

    public Product(String productName) {
        this.productName = productName;
    }

    public Product(long productID, String productName, double price) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (productID ^ (productID >>> 32));
        result = prime * result + ((productName == null) ? 0 : productName.hashCode());
        result = prime * result + (int)(price);

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
        Product other = (Product) obj;
        if (productID != other.productID || price != other.price)
            return false;
        if (productName == null) {
            if (other.productName != null)
                return false;
        } else if (!productName.equals(other.productName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [id=" + productID + ", name=" + productName + ", price=" + price + "]";
    }
}
