package com.example.UTN.src.Activities.tabs.view_models;

import androidx.lifecycle.ViewModel;

import com.example.UTN.src.Models.Category;
import com.example.UTN.src.Models.Product;
import com.example.UTN.src.Services.CategoryService;
import com.example.UTN.src.Services.ProductService;

import java.util.List;

public class AddTabViewModel extends ViewModel {
    private List<Category> categories = null;

    public List<Category> getCategories() {
        // Singleton
        if (this.categories == null) {
            categories = CategoryService.findCategories();
        }

        return this.categories;
    }

    public void addProduct(Product product) {
        ProductService.saveProduct(product);
    }
}