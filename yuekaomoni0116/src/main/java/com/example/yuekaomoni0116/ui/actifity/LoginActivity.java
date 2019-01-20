package com.example.yuekaomoni0116.ui.actifity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yuekaomoni0116.R;
import com.example.yuekaomoni0116.data.bean.LoginBean;
import com.example.yuekaomoni0116.di.contran.IContrainLogin;
import com.example.yuekaomoni0116.di.presenter.ILoginPresenterlmpl;
import com.google.gson.Gson;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements IContrainLogin.ILoginView {

    @BindView(R.id.ed_userName_login)
    EditText edUserNameLogin;
    @BindView(R.id.ed_password_login)
    EditText edPasswordLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register_login)
    Button btnRegisterLogin;
    private ILoginPresenterlmpl presenterlmpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenterlmpl = new ILoginPresenterlmpl();
        presenterlmpl.attahView(this);

    }

    @OnClick({R.id.btn_login, R.id.btn_register_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String username = edUserNameLogin.getText().toString();
                String password = edPasswordLogin.getText().toString();
                presenterlmpl.ruquestLoginData(username,password);
                break;
            case R.id.btn_register_login:
                startActivity(new Intent(LoginActivity.this,ZhuceActivity.class));
                break;
        }
    }

    @Override
    public void setLoginData(String responseData) {
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(responseData, LoginBean.class);
        if (loginBean.getStatus().equals("0000")){

                startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }else if (loginBean.getStatus().equals("1001")){
            Toast.makeText(this, loginBean.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterlmpl.decathView(this);
    }
}
