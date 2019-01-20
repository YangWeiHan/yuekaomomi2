package com.example.shoppingcar.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.czp.library.ArcProgress;
import com.czp.library.OnTextCenter;
import com.example.shoppingcar.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.guide_title)
    TextView guideTitle;
    @BindView(R.id.myProgress)
    ArcProgress mProgress;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            ArcProgress progressBar = (ArcProgress)msg.obj;
            progressBar.setProgress(msg.what);
            return true;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        guideTitle.setText("购物车引导页");
        //默认实现的类
        mProgress.setOnCenterDraw(new OnTextCenter(Color.GREEN, 50));
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    //判断结束
                    if (isFinishing()) {
                        break;
                    }
                    //系统时钟 睡 100 毫秒
                    SystemClock.sleep(20);
                    mHandler.sendMessage(mHandler.obtainMessage(i, mProgress));
                    if (i == 100){
                        startActivity(new Intent(GuideActivity.this,FlowLayoutActivity.class));

                    }
                }
            }
        }).start();

    }
}
