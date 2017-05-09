package com.skyshi.notification;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSubscribe = (Button) findViewById(R.id.btnSubscribe);
        Button btnUnsubscribe = (Button) findViewById(R.id.btnUnsubcribe);

        sharedPreferences   = getSharedPreferences(Constants.PREF_NAME, Activity.MODE_PRIVATE);
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("news");
                Log.d("FIREBASE_NOTIF_TOKEN", sharedPreferences.getString(Constants.FIREBASE_NOTIF_TOKEN, ""));
                Log.d("token", "onClick: "+ FirebaseInstanceId.getInstance().getToken());
            }
        });

        btnUnsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("news");
            }
        });
    }
}
