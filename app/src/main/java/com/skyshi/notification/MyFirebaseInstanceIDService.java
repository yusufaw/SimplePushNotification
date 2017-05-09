package com.skyshi.notification;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by yusuf on 5/8/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";
    private SharedPreferences sharedPreferences;

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String strRefreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + strRefreshedToken);

        sendRegistrationToServer(strRefreshedToken);

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
