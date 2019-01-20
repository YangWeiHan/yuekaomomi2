package com.example.qr_code;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ShowActivity extends AppCompatActivity implements QRCodeView.Delegate {
    private ZXingView xingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //获取资源ID
        xingView = findViewById(R.id.zXing);
        xingView.setDelegate(this);

    }
    @Override
    protected void onResume() {
        super.onResume();
        //打开相机
        xingView.startCamera();
        //显示扫描框，并且延迟1.5秒后开始识别
        xingView.startSpotAndShowRect();
        /*xingView.openFlashlight();*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        xingView.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        xingView.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
