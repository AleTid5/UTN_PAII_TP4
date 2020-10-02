package com.example.UTN.src.Services;

import com.example.UTN.src.Models.Category;

import java.util.Arrays;
import java.util.List;

public abstract class CategoryService {
    public static List<Category> findCategories() {
        return Arrays.asList(
                new Category(0, "Seleccione una categoria"),
                new Category(1, "Categoria 1"),
                new Category(2, "Categoria 2"),
                new Category(3, "Categoria 3"));
    }
}
