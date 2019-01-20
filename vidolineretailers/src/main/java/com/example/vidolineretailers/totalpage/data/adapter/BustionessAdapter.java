package com.example.vidolineretailers.totalpage.data.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.vidolineretailers.R;
import com.example.vidolineretailers.totalpage.data.bean.GoodsBean;

import java.util.List;

public class BustionessAdapter extends BaseQuickAdapter<GoodsBean.DataBean,BaseViewHolder> {
    OnBusinessItemClickLisenter onBusinessItemClickLisenter;

    public interface OnBusinessItemClickLisenter {
        public void onCallBack();
    }

    public void setOnBusinessItemClickLisenter(OnBusinessItemClickLisenter onBusinessItemClickLisenter) {
        this.onBusinessItemClickLisenter = onBusinessItemClickLisenter;
    }
    public BustionessAdapter(int layoutResId, @Nullable List<GoodsBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final GoodsBean.DataBean item) {
        helper.setText(R.id.business_name,item.getSellerName());
        final CheckBox cb_business = helper.getView(R.id.cb_business);

       final RecyclerView recyclerView_goods = helper.getView(R.id.recyclerView_goods);
        List<GoodsBean.DataBean.ListBean> goods_list = item.getList();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView_goods.setLayoutManager(linearLayoutManager);

        final GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.shoppingcar_goods_item, goods_list);
        recyclerView_goods.setAdapter(goodsAdapter);

        cb_business.setOnCheckedChangeListener(null);
        cb_business.setChecked(item.getBusinessChecked());

        goodsAdapter.setOnGoodsItemClickLisenter(new GoodsAdapter.OnGoodsItemClickLisenter() {
            @Override
            public void onCallBack() {
                //遍历获取所有子条目，只要有一个未勾选，商品类别也应该是未勾选
                boolean result = true;
                for (int i = 0; i < item.getList().size(); i++) {
                    result = result & item.getList().get(i).getGoodsChecked();
                }
                cb_business.setChecked(result);
                goodsAdapter.notifyDataSetChanged();
                onBusinessItemClickLisenter.onCallBack();
            }
        });

        cb_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < item.getList().size(); i++) {
                    item.getList().get(i).setGoodsChecked(cb_business.isChecked());
                }
                item.setBusinessChecked(cb_business.isChecked());
                notifyDataSetChanged();
                onBusinessItemClickLisenter.onCallBack();
            }
        });
    }
}
