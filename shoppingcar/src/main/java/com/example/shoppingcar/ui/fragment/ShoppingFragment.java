package com.example.shoppingcar.ui.fragment;

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
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.shoppingcar.R;
import com.example.shoppingcar.data.adapter.BusioessAdapter;
import com.example.shoppingcar.data.bean.GoodsBean;
import com.example.shoppingcar.di.contract.IContractGoods;
import com.example.shoppingcar.di.presenter.IStoerPresenterlmpl;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class ShoppingFragment extends Fragment implements IContractGoods.IStoerView {

    private RecyclerView recyclerView_busioess;
    private IStoerPresenterlmpl presenterlmpl;
    private List<GoodsBean.DataBean> businessList;
    private BusioessAdapter busioessAdapter;
    private CheckBox cb_all;
    private TextView tv_price;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_fragment,container,false);
        recyclerView_busioess = view.findViewById(R.id.recyclerView_busioess);
        cb_all =view.findViewById(R.id.cb_all);
        tv_price = view.findViewById(R.id.tv_price);
        presenterlmpl = new IStoerPresenterlmpl();
        presenterlmpl.attahView(this);
        presenterlmpl.requestStoerData();
        return view;

    }

    private void cbCkech() {
        //当点击全选按钮的时候  做一个点击事件
        cb_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //全选逻辑的处理
                for (int i = 0; i < businessList.size(); i++) {
                    //首先让外层的商家条目全部选中
                    businessList.get(i).setBusinessChecked(cb_all.isChecked());
                    //然后让里层的商品条目全部选中
                    for (int j = 0; j < businessList.get(i).getList().size(); j++) {
                        businessList.get(i).getList().get(j).setGoodsChecked(cb_all.isChecked());
                    }
                }
                calculateTotalCountPrice();
                busioessAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void setStoerData(GoodsBean goodsBean) {
        businessList = goodsBean.getData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView_busioess.setLayoutManager(layoutManager);
        busioessAdapter = new BusioessAdapter(R.layout.business_item,businessList);
        recyclerView_busioess.setAdapter(busioessAdapter);

        cb_all.setOnCheckedChangeListener(null);
        cbCkech();

       busioessAdapter.setOnBusioessItemClickListenter(new BusioessAdapter.OnBusioessItemClickListenter() {
            @Override
            public void callback() {
                boolean result = true;
                for (int i = 0; i < businessList.size(); i++) {
                    boolean businessChecked = businessList.get(i).getBusinessChecked();
                    result = result & businessChecked;
                    for (int j = 0; j < businessList.get(i).getList().size() ; j++) {
                        boolean goodsChecked = businessList.get(i).getList().get(j).getGoodsChecked();
                        result = result & goodsChecked;
                    }
                }
                    cb_all.setChecked(result);
                //计算总价
                calculateTotalCountPrice();
               // busioessAdapter.notifyDataSetChanged();
            }
       });
    }
    private void calculateTotalCountPrice() {
        //计算总价
        double totalCountPrice = 0;
        //外层条目
        for (int i = 0; i < businessList.size(); i++) {
            //内层条目
            for (int j = 0; j < businessList.get(i).getList().size(); j++) {
                if (businessList.get(i).getList().get(j).getGoodsChecked() == true){
                    double goodsPrice = (businessList.get(i).getList().get(j).getPrice()) * (businessList.get(i).getList().get(j).getDefalutNumber());
                    totalCountPrice = totalCountPrice + goodsPrice;
                }
            }
        }
        tv_price.setText("总价是：￥"+String.valueOf(totalCountPrice));
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterlmpl.decathView(this);
    }



}










        /*
        //private RefreshLayout refreshLayout;
        // refreshLayout = view.findViewById(R.id.refreshLayout_shop);


        //设置 Header 为 贝塞尔雷达 样式
        this.refreshLayout.setRefreshHeader(new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
        this.refreshLayout.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale));

        this.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(2000*//*,false*//*);//传入false表示刷新失败
        }
        });
        this.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore(2000*//*,false*//*);//传入false表示加载失败
        }
        });*/