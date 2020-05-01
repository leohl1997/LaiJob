package com.laioffer.githubexample.ui.recommendation;

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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.ui.HomeList.ItemDataAdapter;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.jobInfo.JobInfoFragment;

import java.util.ArrayList;


public class RecommendationFragment extends BaseFragment<RecommendationViewModel, RecommendationRepository>
        implements ItemDataAdapter.OnNoteListener{

    private NavigationManager navigationManager;
    private ItemDataAdapter adapter = new ItemDataAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_job_fragment, container, false);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }



    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        RecyclerView rv = view.findViewById(R.id.FavInfo);
        FloatingActionButton backFab = view.findViewById(R.id.backFab);
        backFab.setOnClickListener(v -> navigationManager.goBack());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
        viewModel.getListJobMutableLiveData().observe(getViewLifecycleOwner(), list -> {
            if (list == null || list.size() == 0) {
                return;
            }
            adapter.setItems(new ArrayList<>(list));
            adapter.setOnNoteListener(this);
            adapter.notifyDataSetChanged();
        });

    }



    @Override
    protected RecommendationViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(RecommendationViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new RecommendationViewModel(getRepository());
            }
        };
    }

    @Override
    protected RecommendationRepository getRepository() {
        return new RecommendationRepository();
    }

    @Override
    public void onNoteClick(int position, ItemDataAdapter adapter) {
        Job current = adapter.getItem(position);
        JobInfoFragment fragment = JobInfoFragment.newInstance(current);
        //Utils.constructToast(getContext(),current.getJobDescription()).show();
        navigationManager.navigateTo(fragment);

    }
}