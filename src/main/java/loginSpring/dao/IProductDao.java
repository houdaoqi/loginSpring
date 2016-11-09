package loginSpring.dao;

import loginSpring.po.Product;

import java.util.List;

/**
 * Created by lenovo on 11/8/2016.
 */
public interface IProductDao {
    void save(Product product);

    int update(Product product);

    void insert(Product product);

    Product select(Product product);

    List<Product> selectAll();
}
