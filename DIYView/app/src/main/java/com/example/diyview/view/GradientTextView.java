package com.example.diyview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.diyview.R;

public class GradientTextView extends AppCompatTextView {

    private Direction DIRECTION = Direction.LEFT_TO_RIGHT;
    private float mCurrentProgress = 0.0f;

    private int mOriginColor;
    private int mChangeColor;
    private Paint mOriginPaint;
    private Paint mChangePaint;

    public GradientTextView(Context context) {
        super(context);
        initData(context, null);
    }

    public GradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData(context, attrs);
    }

    public GradientTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
    }

    private void initData(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GradientTextView);
        mOriginColor = typedArray.getColor(R.styleable.GradientTextView_originColor, getCurrentTextColor());
        mChangeColor = typedArray.getColor(R.styleable.GradientTextView_changeColor, getCurrentTextColor());
        typedArray.recycle();

        //初始两个颜色画笔
        mOriginPaint = createPaint(mOriginColor);
        mChangePaint = createPaint(mChangeColor);

    }

    public enum Direction{
         LEFT_TO_RIGHT, RIGHT_TO_LEFT
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float middle = mCurrentProgress * (getWidth() - getPaddingLeft() - getPaddingRight()) +getPaddingLeft();
        //颜色从左到右
        if (DIRECTION ==Direction.LEFT_TO_RIGHT){
            //先画左边的红色
            drawPartText(canvas, getPaddingLeft(), middle, mChangePaint);
            //再画右边原色
            drawPartText(canvas, middle, getWidth()-getPaddingRight(), mOriginPaint);
        }else {
            //先画右边的红色
            drawPartText(canvas, middle, getWidth()-getPaddingRight(), mChangePaint);
            //再画左边原色
            drawPartText(canvas, getPaddingLeft(), middle, mOriginPaint);
        }
    }

    private void drawPartText(Canvas canvas, float start, float end, Paint paint) {
        canvas.save();
        canvas.clipRect(new RectF(start, 0, end, getHeight()));
        //中线的位置
        int centerLine = (getHeight() - getPaddingTop() - getPaddingBottom()) / 2 + getPaddingTop();
        //计算基线位置
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float baseLine = centerLine - fontMetrics.ascent / 2 - fontMetrics.descent / 2;
        canvas.drawText((String) getText(), getPaddingLeft(), baseLine, paint);
        canvas.restore();
    }

    private Paint createPaint(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setTextSize(getTextSize());
        paint.setDither(false);
        return paint;
    }

    public void setMCurrentProgress(float mCurrentProgress) {
        this.mCurrentProgress = mCurrentProgress;
        invalidate();
    }
    public void setDIRECTION(Direction DIRECTION) {
        this.DIRECTION = DIRECTION;
    }

    public void setmOriginColor(int mOriginColor) {
        this.mOriginColor = mOriginColor;
    }

    public void setmChangeColor(int mChangeColor) {
        this.mChangeColor = mChangeColor;
        mChangePaint.setColor(mChangeColor);
    }
}
