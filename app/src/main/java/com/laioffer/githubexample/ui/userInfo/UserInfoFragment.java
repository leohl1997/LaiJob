package com.laioffer.githubexample.ui.userInfo;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.laioffer.githubexample.MainActivity;
import com.laioffer.githubexample.OnBoardingActivity;
import com.laioffer.githubexample.R;
import com.laioffer.githubexample.ui.HomeMap.HomeMapFragment;
import com.laioffer.githubexample.ui.favorite.FavoriteJobFragment;

import java.util.Objects;

public class UserInfoFragment extends Fragment {

    private UserInfoViewModel mViewModel;

    public static UserInfoFragment newInstance() {
        return new UserInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_info_fragment, container, false);
        Button button = view.findViewById(R.id.Favorite);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_home_list, new FavoriteJobFragment(), null)
                        .addToBackStack(null)
                        .commit();
            }
        });
        Button button1 = view.findViewById(R.id.SignOut);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UserInfoViewModel.class);
        // TODO: Use the ViewModel
    }

}
