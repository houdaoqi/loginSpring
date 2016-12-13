package loginSpring.service.impl;

import loginSpring.dao.IProductDao;
import loginSpring.po.Product;
import loginSpring.service.IProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lenovo on 11/8/2016.
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Resource(name="productDaoSqliteImpl")
    private IProductDao productDao;

    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * retrieve the product by product id
     * @param id the product id
     * @return the retrieved product
     */
    @Override
    public Product findProductByID(long id) {
        return null;
    }

    /**
     * retrieve product based on the product name
     * @param name the product name
     * @return the retrieved product
     */
    @Override
    public Product findProductByName(String name) {
        Product product = new Product(name);
        return productDao.select(product);
    }

    /**
     * check if the product exists in database based on product name
     * @param name the prodcut name
     * @return true means exists
     */
    @Override
    public boolean isProductExist(String name) {
        Product product = findProductByName(name);
        return product != null;
    }

    /**
     * retrieve all products in database
     * @return a list of products
     */
    @Override
    public List<Product> getProducts() {
        return productDao.selectAll();
    }

    /**
     * insert the product in database
     * @param product the product to be inserted
     */
    @Override
    public void insertProduct(Product product) {
        productDao.insert(product);
    }

    @Override
    public void saveProduct(Product product) {

    }
}
