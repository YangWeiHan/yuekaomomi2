package com.example.vidolineretailers.totalpage.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.vidolineretailers.R;
import com.example.vidolineretailers.baseactivity.BaseActivity;
import com.example.vidolineretailers.totalpage.ui.fragment.CarFragment;
import com.example.vidolineretailers.totalpage.ui.fragment.CircleFragment;
import com.example.vidolineretailers.totalpage.ui.fragment.HomeFragment;
import com.example.vidolineretailers.totalpage.ui.fragment.ListFragment;
import com.example.vidolineretailers.totalpage.ui.fragment.MainFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TotalPageActivity extends BaseActivity {

    @BindView(R.id.home_pager)
    ViewPagerSlide homePager;
    @BindView(R.id.foot_image)
    ImageView footImage;
    @BindView(R.id.tab_car)
    RadioButton homeCar;
    @BindView(R.id.home_home)
    RadioButton homeHome;
    @BindView(R.id.home_circle)
    RadioButton homeCircle;
    @BindView(R.id.home_list)
    RadioButton homeList;
    @BindView(R.id.home_main)
    RadioButton homeMain;
    @BindView(R.id.radio_group)
    RadioGroup radioGroup;
    private List<Fragment> fragments_list;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        //开启一个事物
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    @Override
    protected void initData() {
        totalPager();
    }

    private void totalPager() {
        fragments_list = new ArrayList<>();
        fragments_list.add(new HomeFragment());
        fragments_list.add(new CircleFragment());
        fragments_list.add(new CarFragment());
        fragments_list.add(new ListFragment());
        fragments_list.add(new MainFragment());
        homePager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments_list.get(i);
            }

            @Override
            public int getCount() {
                return fragments_list.size();
            }
        });
        homePager.setSlide(false);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.home_home:
                        homePager.setCurrentItem(0);
                        break;
                    case R.id.home_circle:
                        homePager.setCurrentItem(1);
                        break;
                    case R.id.tab_car:
                        homePager.setCurrentItem(2);
                        break;
                    case R.id.home_list:
                        homePager.setCurrentItem(3);
                        break;
                    case R.id.home_main:
                        homePager.setCurrentItem(4);
                        break;
                }
            }
        });
    }

    @Override
    protected int initContextView() {
        return R.layout.activity_total_page;
    }


}
