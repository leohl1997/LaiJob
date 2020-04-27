package com.laioffer.githubexample;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.firebase.analytics.FirebaseAnalytics;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.laioffer.githubexample.ui.HomeList.HomeListFragment;

import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.util.config;
import com.laioffer.githubexample.util.Utils;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements LocationListener,NavigationManager {

    private DrawerLayout drawerLayout;
    private FirebaseAnalytics firebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        getLocation();

<<<<<<< HEAD
        //navigateTo(new CommentFragment());
        navigateTo(new OnBoardingBaseFragment());
=======
        navigateTo(new OnBoardingSplashFragment());
>>>>>>> bc0c1af1071fcf9a78b627c46fc9c842d2395ee1
    }

    @Override
    public void navigateTo(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.first_fragment, fragment, null)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateToWithAnimation(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_to_right, R.anim.exit_to_right,
                        R.anim.enter_to_right, R.anim.exit_to_right)
                .replace(R.id.first_fragment, fragment, null)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateWithFragmentDestroy(Fragment target, Fragment current) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.first_fragment, target, null)
                .addToBackStack(null)
                .remove(current)
                .commit();
    }

    @Override
    public void goBack() {
        getSupportFragmentManager().popBackStack();
    }


    void getLocation() {
        try {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
<<<<<<< HEAD
        config.lat = location.getLatitude();
        config.lon = location.getLongitude();
=======

        Config.latitude = location.getLatitude();
        Config.longitude = location.getLongitude();
>>>>>>> bc0c1af1071fcf9a78b627c46fc9c842d2395ee1
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Utils.constructToast(MainActivity.this, "Please Enable GPS and Internet").show();
    }

}
