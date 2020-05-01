package com.laioffer.githubexample.ui.jobInfo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.databinding.JobInfoFragmentBinding;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.ui.HomeList.ItemDataAdapter;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.map.CardAdapter;
import com.squareup.picasso.Picasso;


public class RecommendationFragment extends BaseFragment<JobInfoViewModel, JobInfoRepository>
        implements ItemDataAdapter.OnNoteListener {

    private NavigationManager navigationManager;
    private JobInfoFragmentBinding binding;
    private ItemDataAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private CardView cardView;

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

    public View onViewCreated(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View recommendationView = inflater.inflate(R.layout.map_card, container, false);
        cardView = recommendationView.findViewById(R.id.map_card_view);
        cardView.setMaxCardElevation(cardView.getCardElevation() *
                CardAdapter.MAX_ELEVATION_FACTOR);
        Job job = (Job) getArguments().getSerializable("job");
        TextView title = recommendationView.findViewById(R.id.cv_title);
        title.setText(job.name);
        TextView companyName = recommendationView.findViewById(R.id.cv_company_name);
        companyName.setText(job.company);
        TextView location = recommendationView.findViewById(R.id.cv_location);
        location.setText(job.address);
        cardView.setOnClickListener( v -> {
            JobInfoFragment fragment = JobInfoFragment.newInstance(job);
            navigationManager.navigateTo(fragment);
        });
        ImageView imageView = recommendationView.findViewById(R.id.cvImg_info);
        if (!job.imageUrl.isEmpty()) {
            Picasso.get().setLoggingEnabled(true);
            Picasso.get().load(job.imageUrl).placeholder(R.drawable.ic_center)
                    .resize(70,70)
                    .into(imageView);

        }
        return recommendationView;


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