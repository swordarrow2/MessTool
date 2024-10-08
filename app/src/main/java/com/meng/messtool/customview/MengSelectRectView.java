package com.meng.messtool.customview;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;

import com.meng.tools.*;

public class MengSelectRectView extends View {

    private Bitmap imageViewBackground = null;
    private Bitmap bmpUseRect = null;
    private float xishu;
    private float mLeft;
    private float mTop;
    private boolean seted = false;

    public MengSelectRectView(Context c, AttributeSet attr) {
        super(c, attr);
    }

    /* public mengSelectRectView(Context context,Bitmap seleBmp,float screenW,float screenH,int qrSize){
         super(context);
         setup(seleBmp,screenW,screenH,qrSize);
         seted=true;
     }
 */
    public void setSize(int qrSize) {
        bmpUseRect = Bitmap.createBitmap((int) (qrSize * xishu), (int) (qrSize * xishu), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpUseRect);
        c.drawARGB(0x7f, 0x7f, 0xca, 0x00);
        invalidate();
    }

    public void setup(Bitmap seleBmp, float screenW, float screenH, int qrSize) {
        float bmpW = seleBmp.getWidth();
        float bmpH = seleBmp.getHeight();
        xishu = Math.min(screenH / bmpH, screenW / bmpW);
        imageViewBackground = QrUtils.scaleBitmap(seleBmp, xishu);
        bmpUseRect = Bitmap.createBitmap((int) (qrSize * xishu), (int) (qrSize * xishu), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpUseRect);
        c.drawARGB(0x7f, 0x7f, 0xca, 0x00);
        seted = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!seted) return;
        canvas.drawBitmap(imageViewBackground, 0, 0, null); // 绘制背景图像
        canvas.drawBitmap(bmpUseRect, mLeft, mTop, null); // 绘制选择框
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!seted) return true;
        int x = (int) event.getX();
        int y = (int) event.getY();
        mLeft = between(x - bmpUseRect.getWidth() / 2, 0, imageViewBackground.getWidth() - bmpUseRect.getWidth());
        mTop = between(y - bmpUseRect.getHeight() / 2, 0, imageViewBackground.getHeight() - bmpUseRect.getHeight());
        //tv.setText("x:"+(mLeft/xishu)+"  y:"+(mTop/xishu));
        invalidate(); // 重绘画布
        return true;
    }

    private int between(float a, int min, int max) {
        if (a < min) a = min;
        if (a > max) a = max;
        return (int) a;
    }

    public float getSelectLeft() {
        return mLeft;
    }

    public float getSelectTop() {
        return mTop;
    }

    public float getXishu() {
        return xishu;
    }
}
