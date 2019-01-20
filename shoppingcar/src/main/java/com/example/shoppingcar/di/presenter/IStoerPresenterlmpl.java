package com.example.shoppingcar.di.presenter;

import com.example.shoppingcar.data.bean.GoodsBean;
import com.example.shoppingcar.di.contract.IContractGoods;
import com.example.shoppingcar.di.model.IStoerModellmpl;

import java.lang.ref.SoftReference;

public class IStoerPresenterlmpl implements IContractGoods.IStoerPresenter<IContractGoods.IStoerView> {

    IContractGoods.IStoerView iStoerView;
    private SoftReference<IContractGoods.IStoerView> reference;
    private IStoerModellmpl modellmpl;


    @Override
    public void attahView(IContractGoods.IStoerView iStoerView) {
        this.iStoerView = iStoerView;
        reference = new SoftReference<>(iStoerView);
        modellmpl = new IStoerModellmpl();
    }

    @Override
    public void decathView(IContractGoods.IStoerView iStoerView) {
        reference.clear();
    }

    @Override
    public void requestStoerData() {
       modellmpl.containStoerData(new IContractGoods.IStoerModel.OnCallBack() {
           @Override
           public void onCallBack(GoodsBean goodsBean) {
               iStoerView.setStoerData(goodsBean);
           }
       });
    }
}
