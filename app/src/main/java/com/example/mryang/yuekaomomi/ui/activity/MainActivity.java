package com.example.mryang.yuekaomomi.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mryang.yuekaomomi.R;
import com.example.mryang.yuekaomomi.data.bean.LeftGoodsBean;
import com.example.mryang.yuekaomomi.data.bean.RightGoodsBean;
import com.example.mryang.yuekaomomi.di.contract.IGoodsContract;
import com.example.mryang.yuekaomomi.di.presenter.ILeftGoodsPresenterlmpl;
import com.example.mryang.yuekaomomi.ui.adapter.LeftGoodsAdapter;
import com.example.mryang.yuekaomomi.ui.adapter.RightGoodsAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IGoodsContract.IView {

    @BindView(R.id.left_goods_recyclerView)
    RecyclerView leftGoodsRecyclerView;
    @BindView(R.id.right_goods_recyclerView)
    RecyclerView rightGoodsRecyclerView;
    private Context context;
    private LeftGoodsAdapter leftAdapter;
    private List<LeftGoodsBean.DataBean> leftGoodsData;
    private IGoodsContract.IPresenter presenterlmpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        presenterlmpl = new ILeftGoodsPresenterlmpl();
        presenterlmpl.attachView(this);
        presenterlmpl.requestCategoryData();


    }
    @Override
    public void showCategoryData(final String reponseData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //获取数据源
                Gson gson = new Gson();
                LeftGoodsBean leftGoodsBean = gson.fromJson(reponseData, LeftGoodsBean.class);
                leftGoodsData = leftGoodsBean.getData();
                //布局管理器
                LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                leftGoodsRecyclerView.setLayoutManager(manager);
                //设置适配器
                leftAdapter = new LeftGoodsAdapter(R.layout.left_goods_item,leftGoodsData);
                leftGoodsRecyclerView.setAdapter(leftAdapter);
                //设置点击事件

                leftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        int cid = leftGoodsData.get(position).getCid();
                        presenterlmpl.requestRightData(cid);
                    }
                });
            }
        });
    }

    @Override
    public void showRightData(final String reponseData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                RightGoodsBean rightGoodsBean = gson.fromJson(reponseData, RightGoodsBean.class);
                //数据源
                List<RightGoodsBean.DataBean> rightGoodsBeanData = rightGoodsBean.getData();
                //布局管理器
                LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
                rightGoodsRecyclerView.setLayoutManager(layoutManager);
                //设置适配器
                RightGoodsAdapter rightAdapter = new RightGoodsAdapter(R.layout.right_goods,rightGoodsBeanData);
                rightGoodsRecyclerView.setAdapter(rightAdapter);

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       presenterlmpl.detachView(this);
    }
}
