package com.example.mryang.yuekaomomi.di.model;

import com.example.mryang.yuekaomomi.data.aplis.Aplis;
import com.example.mryang.yuekaomomi.data.utils.OKHttpUtil;
import com.example.mryang.yuekaomomi.di.contract.IGoodsContract;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ILeftGoodsModellmpl implements IGoodsContract.IModel {
    @Override
    public void containLeftData(final OnCallBack onCallBack) {
        OKHttpUtil.getInstance().get(Aplis.LEFT_UTL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //错误数据
                String errorMsg = e.getMessage();
                onCallBack.onCallBack(errorMsg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                onCallBack.onCallBack(responseData);
            }
        });

    }

    @Override
    public void containRightData(int cid, final OnCallBack onCallBack) {
        OKHttpUtil.getInstance().get(Aplis.RIGHT_UTL + "?cid="+cid, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //错误的数据
                String errorMsg = e.getMessage();
                onCallBack.onCallBack(errorMsg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();

                onCallBack.onCallBack(responseData);
            }
        });
    }
}
