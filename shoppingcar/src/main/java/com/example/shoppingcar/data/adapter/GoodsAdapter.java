package com.example.shoppingcar.data.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shoppingcar.R;
import com.example.shoppingcar.data.bean.GoodsBean;
import com.example.shoppingcar.ui.weight.CalculatorView;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<GoodsBean.DataBean.ListBean,BaseViewHolder> {

    //整一个接口回调  用于单选的处理  为了商家的适配器能都调用
    private GoodsCallBackListener goodsCallBackListener;
    //构造方法
    public void setGoodsCallBackListener(GoodsCallBackListener goodsCallBackListener) {
        this.goodsCallBackListener = goodsCallBackListener;
    }
    //返回到  商品的适配器
    public interface GoodsCallBackListener {
        //回调的数据
        void callBack();
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
        //得到CB 的资源ID
        final CheckBox cb_goods = helper.getView(R.id.cb_goods);
        //避免冲突
        cb_goods.setOnCheckedChangeListener(null);
        //得到 字条目的选中状态
        cb_goods.setChecked(item.getGoodsChecked());
       //以接口的方式把状态回传给外层(商家的适配器)
        cb_goods.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Bean对象状态进行更新完毕
                item.setGoodsChecked(isChecked);
                //  进行回传
                goodsCallBackListener.callBack();
            }
        });
        
        /*
        * 加加  减减
        * */
        CalculatorView calculatorView = helper.getView(R.id.cv_calculatorView);
        calculatorView.setOnCalCulatorLisenter(new CalculatorView.OnCalCulatorLisenter() {
            @Override
            public void onDecrease(int number) {
                //减减

                item.setDefalutNumber(number);
                goodsCallBackListener.callBack();

            }

            @Override
            public void onAdd(int number) {
                //加加
                item.setDefalutNumber(number);
                goodsCallBackListener.callBack();
            }
        });
    }



}

