
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

public class PZSHAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<HomeGoodsBean.ResultBean.PzshBean.CommodityListBeanX> pzshBean;

    public PZSHAdapter(Context context) {
        this.context = context;
        pzshBean = new ArrayList<>();
    }

    public void setPzshBean(List<HomeGoodsBean.ResultBean.PzshBean.CommodityListBeanX> pzshBean) {
        this.pzshBean = pzshBean;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = View.inflate(context, R.layout.pzss_goods_item,null);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.pzsh_tv_price.setText("$"+pzshBean.get(i).getPrice()+"");
        holder.pzsh_tv_name.setText(pzshBean.get(i).getCommodityName());
        Glide.with(context).load(pzshBean.get(i).getMasterPic()).into(holder.pzsh_iv_icon);
    }

    @Override
    public int getItemCount() {
        return pzshBean.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pzsh_iv_icon;
        TextView pzsh_tv_name,pzsh_tv_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pzsh_iv_icon = itemView.findViewById(R.id.pzss_iv_icon);
            pzsh_tv_name = itemView.findViewById(R.id.pzss_tv_name);
            pzsh_tv_price = itemView.findViewById(R.id.pzss_tv_price);
        }
    }
}

