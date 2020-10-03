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
        add(new Product(1, "Mochila con soporte", 10, CategoryService.findCategories().get(1)));
        add(new Product(2, "Termo Stanley 1lt", 350, CategoryService.findCategories().get(2)));
        add(new Product(5, "Caña pesca aluminio", 350, CategoryService.findCategories().get(2)));
        add(new Product(7, "Tanza 50m", 350, CategoryService.findCategories().get(2)));
    }};

    public static Product findProduct(Integer id) throws ProductException {
        Optional<Product> optionalProduct = products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();

        if (!optionalProduct.isPresent()) {
            throw new ProductException("El Id ingresado no pertenece a ningún producto");
        }

        if (!optionalProduct.get().isActive()) {
            throw new ProductException("El producto no se encuentra activo");
        }

        return optionalProduct.get();
    }

    public static List<Product> findProducts() {
        return products.stream().filter(Product::isActive).collect(Collectors.toList());
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

    public static void removeProduct(Product product) {
        products = products.stream().filter(p -> !p.getId().equals(product.getId())).collect(Collectors.toList());
    }
}
