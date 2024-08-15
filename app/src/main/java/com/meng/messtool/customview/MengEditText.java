package com.meng.messtool.customview;

import android.content.*;
import android.util.*;

public class MengEditText extends android.support.v7.widget.AppCompatEditText {

    /*
     *@author 清梦
     *@date 2024-04-19 19:47:49
     */
    public static final String TAG = "SjfEditText";

    public MengEditText(Context context) {
        super(context);
    }

    public MengEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MengEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public double getDouble() throws NumberFormatException {
        double d = Double.parseDouble(getText().toString());
        if (Double.isNaN(d)) {
            throw new NumberFormatException("NaN");
        }
        return d;
    }
}
