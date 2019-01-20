package com.example.shoppingcar.data.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shoppingcar.R;
import com.example.shoppingcar.data.bean.GoodsBean;

import java.util.List;

public class BusioessAdapter extends BaseQuickAdapter<GoodsBean.DataBean,BaseViewHolder> {
    //设置商家的接口回调  把数据回传给V层
    OnBusioessItemClickListenter onBusioessItemClickListenter;

    public interface OnBusioessItemClickListenter{

        void callback();
    }

    public void setOnBusioessItemClickListenter(OnBusioessItemClickListenter onBusioessItemClickListenter) {
        this.onBusioessItemClickListenter = onBusioessItemClickListenter;
    }

    public BusioessAdapter(int layoutResId, @Nullable List<GoodsBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final GoodsBean.DataBean item) {
        //设置商家名称
        helper.setText(R.id.business_name,item.getSellerName());
        //获取ID  获取商家 的资源ID
        final CheckBox cb_business = helper.getView(R.id.cb_business);
        //避免焦点强占
        cb_business.setOnCheckedChangeListener(null);
        //获得Bean类里  商家的选中状态
        cb_business.setChecked(item.getBusinessChecked());

        //展示商家里的商品
        RecyclerView recyclerView_googs = helper.getView(R.id.recyclerView_goods);
        
        //设置子条目数据源
        List<GoodsBean.DataBean.ListBean> goodsList = item.getList();
        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView_googs.setLayoutManager(layoutManager);
        //适配器
        final GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.goods_item, goodsList);
        recyclerView_googs.setAdapter(goodsAdapter);

        /*****************************************************************/
        //首先处理内部子条目控制外部条目
        goodsAdapter.setGoodsCallBackListener(new GoodsAdapter.GoodsCallBackListener() {
            @Override
            public void callBack() {
            //遍历获取所有子条目，只要有一个未勾选，商品类别也应该是未勾选
               boolean result = true;
                for (int i = 0; i < item.getList().size(); i++) {
                    result = result & item.getList().get(i).getGoodsChecked();
                }
                cb_business.setChecked(result);
                //刷新 适配器
                goodsAdapter.notifyDataSetChanged();
                //把最后的状态进行回传
                onBusioessItemClickListenter.callback();
            }
        });

        /*****************************************************************/
        //然后外层的商品类别条目需要控制里面的商品条目
        cb_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取商品类别勾选状态
                //外层商品类别条目获取的关键：cb_business.isChecked()
                for (int i = 0; i < item.getList().size(); i++) {
                    item.getList().get(i).setGoodsChecked(cb_business.isChecked());
                }

                item.setBusinessChecked(cb_business.isChecked());
                //刷新
                notifyDataSetChanged();
                //把最后的状态进行回传
                onBusioessItemClickListenter.callback();
            }
        });

    }
}
