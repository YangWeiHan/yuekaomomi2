package com.example.shoppingcar.data.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shoppingcar.ui.fragment.GoodsCarFragment;
import com.example.shoppingcar.ui.fragment.MyPagerFragment;
import com.example.shoppingcar.ui.fragment.ShoppingFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    //设置数据 展示几个Fragment
    private String[] list = new String[]{
            "商品展示","购物车","我的"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new ShoppingFragment();
            case 1:
                return new GoodsCarFragment();
            case 2:
                return new MyPagerFragment();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list[position];
    }
}
