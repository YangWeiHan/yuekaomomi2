package com.example.vidolineretailers.baseactivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(initContextView());

        initView();

        initData();

    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int initContextView();
}
