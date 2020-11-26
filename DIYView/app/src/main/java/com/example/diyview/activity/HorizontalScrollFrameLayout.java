package com.example.diyview.activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.example.diyview.R;


public class HorizontalScrollFrameLayout extends FrameLayout {

    private int width;
    private int height;
    private float sweep_valueable_rate = 0.4f;//默认横向滑动长度超过父布局0.4倍，为有效滑动
    private int page = 0;
    private OnPagerChangeLisener mPagerChangeLisener;

    public HorizontalScrollFrameLayout(Context context) {
        super(context);
        init(context, null);
    }

    public HorizontalScrollFrameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public HorizontalScrollFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalScrollLinearLayout);
        sweep_valueable_rate = typedArray.getFloat(R.styleable.HorizontalScrollLinearLayout_sweep_valueable_rate, sweep_valueable_rate);
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int startX = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            childAt.layout((int) (startX + theFinalDistanceX), childAt.getTop(), (int) (startX + theFinalDistanceX + childAt.getWidth()), childAt.getBottom());
            startX = childAt.getWidth();
//            Log.e("onLayout i:" + i, "--> Left: " + ((int) (startX + theFinalDistanceX)) + " width: " + childAt.getWidth() + " + theFinalDistanceX: " + theFinalDistanceX);
        }
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
//              Log.e("onTouchEvent: ", "ACTION_DOWN -> fingerX:" + fingerX);
                break;
            case MotionEvent.ACTION_MOVE:
//              Log.e("onTouchEvent: ", "ACTION_MOVE -> fingerX:" + fingerX);
                distanceX = event.getX() - fingerX;
                distanceY = event.getY() - fingerY;
//                Log.e("onTouchEvent: ", "distanceX:" + distanceX + "event.getX():" + event.getX() + "fingerX:" + fingerX);
                fingerX = event.getX();
                fingerY = event.getY();
//                Log.e("onTouchEvent: ", "ACTION_MOVE -> theFinalDistanceX:" + theFinalDistanceX);
                if (Math.abs(distanceX) > Math.abs(distanceY)) {
                    theFinalDistanceX += distanceX;
                    requestLayout();
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
//                Log.e("onTouchEvent: ", "ACTION_UP -> DistanceX:" + distanceX);
                int startX = 0 - page * width;
                //划过sweep_valueable_rate为有效滑动
                if (width * sweep_valueable_rate < Math.abs(Math.abs(startX) - Math.abs(theFinalDistanceX))) {
                    int endValue = 0;
                    //显示下一版
                    if (theFinalDistanceX < 0 - page * width && page < getChildCount() - 1) {
//                        Log.e("page1: ", page + "");
                        endValue = 0 - (page + 1) * width;
                        ValueAnimator valueAnimator = ValueAnimator.ofFloat(theFinalDistanceX, endValue);
                        final int finalEndValue = endValue;
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                theFinalDistanceX = (float) animation.getAnimatedValue();
                                requestLayout();
                                if (theFinalDistanceX == finalEndValue) {
                                    page++;
                                    if (mPagerChangeLisener != null) {
                                        mPagerChangeLisener.onPageChange(page);
                                    }
//                                    Log.e("page1: ", page + "");
                                }
                            }
                        });
                        valueAnimator.start();
                        //显示上一版
                    } else if (theFinalDistanceX >= 0 - page * width && theFinalDistanceX < 0 && page > 0) {
//                        Log.e("page1: ", page + "");
                        endValue = 0 - (page - 1) * width;
                        ValueAnimator valueAnimator = ValueAnimator.ofFloat(theFinalDistanceX, endValue);
                        final int finalEndValue = endValue;
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                theFinalDistanceX = (float) animation.getAnimatedValue();
                                requestLayout();
                                if (theFinalDistanceX == finalEndValue) {
                                    page--;
                                    if (mPagerChangeLisener != null) {
                                        mPagerChangeLisener.onPageChange(page);
                                    }
//                                    Log.e("page1: ", page + "");
                                }
                            }
                        });
                        valueAnimator.start();
                        //弹回当前
                    } else {
//                        Log.e("page3: ", page + "");
                        ValueAnimator valueAnimator = ValueAnimator.ofFloat(theFinalDistanceX, theFinalDistanceX > 0 ? page * width : 0 - page * width);
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                theFinalDistanceX = (float) animation.getAnimatedValue();
                                requestLayout();
                            }
                        });
                        valueAnimator.start();
                    }

                } else {
                    //弹回当前
//                    Log.e("page4: ", page + "");
                    ValueAnimator valueAnimator = ValueAnimator.ofFloat(theFinalDistanceX, theFinalDistanceX > 0 ? page * width : 0 - page * width);
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            theFinalDistanceX = (float) animation.getAnimatedValue();
                            requestLayout();
                        }
                    });
                    valueAnimator.start();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    float interceptFingerX;
    float interceptFingerY;
    float interceptDistanceX;
    float interceptDistanceY;
    private boolean dealByMe = false;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                interceptDistanceX = 0;
                interceptDistanceY = 0;
                fingerX = event.getX();
                fingerY = event.getY();
                interceptFingerX = event.getX();
                interceptFingerY = event.getY();
                dealByMe = false;
//                Log.e("onInterceptTouchEv", "TouchEvent ACTION_DOWN -> interceptFingerX :" + interceptFingerX);
                break;
            case MotionEvent.ACTION_MOVE:
                interceptDistanceX = event.getX() - interceptFingerX;
                interceptDistanceY = event.getY() - interceptFingerY;
                interceptFingerX = event.getX();
                interceptFingerY = event.getY();
//                Log.e("onInterceptTouchEv", "TouchEvent ACTION_MOVE -> event.getX():" + event.getX());
                //如果横滑距离大于竖滑距离，则拦截事件给自己,并且锁定为此事件后续的一系列事件将由自己消费
                if (Math.abs(interceptDistanceX) > Math.abs(interceptDistanceY)) {
                    dealByMe = true;
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                return dealByMe;
            case MotionEvent.ACTION_UP:
                interceptFingerX = 0;
                interceptFingerY = 0;
                interceptDistanceX = 0;
                interceptDistanceY = 0;
                dealByMe = false;
//                Log.e("onInterceptTouchEv", "TouchEvent ACTION_UP -> interceptDistanceX:" + interceptDistanceX);
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    public void scrollToPosition(final int position) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(theFinalDistanceX, 0 - position * width);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                theFinalDistanceX = (float) animation.getAnimatedValue();
                requestLayout();
                if (theFinalDistanceX == 0 - position * width) {
                    page = position;
                }
            }
        });
        valueAnimator.start();
    }

    public abstract static class OnPagerChangeLisener {
        protected abstract void onPageChange(int position);
    }

    public void setmPagerChangeLisener(OnPagerChangeLisener mPagerChangeLisener) {
        this.mPagerChangeLisener = mPagerChangeLisener;
    }
}
