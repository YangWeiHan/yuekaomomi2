package com.example.shoppingcar.di.contract;

import com.example.shoppingcar.data.bean.GoodsBean;

public interface IContractGoods {

    public interface IStoerView{

        void setStoerData(GoodsBean goodsBean);
    }

    public interface IStoerPresenter<IStoerView>{
        //绑定
        void attahView(IStoerView iStoerView);
        //解绑
        void decathView(IStoerView iStoerView);
        //去请求数据
        void requestStoerData();
    }

    public interface IStoerModel{

        void containStoerData(OnCallBack onCallBack);

        public interface OnCallBack{
            void onCallBack(GoodsBean goodsBean);
        }
    }
}
