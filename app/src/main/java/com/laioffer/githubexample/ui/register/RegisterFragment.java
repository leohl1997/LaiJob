package com.laioffer.githubexample.ui.register;

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
import com.laioffer.githubexample.databinding.RegisterFragmentBinding;
import com.laioffer.githubexample.util.Utils;

public class RegisterFragment extends BaseFragment<RegisterViewModel, RegisterRepository> {

    private RegisterFragmentBinding binding;

    public void onAttach(@NonNull Context context) {
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

        binding.btnRegister.setOnClickListener( v -> {
            viewModel.register(new RegisterEvent(binding.etUserId.getText().toString(),
                    Utils.md5Encryption(binding.etPassword.getText().toString()),
                    binding.etFirstName.getText().toString(),
                    binding.etLastName.getText().toString()));
        });
        viewModel.getErrMsgMutableLiveData().observe(getViewLifecycleOwner(), errMsg -> {
            Utils.constructToast(getContext(), errMsg).show();
        });
        viewModel.getRemoteResponseLiveData().observe(getViewLifecycleOwner(), it -> {
            if (it == null) {
                Utils.constructToast(getContext(), "Error! empty response body!").show();
            } else {
                Utils.constructToast(getContext(), it.status).show();
                // do we need to redirect to the userInfo fragment?
            }
        });
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
