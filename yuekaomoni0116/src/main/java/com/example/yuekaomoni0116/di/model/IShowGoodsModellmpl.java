package com.example.yuekaomoni0116.di.model;

import com.example.yuekaomoni0116.data.apils.Apils;
import com.example.yuekaomoni0116.data.bean.GoodsBean;
import com.example.yuekaomoni0116.di.contran.IContrainGoods;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IShowGoodsModellmpl implements IContrainGoods.IshowGoodsModel {
    @Override
    public void containData(final OnCallBack onCallBack) {
        OkGo.<String>get(Apils.SHOPGOOSD_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responseData = response.body().toString();
                Gson gson = new Gson();
                GoodsBean goodsBean = gson.fromJson(responseData, GoodsBean.class);
                onCallBack.setData(goodsBean);
            }
        });
    }
}
