package com.laioffer.githubexample.ui.editEdu;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.databinding.EditEduFragmentBinding;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.userInfo.UserInfoFragment;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.DateUtil;
import com.laioffer.githubexample.util.Utils;

import java.util.Objects;

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
        TextInputEditText startDateInputEditText = binding.startDate;
        startDateInputEditText.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
        binding.startDate.setOnClickListener(v -> {
            onStartCalenderClicked();
        });
        TextInputEditText endDateInputEditText = binding.endDate;
        endDateInputEditText.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
        binding.endDate.setOnClickListener(v -> {
            onEndCalenderClicked();
        });
        binding.save.setOnClickListener(v -> {
            Config.schoolName = binding.schoolName.getText().toString();
//            Config.schoolStartData = binding.startDate.getText().toString();
//            Config.schoolEndData =  binding.endDate.getText().toString();
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

    private void onStartCalenderClicked(){
        final Calendar calendar = Calendar.getInstance();
        binding.startDate.setText(DateUtil.date2String(calendar.getTime(),DateUtil.YMD_FORMAT));
        DatePickerDialog dialog = new DatePickerDialog(Objects.requireNonNull(getContext()), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                LogUtils.d(TAG, "onDateSet: year: " + year + ", month: " + month + ", dayOfMonth: " + dayOfMonth);

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                binding.startDate.setText(DateUtil.date2String(calendar.getTime(),DateUtil.YMD_FORMAT));
                Config.schoolStartData = DateUtil.date2String(calendar.getTime(),DateUtil.YMD_FORMAT);
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH) );
        dialog.show();
    }

    private void onEndCalenderClicked(){
        final Calendar calendar = Calendar.getInstance();
        binding.endDate.setText(DateUtil.date2String(calendar.getTime(),DateUtil.YMD_FORMAT));
        DatePickerDialog dialog = new DatePickerDialog(Objects.requireNonNull(getContext()), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                LogUtils.d(TAG, "onDateSet: year: " + year + ", month: " + month + ", dayOfMonth: " + dayOfMonth);

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                binding.endDate.setText(DateUtil.date2String(calendar.getTime(),DateUtil.YMD_FORMAT));
                Config.schoolEndData= DateUtil.date2String(calendar.getTime(),DateUtil.YMD_FORMAT);
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH) );
        dialog.show();
    }
}
