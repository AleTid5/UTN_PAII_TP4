package com.example.UTN.src.Activities.tabs.view_models;

import androidx.lifecycle.MutableLiveData;

import com.example.UTN.src.Models.Product;
import com.example.UTN.src.Services.ProductService;

public class AddTabViewModel extends WithCategoryViewModel {
    private static MutableLiveData<Boolean> liveAddedProduct = new MutableLiveData<>();

    public void addProduct(Product product) {
        ProductService.saveProduct(product);
    }

    public MutableLiveData<Boolean> getLiveAddedProduct() {
        return liveAddedProduct;
    }

    public static void productAdded(Boolean addedProduct) {
        liveAddedProduct.postValue(addedProduct);
    }
}