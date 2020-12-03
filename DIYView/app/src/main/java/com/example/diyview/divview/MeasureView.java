package com.example.diyview.divview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.diyview.R;

public class MeasureView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint textPaint;
    private String words;
    private int textSize = 70; //默认值

    public MeasureView(Context context) {
        super(context);
        initPaint();
    }

    public MeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MeasureView, 0, 0);
        words = typedArray.getString(R.styleable.MeasureView_text);
        textSize = typedArray.getInteger(R.styleable.MeasureView_textSize,0);//单位转换 todo
        typedArray.recycle();
        initPaint();
    }

    public MeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint.setColor(Color.parseColor("#FF4081"));
        textPaint = new Paint();
        textPaint.setColor(getContext().getResources().getColor(R.color.blue));
        textPaint.setTextSize(textSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float left = getLeft();
        float right = getRight();
        float top = getTop();
        float bottom = getBottom();
        canvas.drawRect(left,top,right,bottom,paint);
        canvas.save();
        //绘制文字
        float textWidth = textPaint.measureText(words);// The width of the text
        float leftX = getWidth() / 2 - textWidth / 2;
        float baseLineY = (getHeight() / 2) + Math.abs(textPaint.ascent() + textPaint.descent()) / 2;
        canvas.drawText(words,leftX,baseLineY,textPaint);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measuredHeight(heightMeasureSpec));
    }

    /**
     * 测量宽
     *
     * @param widthMeasureSpec
     */
    private int measureWidth(int widthMeasureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 测量高
     *
     * @param heightMeasureSpec
     */
    private int measuredHeight(int heightMeasureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}