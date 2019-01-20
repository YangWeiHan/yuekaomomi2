package com.example.vidolineretailers.homepage.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vidolineretailers.R;
import com.example.vidolineretailers.homepage.data.bean.LoginBean;
import com.example.vidolineretailers.homepage.di.Contract.ILoginContract;
import com.example.vidolineretailers.homepage.di.presenter.ILoginPresenter;
import com.example.vidolineretailers.totalpage.ui.activity.TotalPageActivity;
import com.google.gson.Gson;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends AppCompatActivity implements ILoginContract.ILoginView {


    @BindView(R.id.icon_phone)
    ImageView iconPhone;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.lineone)
    TextView lineone;
    @BindView(R.id.icon_lock)
    ImageView iconLock;
    @BindView(R.id.edit_pass)
    EditText editPass;

    @BindView(R.id.linetwo)
    TextView linetwo;
    @BindView(R.id.cb_rememberpass)
    CheckBox cbRememberpass;
    @BindView(R.id.fast_zcz)
    TextView fastZcz;
    @BindView(R.id.button_login)
    Button buttonLogin;
    private ILoginPresenter presenter;
    private SharedPreferences sharedPreferences;
    //防误触
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private long lastClickTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        ButterKnife.bind(this);
        presenter = new ILoginPresenter();
        presenter.attahView(this);

        //自动登录
        sharedPreferences = getSharedPreferences("RememberPassword",Context.MODE_PRIVATE);
        boolean RememberPasswordLogin = sharedPreferences.getBoolean("记住密码", false);
        if (RememberPasswordLogin){
            String rm_phone = sharedPreferences.getString("RM_phone", "");
            String rm_pass = sharedPreferences.getString("RM_Pass", "");

                editPhone.setText(rm_phone);
                editPass.setText(rm_pass);
                cbRememberpass.setChecked(RememberPasswordLogin);

        }
    }
    @OnClick({R.id.fast_zcz, R.id.button_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fast_zcz:
                long currentTime = Calendar.getInstance().getTimeInMillis();
                if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                    lastClickTime = currentTime;
                    //在下面做点击的处理
                    startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                }

                break;
            case R.id.button_login:
                String username = editPhone.getText().toString();
                String password = editPass.getText().toString();
                presenter.ruquestRegisterData(username,password);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean("记住密码",cbRememberpass.isChecked());
                edit.putString("RM_phone",username);
                edit.putString("RM_Pass",password);
                edit.commit();
                break;


        }
    }

    @Override
    public void setLoginData(String responseData) {
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(responseData, LoginBean.class);
        if (loginBean.getStatus().equals("0000")){
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                //在下面做点击的处理
                startActivity(new Intent(LoginActivity.this,TotalPageActivity.class));
                Log.d("TAG",loginBean.getResult().getUserId()+"");
                Log.d("TAG",loginBean.getResult().getSessionId()+"");
            }
        }else if (loginBean.getStatus().equals("1001")){
            Toast.makeText(this, loginBean.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.decathView(this);
    }
}
