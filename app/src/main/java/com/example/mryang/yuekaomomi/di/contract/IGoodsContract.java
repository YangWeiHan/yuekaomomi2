package com.example.mryang.yuekaomomi.di.contract;

public interface IGoodsContract {
    //V层
    public interface IView {
        //展示分类信息
        public void showCategoryData(String reponseData);
        //展示详情页
        public void showRightData(String reponseData);
    }
    //P层
    public interface IPresenter<IView> {
        //绑定
        public void attachView(IView liftGoodsView);

        //解绑
        public void detachView(IView liftGoodsView);

        //请求分类数据
        public void requestCategoryData();
        //请求详情页信息
        public void requestRightData(int cid);

        }

    //M层
    public interface IModel {
        //请求分类数据
        public void containLeftData(OnCallBack onCallBack);
        //请求详情数据
        public void containRightData(int cid,OnCallBack onCallBack);
        //回调
        public interface OnCallBack {

            public void onCallBack(String reponseData);
        }
    }
    }
