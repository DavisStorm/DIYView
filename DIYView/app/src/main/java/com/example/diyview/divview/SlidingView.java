package com.example.diyview.divview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.diyview.R;
import com.example.diyview.utils.LogUtil;

public class SlidingView extends FrameLayout {

    private int STATUS = 0;//0为侧滑隐藏状态，1为打开
    private int height;
    private int width;
    private float effective_slide_rate = 0.5f;
    private int childLeftWidth;
    private float originX;

    public SlidingView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public SlidingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SlidingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlidingView);
        effective_slide_rate = typedArray.getFloat(R.styleable.SlidingView_effective_slide_rate, effective_slide_rate);
        if (effective_slide_rate > 1 || effective_slide_rate < 0) throw new NumberFormatException(
                "SlidingView value effective_slide_rate must between 0 to 1");
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();
        height = getHeight();
        childLeftWidth = getChildAt(1).getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (getChildCount() != 2)
            throw new RuntimeException("MotionEventTestFrameLayout just only need 2 kids");
        //侧滑视图
        View childLeft = getChildAt(1);
        childLeft.layout((int) (0 - childLeft.getWidth() + totalDistance), childLeft.getTop(), (int) totalDistance, childLeft.getBottom());
        //内容视图
        View childRight = getChildAt(0);
        childRight.layout((int) totalDistance, childRight.getTop(), (int) (totalDistance + childRight.getWidth()), childRight.getBottom());
    }

    private float totalDistance = 0f;//正为右滑，负为左滑

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.e("out onTouchEvent", "ACTION_DOWN");
                //根据onIntecept的逻辑，DOWN事件不会被触发
                break;
            case MotionEvent.ACTION_MOVE:
                //根据onIntecept的逻辑，已经确定了这里要消费事件，所以一定返回true
                float distanceX = event.getX() - interceptX;
                interceptX = event.getX();
                totalDistance += distanceX;
                if (totalDistance >= childLeftWidth || totalDistance <= 0) {
                    totalDistance = totalDistance <= 0 ? 0 : childLeftWidth;
                }
                //Math.abs(event.getX() - originX) > width * effective_slide_rate
                if (Math.abs(totalDistance) > width * effective_slide_rate) {//滑动距离达到有效长度
                    if (STATUS == 0 && totalDistance > 0) {//右滑
                        STATUS = 1;
                    }
                    if (STATUS == 1 && totalDistance < 0) {//左滑
                        STATUS = 0;
                    }
                } else {//没有达到有效长度，可能是还没滑到也有可能是已经滑到了但是又滑回来了
                    STATUS = totalDistance > 0 ? 0 : 1;
                }
                LogUtil.e("out onTouchEvent", "ACTION_MOVE totalDistance：" + totalDistance);
                requestLayout();
                return true;
            case MotionEvent.ACTION_UP:
                LogUtil.e("out onTouchEvent", "ACTION_UP");
                float endValue = STATUS == 0 ? 0 : childLeftWidth;
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(totalDistance, endValue);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        totalDistance = (float) animation.getAnimatedValue();
                        requestLayout();
                    }
                });
                valueAnimator.start();
                break;
        }
        return super.onTouchEvent(event);
    }


    private float interceptX;
    private float interceptY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        //内部有控件点击事件这么写，没有就直接return true
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.e("out onInterceptTouchEvent", "ACTION_DOWN");
                interceptX = event.getX();
                interceptY = event.getY();
                originX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.e("out onInterceptTouchEvent", "ACTION_MOVE");
                float distanceX = event.getX() - interceptX;
                float distanceY = event.getY() - interceptY;
                //只消费横向滑动
                if (Math.abs(distanceX) > Math.abs(distanceY)) {
                    //侧滑菜单闭合状态下在屏幕向右滑动
                    if (distanceX > 0 && STATUS == 0) {
                        return true;
                    }
                    //侧滑菜单打开状态下在屏幕向左滑动
                    else if (distanceX < 0 && STATUS == 1) {
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.e("out onInterceptTouchEvent", "ACTION_UP");
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

}
