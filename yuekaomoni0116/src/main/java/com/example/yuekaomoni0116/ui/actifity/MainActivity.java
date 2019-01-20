package com.example.yuekaomoni0116.ui.actifity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.yuekaomoni0116.R;
import com.example.yuekaomoni0116.data.adapter.MyPagetAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.shouye_viewpager)
    ViewPager shouyeViewpager;
    @BindView(R.id.shouye_tablayout)
    TabLayout shouyeTablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        shouyeViewpager.setAdapter(new MyPagetAdapter(getSupportFragmentManager()));
        shouyeTablayout.setupWithViewPager(shouyeViewpager);
    }

}
