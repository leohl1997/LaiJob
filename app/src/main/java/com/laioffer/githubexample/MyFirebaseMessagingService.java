package com.laioffer.githubexample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.laioffer.githubexample.remote.ApiService;
import com.laioffer.githubexample.remote.ApiUtils;
import com.laioffer.githubexample.remote.response.BaseResponse;
import com.laioffer.githubexample.ui.HomeList.TokenEvent;
import com.laioffer.githubexample.util.Config;
import com.laioffer.githubexample.util.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseService";
    private static final ApiService apiService = ApiUtils.getAPIService();

    @Override
    public void onNewToken(@NonNull String token) {
        if (Utils.isNullOrEmpty(Config.userId)) {
            return;
        }
        TokenEvent tokenEvent = new TokenEvent();
        tokenEvent.userId = Config.userId;
        tokenEvent.token = token;
        Call<BaseResponse> call = apiService.sendToken(tokenEvent);
        call.enqueue(new Callback<BaseResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.code() == 200) {
                    Utils.constructToast(getBaseContext(), "Send new token success").show();
                } else {
                    Utils.constructToast(getBaseContext(), "Send new token fail").show();
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Utils.constructToast(getBaseContext(),
                        "Send new token fail." + t.getMessage()).show();
            }
        });

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.

    }

    /**
     * Create and show a simple notification containing the received FCM message.
     */
    /**
     * Create and show a simple notification containing the received FCM message.
     */
    private void sendNotification(RemoteMessage remoteMessage) {

    }


}
