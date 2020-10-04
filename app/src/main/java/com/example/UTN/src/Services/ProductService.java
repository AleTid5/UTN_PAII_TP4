package com.example.UTN.src.Services;

import com.example.UTN.src.Database.ProductDB;
import com.example.UTN.src.Models.Product;

public abstract class ProductService {
    public static void findProduct(Integer id) {
        ProductDB.findProduct(id);
    }

    public static void findProducts() {
        ProductDB.findProducts();
    }

    public static void saveProduct(Product product) {
        ProductDB.addProduct(product);
    }

    public static void updateProduct(Product product) {
        ProductDB.updateProduct(product);
    }

    public static void removeProduct(Product product) {
        ProductDB.removeProduct(product);
    }
}
