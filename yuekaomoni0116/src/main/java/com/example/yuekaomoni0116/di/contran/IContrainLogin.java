package com.example.yuekaomoni0116.di.contran;

public interface IContrainLogin {
    public interface ILoginView{

        void setLoginData(String responseData);
    }

    public interface ILoginPresenter<ILRView>{

        void attahView(ILRView ilrView);

        void decathView(ILRView ilrView);

        void ruquestLoginData(String username, String password);
    }

    public interface ILoginModel{
        void containLoginData(String username, String password,OnCallBack onCallBack);



        public interface OnCallBack{
            void setData(String responseData);
        }
    }
}
