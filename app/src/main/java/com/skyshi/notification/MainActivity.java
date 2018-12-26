package com.skyshi.notification;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.PayloadBuilder;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSubscribe = findViewById(R.id.btnSubscribe);
        Button btnUnsubscribe = findViewById(R.id.btnUnsubcribe);

        Button btnTestNotification = findViewById(R.id.btnTestNotif);
        Button btnTestGroupNotification = findViewById(R.id.btnTestGroupNotif);

        sharedPreferences = getSharedPreferences(Constants.PREF_NAME, Activity.MODE_PRIVATE);
        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("news");
                Log.d("FIREBASE_NOTIF_TOKEN", sharedPreferences.getString(Constants.FIREBASE_NOTIF_TOKEN, ""));
                Log.d("token", "onClick: " + FirebaseInstanceId.getInstance().getToken());
            }
        });

        btnUnsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("news");
            }
        });

        btnTestNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFirebaseMessagingService.sendNotification("My Notification", "Hello Kamu");
            }
        });

        btnTestGroupNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyFirebaseMessagingService.showGroupNotification();
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

//        PayloadBuilder builder = new PayloadBuilder()
//                .putAttrDate("sign-up-date", new Date())
//                .putAttrString("type", "user-pass");
//        //sample track event
//        MoEHelper.getInstance(getApplicationContext()).trackEvent("Sign Up", builder.build());
    }
}
