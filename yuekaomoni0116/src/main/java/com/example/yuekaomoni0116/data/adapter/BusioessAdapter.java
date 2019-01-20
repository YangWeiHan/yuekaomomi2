package com.example.yuekaomoni0116.data.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yuekaomoni0116.R;
import com.example.yuekaomoni0116.data.bean.GoodsBean;

import java.util.List;

public class BusioessAdapter extends BaseQuickAdapter<GoodsBean.DataBean,BaseViewHolder> {

    OnBusinessItemClickLisenter onBusinessItemClickLisenter;
    public interface OnBusinessItemClickLisenter {
        public void onCallBack();
    }
    public void setOnBusinessItemClickLisenter(OnBusinessItemClickLisenter onBusinessItemClickLisenter) {
        this.onBusinessItemClickLisenter = onBusinessItemClickLisenter;
    }

    public BusioessAdapter(int layoutResId, @Nullable List<GoodsBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final GoodsBean.DataBean item) {
        helper.setText(R.id.business_name, item.getSellerName());
        final CheckBox cb_business = helper.getView(R.id.cb_business);

        RecyclerView recyclerView_goods = helper.getView(R.id.recyclerView_goods);
        List<GoodsBean.DataBean.ListBean> goods_list = item.getList();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView_goods.setLayoutManager(linearLayoutManager);

        final GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.goods_item, goods_list);
        recyclerView_goods.setAdapter(goodsAdapter);
        //得到  商家单选框的资源ID  避免焦点抢占   获取选中状态
        //内部子条目控制外部条目
        cb_business.setOnCheckedChangeListener(null);
        cb_business.setChecked(item.getBusinessChecked());
        //在商家的适配器中  接收到商品适配器通过回到方法传来的值   进行逻辑分析
        goodsAdapter.setOnGoodsItemClickLisenter(new GoodsAdapter.OnGoodsItemClickLisenter() {
            @Override
            public void onCallBack() {
                //遍历商家获取所有子条目，只要有一个未勾选，商品类别也应该是未勾选
                //首先设置一个变量    默认值为true
                boolean result = true;
                //遍历商家里的所有子条目
                for (int i = 0; i < item.getList().size(); i++) {
                    //这里的核心思想是  true&fales
                        result = result & item.getList().get(i).getGoodsChecked();
                }
                //得到一个状态值后  将其设置给 商家的状态值
                cb_business.setChecked(result);
                //刷新子条目
                goodsAdapter.notifyDataSetChanged();

                onBusinessItemClickLisenter.onCallBack();
            }
        });
        //外层的商品类别条目需要控制里面的商品条目
        cb_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取商品的勾选状态
                for (int i = 0; i < item.getList().size(); i++) {
                    //获取到商家的选中状态
                    //之后给每一个子条目赋值
                    item.getList().get(i).setGoodsChecked(cb_business.isChecked());
                }

                item.setBusinessChecked(cb_business.isChecked());
                notifyDataSetChanged();
                //把最后的状态进行回传
                onBusinessItemClickLisenter.onCallBack();
            }
        });
    }
}
