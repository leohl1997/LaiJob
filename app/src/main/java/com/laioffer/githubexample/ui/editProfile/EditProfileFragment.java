package com.laioffer.githubexample.ui.editProfile;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.InputType;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.databinding.EditProfileFragmentBinding;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.userInfo.UserInfoFragment;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.DateUtil;
import com.laioffer.githubexample.util.Utils;

import java.util.Objects;


public class EditProfileFragment extends BaseFragment<EditProfileViewModel, EditProfileRepository> {
    private NavigationManager navigationManager;
    private EditProfileViewModel mViewModel;
    private EditProfileFragmentBinding binding;
    //final Calendar calendar = Calendar.getInstance();

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
            navigationManager.navigateWithFragmentDestroy(new UserInfoFragment(),EditProfileFragment.newInstance());
        });
        TextInputEditText textInputEditText = binding.dateBirth;
        textInputEditText.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
        textInputEditText.setOnClickListener(v -> {
            onCalenderClicked();
        });
        binding.save.setOnClickListener(v -> {
            Config.firstName = binding.firstName.getText().toString();
            Config.lastName = binding.lastName.getText().toString();
            Config.phone = binding.phoneNumber.getText().toString();
            Config.email = binding.email.getText().toString();
            Config.address = binding.address.getText().toString();
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

    private void onCalenderClicked(){
        final Calendar calendar = Calendar.getInstance();
        binding.dateBirth.setText(DateUtil.date2String(calendar.getTime(),DateUtil.YMD_FORMAT));
        DatePickerDialog dialog = new DatePickerDialog(Objects.requireNonNull(getContext()), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                LogUtils.d(TAG, "onDateSet: year: " + year + ", month: " + month + ", dayOfMonth: " + dayOfMonth);

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                Config.dataOfBirth = DateUtil.date2String(calendar.getTime(),DateUtil.YMD_FORMAT);
                binding.dateBirth.setText(DateUtil.date2String(calendar.getTime(),DateUtil.YMD_FORMAT));
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH) );
        dialog.show();
    }
}
