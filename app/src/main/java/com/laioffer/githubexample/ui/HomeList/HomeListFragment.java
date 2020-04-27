package com.laioffer.githubexample.ui.HomeList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
import com.laioffer.githubexample.ui.map.MapFragment;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.favorite.FavoriteJobFragment;
import com.laioffer.githubexample.ui.jobInfo.JobInfoFragment;
import com.laioffer.githubexample.ui.search.SearchFragment;
import com.laioffer.githubexample.ui.userInfo.UserInfoFragment;
import com.laioffer.githubexample.util.config;

public class HomeListFragment extends BaseFragment<HomeListViewModel, HomeListRepository> {
    private HomeListViewModel mViewModel;
    private NavigationManager navigationManager;
    private DrawerLayout drawerLayout;
    private AppCompatActivity mactivity;

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
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.home_list_fragment, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);

        mactivity = (AppCompatActivity) getActivity();
        assert mactivity != null;
        mactivity.setSupportActionBar(toolbar);
        ActionBar actionbar = mactivity.getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.baseline_home_black_18dp);


        drawerLayout = view.findViewById(R.id.drawer_layout);

        NavigationView navigationView = view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();
                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        if (menuItem.getItemId() == R.id.drawer_logout) {
                            config.username = null;
                            mactivity.finish();
                        }
                        if (menuItem.getItemId() == R.id.user_info) {
                            config.username = null;
                            navigationManager.navigateTo(new UserInfoFragment());
                        }
                        if (menuItem.getItemId() == R.id.favorite) {
                            config.username = null;
                            navigationManager.navigateTo(new FavoriteJobFragment());
                        }
                        if (menuItem.getItemId() == R.id.search) {
                            config.username = null;
                            navigationManager.navigateTo(new SearchFragment());
                        }
                        return true;
                    }
                });

        drawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDrawerOpened(@NonNull View drawerView) {
                        final TextView user_textview = (TextView) drawerView.findViewById(R.id.user_name);
                        final TextView location_textview = (TextView) drawerView.findViewById(R.id.user_location);

//                        // Respond when the drawer is opened
//                        mLocationTracker.getLocation();
//                        final double longitude = mLocationTracker.getLongitude();
//                        final double latitude = mLocationTracker.getLatitude();
//                        UserInfo userInfo = new UserInfo();
//                        if (userInfo.profile.user_id == null) {
//                            user_textview.setText("");
//                            location_textview.setText("");
//                        } else {
//                            //LoginEvent event = new LoginEvent();
//                            //user_textview.setText(new UserInfo().profile.user_id);
////                            location_textview.setText("Lat=" + new DecimalFormat(".##").
////                                    format(latitude) + ",Lon=" + new DecimalFormat(".##").
////                                    format(longitude));
//                        }
                    }

                    @Override
                    public void onDrawerClosed(@NonNull View drawerView) {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {

                    }
                }
        );
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
                navigationManager.navigateTo(new MapFragment());
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
        //mViewModel = ViewModelProviders.of(this).get(HomeListViewModel.class);
        // TODO: Use the ViewModel
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
}
