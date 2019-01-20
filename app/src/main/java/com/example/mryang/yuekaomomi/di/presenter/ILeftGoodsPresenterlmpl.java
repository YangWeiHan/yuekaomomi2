package com.example.mryang.yuekaomomi.di.presenter;

import com.example.mryang.yuekaomomi.di.contract.IGoodsContract;
import com.example.mryang.yuekaomomi.di.model.ILeftGoodsModellmpl;

import java.lang.ref.SoftReference;

public class ILeftGoodsPresenterlmpl implements IGoodsContract.IPresenter<IGoodsContract.IView> {
    IGoodsContract.IView liftGoodsView;
    private SoftReference<IGoodsContract.IView> softReference;
    private ILeftGoodsModellmpl modellmpl;

    @Override
    public void attachView(IGoodsContract.IView liftGoodsView) {
        this.liftGoodsView = liftGoodsView;
        softReference = new SoftReference<>(liftGoodsView);
        modellmpl = new ILeftGoodsModellmpl();
    }

    @Override
    public void detachView(IGoodsContract.IView liftGoodsView) {
            softReference.clear();
    }

    @Override
    public void requestCategoryData() {
        modellmpl.containLeftData(new IGoodsContract.IModel.OnCallBack() {
            @Override
            public void onCallBack(String reponseData) {
                liftGoodsView.showCategoryData(reponseData);
            }
        });
    }

    @Override
    public void requestRightData(int cid) {
        modellmpl.containRightData(cid, new IGoodsContract.IModel.OnCallBack() {
            @Override
            public void onCallBack(String reponseData) {
                liftGoodsView.showRightData(reponseData);
            }
        });
    }
}
