package com.skyshi.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by yusuf on 5/5/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    static String CHANNEL_ID = "EXAMPLE_CHANNEL_ID";
    static String GROUP_KEY_WORK_EMAIL = "HEHE_EMAIL";
    static int SUMMARY_ID = 9;


    private static final String TAG = "MyFirebaseIIDService";
    private SharedPreferences sharedPreferences;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        sendNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("body"));
    }

    @Override
    public void onNewToken(String s) {
        sendRegistrationToServer(s);
    }

    public static void sendNotification(String messageTitle, String messageBody) {
        Intent intent = new Intent(MainApplication.context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainApplication.context, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainApplication.context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) MainApplication.context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "android_notification", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.enableLights(true);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0, notificationBuilder.build());
    }

    public static void showGroupNotification() {
        Notification newMessageNotification1 =
                new NotificationCompat.Builder(MainApplication.context, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_email_black_24dp)
                        .setContentTitle("Email 1")
                        .setContentText("You will not believe...")
                        .setGroup(GROUP_KEY_WORK_EMAIL)
                        .build();

        Notification newMessageNotification2 =
                new NotificationCompat.Builder(MainApplication.context, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_email_black_24dp)
                        .setContentTitle("Email 2")
                        .setContentText("Please join us to celebrate the...")
                        .setGroup(GROUP_KEY_WORK_EMAIL)
                        .build();

        Notification summaryNotification =
                new NotificationCompat.Builder(MainApplication.context, CHANNEL_ID)
                        .setContentTitle("Hehe")
                        //set content text to support devices running API level < 24
                        .setContentText("Two new messages")
                        .setSmallIcon(R.drawable.ic_email_black_24dp)
                        //build summary info into InboxStyle template
                        .setStyle(new NotificationCompat.InboxStyle()
                                .addLine("Alex Faarborg  Check this out")
                                .addLine("Jeff Chang    Launch Party")
                                .setBigContentTitle("2 new messages")
                                .setSummaryText("janedoe@example.com"))
                        //specify which group this notification belongs to
                        .setGroup(GROUP_KEY_WORK_EMAIL)
                        //set this notification as the summary for the group
                        .setGroupSummary(true)
                        .build();

        NotificationManager notificationManager =
                (NotificationManager) MainApplication.context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, newMessageNotification1);
        notificationManager.notify(2, newMessageNotification2);
        notificationManager.notify(SUMMARY_ID, summaryNotification);

    }


    private void sendRegistrationToServer(String strRefreshedToken) {
        //You can implement this method to store the token on your server
        //Not required for current project
        sharedPreferences   = getSharedPreferences(Constants.PREF_NAME, Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.FIREBASE_NOTIF_TOKEN, strRefreshedToken);
        editor.apply();
    }
}
