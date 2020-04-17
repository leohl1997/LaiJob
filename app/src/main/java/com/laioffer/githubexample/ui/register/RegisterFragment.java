package com.laioffer.githubexample.ui.register;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.databinding.RegisterFragmentBinding;
import com.laioffer.githubexample.ui.HomeList.HomeListFragment;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.login.LoginFragment;
import com.laioffer.githubexample.ui.login.LoginRepository;
import com.laioffer.githubexample.ui.login.LoginViewModel;

public class RegisterFragment extends BaseFragment {

    RegisterFragmentBinding binding;

    public void onAttach(Context context) {
        super.onAttach(context);

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       binding = RegisterFragmentBinding.inflate(inflater, container, false);
       return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected RegisterViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(RegisterViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new RegisterViewModel(getRepository());
            }
        };
    }

    @Override
    protected RegisterRepository getRepository() {
        return new RegisterRepository();
    }
}
