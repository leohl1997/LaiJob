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
import android.widget.ImageButton;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;
import com.laioffer.githubexample.R;
import com.laioffer.githubexample.base.BaseFragment;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.remote.response.UserInfo;

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


public class HomeListFragment extends BaseFragment<HomeListViewModel, HomeListRepository>
        implements ItemDataAdapter.OnNoteListener{
    SearchEvent searchEvent;
    private DrawerLayout drawerLayout;
    private AppCompatActivity mactivity;
    private LottieAnimationView animationView;
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
        getAllItem(searchEvent.getKeyWord());
    }

    private void getAllItem(String keyWord) {

        viewModel.getListJobMutableLiveData(keyWord).observe(getViewLifecycleOwner(), list -> {
            animationView.setVisibility(View.INVISIBLE);
            if (list == null || list.size() == 0) {
                Utils.constructToast(getContext(),"Loading failed. Check connection").show();
                return;
            }
            adapter.setItems(new ArrayList<>(list),searchEvent.getFilterRule());
            adapter.setOnNoteListener(this);
            adapter.notifyDataSetChanged();
        });
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.home_list_fragment, container, false);
        animationView = view.findViewById(R.id.loading_animation);
        //animationView.setVisibility(View.VISIBLE);

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
                            Config.username = null;
                            mactivity.finish();
                        }
                        if (menuItem.getItemId() == R.id.user_info) {
                            Config.username = null;
                            navigationManager.navigateTo(new UserInfoFragment());
                        }
                        if (menuItem.getItemId() == R.id.favorite) {
                            Config.username = null;
                            navigationManager.navigateTo(new FavoriteJobFragment());
                        }
                        if (menuItem.getItemId() == R.id.search) {
                            Config.username = null;
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

                    @Override
                    public void onDrawerOpened(@NonNull View drawerView) {
                        final TextView user_textview = (TextView) drawerView.findViewById(R.id.user_name);
                        final TextView location_textview = (TextView) drawerView.findViewById(R.id.user_location);

//                        // Respond when the drawer is opened
//                        mLocationTracker.getLocation();
//                        final double longitude = mLocationTracker.getLongitude();
//                        final double latitude = mLocationTracker.getLatitude();

                        if (Config.username == null) {
                            user_textview.setText("");
                            location_textview.setText("");
                        } else {
                            user_textview.setText(Config.username);
//                            location_textview.setText("Lat=" + new DecimalFormat(".##").
//                                    format(latitude) + ",Lon=" + new DecimalFormat(".##").
//                                    format(longitude));
                        }
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


        ImageButton button3 = view.findViewById(R.id.HomeMap);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationManager.navigateTo(new MapFragment());
            }
        });

        return view;
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
        JobInfoFragment fragment = JobInfoFragment.newInstance(current);
        //Utils.constructToast(getContext(),current.getJobDescription()).show();
        navigationManager.navigateTo(fragment);

    }
}