package com.example.UTN.src.Models;

import androidx.annotation.NonNull;

public class Category {
    private Integer id;
    private String description;

    public Category(Integer id) {
        this.id = id;
    }

    public Category(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return description;
    }
}
