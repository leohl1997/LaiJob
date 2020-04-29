package com.laioffer.githubexample;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.laioffer.githubexample.remote.response.Job;
import com.laioffer.githubexample.ui.NavigationManager;
import com.laioffer.githubexample.ui.jobInfo.JobInfoFragment;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.Utils;


public class MainActivity extends AppCompatActivity implements LocationListener, NavigationManager {

    private DrawerLayout drawerLayout;
    private FirebaseAnalytics firebaseAnalytics;
    private final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        getLocation();
        getInstanceId();


        navigateTo(new OnBoardingSplashFragment());

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
                .remove(current)
                .replace(R.id.first_fragment, target, null)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goBack() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        Job job = (Job) intent.getExtras().getSerializable("job");
        JobInfoFragment fragment = JobInfoFragment.newInstance(job);
        navigateTo(fragment);
    }


    void getLocation() {
        try {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    void getInstanceId() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "getInstanceId failed", task.getException());
                        return;
                    }

                    String token = task.getResult().getToken();
                    String msg = getString(R.string.msg_token_fmt, token);
                    Log.d(TAG, msg);
                    Config.token = token;
                });
    }

    @Override
    public void onLocationChanged(Location location) {
//        Config.lat = location.getLatitude();
//        Config.lon = location.getLongitude();
        Config.latitude = location.getLatitude();
        Config.longitude = location.getLongitude();
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
