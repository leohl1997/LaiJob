package com.laioffer.githubexample.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.databinding.LoginFragmentBinding;
import com.laioffer.githubexample.remote.RemoteResponseListener;
import com.laioffer.githubexample.remote.response.RemoteResponse;
import com.laioffer.githubexample.remote.response.UserInfo;
import com.laioffer.githubexample.ui.HomeList.HomeListFragment;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.util.Utils;

public class LoginFragment extends BaseFragment<LoginViewModel, LoginRepository>
        implements RemoteResponseListener<UserInfo> {

    private LoginFragmentBinding binding;
    private NavigationManager navigationManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.setResponseListener(this);
        binding.btnLogin.setOnClickListener( v -> {
            viewModel.login(binding.etUserId.getText().toString(),
                    binding.etPassword.getText().toString());
        });
    }

    @Override
    protected LoginViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(LoginViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new LoginViewModel(getRepository());
            }
        };
    }

    @Override
    protected LoginRepository getRepository() {
        return new LoginRepository();
    }

    @Override
    public void onSuccess(LiveData<RemoteResponse<UserInfo>> response) {
        response.observe(this, it -> {
            if (it != null && it.status.equals("OK")) {
                Utils.constructToast(getContext(), "Login success!").show();
                UserInfo info = it.response;
                Utils.constructToast(getContext(), info.name).show();
                // start other fragment
                navigationManager.navigateTo(new HomeListFragment());
            } else {
                Utils.constructToast(getContext(), it == null ? "Error !" : it.status).show();
            }
        });
    }

    @Override
    public void onFailure(String msg) {
        Utils.constructToast(getContext(), msg).show();
    }
}
