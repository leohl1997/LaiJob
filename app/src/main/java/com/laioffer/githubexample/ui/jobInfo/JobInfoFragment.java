package com.laioffer.githubexample.ui.jobInfo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.databinding.JobInfoFragmentBinding;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.comment.CommentFragment;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.Utils;

public class JobInfoFragment extends BaseFragment<JobInfoViewModel, JobInfoRepository>
    implements JobInfoRecyclerViewAdapter.SaveItemListener {

    private NavigationManager navigationManager;
    private JobInfoFragmentBinding binding;
    private JobInfoRecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Job currentJob = (Job) getArguments().getSerializable("job");
        if (currentJob == null) {
            return;
        }
        adapter = new JobInfoRecyclerViewAdapter(currentJob, this);
        // maybe change
        linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rvMain.setLayoutManager(linearLayoutManager);
        binding.rvMain.setAdapter(adapter);
        viewModel.setJobIdLiveData(currentJob.itemId);
        viewModel.getCommentLiveData().observe(getViewLifecycleOwner(), list -> {
            if (list == null || list.size() == 0) {
                return;
            }
            adapter.addAll(list);
            adapter.notifyDataSetChanged();
            adapter.getCommentNumber().setText(String.format("%d", list.size()));
            Double avg = list.stream()
                    .mapToDouble(comment -> comment.rating)
                    .average()
                    .orElse(0.0);
            adapter.getAvgRating().setText(String.format("%.1f", avg));
        });
        viewModel.getSaveResponse().observe(getViewLifecycleOwner(), msg -> {
            if (msg.equals("Save Success") && !currentJob.favorite) {
                return;
            }
            Button button = linearLayoutManager.findViewByPosition(0).findViewById(R.id.save);
            if (msg.equals("Save Success!")) {
                button.setBackground(getView()
                        .getResources()
                        .getDrawable(R.drawable.btn_custom_selected));
                ((Job) getArguments().getSerializable("job")).favorite = true;
                adapter.getSaveButton().setText(R.string.saved);
            } else if (msg.equals("Unsave Success!")) {
                button.setBackground(getView()
                        .getResources()
                        .getDrawable(R.drawable.btn_custom));
                adapter.getSaveButton().setText(R.string.save);
                ((Job) getArguments().getSerializable("job")).favorite = false;
            }
            Utils.constructToast(getContext(), msg).show();
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

    @Override
    public void onSaveClicked() {
        Job currentJob = (Job) getArguments().getSerializable("job");
        SaveEvent saveEvent = new SaveEvent();
        saveEvent.userId = Config.username;
        saveEvent.job = currentJob;
        viewModel.setSaveEvent(saveEvent);
    }

    @Override
    public void onBackClicked() {
        navigationManager.goBack();
    }

    @Override
    public void onCommentClicked() {
        Job currentJob = (Job) getArguments().getSerializable("job");
        CommentFragment commentFragment = CommentFragment.getInstance(currentJob);
        navigationManager.navigateTo(commentFragment);
    }

    @Override
    public void onApplyCLicked() {
        Job currentJob = (Job) getArguments().getSerializable("job");
        WebFragment webFragment = WebFragment.getInstance(currentJob);
        navigationManager.navigateTo(webFragment);
    }
}
