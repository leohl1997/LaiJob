package com.laioffer.githubexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.laioffer.githubexample.ui.HomeList.HomeListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }


    // add Fragment to the activity
    //getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, MainFragment.newInstance()).commit();
}
