package com.laioffer.githubexample.ui.editEdu;

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
import com.laioffer.githubexample.databinding.EditEduFragmentBinding;
import com.laioffer.githubexample.remote.response.UserInfo;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.userInfo.UserInfoFragment;
import com.laioffer.githubexample.util.Utils;

public class EditEduFragment extends BaseFragment<EditEduViewModel, EditEduRepository> {
    private NavigationManager navigationManager;
    private EditEduFragmentBinding binding;
    private EditEduViewModel mViewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }


    public static EditEduFragment newInstance() {
        return new EditEduFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       binding = EditEduFragmentBinding.inflate(inflater, container, false);
       return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.back.setOnClickListener(v -> {
            navigationManager.navigateTo(new UserInfoFragment());
        });
        binding.save.setOnClickListener(v -> {
            viewModel.sendEdu(new EditEduEvent( binding.schoolName.getText().toString(),
                    binding.startDate.getText().toString(),
                    binding.endDate.getText().toString()));
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
        // TODO: Use the ViewModel
    }

    @Override
    protected EditEduViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(EditEduViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new EditEduViewModel(getRepository());
            }
        };
    }

    @Override
    protected EditEduRepository getRepository() {
        return new EditEduRepository();
    }
}
