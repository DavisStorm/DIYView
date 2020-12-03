package com.example.diyview.divview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class SuperLikeView extends AppCompatButton {

    private int status;
    private int ANIM_START = 1;
    private int ANIM_ACTING = 2;
    private int ANIM_END = 3;

    public SuperLikeView(Context context) {
        super(context);
    }

    public SuperLikeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperLikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if (){
//            return super.onTouchEvent(event);
//        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                status = ANIM_START;
                Log.e("onTouchEvent: ", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                status = ANIM_ACTING;
                Log.e("onTouchEvent: ", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                status = ANIM_END;
                Log.e("onTouchEvent: ", "ACTION_UP");
                break;
        }
        return true;
    }

    public void setLikeAnim(){

    }
}
