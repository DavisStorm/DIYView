package com.example.diyview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SlidingView extends FrameLayout {
    public SlidingView(@NonNull Context context) {
        super(context);
    }

    public SlidingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    float fingerX;
    float fingerY;
    float distanceX;
    float distanceY;
    float theFinalDistanceX = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                distanceX = 0;
                distanceY = 0;
                fingerX = event.getX();
                fingerY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                distanceX = event.getX() - fingerX;
                distanceY = event.getY() - fingerY;
                fingerX = event.getX();
                fingerY = event.getY();
                theFinalDistanceX += distanceX;
                requestLayout();
                return true;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (getChildCount()!=2) throw new RuntimeException("SlidingView must have two child view");
        //定位侧滑布局位置
        View childLeft = getChildAt(1);
        int childLeftWidth = childLeft.getWidth();
        int childLeftL = (int) (theFinalDistanceX - childLeftWidth);
        int childLeftR = (int) (theFinalDistanceX);
        childLeft.layout(childLeftL,childLeft.getTop(),childLeftR,childLeft.getBottom());

        //定位内容布局位置
        View childRight = getChildAt(0);
        int childRightWidth = childRight.getWidth();
        int childRightL = (int) (theFinalDistanceX);
        int childRightR = (int) (theFinalDistanceX+childRightWidth);
        childLeft.layout(childRightL,childRight.getTop(),childRightR,childRight.getBottom());
    }
}
