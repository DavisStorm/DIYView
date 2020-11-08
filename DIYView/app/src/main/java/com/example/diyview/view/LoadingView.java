package com.example.diyview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.diyview.R;

public class LoadingView extends View {

    private int leftColor;
    private int rightColor;
    private int middleColor;
    private int timeforoneround;
    private int repeatCount;
    private int viewWidth;
    private int viewHeight;
    private int radiu = 20;
    private Paint paint;
    private int[] colorQueue;
    private float timeLines;
    private String TAG = "LoadingView";

    public LoadingView(Context context) {
        super(context);
        init(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingView);
        leftColor = typedArray.getColor(R.styleable.LoadingView_leftColor, getResources().getColor(R.color.grey));
        rightColor = typedArray.getColor(R.styleable.LoadingView_rightColor, getResources().getColor(R.color.grey));
        middleColor = typedArray.getColor(R.styleable.LoadingView_middleColor, getResources().getColor(R.color.grey));
        colorQueue = new int[]{leftColor, middleColor, rightColor};
        timeforoneround = typedArray.getInteger(R.styleable.LoadingView_timeforoneround, 500);
        repeatCount = typedArray.getInteger(R.styleable.LoadingView_repeatCount, 1);
        typedArray.recycle();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = getWidth();
        viewHeight = getHeight();
        radiu = viewHeight / 2;
        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.setRepeatCount(repeatCount);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(timeforoneround);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            boolean reachEnd = true;

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int process = (int) animation.getAnimatedValue();
                timeLines = process / 100f;
                Log.e(TAG, "animation.getAnimatedValue(): " + animation.getAnimatedValue());
                if (timeLines < 0.1f && !reachEnd) {
                    reachEnd = true;
                    int first = colorQueue[0];
                    int secode = colorQueue[1];
                    int third = colorQueue[2];
                    colorQueue = new int[]{third, first, secode};
                }
                if (timeLines >= 0.9f && reachEnd) {
                    reachEnd = false;
                }
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (timeLines < 0.5) {
            paint.setColor(colorQueue[0]);
            canvas.drawCircle(viewWidth / 2 - (viewWidth - radiu * 2) * timeLines, viewHeight / 2, radiu, paint);
            paint.setColor(colorQueue[1]);
            canvas.drawCircle(viewWidth / 2, viewHeight / 2, radiu, paint);
            paint.setColor(colorQueue[2]);
            canvas.drawCircle(viewWidth / 2 + (viewWidth - radiu * 2) * timeLines, viewHeight / 2, radiu, paint);
        } else {
            paint.setColor(colorQueue[0]);
            canvas.drawCircle(radiu + ((viewWidth - radiu * 2) * timeLines - (viewWidth / 2 - radiu)), viewHeight / 2, radiu, paint);
            paint.setColor(colorQueue[1]);
            canvas.drawCircle(viewWidth / 2, viewHeight / 2, radiu, paint);
            paint.setColor(colorQueue[2]);
            canvas.drawCircle(viewWidth / 2 + (viewWidth - radiu * 2) * (1-timeLines), viewHeight / 2, radiu, paint);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }
}
