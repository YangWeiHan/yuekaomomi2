package com.example.yuekaomoni0116.di.contran;

public interface IContrainLR {

    public interface ILRView{

        void setRegisterData(String responseData);



    }

    public interface ILRPresenter<ILRView>{

        void attahView(ILRView ilrView);

        void decathView(ILRView ilrView);

        void ruquestRegisterData(String username, String password);



    }

    public interface ILRModel{

        void containRegisterData(String username, String password,OnCallBack onCallBack);



        public interface OnCallBack{
            void setData(String responseData);
        }
    }

}
