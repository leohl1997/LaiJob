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

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.databinding.LoginFragmentBinding;
import com.laioffer.githubexample.ui.HomeList.HomeListFragment;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.favorite.FavoriteJobFragment;
import com.laioffer.githubexample.ui.map.MapFragment;
import com.laioffer.githubexample.ui.search.SearchEvent;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.Utils;

public class LoginFragment extends BaseFragment<LoginViewModel, LoginRepository> {

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnLogin.setOnClickListener( v -> {
            viewModel.login(new LoginEvent(binding.etUserIdLogin.getText().toString(),
                    Utils.md5Encryption(binding.etPasswordLogin.getText().toString())));  // faker user info
        });
        viewModel.getRemoteResponseMutableLiveData().observe(getViewLifecycleOwner(), it -> {
            if (it != null && it.status.equals("OK")) {
                Utils.constructToast(getContext(), "Login success!").show();
                Config.userId = it.response.userId;
                Config.username = it.response.name;
                Utils.hideKeyboard(getActivity());
                viewModel.setNull();
                navigationManager.navigateTo(new HomeListFragment(new SearchEvent(0,"")));
            } else {
                Utils.constructToast(getContext(), it == null ? "Error !" : it.status).show();
            }
        });
        viewModel.getErrMsgMutableLiveData().observe(getViewLifecycleOwner(), it -> {
            Utils.constructToast(getContext(), it).show();
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

}
