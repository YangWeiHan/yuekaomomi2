package com.example.vidolineretailers.homepage.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vidolineretailers.R;
import com.example.vidolineretailers.homepage.data.bean.LoginBean;
import com.example.vidolineretailers.homepage.data.bean.RegisterBean;
import com.example.vidolineretailers.homepage.di.Contract.ILRContract;
import com.example.vidolineretailers.homepage.di.presenter.ILRPresenterlmpl;
import com.google.gson.Gson;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements ILRContract.ILRView {


    @BindView(R.id.icon_phone)
    ImageView iconPhone;
    @BindView(R.id.edit_phone_register)
    EditText editPhoneRegister;
    @BindView(R.id.lineone)
    TextView lineone;
    @BindView(R.id.icon_Verification)
    ImageView iconVerification;
    @BindView(R.id.edit_VerificationCode)
    EditText editVerificationCode;
    @BindView(R.id.re_VerificationCode)
    TextView reVerificationCode;
    @BindView(R.id.linetwo)
    TextView linetwo;
    @BindView(R.id.icon_lock)
    ImageView iconLock;
    @BindView(R.id.edit_pass_register)
    EditText editPassRegister;
    @BindView(R.id.linethree)
    TextView linethree;
    @BindView(R.id.back_loginpager)
    TextView backLoginpager;
    @BindView(R.id.button_register)
    Button buttonRegister;
    private ILRPresenterlmpl presenterlmpl;
    //防误触
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        presenterlmpl = new ILRPresenterlmpl();

        presenterlmpl.attahView(this);
    }
    @OnClick({R.id.back_loginpager, R.id.button_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_loginpager:
                long currentTime = Calendar.getInstance().getTimeInMillis();
                if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                    lastClickTime = currentTime;
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }
                break;
            case R.id.button_register:
                String username = editPhoneRegister.getText().toString();
                String password = editPassRegister.getText().toString();
                presenterlmpl.ruquestRegisterData(username,password);
                break;
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenterlmpl.decathView(this);
    }

    @Override
    public void setRegisterData(String responseData) {
        Gson gson = new Gson();
        RegisterBean registerBean = gson.fromJson(responseData, RegisterBean.class);
        if (registerBean.getStatus().equals("0000")){
            Toast.makeText(this,registerBean.getMessage(),Toast.LENGTH_LONG).show();
        }else if (registerBean.getStatus().equals("1001")){
            Toast.makeText(this,registerBean.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
