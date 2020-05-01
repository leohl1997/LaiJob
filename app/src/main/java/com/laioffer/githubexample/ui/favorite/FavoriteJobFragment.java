package com.laioffer.githubexample.ui.favorite;

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
import android.widget.Button;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.databinding.FavoriteJobFragmentBinding;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.ui.HomeList.HomeListFragment;
import com.laioffer.githubexample.ui.HomeList.ItemDataAdapter;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.map.MapFragment;
import com.laioffer.githubexample.ui.jobInfo.JobInfoFragment;
import com.laioffer.githubexample.ui.search.SearchEvent;

import java.util.ArrayList;

public class FavoriteJobFragment extends BaseFragment<FavoriteJobViewModel, FavoriteJobRepository>
        implements  ItemDataAdapter.OnNoteListener {

    private FavoriteJobFragmentBinding binding;
    private ItemDataAdapter adapter = new ItemDataAdapter();
    private NavigationManager navigationManager;

    public static FavoriteJobFragment newInstance() {
        return new FavoriteJobFragment();
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FavoriteJobFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void getAllItem() {
        viewModel.getFavJobLiveData().observe(getViewLifecycleOwner(), list -> {
            adapter.setItems(new ArrayList<>(list));
            adapter.setOnNoteListener(this);
            adapter.notifyDataSetChanged();
        });
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.backFab.setOnClickListener(v ->  navigationManager.navigateTo(new HomeListFragment(new SearchEvent(0,""))));
        binding.FavInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.FavInfo.setHasFixedSize(true);
        binding.FavInfo.setAdapter(adapter);
        getAllItem();
    }

    @Override
    protected FavoriteJobViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(FavoriteJobViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new FavoriteJobViewModel(getRepository());
            }
        };
    }

    @Override
    protected FavoriteJobRepository getRepository() {
        return new FavoriteJobRepository();
    }

    @Override
    public void onNoteClick(int position, ItemDataAdapter adapter) {
        Job current = adapter.getItem(position);
        JobInfoFragment fragment = JobInfoFragment.newInstance(current);
        navigationManager.navigateTo(fragment);
    }
}
