package com.example.yuekaomoni0116.ui.actifity;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,"5a12384aa40fa3551f0001d1","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        //下面这一行代码根据需要设置自己的，当前表示qq
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");


    }
}
