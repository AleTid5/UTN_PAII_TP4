package com.example.UTN.src.Activities.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.UTN.R;
import com.example.UTN.src.Activities.tabs.view_models.AddTabViewModel;
import com.example.UTN.src.Builders.ProductBuilder;
import com.example.UTN.src.Exceptions.ProductException;
import com.example.UTN.src.Interfaces.TabInterface;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class AddTab extends Fragment implements TabInterface {

    private AddTabViewModel mViewModel;

    public static AddTab newInstance() {
        return new AddTab();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_tab_fragment, container, false);

        Button buttonAdd = root.findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(this::onAddProduct);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddTabViewModel.class);
        Spinner spinner = requireView().findViewById(R.id.spinner_category);

        spinner.setAdapter(new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, mViewModel.getCategories()));
    }

    @Override
    public int getTabName() {
        return R.string.tab_text_add;
    }

    private void onAddProduct(View view) {
        try {
            mViewModel.addProduct(new ProductBuilder()
                    .setId(requireView().findViewById(R.id.input_id))
                    .setName(requireView().findViewById(R.id.input_product_name))
                    .setStock(requireView().findViewById(R.id.input_stock))
                    .setCategory(requireView().findViewById(R.id.spinner_category))
                    .build()
            );

            Snackbar.make(view, "El producto ha sido agregado exitosamente!", Snackbar.LENGTH_LONG).show();
        } catch (ProductException e) {
            Snackbar.make(view, Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_LONG).show();
        } catch (Exception e) {
            Snackbar.make(view, "Los datos están incompletos", Snackbar.LENGTH_LONG).show();
        }
    }
}