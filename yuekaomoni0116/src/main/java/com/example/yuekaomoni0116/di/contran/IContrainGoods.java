package com.example.yuekaomoni0116.di.contran;

import com.example.yuekaomoni0116.data.bean.GoodsBean;

public interface IContrainGoods {

    public interface IShowGoodsView{
        void setGoodsData(GoodsBean goodsBean);
    }

    public interface IShowGoodsPresenter<IShowGoodsView>{

        void attchView(IShowGoodsView iShowGoodsView);

        void decathView(IShowGoodsView iShowGoodsView);

        void setContainData();
    }

    public interface IshowGoodsModel {

        void containData(OnCallBack onCallBack);

        public interface OnCallBack{

            void setData(GoodsBean goodsBean);
        }

    }
}
