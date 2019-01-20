package com.example.shoppingcar.ui.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shoppingcar.R;

public class CalculatorView extends LinearLayout implements OnClickListener {
    private Button btn_add;
    private Button btn_decrease;
    private TextView tv_number;

    public CalculatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        View rootView = LayoutInflater.from(context).inflate(R.layout.calculatorview_item, this);
        btn_add = rootView.findViewById(R.id.btn_add);
        btn_decrease = rootView.findViewById(R.id.btn_decrease);
        tv_number = rootView.findViewById(R.id.tv_number);
        btn_add.setOnClickListener(this);
        btn_decrease.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        String numberString = tv_number.getText().toString();
        int number = Integer.parseInt(numberString);
        switch (view.getId()){
            case R.id.btn_decrease:
                number = number - 1;
                if (number < 0 ){
                    number = 0;
                    //设置最小值为0
                    tv_number.setText(String.valueOf(number));
                }
                tv_number.setText(String.valueOf(number));
                //接口回调回传数值
                onCalCulatorLisenter.onDecrease(number);
                break;
            case R.id.btn_add:
                number = number + 1;
                tv_number.setText(String.valueOf(number));
                onCalCulatorLisenter.onAdd(number);
                break;
        }
    }
    OnCalCulatorLisenter onCalCulatorLisenter;

    public interface OnCalCulatorLisenter{
        //减减
        void onDecrease(int number);
        //加加
        void onAdd(int number);
    }

    public void setOnCalCulatorLisenter(OnCalCulatorLisenter onCalCulatorLisenter) {
        this.onCalCulatorLisenter = onCalCulatorLisenter;
    }
}
