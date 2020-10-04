package com.example.UTN.src.Activities.tabs.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.UTN.src.Models.Product;
import com.example.UTN.src.Services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListTabViewModel extends ViewModel {
    private static MutableLiveData<List<Product>> liveProductList = new MutableLiveData<>();

    public ListTabViewModel() {
        liveProductList.setValue(new ArrayList<>());
        ProductService.findProducts();
    }

    public void removeProduct(Product product) {
        ProductService.removeProduct(product);
        List<Product> productList = Objects.requireNonNull(liveProductList.getValue())
                .stream()
                .filter(p -> !p.getId().equals(product.getId()))
                .collect(Collectors.toList());
        liveProductList.postValue(productList);
    }

    public LiveData<List<Product>> getProductList() {
        return liveProductList;
    }

    public static void addProduct(Product product) {
        Objects.requireNonNull(liveProductList.getValue()).add(product);
        liveProductList.postValue(liveProductList.getValue());
    }

    public static void updateProduct(Product product) {
        List<Product> products = Objects.requireNonNull(liveProductList.getValue());
        int productIndex = products.stream().map(Product::getId).collect(Collectors.toList()).indexOf(product.getId());
        products.set(productIndex, product);
        liveProductList.postValue(liveProductList.getValue());
    }
}