//Nayan Pasari
//ID: 111868106
package com.example.hackmatcher;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String MESSAGE = "message";

    /*
    OnCreate method to make the notification channel work.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    /*
    createNotificationChannel: Method to create a notification with a message/description.
     */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    MESSAGE,
                    "message",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Hello, a new student's here!");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
