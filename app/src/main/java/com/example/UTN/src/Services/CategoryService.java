package com.example.UTN.src.Services;

import com.example.UTN.src.Database.CategoryDB;

public abstract class CategoryService {
    public static void findCategories() {
        CategoryDB.findCategories();
    }
}
