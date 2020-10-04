package com.example.UTN.src.Activities.tabs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.UTN.R;
import com.example.UTN.src.Activities.tabs.view_models.UpdateTabViewModel;
import com.example.UTN.src.Builders.ProductBuilder;
import com.example.UTN.src.Exceptions.ProductException;
import com.example.UTN.src.Interfaces.TabInterface;
import com.example.UTN.src.Models.Product;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class UpdateTab extends Fragment implements TabInterface {

    private UpdateTabViewModel mViewModel;
    private static boolean isEditable = false;
    private Integer selectedId = null;

    public static UpdateTab newInstance() {
        return new UpdateTab();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.update_tab_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(UpdateTabViewModel.class);

        setIsEditable(root);
        root.findViewById(R.id.button_search).setOnClickListener(v -> this.onSearchProduct(root));
        root.findViewById(R.id.button_update).setOnClickListener(v -> this.onUpdateProduct(root));

        mViewModel.getProduct().observe(getViewLifecycleOwner(), product -> {
            try {
                isEditable = false;

                if (!product.isActive()) {
                    throw new ProductException("El producto se encuentra dado de baja");
                }

                ((TextView) root.findViewById(R.id.input_product_name)).setText(product.getName());
                ((TextView) root.findViewById(R.id.input_stock)).setText(product.getStock().toString());
                ((Spinner) root.findViewById(R.id.spinner_category)).setSelection(mViewModel.getCategoryIndex(product.getCategory()));

                isEditable = true;
            } catch (ProductException e) {
                Snackbar.make(root, e.getMessage(), Snackbar.LENGTH_LONG).show();
            } catch (Exception e) {
                Snackbar.make(root, "Debe ingresar un ID válido", Snackbar.LENGTH_LONG).show();
                e.printStackTrace();
            } finally {
                setIsEditable(root);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Spinner spinner = requireView().findViewById(R.id.spinner_category);

        spinner.setAdapter(new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, mViewModel.getCategories()));
    }

    @Override
    public int getTabName() {
        return R.string.tab_text_update;
    }

    private void onSearchProduct(View view) {
        try {
            this.selectedId = null;

            // Establece el Id para ser utilizado mas tarde
            this.selectedId = Integer.parseInt(((TextView) view.findViewById(R.id.input_id)).getText().toString());

            mViewModel.findProduct(this.selectedId);
        } catch (Exception e) {
            Snackbar.make(view, "Debe ingresar un ID válido", Snackbar.LENGTH_LONG).show();
        }
    }

    private void onUpdateProduct(View view) {
        try {
            mViewModel.updateProduct(new ProductBuilder(this.selectedId)
                    .setName(requireView().findViewById(R.id.input_product_name))
                    .setStock(requireView().findViewById(R.id.input_stock))
                    .setCategory(requireView().findViewById(R.id.spinner_category))
                    .build()
            );

            ((TextView) requireView().findViewById(R.id.input_product_name)).setText("");
            ((TextView) requireView().findViewById(R.id.input_stock)).setText("");
            ((Spinner) requireView().findViewById(R.id.spinner_category)).setSelection(0, true);

            Snackbar.make(view, "El producto ha sido modificado exitosamente!", Snackbar.LENGTH_LONG).show();
        } catch (ProductException e) {
            Snackbar.make(view, Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_LONG).show();
        } catch (Exception e) {
            Snackbar.make(view, "Los datos están incompletos", Snackbar.LENGTH_LONG).show();
        }
    }

    private void setIsEditable(View view) {
        view.findViewById(R.id.input_product_name).setEnabled(isEditable);
        view.findViewById(R.id.input_stock).setEnabled(isEditable);
        view.findViewById(R.id.spinner_category).setEnabled(isEditable);
        view.findViewById(R.id.button_update).setEnabled(isEditable);
    }
}