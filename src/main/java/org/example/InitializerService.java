package org.example;

import org.example.Factories.OrderFactory;
import org.example.Factories.ProductFactory;
import org.example.Repositories.OrderRepository;
import org.example.Repositories.ProductRepository;

import java.util.List;

public class InitializerService {
    private final OrderFactory orderFactory = new OrderFactory();
    private final ProductFactory productFactory = new ProductFactory();
    private final ProductRepository productRepository = new ProductRepository();
    private final OrderRepository orderRepository = new OrderRepository();
    private final DataOperations dataOperations = new DataOperations();

    InitializerService() {
        initializeProducts();
        initializeOrders();
    }

    private void initializeProducts() {
        productRepository.addProduct(productFactory.createProduct("Hammer", "Tool", 34.99));
        productRepository.addProduct(productFactory.createProduct("Axe", "Tool", 49.99));
        productRepository.addProduct(productFactory.createProduct("Sledgehammer", "Tool", 79.99));
        productRepository.addProduct(productFactory.createProduct("Screwdriver", "Tool", 9.99));
        productRepository.addProduct(productFactory.createProduct("Nails (20-pack)", "Fastener", 9.99));
        productRepository.addProduct(productFactory.createProduct("Nails (100-pack)", "Fastener", 38.99));
        productRepository.addProduct(productFactory.createProduct("Screws (20-pack)", "Fastener", 14.99));
        productRepository.addProduct(productFactory.createProduct("Screws (100-pack)", "Fastener", 44.99));
    }

    private void initializeOrders() {
        orderRepository.addOrder(orderFactory.createOrder(List.of(productRepository.getProductByName("Hammer"), productRepository.getProductByName("Nails (20-pack)")), "Pelle"));
        orderRepository.addOrder(orderFactory.createOrder(List.of(productRepository.getProductByName("Axe"), productRepository.getProductByName("Sledgehammer")), "Kalle"));
        orderRepository.addOrder(orderFactory.createOrder(List.of(productRepository.getProductByName("Screwdriver")), "Stina"));
        orderRepository.addOrder(orderFactory.createOrder(productRepository.getProducts().values().stream().toList(), "Lisa"));
        orderRepository.addOrder(orderFactory.createOrder(List.of(productRepository.getProductByName("Sledgehammer"), productRepository.getProductByName("Screwdriver")), "Pelle"));
        orderRepository.addOrder(orderFactory.createOrder(List.of(productRepository.getProductByName("Screws (100-pack)")), "Stina"));
        orderRepository.addOrder(orderFactory.createOrder(List.of(productRepository.getProductByName("Nails (100-pack)"), productRepository.getProductByName("Screws (20-pack)")), "Kalle"));
    }

    public void run() {
        View view = new View(orderRepository, productRepository, dataOperations);
        view.run();
        view.testErroneousUse(orderFactory, productFactory);
    }
}
