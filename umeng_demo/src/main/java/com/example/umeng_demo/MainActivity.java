package com.example.umeng_demo;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_share_withoutboard)
    Button btnShareWithoutboard;
    @BindView(R.id.btn_share_withboard)
    Button btnShareWithboard;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.iv_uid)
    TextView ivUid;
    @BindView(R.id.iv_name)
    TextView ivName;
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList =
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
                            , Manifest.permission.ACCESS_FINE_LOCATION
                            , Manifest.permission.CALL_PHONE
                            , Manifest.permission.READ_LOGS
                            , Manifest.permission.READ_PHONE_STATE
                            , Manifest.permission.READ_EXTERNAL_STORAGE
                            , Manifest.permission.SET_DEBUG_APP
                            , Manifest.permission.SYSTEM_ALERT_WINDOW
                            , Manifest.permission.GET_ACCOUNTS
                            , Manifest.permission.WRITE_APN_SETTINGS
                            , Manifest.permission.CAMERA
                    };
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
    }

    @OnClick({R.id.btn_share_withoutboard, R.id.btn_share_withboard, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_share_withoutboard:
                UMImage image = new UMImage(MainActivity.this, R.drawable.timg);//本地文件
                new ShareAction(MainActivity.this)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withMedia(image)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.btn_share_withboard:
                UMImage image1 = new UMImage(MainActivity.this, R.drawable.xiaxia);//本地文件
                new ShareAction(MainActivity.this)
                        .withMedia(image1)
                        .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                        .setCallback(shareListener).open();
                break;
            case R.id.btn_login:
                //获取友盟封装的分享对象
                UMShareAPI umShareAPI = UMShareAPI.get(this);

                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
            String uid = data.get("uid");
            String name = data.get("name");
            String iconurll = data.get("iconurl");
            ivUid.setText(uid);
            ivName.setText(name);
            Glide.with(MainActivity.this).load(iconurll).into(ivIcon);

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

}
