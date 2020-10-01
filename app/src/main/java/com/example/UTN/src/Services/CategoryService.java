package com.example.UTN.src.Services;

import com.example.UTN.src.Models.Category;

import java.util.Arrays;
import java.util.List;

public abstract class CategoryService {
    public static List<Category> findCategories() {
        return Arrays.asList(new Category("Categoria 1"), new Category("Categoria 2"), new Category("Categoria 3"));
    }
}
