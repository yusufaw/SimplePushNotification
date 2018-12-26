package com.skyshi.notification;

import android.app.Application;
import android.content.Context;

import com.moengage.core.Logger;
import com.moengage.core.MoEngage;

public class MainApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        MoEngage moEngage =
                new MoEngage.Builder(this, getString(R.string.moengage_key))//enter your own app id
//                 .setSenderId("XXXXXXXXXXXX")//enter your gcm sender_id
                        .setLogLevel(Logger.VERBOSE)//enabling Logs for debugging
                        .enableLogsForSignedBuild() //Make sure this is removed before apps are pushed to production
                        .setNotificationSmallIcon(R.drawable.ic_email_black_24dp)//small icon should be flat, pictured face on, and must be white on a transparent background.
                        .setNotificationLargeIcon(R.mipmap.ic_launcher)
                        .enableLocationServices()//enabled To track location and run geo-fence campaigns
                        .build();

        MoEngage.initialise(moEngage);
    }
}
