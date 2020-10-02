package com.example.UTN.src.Activities.tabs.view_models;

import androidx.lifecycle.ViewModel;

import com.example.UTN.src.Exceptions.ProductException;
import com.example.UTN.src.Models.Category;
import com.example.UTN.src.Models.Product;
import com.example.UTN.src.Services.CategoryService;
import com.example.UTN.src.Services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

public class UpdateTabViewModel extends ViewModel {
    private List<Category> categories = null;

    public List<Category> getCategories() {
        // Singleton
        if (this.categories == null) {
            this.categories = CategoryService.findCategories();
        }

        return this.categories;
    }

    public Integer getCategoryIndex(Category category) {
        return this.categories.stream().map(Category::getId).collect(Collectors.toList()).indexOf(category.getId());
    }

    public Product getProduct(Integer id) throws ProductException {
        return ProductService.findProduct(id);
    }

    public void updateProduct(Product product) {
        ProductService.updateProduct(product);
    }
}