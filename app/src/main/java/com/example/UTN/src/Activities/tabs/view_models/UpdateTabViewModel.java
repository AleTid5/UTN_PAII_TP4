package com.example.UTN.src.Activities.tabs.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.UTN.src.Models.Product;
import com.example.UTN.src.Services.ProductService;

public class UpdateTabViewModel extends WithCategoryViewModel {
    private static MutableLiveData<Product> liveProduct = new MutableLiveData<>();

    public UpdateTabViewModel() {
        liveProduct.setValue(new Product());
    }

    public LiveData<Product> getProduct() {
        return liveProduct;
    }

    public void findProduct(Integer id) {
        liveProduct.setValue(null);
        ProductService.findProduct(id);
    }

    public void updateProduct(Product product) {
        ProductService.updateProduct(product);
        ListTabViewModel.updateProduct(product);
    }

    public static void productFound(Product product) {
        liveProduct.postValue(product);
    }
}