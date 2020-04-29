package com.laioffer.githubexample.ui.editProfile;

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
import com.laioffer.githubexample.databinding.EditProfileFragmentBinding;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.userInfo.UserInfoFragment;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.Utils;

public class EditProfileFragment extends BaseFragment<EditProfileViewModel, EditProfileRepository> {
    private NavigationManager navigationManager;
    private EditProfileViewModel mViewModel;
    private EditProfileFragmentBinding binding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }


    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = EditProfileFragmentBinding.inflate(inflater, container, false);
        binding.back.setOnClickListener(v -> {
            navigationManager.navigateTo(new UserInfoFragment());
        });
        binding.save.setOnClickListener(v -> {
            Config.firstName = binding.firstName.getText().toString();
            Config.lastName = binding.lastName.getText().toString();
            Config.phone = binding.phoneNumber.getText().toString();
            Config.email = binding.email.getText().toString();
            Config.address = binding.address.getText().toString();
            Config.dataOfBirth = binding.dateBirth.getText().toString();
            viewModel.sendProfile(new EditProfileEvent(binding.firstName.getText().toString(),
                    binding.lastName.getText().toString(),
                    binding.phoneNumber.getText().toString(),
                    binding.email.getText().toString(),
                    binding.address.getText().toString(),
                    binding.dateBirth.getText().toString()));
        });
        viewModel.getMsgMutableLiveData().observe(getViewLifecycleOwner(), msg -> {
            Utils.constructToast(getContext(), msg).show();
        });
        viewModel.getResponseLiveData().observe(getViewLifecycleOwner(), it -> {
            if (it == null) {
                Utils.constructToast(getContext(), "Error! empty response body!").show();
            } else {
                Utils.constructToast(getContext(), it.status).show();
                // do we need to redirect to the userInfo fragment?
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    @Override
    protected EditProfileViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(EditProfileViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new EditProfileViewModel(getRepository());
            }
        };
    }

    @Override
    protected EditProfileRepository getRepository() {
        return new EditProfileRepository();
    }
}
