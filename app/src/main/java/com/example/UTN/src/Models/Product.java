package com.example.UTN.src.Models;

public class Product {
    private Integer id;
    private String name;
    private Integer stock;
    private Category category;

    public Product() {}
    
    public Product(Integer id, String name, Integer stock, Category category) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
