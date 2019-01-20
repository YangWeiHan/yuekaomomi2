package com.example.vidolineretailers.totalpage.data.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.vidolineretailers.R;
import com.example.vidolineretailers.totalpage.data.bean.GoodsBean;
import com.example.vidolineretailers.totalpage.ui.weight.CalculationView;

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
        CheckBox cb_goods = helper.getView(R.id.cb_goods);
        ImageView good_icon = helper.getView(R.id.good_icon);

        String imagesUrl = item.getImages();
        String[] split = imagesUrl.split("\\|");
        Glide.with(mContext).load(split[0]).into(good_icon);

        cb_goods.setOnCheckedChangeListener(null);
        cb_goods.setChecked(item.getGoodsChecked());
        //添加商品的选中状态
        cb_goods.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setGoodsChecked(isChecked);
                if (onGoodsItemClickLisenter != null){
                    onGoodsItemClickLisenter.onCallBack();
                }
            }
        });

        //加减器
        CalculationView cv_calculatorView = helper.getView(R.id.cv_calculatorView);
       /* cv_calculatorView.setOnCalCulatorLisenter(new CalculationView.OnCalCulatorLisenter() {
            @Override
            public void onDecrese(int number) {
                //对新增的字段进行改动
                item.setNum(number);
                onGoodsItemClickLisenter.onCallBack();
            }

            @Override
            public void onAdd(int number) {
                //对新增的字段进行改动
                item.setNum(number);
                onGoodsItemClickLisenter.onCallBack();
            }
        });*/

       cv_calculatorView.setOnCalCulatorLisenter(new CalculationView.OnCalCulatorLisenter() {
           @Override
           public void onDecrease(int number) {
               //对新增的字段进行改动
               item.setNum(number);
               onGoodsItemClickLisenter.onCallBack();
           }

           @Override
           public void onAdd(int number) {
               //对新增的字段进行改动
               item.setNum(number);
               onGoodsItemClickLisenter.onCallBack();
           }
       });
    }
}
