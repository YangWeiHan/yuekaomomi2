package com.example.vidolineretailers.totalpage.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.vidolineretailers.R;
import com.example.vidolineretailers.homepage.data.bean.HomeGoodsBean;

import java.util.ArrayList;
import java.util.List;

public class RXXPAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HomeGoodsBean.ResultBean.RxxpBean.CommodityListBean> rxxpBean;

    public RXXPAdapter(Context context) {
        this.context = context;
        rxxpBean = new ArrayList<>();
    }

    public void setRxxpBean(List<HomeGoodsBean.ResultBean.RxxpBean.CommodityListBean> rxxpBean) {
        this.rxxpBean = rxxpBean;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = View.inflate(context, R.layout.rxxp_goods_item,null);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.rxxp_tv_price.setText("$"+rxxpBean.get(i).getPrice()+"");
        holder.rxxp_tv_name.setText(rxxpBean.get(i).getCommodityName());
        Glide.with(context).load(rxxpBean.get(i).getMasterPic()).into(holder.rxxp_iv_icon);
    }

    @Override
    public int getItemCount() {
        return rxxpBean.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView rxxp_iv_icon;
        TextView rxxp_tv_name,rxxp_tv_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rxxp_iv_icon = itemView.findViewById(R.id.rxxp_iv_icon);
            rxxp_tv_name = itemView.findViewById(R.id.rxxp_tv_name);
            rxxp_tv_price = itemView.findViewById(R.id.rxxp_tv_price);
        }
    }
}
