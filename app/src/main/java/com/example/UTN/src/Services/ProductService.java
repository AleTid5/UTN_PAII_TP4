package com.example.UTN.src.Services;

import com.example.UTN.src.Exceptions.ProductException;
import com.example.UTN.src.Models.Category;
import com.example.UTN.src.Models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class ProductService {
    private static List<Product> products = new ArrayList<Product>(){{
        add(new Product(1, "Producto uno", 10, CategoryService.findCategories().get(1)));
    }};

    public static Product findProduct(Integer id) throws ProductException {
        Optional<Product> optionalProduct = products.stream()
                .filter((Product product) -> product.getId().equals(id))
                .findFirst();

        if (!optionalProduct.isPresent()) {
            throw new ProductException("El Id ingresado no pertenece a ningún producto");
        }

        return optionalProduct.get();
    }

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

    public static void updateProduct(Product product) {
        try {
            int productIndex = products.stream().map(Product::getId).collect(Collectors.toList()).indexOf(product.getId());
            products.set(productIndex, product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
