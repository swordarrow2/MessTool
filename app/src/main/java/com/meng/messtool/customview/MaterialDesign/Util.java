package com.meng.messtool.customview.MaterialDesign;

import android.content.*;

final class Util {

    static int dpToPx(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * scale);
    }
}
