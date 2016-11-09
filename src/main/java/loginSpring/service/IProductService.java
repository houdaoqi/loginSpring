package loginSpring.service;

import loginSpring.po.Product;

import java.util.List;

/**
 * Created by lenovo on 11/8/2016.
 */
public interface IProductService {

    Product findProductByName(String name);

    boolean isProductExist(String name);

    List<Product> getProducts();

    void insertProduct(Product product);

    void saveProduct(Product product);

}
