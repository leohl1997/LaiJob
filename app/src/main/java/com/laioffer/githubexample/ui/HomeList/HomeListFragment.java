package com.laioffer.githubexample.ui.HomeList;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.ui.HomeMap.HomeMapFragment;
import com.laioffer.githubexample.ui.jobInfo.JobInfoFragment;
import com.laioffer.githubexample.ui.search.SearchFragment;
import com.laioffer.githubexample.ui.userInfo.UserInfoFragment;

public class HomeListFragment extends Fragment {

    private HomeListViewModel mViewModel;

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
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_home_list, new SearchFragment(), null)
                        .addToBackStack(null)
                        .commit();
            }
        });
        Button button2 = view.findViewById(R.id.JobInfo);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_home_list, new JobInfoFragment(), null)
                        .addToBackStack(null)
                        .commit();
            }
        });
        Button button3 = view.findViewById(R.id.HomeMap);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_home_list, new HomeMapFragment(), null)
                        .addToBackStack(null)
                        .commit();
            }
        });
        Button button4 = view.findViewById(R.id.UserInfo);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_home_list, new UserInfoFragment(), null)
                        .addToBackStack(null)
                        .commit();
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
}
