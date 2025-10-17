package org.example.Models;

import org.example.Exceptions.EmptyProductCategoryException;
import org.example.Exceptions.EmptyProductNameException;
import org.example.Exceptions.NegativeProductPriceException;

public class Product {
    private final int id;
    private static int idGenerator;
    private final String name;
    private final String category;
    private final double price;

    public Product() {
        this.id = -1;
        this.name = null;
        this.category = null;
        this.price = -1;
    }

    public Product(String name, String category, double price) {
        if (name == null || name.isBlank()) {
            throw new EmptyProductNameException("Product name is missing");
        }
        else if (category == null || category.isBlank()) {
            throw new EmptyProductCategoryException("Product category is missing");
        }
        else if (price < 0) {
            throw new NegativeProductPriceException("Price must be zero or higher");
        }
        else {
            this.id = ++idGenerator;
            this.name = name;
            this.category = category;
            this.price = price;
        }
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
