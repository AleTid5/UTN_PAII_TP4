package com.example.UTN.src.Builders;

import android.widget.Spinner;
import android.widget.TextView;

import com.example.UTN.src.Exceptions.ProductException;
import com.example.UTN.src.Models.Category;
import com.example.UTN.src.Models.Product;

public class ProductBuilder {
    private final Product product;

    public ProductBuilder() {
        this.product = new Product();
    }

    public ProductBuilder(Integer id) {
        this.product = new Product(id);
    }

    public ProductBuilder setId(TextView inputId) throws ProductException {
        int id = Integer.parseInt(inputId.getText().toString());

        if (id < 0) {
            throw new ProductException("El ID debe ser mayor a 0");
        }

        product.setId(id);

        return this;
    }

    public ProductBuilder setName(TextView inputName) throws ProductException {
        String name = inputName.getText().toString().trim();

        if (!name.replace(" ", "").chars().allMatch(Character::isLetter)) {
            throw new ProductException("El Nombre solo puede contener letras");
        }

        if (name.length() < 5 || name.length() > 25) {
            throw new ProductException("El Nombre debe ser mayor a 5 caracteres y menor que 25");
        }

        product.setName(name);

        return this;
    }

    public ProductBuilder setStock(TextView inputStock) throws ProductException {
        int stock = Integer.parseInt(inputStock.getText().toString());

        if (stock < 0) {
            throw new ProductException("El Stock debe ser mayor a 0");
        }

        product.setStock(stock);

        return this;
    }

    public ProductBuilder setCategory(Spinner spinnerCategory) throws ProductException {
        Category category = (Category) spinnerCategory.getSelectedItem();

        if (category.getId().equals(0)) {
            throw new ProductException("Debe seleccionar una categoría válida");
        }

        product.setCategory(category);

        return this;
    }

    public Product build() {
        return this.product;
    }
}
