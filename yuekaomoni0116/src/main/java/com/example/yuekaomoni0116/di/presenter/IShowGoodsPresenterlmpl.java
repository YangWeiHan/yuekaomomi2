package com.example.yuekaomoni0116.di.presenter;

import com.example.yuekaomoni0116.data.bean.GoodsBean;
import com.example.yuekaomoni0116.di.contran.IContrainGoods;
import com.example.yuekaomoni0116.di.model.IShowGoodsModellmpl;

import java.lang.ref.SoftReference;

public class IShowGoodsPresenterlmpl implements IContrainGoods.IShowGoodsPresenter<IContrainGoods.IShowGoodsView> {
    IContrainGoods.IShowGoodsView iShowGoodsView;
    private SoftReference<IContrainGoods.IShowGoodsView> reference;
    private IShowGoodsModellmpl modellmpl;

    @Override
    public void attchView(IContrainGoods.IShowGoodsView iShowGoodsView) {
        this.iShowGoodsView = iShowGoodsView;
        reference = new SoftReference<>(iShowGoodsView);
        modellmpl = new IShowGoodsModellmpl();
    }

    @Override
    public void decathView(IContrainGoods.IShowGoodsView iShowGoodsView) {
        reference.clear();
    }

    @Override
    public void setContainData() {
        modellmpl.containData(new IContrainGoods.IshowGoodsModel.OnCallBack() {
            @Override
            public void setData(GoodsBean goodsBean) {
                iShowGoodsView.setGoodsData(goodsBean);
            }
        });
    }
}
