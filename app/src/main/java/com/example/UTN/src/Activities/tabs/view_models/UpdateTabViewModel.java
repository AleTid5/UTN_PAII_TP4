package com.example.UTN.src.Activities.tabs.view_models;

import com.example.UTN.src.Exceptions.ProductException;
import com.example.UTN.src.Models.Product;
import com.example.UTN.src.Services.ProductService;

public class UpdateTabViewModel extends WithCategoryViewModel {
    public Product getProduct(Integer id) throws ProductException {
        return ProductService.findProduct(id);
    }

    public void updateProduct(Product product) {
        ProductService.updateProduct(product);
        ListTabViewModel.updateProduct(product);
    }
}