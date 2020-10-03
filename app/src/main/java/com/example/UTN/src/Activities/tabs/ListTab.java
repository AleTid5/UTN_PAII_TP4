package com.example.UTN.src.Activities.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.UTN.R;
import com.example.UTN.src.Activities.Adapters.ProductAdapter;
import com.example.UTN.src.Activities.tabs.view_models.ListTabViewModel;
import com.example.UTN.src.Interfaces.TabInterface;

public class ListTab extends Fragment implements TabInterface {

    private ListTabViewModel mViewModel;

    public static ListTab newInstance() {
        return new ListTab();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.list_tab_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ListTabViewModel.class);
        mViewModel.getProductList().observe(getViewLifecycleOwner(), productList -> {
            ProductAdapter adapter = new ProductAdapter(mViewModel, productList);

            GridView gridView = requireView().findViewById(R.id.grid_product_view);
            gridView.setAdapter(adapter);
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public int getTabName() {
        return R.string.tab_text_list;
    }
}