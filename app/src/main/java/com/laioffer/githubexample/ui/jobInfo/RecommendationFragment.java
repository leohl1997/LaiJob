package com.laioffer.githubexample.ui.jobInfo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.remote.response.UserInfo;

import com.laioffer.githubexample.ui.HomeList.HomeListRepository;
import com.laioffer.githubexample.ui.HomeList.ItemDataAdapter;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.favorite.FavoriteJobFragment;
import com.laioffer.githubexample.ui.jobInfo.JobInfoFragment;
import com.laioffer.githubexample.ui.login.LoginViewModel;
import com.laioffer.githubexample.ui.map.MapFragment;
import com.laioffer.githubexample.ui.search.SearchEvent;
import com.laioffer.githubexample.ui.search.SearchFragment;
import com.laioffer.githubexample.ui.userInfo.UserInfoFragment;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.Utils;

import java.util.ArrayList;


public class RecommendationFragment extends BaseFragment<RecommendationViewModel, RecommendationRepository>
        implements ItemDataAdapter.OnNoteListener{
    SearchEvent searchEvent;
    private DrawerLayout drawerLayout;
    private AppCompatActivity mactivity;

    private NavigationManager navigationManager;

    private ItemDataAdapter adapter = new ItemDataAdapter();

    public RecommendationFragment(SearchEvent searchEvent) {
        super();
        this.searchEvent = searchEvent;

    }

    public void onAttach(Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }

    public static RecommendationFragment newInstance(int filterRule, String keyWord) {
        return new RecommendationFragment(new SearchEvent(filterRule,keyWord));
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        RecyclerView rv = view.findViewById(R.id.JobInfo);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
        getAllItem(searchEvent.getKeyWord());
    }

    private void getAllItem(String keyWord) {

        viewModel.getListJobMutableLiveData(keyWord).observe(getViewLifecycleOwner(), list -> {
            adapter.setItems(new ArrayList<>(list),searchEvent.getFilterRule());
            adapter.setOnNoteListener(this);
            adapter.notifyDataSetChanged();
        });
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(HomeListViewModel.class);
        // TODO: Use the ViewModel
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
