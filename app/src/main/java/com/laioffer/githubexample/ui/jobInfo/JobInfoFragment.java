package com.laioffer.githubexample.ui.jobInfo;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.databinding.JobInfoFragmentBinding;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.remote.response.UserInfo;
import com.laioffer.githubexample.ui.HomeList.HomeListFragment;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.comment.JobInfoRecyclerViewAdapter;
import com.laioffer.githubexample.ui.login.LoginEvent;
import com.laioffer.githubexample.util.Utils;

public class JobInfoFragment extends BaseFragment<JobInfoViewModel, JobInfoRepository> {

    private NavigationManager navigationManager;
    private JobInfoFragmentBinding binding;
    private JobInfoRecyclerViewAdapter adapter;

    public static JobInfoFragment newInstance(Job job) {

        JobInfoFragment jobInfoFragment = new JobInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable("job", job);
        jobInfoFragment.setArguments(args);
        return jobInfoFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = JobInfoFragmentBinding.inflate(inflater, container, false);
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
        Job currentJob = (Job) getArguments().getSerializable("job");
        if (currentJob == null) {
            return;
        }
        adapter = new JobInfoRecyclerViewAdapter(currentJob);
        // maybe change
        binding.rvMain.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvMain.setAdapter(adapter);
        viewModel.setJobIdLiveData(currentJob.itemId);
        viewModel.getCommentLiveData().observe(getViewLifecycleOwner(), list -> {
            Utils.constructToast(getContext(), adapter.getAvgRating().getText().toString()).show();
            if (list == null) {
                return;
            }
            adapter.addAll(list);
            adapter.notifyDataSetChanged();
            Utils.constructToast(getContext(), adapter.getAvgRating().toString()).show();
        });

    }

    @Override
    protected JobInfoViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(JobInfoViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new JobInfoViewModel(getRepository());
            }
        };
    }

    @Override
    protected JobInfoRepository getRepository() {
        return new JobInfoRepository();
    }
}
