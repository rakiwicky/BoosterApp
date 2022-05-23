package com.rw.boosterapp;

import android.app.Application;

import com.rw.boosterapp.database.ormlite.DBHelper;

/**
 * Created by Rakhita on 4/21/2018.
 */

public class InitApp extends Application {
    @Override
    public void onCreate() {

        super.onCreate();
        //initial database creation..
        new DBHelper(getApplicationContext()).getWritableDatabase();
    }
}
