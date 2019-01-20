package com.example.shoppingcar.di.model;

import com.example.shoppingcar.data.Apils;
import com.example.shoppingcar.data.bean.GoodsBean;
import com.example.shoppingcar.di.contract.IContractGoods;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.lang.ref.SoftReference;

public class IStoerModellmpl implements IContractGoods.IStoerModel {

    @Override
    public void containStoerData(final OnCallBack onCallBack) {
        OkGo.<String>get(Apils.SHOPGOOSD_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responesData = response.body().toString();
                Gson gson = new Gson();
                GoodsBean goodsBean = gson.fromJson(responesData, GoodsBean.class);
                onCallBack.onCallBack(goodsBean);
            }
        });

    }
}
