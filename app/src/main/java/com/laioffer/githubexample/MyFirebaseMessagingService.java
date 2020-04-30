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
import android.os.Bundle;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.laioffer.githubexample.remote.ApiService;
import com.laioffer.githubexample.remote.ApiUtils;
import com.laioffer.githubexample.remote.response.BaseResponse;
import com.laioffer.githubexample.remote.response.Job;
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
    private Context mContext;

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
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (mContext == null) {
            mContext = getBaseContext();
        }
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            sendNotification(remoteMessage);
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }


    }

    /**
     * Create and show a simple notification containing the received FCM message.
     */
    /**
     * Create and show a simple notification containing the received FCM message.
     */
    private void sendNotification(@NonNull RemoteMessage remoteMessage) {
        Gson gson = new Gson();
        String userId = remoteMessage.getData().get("user_id");
        String comment = remoteMessage.getData().get("comment");
        String jobStr = remoteMessage.getData().get("job");
        Job job = gson.fromJson(jobStr, Job.class);

        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("job", job);
        intent.putExtras(args);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "101";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_HIGH);

            //Configure Notification Channel
            notificationChannel.setDescription("Laijob Notfication");
            notificationChannel.enableLights(true);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setContentTitle(userId + " comments on Job " + job.name + "!")  // job.name
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.star_solid)
                .setSound(defaultSound)
                .setContentText(comment)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_MAX);

        notificationManager.notify(1, notificationBuilder.build());

    }


}
