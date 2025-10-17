package org.example;

import org.example.Factories.OrderFactory;
import org.example.Factories.ProductFactory;
import org.example.Repositories.OrderRepository;
import org.example.Repositories.ProductRepository;

import java.util.LinkedList;
import java.util.List;

public class View {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final DataOperations dataOperations;

    public View(OrderRepository orderRepository, ProductRepository productRepository, DataOperations dataOperations) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.dataOperations = dataOperations;
    }

    public void run() {
        System.out.println("Testing the app");

        System.out.println("\nAll orders");
        orderRepository.getAllOrders().forEach(System.out::println);

        System.out.println("\nShow all tools sorted by price");
        dataOperations.findProductsByCategory("Tool", productRepository).forEach(System.out::println);

        System.out.println("\nShow value of Pelle's total orders");
        System.out.println(dataOperations.calculateCustomersTotalOrderValue("Pelle", orderRepository));

        System.out.println("\nShow the three most bought products");
        dataOperations.findTopThreeMostBoughtProducts(orderRepository).forEach(System.out::println);
    }

    public void testErroneousUse(OrderFactory orderFactory, ProductFactory productFactory) {

        System.out.println("\n\nTesting erroneous entries");
        /* Empty order (won't happen if the Factory is used correctly, this was handled specifically
         * (and not in a pretty way) because we were told you were going to try to break things) */
        orderRepository.addOrder(null);

        // Empty product list
        orderRepository.addOrder(orderFactory.createOrder(new LinkedList<>(), "Pelle"));
        orderRepository.addOrder(orderFactory.createOrder(null, "Pelle"));

        // Empty customer name
        orderRepository.addOrder(orderFactory.createOrder(List.of(productRepository.getProductByName("Hammer")), ""));
        orderRepository.addOrder(orderFactory.createOrder(List.of(productRepository.getProductByName("Hammer")), null));

        /* Empty product (won't happen if the Factory is used correctly, this was handled specifically
         * (and not in a pretty way) because we were told you were going to try to break things) */
        productRepository.addProduct(null);

        // Faulty product creation
        productRepository.addProduct(productFactory.createProduct(null, "test", 42));
        productRepository.addProduct(productFactory.createProduct("Hamburger", "\n ", 42));
        productRepository.addProduct(productFactory.createProduct("Drill", "Tool", -399));
    }
}
