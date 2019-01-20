package com.example.yuekaomoni0116.ui.fragment;

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
import android.widget.Toast;

import com.example.yuekaomoni0116.R;
import com.example.yuekaomoni0116.data.adapter.BusioessAdapter;
import com.example.yuekaomoni0116.data.bean.GoodsBean;
import com.example.yuekaomoni0116.di.contran.IContrainGoods;
import com.example.yuekaomoni0116.di.presenter.IShowGoodsPresenterlmpl;

import java.util.List;

public class ShoppingFragment extends Fragment implements IContrainGoods.IShowGoodsView,View.OnClickListener {

    private IShowGoodsPresenterlmpl presenterlmpl;
    private List<GoodsBean.DataBean> businessList;
    private RecyclerView recyclerView_busioess;
    private BusioessAdapter busioessAdapter;
    private CheckBox cb_all;
    private TextView tv_price;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_fragment,container,false);
        recyclerView_busioess = view.findViewById(R.id.recyclerView_busioess);
        cb_all = view.findViewById(R.id.cb_all);
        tv_price = view.findViewById(R.id.tv_price);
      //  checkBoxAllisCheck();
        presenterlmpl = new IShowGoodsPresenterlmpl();
        presenterlmpl.attchView(this);
        presenterlmpl.setContainData();
        return view;
    }

    private void calculateTotalCount(){
       double totalCount = 0;
        for (int i = 0; i < businessList.size(); i++) {
            for (int j = 0; j < businessList.get(i).getList().size(); j++) {
                if (businessList.get(i).getList().get(j).getGoodsChecked()){
                    double goods_price = businessList.get(i).getList().get(j).getPrice();
                    int goods_num = businessList.get(i).getList().get(j).getNum();
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
        businessList = goodsBean.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView_busioess.setLayoutManager(linearLayoutManager);
        busioessAdapter = new BusioessAdapter(R.layout.busioess_item, businessList);
        recyclerView_busioess.setAdapter(busioessAdapter);
        //得到商家适配器里回传回来的数据
        busioessAdapter.setOnBusinessItemClickLisenter(new BusioessAdapter.OnBusinessItemClickLisenter() {
            //给一个初变量 默认为fales   遍历商家  商品的所有条目
            @Override
            public void onCallBack() {
                boolean result = true;
                for (int i = 0; i < businessList.size(); i++) {
                    //外层选中状态
                    boolean businessChecked = businessList.get(i).getBusinessChecked();
                    result = result & businessChecked;
                    for (int j = 0; j <businessList.get(i).getList().size() ; j++) {
                        //里层选中状态
                        boolean goodsChecked = businessList.get(i).getList().get(j).getGoodsChecked();
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
        presenterlmpl.setContainData();

    }

    @Override
    public void onClick(View v) {
        //全选逻辑   先遍历商家  在遍历商品
        for (int i = 0; i < businessList.size(); i++) {
            businessList.get(i).setBusinessChecked(cb_all.isChecked());
            for (int j = 0; j < businessList.get(i).getList().size(); j++) {
                businessList.get(i).getList().get(j).setGoodsChecked(cb_all.isChecked());
            }
        }
        busioessAdapter.notifyDataSetChanged();
        calculateTotalCount();
    }
}
