package com.example.yuekaomoni0116.ui.fragment;


import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuekaomoni0116.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;


public class MainFragment extends Fragment implements View.OnClickListener{
    private Button QQ_Login;
    private ImageView QQ_image_icon;
    private TextView QQ_name;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment,container,false);
        QQ_image_icon = view.findViewById(R.id.QQ_image_icon);
        QQ_Login = view.findViewById(R.id.QQ_Login);
        QQ_name = view.findViewById(R.id.QQ_name);
        QQ_Login.setOnClickListener(this);
        checkPermission();
        return view;
    }

    /**
     * 添加回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
    }



    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(getActivity(), mPermissionList, 123);
        }
    }
    /**
     * 动态添加权限回调，模拟器Android版本小于6.0的不用写，再有问的打死
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.QQ_Login:
                //获得UMShareAPI实例
                UMShareAPI umShareAPI =  UMShareAPI.get(getContext());

                //开始登录
                //第一个参数：上下文
                //第二个参数，登录哪种平台
                //第三个参数，添加回调
                umShareAPI.getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, new UMAuthListener() {
                    /**
                     * 开始登录回调
                     * @param share_media
                     */
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.i("dj", "UMAuthListener onStart");
                    }

                    /**
                     * 登录完成
                     * @param share_media
                     * @param i
                     * @param map
                     */
                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        //头像，昵称，如果拿不到，自己debug看Key是啥，再问打死
                        Log.i("dj", "UMAuthListener onComplete");

                        //获取名字
                        String name  = map.get("screen_name");
                        //获取头像
                        String img  = map.get("profile_image_url");

                        Log.i("dj", "name is "+ name);
                        Log.i("dj", "img is "+ img);
                    }

                    /**
                     * 登录失败
                     * @param share_media
                     * @param i
                     * @param throwable
                     */
                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        Log.i("dj", "UMAuthListener onError" + throwable.getLocalizedMessage());
                    }

                    /**
                     * 登录取消
                     * @param share_media
                     * @param i
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        Log.i("dj", "UMAuthListener onCancel");
                    }
                });
                break;
            default:
                break;
        }

        }

    }

