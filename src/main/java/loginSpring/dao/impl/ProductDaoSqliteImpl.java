package loginSpring.dao.impl;

import loginSpring.dao.IProductDao;
import loginSpring.po.Product;
import loginSpring.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 11/8/2016.
 */
@Repository
public class ProductDaoSqliteImpl implements IProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public final void setJdbcTemplate(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public void save(Product product) {

    }

    @Override
    public int update(Product product) {
        return 0;
    }

    @Override
    public void insert(Product product) {
        String sql = "INSERT INTO product " +
                "(id, name, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, new Object[] { product.getProductID(),
                product.getProductName(),product.getPrice()
        });
    }

    @Override
    public Product select(Product product) {
        System.out.println("The select function in ProductDaoSqliteImpl class is called");
        System.out.println("The to find product is:" + product.getProductName());
        String sql = "select id, name, price from product where name = ?";
        Object[] param = new Object[]{product.getProductName()};
        int[] types = {Types.VARCHAR};
        Product result = null;
        try {
            result = jdbcTemplate.queryForObject(sql, param, types, new RowMapper<Product>() {
                @Override
                public Product mapRow(ResultSet resultSet, int i) throws SQLException {
                    Product p = new Product();
                    p.setProductID(resultSet.getInt(1));
                    p.setProductName(resultSet.getString(2));
                    p.setPrice(resultSet.getFloat(3));
                    return p;
                }
            });
        }catch (EmptyResultDataAccessException e){
            //e.printStackTrace();
            System.out.println("The exception is: " + e.getMessage());
            //throw new LoginException("No user found", e, ResultCode.NO_USER);
            return null;
        }
//        if(!result.getUserPassword().equals(toFindUser.getUserPassword())){
//            throw new LoginException("User found, but password invalid", new RuntimeException(), ResultCode.INVALID_PASS);
//        }
        //System.out.println(result.getName());
        //System.out.println(result.getPassword());
        return new Product(result.getProductID(), result.getProductName(), result.getPrice());
    }

    @Override
    public List<Product> selectAll() {
        System.out.println("The selectAll function in ProductDaoSqliteImpl class is called");
        String sql = "SELECT id, name, price FROM product";
        List<Product> products = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Product product = new Product();
            product.setProductID((int)row.get("id"));
            product.setProductName((String)row.get("name"));
            product.setPrice((double)row.get("price"));
            products.add(product);
        }

        for(Product product : products){
            System.out.println("Product ID: " + product.getProductID());
            System.out.println("Product Name: " + product.getProductName());
            System.out.println("Product Price: " + product.getPrice());
        }

        return products;
    }
}
