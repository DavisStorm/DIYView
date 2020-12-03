package com.example.diyview.divview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.example.diyview.R;
import com.example.diyview.utils.SizeUtil;


public class SectionProBarMine extends ProgressBar {

    private Paint paint;
    private int hbv_unReachedColor;
    private float hbv_unReachedHeight;
    private int hbv_reachedColor;
    private float hbv_reachedHeight;
    private int hbv_style = 0;
    private LinearGradient gradientBlue;
    private LinearGradient gradientBg;

    public SectionProBarMine(Context context) {
        super(context);
    }

    public SectionProBarMine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SectionProBarMine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SectionProBarMine);
        hbv_unReachedColor = typedArray.getColor(R.styleable.SectionProBarMine_hbv_unReachedColorMine, Color.GRAY);
        hbv_unReachedHeight = typedArray.getDimension(R.styleable.SectionProBarMine_hbv_unReachedHeightMine, SizeUtil.dp2px(5, getContext()));
        hbv_reachedColor = typedArray.getColor(R.styleable.SectionProBarMine_hbv_reachedColorMine, Color.BLUE);
        hbv_reachedHeight = typedArray.getDimension(R.styleable.SectionProBarMine_hbv_reachedHeightMine, SizeUtil.dp2px(5, getContext()));
        hbv_style = typedArray.getInteger(R.styleable.SectionProBarMine_hbv_style, 0);
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        //画背景
        int startX = getPaddingLeft();
        int endX = getMeasuredWidth() - getPaddingRight();
        int top = getPaddingTop();
        paint.setColor(hbv_unReachedColor);
        paint.setStrokeWidth(hbv_unReachedHeight);
        if (hbv_style==0){
            canvas.drawLine(startX, top, endX, top, paint);
        }else if (hbv_style==1){
            canvas.drawLine(startX, top, endX, top, paint);

            gradientBg = new LinearGradient(endX, top,endX - getProgress() * 1.0f / getMax() * (endX - startX),
                    top,Color.BLUE,hbv_unReachedColor, Shader.TileMode.CLAMP);
            paint.setShader(gradientBg);
            canvas.drawLine(endX, top,endX - getProgress() * 1.0f / getMax() * (endX - startX),top,paint);
        }

        //画进度
        paint.setColor(hbv_reachedColor);
        paint.setStrokeWidth(hbv_reachedHeight);
        if (hbv_style==0){//一般模式
            canvas.drawLine(startX, top, startX + getProgress() * 1.0f / getMax() * (endX - startX), top, paint);
        }else if(hbv_style==1){
            gradientBlue = new LinearGradient(startX, top, startX + getProgress() * 1.0f / getMax() * (endX - startX),
                    top,hbv_reachedColor,Color.BLUE, Shader.TileMode.CLAMP);
            paint.setShader(gradientBlue);
            canvas.drawLine(startX, top, startX + getProgress() * 1.0f / getMax() * (endX - startX), top, paint);
        }
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
        invalidate();
    }
}
