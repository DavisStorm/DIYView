package com.example.diyview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
*@author lenovo
*@date 2020/11/6 15:00
 * 1.动画分为 平移 旋转 透明度
*/
public class AnimShopButtonMine extends View {
    public AnimShopButtonMine(Context context) {
        super(context);
        init(context,null);
    }

    public AnimShopButtonMine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }


    public AnimShopButtonMine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int x=0;
        int y=0;
        //画左边按钮
        drawLeftbtn(canvas,x,y);
        //写中间数字
        //画右边按钮
    }

    private void drawLeftbtn(Canvas canvas, int x, int y) {
        canvas.draw
    }
}
