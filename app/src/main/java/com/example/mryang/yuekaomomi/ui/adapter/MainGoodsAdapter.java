package com.example.mryang.yuekaomomi.ui.adapter;

import android.media.Image;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mryang.yuekaomomi.R;
import com.example.mryang.yuekaomomi.data.bean.RightGoodsBean;
import com.google.gson.Gson;

import java.util.List;

public class MainGoodsAdapter extends BaseQuickAdapter<RightGoodsBean.DataBean.ListBean,BaseViewHolder> {
    public MainGoodsAdapter(int layoutResId, @Nullable List<RightGoodsBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RightGoodsBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_goods_title,item.getName());
        ImageView iv_goods_icov = helper.getView(R.id.iv_goods_icon);
        Glide.with(mContext).load(item.getIcon()).into(iv_goods_icov);

    }
}
