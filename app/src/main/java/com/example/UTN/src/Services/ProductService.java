package com.example.UTN.src.Services;

import com.example.UTN.src.Models.Category;
import com.example.UTN.src.Models.Product;

import java.util.Arrays;
import java.util.List;

public abstract class ProductService {
    private static List<Product> products = Arrays.asList(new Product(1, "product 1", 10, new Category("some")));

    public static List<Product> findProducts() {
        return products;
    }

    public static void saveProduct(Product product) {
        try {
            products.add(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
