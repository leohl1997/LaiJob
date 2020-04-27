package com.laioffer.githubexample.ui.editWork;

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
import com.laioffer.githubexample.databinding.EditWorkFragmentBinding;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.userInfo.UserInfoFragment;

public class EditWorkFragment extends BaseFragment<editWorkViewModel, EditWorkRepository> {
    private NavigationManager navigationManager;
    private editWorkViewModel mViewModel;
    private EditWorkFragmentBinding binding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }


    public static EditWorkFragment newInstance() {
        return new EditWorkFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = EditWorkFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.back.setOnClickListener(v -> {
            navigationManager.navigateTo(new UserInfoFragment());
        });
        // TODO: Use the ViewModel
    }

    @Override
    protected editWorkViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(editWorkViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new editWorkViewModel(getRepository());
            }
        };
    }

    @Override
    protected EditWorkRepository getRepository() {
        return new EditWorkRepository();
    }
}
