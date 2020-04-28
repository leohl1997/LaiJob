package com.laioffer.githubexample.ui.HomeList;

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
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.remote.response.UserInfo;
import com.laioffer.githubexample.ui.HomeMap.HomeMapFragment;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.favorite.FavoriteJobFragment;
import com.laioffer.githubexample.ui.jobInfo.JobInfoFragment;
import com.laioffer.githubexample.ui.login.LoginViewModel;
import com.laioffer.githubexample.ui.search.SearchEvent;
import com.laioffer.githubexample.ui.search.SearchFragment;
import com.laioffer.githubexample.ui.userInfo.UserInfoFragment;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.Utils;

import java.util.ArrayList;


public class HomeListFragment extends BaseFragment<HomeListViewModel, HomeListRepository>
    implements ItemDataAdapter.OnNoteListener{
    SearchEvent searchEvent;
    int filterRule;
    String keyWord;
    private NavigationManager navigationManager;

    private ItemDataAdapter adapter = new ItemDataAdapter();

    public HomeListFragment(SearchEvent searchEvent) {
        super();
        this.searchEvent = searchEvent;

    }

    public void onAttach(Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }

    public static HomeListFragment newInstance(int filterRule, String keyWord) {
        return new HomeListFragment(new SearchEvent(filterRule,keyWord));
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        RecyclerView rv = view.findViewById(R.id.JobInfo);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
        getAllItem(keyWord);
    }

    private void getAllItem(String keyWord) {

        viewModel.getListJobMutableLiveData(keyWord).observe(getViewLifecycleOwner(), list -> {
            adapter.setItems(new ArrayList<>(list),filterRule);
            adapter.setOnNoteListener(this);
            adapter.notifyDataSetChanged();
        });
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.home_list_fragment, container, false);


        Toolbar toolbar = view.findViewById(R.id.toolbar);






        Button button1 = view.findViewById(R.id.search);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationManager.navigateTo(new SearchFragment());
            }
        });
        Button button3 = view.findViewById(R.id.HomeMap);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationManager.navigateTo(new HomeMapFragment());
            }
        });

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this).get(HomeListViewModel.class);
        // TODO: Use the ViewModel
    }



    @Override
    protected HomeListViewModel getViewModel() {
        return new ViewModelProvider(requireActivity(), getFactory()).get(HomeListViewModel.class);
    }

    @Override
    protected ViewModelProvider.Factory getFactory() {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new HomeListViewModel(getRepository());
            }
        };
    }

    @Override
    protected HomeListRepository getRepository() {
        return new HomeListRepository();
    }

    @Override
    public void onNoteClick(int position, ItemDataAdapter adapter) {
        Job current = adapter.getItem(position);
        Utils.constructToast(getContext(),current.getJobDescription()).show();
        navigationManager.navigateTo(new JobInfoFragment());

    }
}