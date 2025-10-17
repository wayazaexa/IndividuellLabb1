package org.example;

import org.example.Models.Product;
import org.example.Repositories.OrderRepository;
import org.example.Repositories.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;

public class DataOperations {
    public List<Product> findProductsByCategory(String category, ProductRepository productRepository) {
        // (Return the result from and) create a stream consisting of all products
        return productRepository.getProducts().values().stream()
                // Filter all products whose category does not match the parameter category
                .filter(p -> Objects.equals(p.getCategory(), category))
                // Sort the resulting stream by price (ascending is the default so that doesn't need to be specified)
                .sorted(Comparator.comparingDouble(Product::getPrice))
                // Collect the result to a list to be returned
                .toList();
    }

    public double calculateCustomersTotalOrderValue(String customer, OrderRepository orderRepository) {
        // (Return the result from and) create a stream consisting of all orders
        return orderRepository.getAllOrders().stream()
                // Filter all orders whose customer name does not match the parameter customer
                .filter(order -> order.getCustomerName().equalsIgnoreCase(customer))
                // Replace every order in the stream with the products that is associated with respective order
                .flatMap(order -> order.getProducts().stream())
                // Replace every product in the stream with the price of that product
                .mapToDouble(Product::getPrice)
                // Calculate the sum of all the doubles in the stream
                .sum();
    }

    public List<String> findTopThreeMostBoughtProducts(OrderRepository orderRepository) {
        // (Return the result from and) create a stream consisting of all orders
        return orderRepository.getAllOrders().stream()
                // Replace every order in the stream with the products that is associated with respective order
                .flatMap(order -> order.getProducts().stream())
                /* Collect the result into groupings (Maps) by product name and the count of how many times each
                 * product name occurred (meaning the key will be the product name and the value will be how many times
                 * it occurs in the stream) */
                .collect(Collectors.groupingBy(Product::getName, Collectors.counting()))
                /* Transform the Map into a "Set view of the Map" and create a stream out of the Set so we can keep
                 * working on it */
                .entrySet().stream()
                // Sort the entries in the entry set based on the value of the key-value pair
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                // Only keep the top 3 from the result
                .limit(3)
                // Replace every key-value pair with just the key (the product name)
                .map(Map.Entry::getKey)
                // Collect the result into a list to be returned
                .toList();
    }
}
