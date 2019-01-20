package com.example.vidolineretailers.totalpage.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.vidolineretailers.R;
import com.example.vidolineretailers.homepage.data.bean.BannerBean;
import com.example.vidolineretailers.homepage.data.bean.HomeGoodsBean;
import com.example.vidolineretailers.totalpage.data.adapter.MLSSAdapter;
/*import com.example.vidolineretailers.totalpage.data.adapter.PZSSAdapter;*/
import com.example.vidolineretailers.totalpage.data.adapter.PZSHAdapter;
import com.example.vidolineretailers.totalpage.data.adapter.RXXPAdapter;
import com.example.vidolineretailers.totalpage.di.contract.IXBannerContract;
import com.example.vidolineretailers.totalpage.di.presenter.IXBannerPresenterlmpl;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IXBannerContract.IXBannerView {
    private XBanner home_xbanner;
    private IXBannerPresenterlmpl presenterlmpl;
    private List<String> babber_list;
    private RecyclerView rxxp_recyclerView;
    private RecyclerView mlss_recyclerView,pzss_recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home,null);
        home_xbanner = view.findViewById(R.id.home_xbanner);
        rxxp_recyclerView = view.findViewById(R.id.rxxp_recyclerView);
        mlss_recyclerView = view.findViewById(R.id.mlss_recyclerView);
        pzss_recyclerView = view.findViewById(R.id.pzss_recyclerView);

        initPresenter();
        return view;
    }

    private void initPresenter() {
        presenterlmpl = new IXBannerPresenterlmpl();
        presenterlmpl.attahView(this);
        //XB  数据
        presenterlmpl.goToXrequestBannerData();
        //商品数据
        presenterlmpl.goToRequestHomeGoodsData();

    }

    @Override
    public void setBannerData(BannerBean bannerBean) {
        babber_list = new ArrayList<>();
        for (int i = 0; i <bannerBean.getResult().size() ; i++) {
            babber_list.add(bannerBean.getResult().get(i).getImageUrl());
        }
        home_xbanner.setData(babber_list,null);
        home_xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(babber_list.get(position)).into((ImageView) view);
            }
        });
    }

    @Override
    public void setHomeGoodsData(HomeGoodsBean homeGoodsBean) {
        List<HomeGoodsBean.ResultBean.RxxpBean> rxxp = homeGoodsBean.getResult().getRxxp();
        List<HomeGoodsBean.ResultBean.MlssBean> mlss = homeGoodsBean.getResult().getMlss();
        List<HomeGoodsBean.ResultBean.PzshBean> pzsh = homeGoodsBean.getResult().getPzsh();
        if (rxxp != null){
            if (rxxp.size() <= 0){
                return;
            }else {
                for (int i = 0; i < rxxp.size(); i++) {
                    //数据源
                    List<HomeGoodsBean.ResultBean.RxxpBean.CommodityListBean> commodityList = rxxp.get(i).getCommodityList();
                    //布局
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                    rxxp_recyclerView.setLayoutManager(layoutManager);

                    //适配器
                    RXXPAdapter rxxpAdapter = new RXXPAdapter(getActivity());
                    rxxpAdapter.setRxxpBean(commodityList);
                    rxxp_recyclerView.setAdapter(rxxpAdapter);
                }
            }
        }
        if (mlss != null){
            if (mlss.size() <= 0){
                return;
            }else {
                for (int i = 0; i < mlss.size(); i++) {
                    //得到数据源
                    List<HomeGoodsBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList = mlss.get(i).getCommodityList();
                    //布局
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                    mlss_recyclerView.setLayoutManager(layoutManager);

                    MLSSAdapter mlssAdapter = new MLSSAdapter(getActivity());
                    mlssAdapter.setMlssBeans(commodityList);
                    mlss_recyclerView.setAdapter(mlssAdapter);
                }
            }
        }
       if (pzsh != null){
            if (pzsh.size() <= 0){
                return;
            }else {
                for (int i = 0; i < pzsh.size(); i++) {
                    //得到数据源
                    List<HomeGoodsBean.ResultBean.PzshBean.CommodityListBeanX> commodityList = pzsh.get(i).getCommodityList();
                    //布局
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,LinearLayoutManager.VERTICAL,false);
                    pzss_recyclerView.setLayoutManager(gridLayoutManager);

                    PZSHAdapter pzssAdapter = new PZSHAdapter(getActivity());
                    pzssAdapter.setPzshBean(commodityList);
                    pzss_recyclerView.setAdapter(pzssAdapter);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterlmpl.decathView(this);
    }
}
