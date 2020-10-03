package com.example.UTN.src.Activities.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.UTN.R;
import com.example.UTN.src.Activities.tabs.view_models.ListTabViewModel;
import com.example.UTN.src.Models.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private ListTabViewModel listTabViewModel;
    private List<Product> elements;

    public ProductAdapter(ListTabViewModel listTabViewModel, List<Product> elements) {
        this.elements = elements;
        this.listTabViewModel = listTabViewModel;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Product getItem(int i) {
        return elements.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View newView = view;

        if (newView == null){
            newView = inflater.inflate(R.layout.product_item_layout,null);
        }

        TextView txtId = newView.findViewById(R.id.text_id);
        TextView txtProductName = newView.findViewById(R.id.text_product_name);
        Button seeMoreBtn = newView.findViewById(R.id.button_see_more);
        FloatingActionButton removeBtn = newView.findViewById(R.id.button_remove);
        Product product = getItem(i);

        txtId.setText(product.getId().toString());
        txtProductName.setText(product.getName());

        seeMoreBtn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle(String.format("%s | %s", product.getId(), product.getName()));
            builder.setMessage(String.format("Stock: %s | Categoría: %s", product.getStock(), product.getCategory().getDescription()));
            builder.setPositiveButton(R.string.text_accept, null);
            builder.create().show();
        });

        removeBtn.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle(String.format("¿Eliminar el producto %s?", product.getId()));
            builder.setMessage(String.format("¿Realmente desea eliminar el producto \"%s\"?", product.getName()));
            builder.setPositiveButton(R.string.text_accept, (dialog, which) -> listTabViewModel.removeProduct(getItem(i)));
            builder.setNegativeButton(R.string.text_cancel, null);
            builder.create().show();
        });

        return newView;
    }
}
