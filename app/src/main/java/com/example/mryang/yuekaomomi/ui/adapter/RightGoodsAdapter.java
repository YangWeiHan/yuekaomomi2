package com.example.mryang.yuekaomomi.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mryang.yuekaomomi.R;
import com.example.mryang.yuekaomomi.data.bean.RightGoodsBean;

import java.util.List;

public class RightGoodsAdapter extends BaseQuickAdapter<RightGoodsBean.DataBean,BaseViewHolder> {
    public RightGoodsAdapter(int layoutResId, @Nullable List<RightGoodsBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RightGoodsBean.DataBean item) {
        helper.setText(R.id.right_goods_title,item.getName());
        RecyclerView main_goods = helper.getView(R.id.main_goods);
        //商品条目
        List<RightGoodsBean.DataBean.ListBean> beanList = item.getList();
        //布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,2,GridLayoutManager.VERTICAL,false);
        main_goods.setLayoutManager(gridLayoutManager);
        //适配器
        MainGoodsAdapter mainAdapter = new MainGoodsAdapter(R.layout.goods_item,beanList);
        main_goods.setAdapter(mainAdapter);


    }
}
