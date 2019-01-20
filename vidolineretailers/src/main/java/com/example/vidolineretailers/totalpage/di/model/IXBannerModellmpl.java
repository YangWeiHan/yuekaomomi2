package com.example.vidolineretailers.totalpage.di.model;

import com.example.vidolineretailers.homepage.apils.Apils;
import com.example.vidolineretailers.homepage.data.bean.BannerBean;
import com.example.vidolineretailers.homepage.data.bean.HomeGoodsBean;
import com.example.vidolineretailers.totalpage.di.contract.IXBannerContract;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IXBannerModellmpl implements IXBannerContract.IXBannerModel {
    @Override
    public void containXBannerData(final OnCallBack onCallBack) {
        OkGo.<String>get(Apils.XBANNER_HOME).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String bannerResponseData = response.body().toString();
                Gson gson = new Gson();
                BannerBean bannerBean = gson.fromJson(bannerResponseData, BannerBean.class);
                onCallBack.setData(bannerBean);
            }
        });
    }

    @Override
    public void setResponesData(final OnHomeCallBack onHomeCallBack) {
        OkGo.<String>get(Apils.HOMEGOODS_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String homeGoodsData = response.body().toString();
                Gson gson = new Gson();
                HomeGoodsBean homeGoodsBean = gson.fromJson(homeGoodsData, HomeGoodsBean.class);
                onHomeCallBack.setHomeGoodsData(homeGoodsBean);
            }
        });
    }
}
