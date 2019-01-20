package com.example.shoppingcar.ui.activity;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), "5663ac6a94", false);
    }
}
