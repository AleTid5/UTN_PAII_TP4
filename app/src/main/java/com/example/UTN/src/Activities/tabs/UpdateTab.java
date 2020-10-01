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
import com.example.UTN.src.Activities.tabs.view_models.UpdateTabViewModel;
import com.example.UTN.src.Interfaces.TabInterface;

public class UpdateTab extends Fragment implements TabInterface {

    private UpdateTabViewModel mViewModel;

    public static UpdateTab newInstance() {
        return new UpdateTab();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.update_tab_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UpdateTabViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public int getTabName() {
        return R.string.tab_text_update;
    }
}