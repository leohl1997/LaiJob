package com.laioffer.githubexample.ui.HomeList;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.ui.HomeMap.HomeMapFragment;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.jobInfo.JobInfoFragment;
import com.laioffer.githubexample.ui.search.SearchFragment;
import com.laioffer.githubexample.ui.userInfo.UserInfoFragment;

public class HomeListFragment extends BaseFragment {
    private HomeListViewModel mViewModel;
    NavigationManager navigationManager;

    public void onAttach(Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }

    public static HomeListFragment newInstance() {
        return new HomeListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_list_fragment, container, false);
        Button button1 = view.findViewById(R.id.search);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationManager.navigateTo(new SearchFragment());
            }
        });
        Button button2 = view.findViewById(R.id.JobInfo);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationManager.navigateTo(new JobInfoFragment());
            }
        });
        Button button3 = view.findViewById(R.id.HomeMap);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationManager.navigateTo(new HomeMapFragment());
            }
        });
        Button button4 = view.findViewById(R.id.UserInfo);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationManager.navigateTo(new UserInfoFragment());
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeListViewModel.class);
        // TODO: Use the ViewModel
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
