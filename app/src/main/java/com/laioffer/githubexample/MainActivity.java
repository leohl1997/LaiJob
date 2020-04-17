package com.laioffer.githubexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.laioffer.githubexample.ui.HomeList.HomeListFragment;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.login.LoginFragment;
import com.laioffer.githubexample.ui.search.SearchFragment;

public class MainActivity extends AppCompatActivity implements NavigationManager {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        navigateTo(new LoginFragment());
    }

    @Override
    public void navigateTo(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.first_fragment, fragment, null)
                .addToBackStack(null)
                .commit();
    }


    // add Fragment to the activity
    //getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, MainFragment.newInstance()).commit();
}
