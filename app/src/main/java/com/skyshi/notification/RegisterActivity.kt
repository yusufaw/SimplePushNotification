package com.skyshi.notification

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.moe.pushlibrary.MoEHelper

import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.content_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        button_loginout.setOnClickListener {
            val sharedPreferences = getSharedPreferences(Constants.PREF_NAME, Activity.MODE_PRIVATE)

            val isAppLogin = sharedPreferences.getBoolean(Constants.IS_APP_LOGIN, false)
            val editor = sharedPreferences.edit()
            if (isAppLogin) {
                MoEHelper.getInstance(applicationContext).logoutUser()
                editor.putBoolean(Constants.IS_APP_LOGIN, false)
                editor.apply()
            } else {
                MoEHelper.getInstance(applicationContext).setUniqueId(12345)
                editor.putBoolean(Constants.IS_APP_LOGIN, true)
                editor.apply()
            }
        }
    }

}
