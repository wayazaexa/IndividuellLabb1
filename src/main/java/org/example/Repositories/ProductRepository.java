package org.example.Repositories;

import org.example.Models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ProductRepository {
    private final Logger log = LoggerFactory.getLogger(ProductRepository.class);

    /* I chose a HashMap to store the products because the order doesn't matter, and it makes more sense to me
     * to access products by name (which is the only way I chose to implement) or id rather than an index.
     */
    private final Map<String, Product> products = new HashMap<>();

    public Map<String, Product> getProducts() {
        return products;
    }

    public Product getProductByName(String name) {
        try {
            return products.get(name);
        }
        catch (NullPointerException e) {
            log.error("Product name is null");
            return null;
        }
    }

    public void addProduct(Product product) {
        // Special case if someone ignores the warnings and send in null
        if (product == null) {
            log.error("Product is null");
        }
        else if (!product.equals(new Product())) {
            products.put(product.getName(), product);
        }
    }
}
