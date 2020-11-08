package com.example.diyview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public abstract class BaseGradientLineMine extends View {
    protected float mPer = 0f;
    protected ValueAnimator mAnimator;

    public BaseGradientLineMine(Context context) {
        super(context);
        initAnim();
    }

    public BaseGradientLineMine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAnim();
    }

    public BaseGradientLineMine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAnim();
    }

    private void initAnim() {
        mAnimator = ValueAnimator.ofFloat(0, 1);
        mAnimator.setRepeatCount(1);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPer = (float) animation.getAnimatedValue();
                buildDataDuringAnim(mPer);
                invalidate();
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setDuration();
    }

    protected abstract void setDuration();

    protected abstract void buildDataDuringAnim(float mPer);

    public void startAnimation(){}
}
