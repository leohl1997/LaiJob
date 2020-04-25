package com.laioffer.githubexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import com.laioffer.githubexample.ui.HomeList.HomeListFragment;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.login.LoginFragment;
import com.laioffer.githubexample.ui.map.MapFragment;
import com.laioffer.githubexample.ui.search.SearchFragment;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.Utils;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements NavigationManager, LocationListener {
    private DrawerLayout drawerLayout;
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        navigateTo(new OnBoardingSplashFragment());
//        navigateTo(new OnBoardingBaseFragment());
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

    void getLocation() {
        try {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Config.lat = location.getLatitude();
        Config.lon = location.getLongitude();
        String msg = "Latitude: " + Config.lat + ", Longitude: " + Config.lon;
        Utils.constructToast(MainActivity.this, msg).show();
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
