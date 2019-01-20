package com.example.yuekaomoni0116.ui.actifity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuekaomoni0116.R;
import com.example.yuekaomoni0116.data.bean.ZhucheBean;
import com.example.yuekaomoni0116.di.contran.IContrainLR;
import com.example.yuekaomoni0116.di.presenter.IRPresenterlmpl;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhuceActivity extends AppCompatActivity implements IContrainLR.ILRView {

    @BindView(R.id.ed_userName)
    EditText edUserName;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.go_back)
    TextView goBack;
    private IRPresenterlmpl presenterlmpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        ButterKnife.bind(this);
        presenterlmpl = new IRPresenterlmpl();
        presenterlmpl.attahView(this);

    }

    @OnClick({R.id.btn_register, R.id.go_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                String username = edUserName.getText().toString();
                String password = edPassword.getText().toString();
                presenterlmpl.ruquestRegisterData(username,password);
                break;
            case R.id.go_back:
                startActivity(new Intent(ZhuceActivity.this,LoginActivity.class));
                break;
        }
    }

    @Override
    public void setRegisterData(String responseData) {
        Gson gson = new Gson();
        ZhucheBean zhucheBean = gson.fromJson(responseData, ZhucheBean.class);
        if (zhucheBean.getStatus().equals("0000")){
            Toast.makeText(this,zhucheBean.getMessage(),Toast.LENGTH_LONG).show();
        }else if (zhucheBean.getStatus().equals("1001")){
            Toast.makeText(this,zhucheBean.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterlmpl.decathView(this);
    }
}
