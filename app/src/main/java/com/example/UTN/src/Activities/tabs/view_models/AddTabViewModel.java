package com.example.UTN.src.Activities.tabs.view_models;

import com.example.UTN.src.Models.Product;
import com.example.UTN.src.Services.ProductService;

public class AddTabViewModel extends WithCategoryViewModel {
    public void addProduct(Product product) {
        ProductService.saveProduct(product);
        ListTabViewModel.addProduct(product);
    }
}