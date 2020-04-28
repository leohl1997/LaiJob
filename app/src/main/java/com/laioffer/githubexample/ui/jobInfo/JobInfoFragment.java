package com.laioffer.githubexample.ui.jobInfo;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.ui.HomeList.Job;

public class JobInfoFragment extends BaseFragment {

    private JobInfoViewModel mViewModel;
    private Job job;

    public static JobInfoFragment newInstance(Job job ) {

        JobInfoFragment fragment =  new JobInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Job", job);
        fragment.setArguments(bundle);
        fragment.job = job;
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.job_info_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(JobInfoViewModel.class);
        // TODO: Use the ViewModel
        //getArguments().getBundle("Job") = job;
    }

    @Override
    protected BaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return null;
    }

    @Override
    protected BaseRepository getRepository() {
        return null;
    }
}
