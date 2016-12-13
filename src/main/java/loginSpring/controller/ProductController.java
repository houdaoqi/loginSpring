package loginSpring.controller;

import loginSpring.po.Product;
import loginSpring.po.User;
import loginSpring.service.IProductService;
import loginSpring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 11/8/2016.
 */
@RestController
public class ProductController {

    @Autowired
    IProductService productService;

    /**
     * Retrieve single product by product name
     * @param name the product name to be searched
     * @return the response which includes the retrieved product
     */
    @RequestMapping(value = "/product/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("name") String name) {
        System.out.println("Fetching product with name " + name);
        Product product = productService.findProductByName(name);
        if (product == null) {
            System.out.println("Product with name " + name + " not found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    //Retrieve single product by product ID

//    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
//        System.out.println("Fetching product with name " + name);
//        Product product = productService.findProductByName(name);
//        if (product == null) {
//            System.out.println("Product with name " + name + " not found");
//            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<Product>(product, HttpStatus.OK);
//    }

    /**
     * Retrieve all products
     * @return the response which includes all products
     */
    @RequestMapping(value = "/product/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProducts() {
        System.out.println("Fetching all products ");
        List<Product> productList = productService.getProducts();
        if (productList == null) {
            System.out.println("No product found");
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }

    /**
     * Create a product
     * @param product the product to be inserted
     * @param ucBuilder build the parth for the added product
     * @return http response which includes https status code
     */
    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity<Void> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating product " + product.getProductID() + " " + product.getProductName() + " " + product.getPrice());

        if (productService.isProductExist(product.getProductName())) {
            System.out.println("A product with name " + product.getProductName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        productService.insertProduct(product);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/product/{name}").buildAndExpand(product.getProductName()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
