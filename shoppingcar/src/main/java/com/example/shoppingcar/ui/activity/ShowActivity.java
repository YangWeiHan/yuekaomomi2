package com.example.shoppingcar.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.shoppingcar.R;
import com.example.shoppingcar.data.adapter.PagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.vp_viewpager)
    ViewPager vpViewpager;
    @BindView(R.id.tb_tablayput)
    TabLayout tbTablayput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);

        vpViewpager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        tbTablayput.setupWithViewPager(vpViewpager);
    }
}
