package com.example.yuekaomoni0116.ui.webgit;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuekaomoni0116.R;

public class CalculatorView extends LinearLayout implements OnClickListener {
    private Button btn_decrease,btn_add;
    private TextView tv_number;

    public CalculatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.jiajiajianjianjian,this);
         btn_decrease = view.findViewById(R.id.btn_decrease);
        tv_number = view.findViewById(R.id.tv_number);
        btn_add = view.findViewById(R.id.btn_add);

        btn_add.setOnClickListener(this);
        btn_decrease.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String numberString = tv_number.getText().toString();
        int number = Integer.parseInt(numberString);
        switch (v.getId()){
            case R.id.btn_decrease   :
                number --;
                if (number<0){
                    number = 0;
                    tv_number.setText(String.valueOf(number));
                    Toast.makeText(getContext(),"亲不能再减啦！",Toast.LENGTH_SHORT).show();
                }
                tv_number.setText(String.valueOf(number));
                onCalCulatorLisenter.onDecrease(number);
                break;
            case R.id.btn_add:
                number++;
                tv_number.setText(String.valueOf(number));
                onCalCulatorLisenter.onDecrease(number);
                break;
        }
    }
    OnCalCulatorLisenter onCalCulatorLisenter;

    public void setOnCalCulatorLisenter(OnCalCulatorLisenter onCalCulatorLisenter) {
        this.onCalCulatorLisenter = onCalCulatorLisenter;
    }

    public interface OnCalCulatorLisenter{

        void onDecrease(int number);

        void onAdd(int number);
    }

}
