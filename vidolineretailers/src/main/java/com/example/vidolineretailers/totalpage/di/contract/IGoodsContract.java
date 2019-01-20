package com.example.vidolineretailers.totalpage.di.contract;

import com.example.vidolineretailers.totalpage.data.bean.GoodsBean;

public interface IGoodsContract {


    public  interface IGoodsView{
        void  setGoodsData(GoodsBean goodsBean);
    }

    public interface IGoodsPresenter<IGoodsView>{

        void attahView(IGoodsView iGoodsView);

        void detachView(IGoodsView iGoodsView);

        void goToRequestGoodsData();
    }

    public interface IGoodsModel{

        void containGoodsData(GoodsCallBack goodsCallBack);

            public interface GoodsCallBack{
                void setResponseData(GoodsBean goodsBean);
            }
    }
}
