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

    @Override
    public Product findProductByID(long id) {
        return null;
    }

    @Override
    public Product findProductByName(String name) {
        Product product = new Product(name);
        return productDao.select(product);
    }

    @Override
    public boolean isProductExist(String name) {
        Product product = findProductByName(name);
        return product != null;
    }

    @Override
    public List<Product> getProducts() {
        return productDao.selectAll();
    }

    @Override
    public void insertProduct(Product product) {
        productDao.insert(product);
    }

    @Override
    public void saveProduct(Product product) {

    }
}
