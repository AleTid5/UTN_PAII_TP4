package com.example.UTN.src.Activities.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.UTN.R;
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
        return inflater.inflate(R.layout.list_tab_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListTabViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public int getTabName() {
        return R.string.tab_text_list;
    }
}