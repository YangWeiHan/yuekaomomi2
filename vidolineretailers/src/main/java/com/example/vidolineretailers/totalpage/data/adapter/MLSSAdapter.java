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

public class MLSSAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<HomeGoodsBean.ResultBean.MlssBean.CommodityListBeanXX> mlssBeans;

    public MLSSAdapter(Context context) {
        this.context = context;
        mlssBeans = new ArrayList<>();
    }

    public void setMlssBeans(List<HomeGoodsBean.ResultBean.MlssBean.CommodityListBeanXX> mlssBeans) {
        this.mlssBeans = mlssBeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = View.inflate(context, R.layout.mlss_goods_item,null);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.mlss_tv_price.setText("$"+mlssBeans.get(i).getPrice());
        holder.mlss_tv_name.setText(mlssBeans.get(i).getCommodityName());
        Glide.with(context).load(mlssBeans.get(i).getMasterPic()).into(holder.mlss_iv_icon);

    }

    @Override
    public int getItemCount() {
        return mlssBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mlss_iv_icon;
        TextView mlss_tv_name,mlss_tv_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mlss_iv_icon = itemView.findViewById(R.id.mlss_iv_icon);
            mlss_tv_name = itemView.findViewById(R.id.mlss_tv_name);
            mlss_tv_price = itemView.findViewById(R.id.mlss_tv_price);
        }
    }
}
