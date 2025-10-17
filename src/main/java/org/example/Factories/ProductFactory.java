package org.example.Factories;

import org.example.Models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductFactory {
    private final Logger log = LoggerFactory.getLogger(ProductFactory.class);

    public Product createProduct(String name, String category, double price) {
        try {
            return new Product(name, category, price);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Product();
        }
    }
}
