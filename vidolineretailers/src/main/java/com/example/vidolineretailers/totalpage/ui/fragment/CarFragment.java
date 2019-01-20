package com.example.vidolineretailers.totalpage.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.vidolineretailers.R;
import com.example.vidolineretailers.totalpage.data.adapter.BustionessAdapter;
import com.example.vidolineretailers.totalpage.data.bean.GoodsBean;
import com.example.vidolineretailers.totalpage.di.contract.IGoodsContract;
import com.example.vidolineretailers.totalpage.di.presenter.IGoodsPresenterlmpl;

import java.util.List;

public class CarFragment extends Fragment implements IGoodsContract.IGoodsView,View.OnClickListener {

    private IGoodsPresenterlmpl presenterlmpl;
    private List<GoodsBean.DataBean> busioness_data;
    private RecyclerView busioness_resyclerView;
    private CheckBox cb_all;
    private TextView tv_price;
    private BustionessAdapter bustionessAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_car,null);
        busioness_resyclerView = view.findViewById(R.id.recyclerView_busioess);
        cb_all = view.findViewById(R.id.cb_all);
        tv_price = view.findViewById(R.id.tv_price);
        presenterlmpl = new IGoodsPresenterlmpl();
        presenterlmpl.attahView(this);
        presenterlmpl.goToRequestGoodsData();

        return view;
    }
    private void calculateTotalCount(){
        double totalCount = 0;
        for (int i = 0; i < busioness_data.size(); i++) {
            for (int j = 0; j < busioness_data.get(i).getList().size(); j++) {
                if (busioness_data.get(i).getList().get(j).getGoodsChecked()){
                    double goods_price = busioness_data.get(i).getList().get(j).getPrice();
                    int goods_num = busioness_data.get(i).getList().get(j).getNum();
                    totalCount = totalCount    +  ( goods_num * goods_price);
                }
            }
        }
        tv_price.setText("合计：￥"+String.valueOf(totalCount));
    }

    @Override
    public void setGoodsData(GoodsBean goodsBean) {
        cb_all.setOnCheckedChangeListener(null);
        cb_all.setOnClickListener(this);
        //数据源
        busioness_data = goodsBean.getData();
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        busioness_resyclerView.setLayoutManager(linearLayoutManager);
        //适配器
        bustionessAdapter = new BustionessAdapter(R.layout.shopping_bustioness_item, busioness_data);
        busioness_resyclerView.setAdapter(bustionessAdapter);

        bustionessAdapter.setOnBusinessItemClickLisenter(new BustionessAdapter.OnBusinessItemClickLisenter() {
            @Override
            public void onCallBack() {
                boolean result = true;
                for (int i = 0; i < busioness_data.size(); i++) {
                    //外层选中状态
                    boolean businessChecked = busioness_data.get(i).getBusinessChecked();
                    result = result & businessChecked;
                    for (int j = 0; j <busioness_data.get(i).getList().size() ; j++) {
                        //里层选中状态
                        boolean goodsChecked = busioness_data.get(i).getList().get(j).getGoodsChecked();
                        result = result & goodsChecked;
                    }
                }
                cb_all.setChecked(result);
                calculateTotalCount();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterlmpl.detachView(this);
    }

    @Override
    public void onClick(View v) {
        //全选逻辑   先遍历商家  在遍历商品
        for (int i = 0; i < busioness_data.size(); i++) {
            busioness_data.get(i).setBusinessChecked(cb_all.isChecked());
            for (int j = 0; j < busioness_data.get(i).getList().size(); j++) {
                busioness_data.get(i).getList().get(j).setGoodsChecked(cb_all.isChecked());
            }
        }
        bustionessAdapter.notifyDataSetChanged();
        calculateTotalCount();
    }
}
