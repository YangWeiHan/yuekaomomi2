package com.example.vidolineretailers.totalpage.di.model;

import com.example.vidolineretailers.homepage.apils.Apils;
import com.example.vidolineretailers.totalpage.data.bean.GoodsBean;
import com.example.vidolineretailers.totalpage.di.contract.IGoodsContract;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IGoodsModellmpl implements IGoodsContract.IGoodsModel {
    @Override
    public void containGoodsData(final GoodsCallBack goodsCallBack) {
        OkGo.<String>get(Apils.SHOPGOOSD_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responseData = response.body().toString();
                Gson gson = new Gson();
                GoodsBean goodsBean = gson.fromJson(responseData, GoodsBean.class);
                goodsCallBack.setResponseData(goodsBean);
            }
        });
    }
}
