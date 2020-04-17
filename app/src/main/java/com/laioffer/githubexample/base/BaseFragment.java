package com.laioffer.githubexample.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.laioffer.githubexample.ui.NavigationManager;

public abstract class BaseFragment <VM extends BaseViewModel<R>, R extends BaseRepository>
        extends Fragment{
    private VM viewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getViewModel();
    }

    protected abstract VM getViewModel();

    protected abstract ViewModelProvider.Factory getFactory();

    protected abstract R getRepository();


}
