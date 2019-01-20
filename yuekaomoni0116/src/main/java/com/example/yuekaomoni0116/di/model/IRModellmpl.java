package com.example.yuekaomoni0116.di.model;

import com.example.yuekaomoni0116.data.apils.Apils;
import com.example.yuekaomoni0116.di.contran.IContrainLR;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IRModellmpl implements IContrainLR.ILRModel {


    @Override
    public void containRegisterData(String username, String password, final OnCallBack onCallBack) {
        String urlString = Apils.REGIDTER_URL+"?phone="+username+"&pwd="+password;

        OkGo.<String>post(urlString).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responseData = response.body().toString();
                onCallBack.setData(responseData);
            }
        });
    }
}
