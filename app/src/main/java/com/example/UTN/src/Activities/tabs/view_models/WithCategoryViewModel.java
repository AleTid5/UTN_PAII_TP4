package com.example.UTN.src.Activities.tabs.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.UTN.src.Models.Category;
import com.example.UTN.src.Services.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class WithCategoryViewModel extends ViewModel {
    private static MutableLiveData<List<Category>> liveCategories = new MutableLiveData<>();

    static {
        liveCategories.setValue(new ArrayList<Category>(){{
            add(new Category(0, "Seleccione una categoria"));
        }});

        CategoryService.findCategories();
    }

    public static LiveData<List<Category>> getCategories() {
        return liveCategories;
    }

    public Integer getCategoryIndex(Category category) {
        return Objects.requireNonNull(liveCategories.getValue())
                .stream().map(Category::getId)
                .collect(Collectors.toList()).indexOf(category.getId());
    }

    public static void addCategory(Category category) {
        try {
            Objects.requireNonNull(liveCategories.getValue()).add(category);
            liveCategories.postValue(liveCategories.getValue());
        } catch (Exception ignored) {}
    }
}
