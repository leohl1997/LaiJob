package com.laioffer.githubexample.ui.HomeMap;

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
import android.widget.Button;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.ui.HomeList.HomeListFragment;
import com.laioffer.githubexample.ui.NavigationManager;

public class HomeMapFragment extends BaseFragment {
    private NavigationManager navigationManager;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
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

    private HomeMapViewModel mViewModel;

    public static HomeMapFragment newInstance() {
        return new HomeMapFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_map_fragment, container, false);
        Button button = view.findViewById(R.id.ListView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationManager.navigateTo(new HomeListFragment());
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.fragment_home_list, new HomeListFragment(), null)
//                        .addToBackStack(null)
//                        .commit();
//                getActivity().onBackPressed();
                //getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(HomeMapViewModel.class);
        // TODO: Use the ViewModel
    }

}
