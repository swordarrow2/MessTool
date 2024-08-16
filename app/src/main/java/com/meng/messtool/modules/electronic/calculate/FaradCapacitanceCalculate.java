package com.meng.messtool.modules.electronic.calculate;

import android.view.*;
import android.view.View.*;

import com.meng.messtool.customview.*;

import java.util.*;

public class FaradCapacitanceCalculate extends BaseDcdcCalculate implements OnClickListener {
    private MengEditText si;
    private MengEditText st;
    private MengEditText sv0;
    private MengEditText sv1;

    @Override
    public String getTitle() {
        return "法拉电容容量估算";
    }

    @Override
    public String getVersionName() {
        return "V1.0.0";
    }

    public void init() {
        setTitle("");
        si = addEditText("充电恒流(A)");
        st = addEditText("充电时间(s)");
        sv0 = addEditText("初始电压(V)");
        sv1 = addEditText("结束电压(V)");

        setButtonClick(this);
    }

    @Override
    public void onClick(View p1) {
        try {
            double result = calculate(sv0.getDouble(), sv1.getDouble(), si.getDouble(), st.getDouble());
            setResult(String.format(Locale.CHINA, "容量约为:%.2fF", result));
        } catch (NumberFormatException e) {
            showToast("请输入正确的数字");
        }
    }

    private double calculate(double v0, double v1, double i, double t) {
        return i * t / (v1 - v0);
    }
}
