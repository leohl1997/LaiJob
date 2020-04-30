package com.laioffer.githubexample.ui.jobInfo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.databinding.JobInfoFragmentBinding;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.ui.HomeList.ItemDataAdapter;
import com.laioffer.githubexample.ui.NavigationManager;




public class RecommendationFragment extends BaseFragment<JobInfoViewModel, JobInfoRepository>
        implements ItemDataAdapter.OnNoteListener {

    private NavigationManager navigationManager;
    private JobInfoFragmentBinding binding;
    private ItemDataAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    public static RecommendationFragment newInstance(Job job) {

        RecommendationFragment recommendationFragment = new RecommendationFragment();
        Bundle args = new Bundle();
        args.putSerializable("job", job);
        recommendationFragment.setArguments(args);
        return recommendationFragment;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Job currentJob = (Job) getArguments().getSerializable("job");
        if (currentJob == null) {
            return;
        }


        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recommendationCard.setLayoutManager(linearLayoutManager);
        binding.recommendationCard.setAdapter(adapter);
        viewModel.setJobIdLiveData(currentJob.itemId);


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


    @Override
    public void onNoteClick(int position, ItemDataAdapter adapter) {

    }
}