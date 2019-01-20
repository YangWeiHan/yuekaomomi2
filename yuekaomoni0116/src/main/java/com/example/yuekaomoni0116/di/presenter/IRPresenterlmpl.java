package com.example.yuekaomoni0116.di.presenter;

import com.example.yuekaomoni0116.di.contran.IContrainLR;
import com.example.yuekaomoni0116.di.model.IRModellmpl;

import java.lang.ref.SoftReference;

public class IRPresenterlmpl implements IContrainLR.ILRPresenter<IContrainLR.ILRView> {
    IContrainLR.ILRView ilrView;
    private SoftReference<IContrainLR.ILRView> ilrViewSoftReference;
    private IRModellmpl modellmpl;

    @Override
    public void attahView(IContrainLR.ILRView ilrView) {
        this.ilrView = ilrView;
        ilrViewSoftReference = new SoftReference<>(ilrView);
        modellmpl = new IRModellmpl();
    }

    @Override
    public void decathView(IContrainLR.ILRView ilrView) {
        ilrViewSoftReference.clear();

    }

    @Override
    public void ruquestRegisterData(String username, String password) {
        modellmpl.containRegisterData(username,password,new IContrainLR.ILRModel.OnCallBack() {
            @Override
            public void setData(String responseData) {
                ilrView.setRegisterData(responseData);
            }
        });
    }
}
