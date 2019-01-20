package com.example.yuekaomoni0116.di.presenter;

import com.example.yuekaomoni0116.di.contran.IContrainLogin;
import com.example.yuekaomoni0116.di.model.ILoginModellmpl;

import java.lang.ref.SoftReference;

public class ILoginPresenterlmpl implements IContrainLogin.ILoginPresenter<IContrainLogin.ILoginView> {
    IContrainLogin.ILoginView iLoginView;
    private SoftReference<IContrainLogin.ILoginView> reference;
    private ILoginModellmpl modellmpl;

    @Override
    public void attahView(IContrainLogin.ILoginView iLoginView) {
       this.iLoginView = iLoginView;
        reference = new SoftReference<>(iLoginView);
        modellmpl = new ILoginModellmpl();
    }

    @Override
    public void decathView(IContrainLogin.ILoginView iLoginView) {
        reference.clear();
    }

    @Override
    public void ruquestLoginData(String username, String password) {
        modellmpl.containLoginData(username,password,new IContrainLogin.ILoginModel.OnCallBack() {
            @Override
            public void setData(String responseData) {
                iLoginView.setLoginData(responseData);
            }
        });
    }
}
