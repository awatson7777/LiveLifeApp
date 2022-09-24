package com.example.appassessment;

import android.Manifest;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.content.ContextCompat;

public class MyApp extends Application {
    public static final String NOTIFY_CHANNEL = "notify";

    @Override
    public void onCreate() {
        super.onCreate(); // gets called before any activity, loads as soon as app is loaded
        createNotification(); // calls a created function
    }
    private void createNotification(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // calls the audio attribute builder if the build version is at least Oreo
            NotificationChannel notify = new NotificationChannel( // sets the notification channel settings
                    NOTIFY_CHANNEL,
                    "notify",
                    NotificationManager.IMPORTANCE_HIGH // sets the notification as high priority which enables a pop down type notification
            );
            notify.setDescription("Notification Working"); // an attribute assigned to the notification channel, in this case a description

            NotificationManager manager = getSystemService(NotificationManager.class); // returns the system level service according to the notification manager
            manager.createNotificationChannel(notify);
        }
    }


}
