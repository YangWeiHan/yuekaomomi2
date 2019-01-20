package com.example.mryang.yuekaomomi.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mryang.yuekaomomi.R;
import com.example.mryang.yuekaomomi.data.bean.LeftGoodsBean;

import java.util.List;

public class LeftGoodsAdapter extends BaseQuickAdapter<LeftGoodsBean.DataBean,BaseViewHolder> {
    public LeftGoodsAdapter(int layoutResId, @Nullable List<LeftGoodsBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LeftGoodsBean.DataBean item) {
        helper.setText(R.id.left_goods_name,item.getName());
    }
}
