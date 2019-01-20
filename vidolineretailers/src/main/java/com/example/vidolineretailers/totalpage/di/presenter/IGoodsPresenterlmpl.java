package com.example.vidolineretailers.totalpage.di.presenter;

import com.example.vidolineretailers.totalpage.data.bean.GoodsBean;
import com.example.vidolineretailers.totalpage.di.contract.IGoodsContract;
import com.example.vidolineretailers.totalpage.di.model.IGoodsModellmpl;

import java.lang.ref.SoftReference;

public class IGoodsPresenterlmpl implements IGoodsContract.IGoodsPresenter<IGoodsContract.IGoodsView> {
    IGoodsContract.IGoodsView iGoodsView;
    private SoftReference<IGoodsContract.IGoodsView> reference;
    private IGoodsModellmpl modellmpl;

    @Override
    public void attahView(IGoodsContract.IGoodsView iGoodsView) {
        this.iGoodsView = iGoodsView;
        reference = new SoftReference<>(iGoodsView);
        modellmpl = new IGoodsModellmpl();
    }

    @Override
    public void detachView(IGoodsContract.IGoodsView iGoodsView) {
        reference.clear();
    }

    @Override
    public void goToRequestGoodsData() {
            modellmpl.containGoodsData(new IGoodsContract.IGoodsModel.GoodsCallBack() {
                @Override
                public void setResponseData(GoodsBean goodsBean) {
                    iGoodsView.setGoodsData(goodsBean);
                }
            });
    }
}
