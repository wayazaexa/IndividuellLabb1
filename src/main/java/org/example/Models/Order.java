package org.example.Models;

import org.example.Exceptions.EmptyCustomerNameException;
import org.example.Exceptions.EmptyOrderException;

import java.util.List;

public class Order {
    private final int orderId;
    private static int orderIdGenerator;
    private final List<Product> products;
    private final String customerName;

    public Order() {
        this.orderId = -1;
        this.products = null;
        this.customerName = null;
    }

    public Order(List<Product> products, String customerName) {
        if (products == null || products.isEmpty()) {
            throw new EmptyOrderException("Order does not contain products");
        }
        else if (customerName == null || customerName.trim().isEmpty()) {
            throw new EmptyCustomerNameException("Customer name is missing");
        }
        else {
            this.orderId = ++orderIdGenerator;
            this.products = products;
            this.customerName = customerName;
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", products=" + products +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
