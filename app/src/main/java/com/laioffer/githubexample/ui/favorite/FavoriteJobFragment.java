package com.laioffer.githubexample.ui.favorite;

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

public class FavoriteJobFragment extends Fragment {

    private FavoriteJobViewModel mViewModel;

    public static FavoriteJobFragment newInstance() {
        return new FavoriteJobFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_job_fragment, container, false);
        Button button = view.findViewById(R.id.JobInfo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        // modified. change the base view to onboarding base fragment.
                        .replace(R.id.first_fragment, new JobInfoFragment(), null)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FavoriteJobViewModel.class);
        // TODO: Use the ViewModel
    }

}
