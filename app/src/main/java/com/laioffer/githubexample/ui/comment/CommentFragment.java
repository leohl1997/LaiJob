package com.laioffer.githubexample.ui.comment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.databinding.CommentFragmentBinding;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.Utils;

import java.util.Calendar;

public class CommentFragment extends BaseFragment<CommentViewModel, CommentRepository> {

    private int rating = 0;
    private CommentFragmentBinding binding;
    private NavigationManager navigationManager;

    public static CommentFragment getInstance(Job job) {
        CommentFragment commentFragment = new CommentFragment();
        Bundle args = new Bundle();
        args.putSerializable("job", job);
        commentFragment.setArguments(args);
        return commentFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CommentFragmentBinding.inflate(getLayoutInflater(), container, false);
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
        binding.btnOneStar.setOnClickListener(v -> {
            unsetStars();
            binding.btnOneStar.setBackground(getResources().getDrawable(R.drawable.star_solid));
            rating = 1;
        });
        binding.btnTwoStar.setOnClickListener(v -> {
            unsetStars();
            binding.btnOneStar.setBackground(getResources().getDrawable(R.drawable.star_solid));
            binding.btnTwoStar.setBackground(getResources().getDrawable(R.drawable.star_solid));
            rating = 2;
        });
        binding.btnThreeStar.setOnClickListener(v -> {
            unsetStars();
            binding.btnOneStar.setBackground(getResources().getDrawable(R.drawable.star_solid));
            binding.btnTwoStar.setBackground(getResources().getDrawable(R.drawable.star_solid));
            binding.btnThreeStar.setBackground(getResources().getDrawable(R.drawable.star_solid));
            rating = 3;
        });
        binding.btnFourStar.setOnClickListener(v -> {
            unsetStars();
            binding.btnOneStar.setBackground(getResources().getDrawable(R.drawable.star_solid));
            binding.btnTwoStar.setBackground(getResources().getDrawable(R.drawable.star_solid));
            binding.btnThreeStar.setBackground(getResources().getDrawable(R.drawable.star_solid));
            binding.btnFourStar.setBackground(getResources().getDrawable(R.drawable.star_solid));
            rating = 4;
        });
        binding.btnFiveStar.setOnClickListener(v -> {
            unsetStars();
            binding.btnOneStar.setBackground(getResources().getDrawable(R.drawable.star_solid));
            binding.btnTwoStar.setBackground(getResources().getDrawable(R.drawable.star_solid));
            binding.btnThreeStar.setBackground(getResources().getDrawable(R.drawable.star_solid));
            binding.btnFourStar.setBackground(getResources().getDrawable(R.drawable.star_solid));
            binding.btnFiveStar.setBackground(getResources().getDrawable(R.drawable.star_solid));
            rating = 5;
        });
        binding.btnComment.setOnClickListener(v -> {
            Bundle args = getArguments();
            Job currJob = (Job) args.getSerializable("job");
            if (currJob == null) {
                return;
            }
            CommentEvent commentEvent = new CommentEvent();
            commentEvent.userId = Config.username;
            commentEvent.itemId = currJob.itemId;
            commentEvent.rating = rating;
            commentEvent.commentText = binding.commentBody.getText().toString();
            commentEvent.currentTime = Calendar.getInstance().getTime().toString();
            viewModel.setCommentEventMutableLiveData(commentEvent);
        });
        viewModel.getMsgMutableLiveData().observe(getViewLifecycleOwner(), msg -> {
            Utils.constructToast(getContext(), msg).show();
        });
        viewModel.getResponseLiveData().observe(getViewLifecycleOwner(), msg -> {
            Utils.constructToast(getContext(), msg).show();
        });
        binding.btnBack.setOnClickListener(v -> navigationManager.goBack());
    }

    @Override
    protected CommentViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(CommentViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new CommentViewModel(getRepository());
            }
        };
    }

    @Override
    protected CommentRepository getRepository() {
        return new CommentRepository();
    }

    private void unsetStars() {
        binding.btnOneStar.setBackground(getResources().getDrawable(R.drawable.star_hollow));
        binding.btnTwoStar.setBackground(getResources().getDrawable(R.drawable.star_hollow));
        binding.btnThreeStar.setBackground(getResources().getDrawable(R.drawable.star_hollow));
        binding.btnFourStar.setBackground(getResources().getDrawable(R.drawable.star_hollow));
        binding.btnFiveStar.setBackground(getResources().getDrawable(R.drawable.star_hollow));
    }

}
