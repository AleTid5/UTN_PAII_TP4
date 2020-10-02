package com.example.UTN.src.Activities.tabs.view_models;

import androidx.lifecycle.ViewModel;

import com.example.UTN.src.Models.Category;
import com.example.UTN.src.Services.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

public abstract class WithCategoryViewModel extends ViewModel {
    private static List<Category> categories = null;

    public List<Category> getCategories() {
        // Singleton
        if (categories == null) {
            categories = CategoryService.findCategories();
        }

        return categories;
    }

    public Integer getCategoryIndex(Category category) {
        return categories.stream().map(Category::getId).collect(Collectors.toList()).indexOf(category.getId());
    }
}
