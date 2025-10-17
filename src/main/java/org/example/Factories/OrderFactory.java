package org.example.Factories;

import org.example.Models.Order;
import org.example.Models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrderFactory {
    private final Logger log = LoggerFactory.getLogger(OrderFactory.class);

    public Order createOrder(List<Product> products, String customerName) {
        try {
            return new Order(products, customerName);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Order();
        }
    }
}
