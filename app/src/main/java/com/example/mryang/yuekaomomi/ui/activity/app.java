package com.example.mryang.yuekaomomi.ui.activity;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    //    CrashReport.initCrashReport(getApplicationContext(), "aef6db48d9", false);
        CrashReport.initCrashReport(getApplicationContext(), "aef6db48d9", false);
    }
}
