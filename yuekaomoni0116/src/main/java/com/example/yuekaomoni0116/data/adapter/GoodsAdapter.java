package com.example.yuekaomoni0116.data.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuekaomoni0116.R;
import com.example.yuekaomoni0116.data.bean.GoodsBean;
import com.example.yuekaomoni0116.ui.webgit.CalculatorView;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<GoodsBean.DataBean.ListBean,BaseViewHolder> {
    OnGoodsItemClickLisenter onGoodsItemClickLisenter;
    public void setOnGoodsItemClickLisenter(OnGoodsItemClickLisenter onGoodsItemClickLisenter) {
        this.onGoodsItemClickLisenter = onGoodsItemClickLisenter;
    }
    //接口回调
    public interface OnGoodsItemClickLisenter {
        public void onCallBack();
    }
    public GoodsAdapter(int layoutResId, @Nullable List<GoodsBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, final GoodsBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_goods_name,item.getTitle());
        helper.setText(R.id.tv_goods_price,"￥"+item.getPrice());

        ImageView good_icon = helper.getView(R.id.good_icon);

        String imagesUrl = item.getImages();
        String[] split = imagesUrl.split("\\|");
        Glide.with(mContext).load(split[0]).into(good_icon);
        //得到  商品的资源ID
        CheckBox cb_goods = helper.getView(R.id.cb_goods);
        //设置  避免焦点抢占
        cb_goods.setOnCheckedChangeListener(null);
        //获取商品的选中状态（在Bean类里面需要定义商品的选中状态）
        cb_goods.setChecked(item.getGoodsChecked());
        //添加商品的选中状态
        cb_goods.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //得到Beean类里商品条目的选中状态
                item.setGoodsChecked(isChecked);
                //判空以后进行数据的回传  （回传后  由商家的适配器进行接收）
                if (onGoodsItemClickLisenter != null){
                    onGoodsItemClickLisenter.onCallBack();
                }
            }
        });
        //加加减减
        CalculatorView cv_calculatorView = helper.getView(R.id.cv_calculatorView);
        cv_calculatorView.setOnCalCulatorLisenter(new CalculatorView.OnCalCulatorLisenter() {
            @Override
            public void onDecrease(int number) {
                item.setNum(number);
                onGoodsItemClickLisenter.onCallBack();
            }

            @Override
            public void onAdd(int number) {
                item.setNum(number);
                onGoodsItemClickLisenter.onCallBack();
            }
        });
    }
}
