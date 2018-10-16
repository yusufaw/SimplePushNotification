package com.skyshi.notification;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
