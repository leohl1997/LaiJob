package com.laioffer.githubexample.ui.userInfo;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.base.BaseRepository;
import com.laioffer.githubexample.base.BaseViewModel;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.favorite.FavoriteJobFragment;

public class UserInfoFragment extends BaseFragment {

    private UserInfoViewModel mViewModel;

    public static UserInfoFragment newInstance() {
        return new UserInfoFragment();
    }

    private NavigationManager navigationManager;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        navigationManager = (NavigationManager) context;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_info_fragment, container, false);
        Button button = view.findViewById(R.id.Favorite);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationManager.navigateTo(new FavoriteJobFragment());
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
