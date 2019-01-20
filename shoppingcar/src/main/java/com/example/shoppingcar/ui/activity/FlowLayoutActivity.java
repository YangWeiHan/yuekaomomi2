package com.example.shoppingcar.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingcar.R;
import com.example.shoppingcar.ui.weight.WeekFlowLayout;

import java.util.UUID;

public class FlowLayoutActivity extends AppCompatActivity {
    private Button Jump_ShowPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
       Jump_ShowPager = findViewById(R.id.Jump_ShowPager);
        Jump_ShowPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FlowLayoutActivity.this,ShowActivity.class));
            }
        });
        init();
    }

    private void init() {
        //获取自定义布局的资源ID

        final WeekFlowLayout flowLayout = findViewById(R.id.flowLayout);


        findViewById(R.id.clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flowLayout.removeAllViews();
            }
        });

        //输入框的资源ID
        final EditText main_edit = findViewById(R.id.main_edit);
        //获取按钮的资源ID  添加点击监听事件
        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取随机字符串   ，当做唯一标示
                UUID uuid = UUID.randomUUID();

                final TextView textView = new TextView(FlowLayoutActivity.this);
                //获取输入框里的值
                textView.setText(main_edit.getText());
                //设置字体颜色
                String s = main_edit.getText().toString();

                textView.setTextColor(Color.BLACK);
                //设置背景颜色

                //添加View
                flowLayout.addView(textView);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String uuid = String.valueOf(v.getTag());
                        Toast.makeText(FlowLayoutActivity.this,"字符串"+textView.getText().toString()+uuid,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}


