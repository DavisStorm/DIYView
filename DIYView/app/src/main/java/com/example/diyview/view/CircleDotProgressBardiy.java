package com.example.diyview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.diyview.R;
import com.example.diyview.utils.SizeUtil;

public class CircleDotProgressBardiy extends View {

    private int lightDotColor;
    private int dimDotColor;
    private int dotNum = 100;
    private int progress = 0;
    private int maxProgress = 200;
    private Paint lightDotPaint;
    private Paint dimDotPaint;
    private Paint percentPaint;
    private Paint unitPaint;
    private Paint btnPaint;
    private Paint btnTextPaint;
    private int percent;
    private float percentTextSize;
    private float defaultPercentTextSize = 28;//单位sp
    private int percentTextColor;
    private float unitTextSize;
    private float defaultUnitTextSize = 13;//单位sp
    private int unitTextColor;
    private String unitText = "";
    private String defaultUnitText = "分";
    private String btnText;
    private float defaultBtnTextSize = 20;
    private float btnTextSize;
    private int btnTextColor;
    private int defaultDtnYspace = 2;//默认marginTop 2dp
    private float btnYspace;
    private int btnBgColor;
    private float startPointY;
    private Paint.FontMetrics btnFontMetrics;
    private float btnTextWidth;
    private float startPointX;
    private OnBtnClickLisener onBtnClickLisener;
    boolean able = false;

    public CircleDotProgressBardiy(Context context) {
        super(context);
    }

    public CircleDotProgressBardiy(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleDotProgressBardiy(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleDotProgressBardiy);
        lightDotColor = typedArray.getColor(R.styleable.CircleDotProgressBardiy_lightDotColor, Color.WHITE);
        dimDotColor = typedArray.getColor(R.styleable.CircleDotProgressBardiy_dimDotColor, Color.GRAY);
        maxProgress = typedArray.getInteger(R.styleable.CircleDotProgressBardiy_maxProgress, 200);
        progress = typedArray.getInteger(R.styleable.CircleDotProgressBardiy_progress, 0);
        percentTextSize = typedArray.getDimension(R.styleable.CircleDotProgressBardiy_percentTextSizediy, defaultPercentTextSize);
        percentTextColor = typedArray.getColor(R.styleable.CircleDotProgressBardiy_percentTextColordiy, Color.WHITE);
        unitTextSize = typedArray.getDimension(R.styleable.CircleDotProgressBardiy_unitTextSizediy, defaultUnitTextSize);
        unitTextColor = typedArray.getColor(R.styleable.CircleDotProgressBardiy_unitTextColordiy, Color.WHITE);
        unitText = typedArray.getString(R.styleable.CircleDotProgressBardiy_unitTextdiy) == null ?
                defaultUnitText : typedArray.getString(R.styleable.CircleDotProgressBardiy_unitTextdiy);
        btnText = typedArray.getString(R.styleable.CircleDotProgressBardiy_btnText);
        //btnTextSize这个值不准确，不知道原因
        btnTextSize = typedArray.getDimension(R.styleable.CircleDotProgressBardiy_btnTextSize, defaultBtnTextSize);
        btnTextColor = typedArray.getColor(R.styleable.CircleDotProgressBardiy_btnTextColor, Color.RED);
        btnYspace = typedArray.getDimension(R.styleable.CircleDotProgressBardiy_btnYspace, defaultDtnYspace);
        btnBgColor = typedArray.getColor(R.styleable.CircleDotProgressBardiy_btnBgColor, Color.RED);

        typedArray.recycle();

        lightDotPaint = new Paint();
        lightDotPaint.setColor(lightDotColor);
        lightDotPaint.setAntiAlias(true); // 消除锯齿
        lightDotPaint.setStyle(Paint.Style.FILL);
        dimDotPaint = new Paint();
        dimDotPaint.setColor(dimDotColor);
        dimDotPaint.setAntiAlias(true); // 消除锯齿
        dimDotPaint.setStyle(Paint.Style.FILL);
        percentPaint = new Paint();
        percentPaint.setColor(percentTextColor);
        percentPaint.setTextSize(SizeUtil.dp2px(percentTextSize, context));
        percentPaint.setAntiAlias(true); // 消除锯齿
        percentPaint.setStyle(Paint.Style.FILL);
        unitPaint = new Paint();
        unitPaint.setColor(unitTextColor);
        unitPaint.setTextSize(SizeUtil.dp2px(unitTextSize, context));
        unitPaint.setAntiAlias(true); // 消除锯齿
        unitPaint.setStyle(Paint.Style.FILL);
        btnPaint = new Paint();
        btnPaint.setTextSize(SizeUtil.dp2px(btnTextSize, context));
        btnPaint.setColor(btnBgColor);
        btnPaint.setAntiAlias(true); // 消除锯齿
        btnTextPaint = new Paint();
        btnTextPaint.setColor(btnTextColor);
        btnTextPaint.setTextSize(SizeUtil.dp2px(btnTextSize, context));
        btnTextPaint.setAntiAlias(true); // 消除锯齿
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 设计100个点在最大可能圆的圈上等距排列，且圆与圆之间刚好放下一个圆
     */
    @Override
    protected void onDraw(Canvas canvas) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        //计算一个圆对应的旋转角度
        float oneDotDegree = 360.0f / (dotNum * 2);
        //计算小圆的半径可以用sin(oneDotDegree/2) = sin(0.9°) = r / R; , 得出
        float r = (float) Math.sin(Math.PI * 0.9 / 180) * centerX;
        canvas.drawCircle(centerX, r, r, lightDotPaint);
        percent = (int) (progress * 100f / maxProgress);
        int count = 1;
        while (count < percent) {
            canvas.drawCircle(centerX, r, r, lightDotPaint);
            canvas.rotate(oneDotDegree * 2, centerX, centerY);
            count++;
        }

        count--;
        while (count++ < 100) {
            canvas.drawCircle(centerX, r, r, dimDotPaint);
            canvas.rotate(oneDotDegree * 2, centerX, centerY);
        }

        //画文字
        float percentTextWidth = percentPaint.measureText(percent + "");
        float unitTextWidth = unitPaint.measureText(unitText);
        float startX = centerX - (percentTextWidth + unitTextWidth) / 2;

        //先画高一点的Text,然后矮一点的text跟高一点的Text的底线对其
        Paint biggerPaint = percentTextSize > unitTextSize ? percentPaint : unitPaint;
        Paint.FontMetrics fontMetrics = biggerPaint.getFontMetrics();
        //文字的底线和空间中线重合，注意fontMetrics.ascent是负值
        float startY = centerY - fontMetrics.descent;
        canvas.drawText(percent + "", startX, startY, percentPaint);
        canvas.drawText(unitText, startX + percentTextWidth, startY, unitPaint);

        //画按钮
        btnFontMetrics = btnPaint.getFontMetrics();
        startPointY = centerY + SizeUtil.dp2px(btnYspace, getContext());
        btnTextWidth = btnPaint.measureText(btnText);
        startPointX = centerX - btnTextWidth / 2;
        Path path = new Path();
        path.reset();
        path.moveTo(startPointX, startPointY);
        RectF rectF = new RectF();
        float left = startPointX - (btnFontMetrics.descent - btnFontMetrics.ascent) / 2;
        float top = startPointY;
        float right = startPointX + (btnFontMetrics.descent - btnFontMetrics.ascent) / 2;
        float bottom = startPointY + btnFontMetrics.descent - btnFontMetrics.ascent;
        rectF.set(left, top, right, bottom);
        path.arcTo(rectF, 90, 180);
        path.rLineTo(btnTextWidth, 0);
        rectF.offset(btnTextWidth, 0);
        path.arcTo(rectF, 270, 180);
        path.rLineTo(-btnTextWidth, 0);
        canvas.drawPath(path, btnPaint);

        //画按钮中的文字
        float btnTextStartY = startPointY - btnFontMetrics.ascent;
        canvas.drawText(btnText, startPointX, btnTextStartY, btnTextPaint);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void setProgress(int progress) {
        if (progress < 0 || progress > maxProgress) {
            throw new IllegalArgumentException(String.format("progress must between 0 and max(%d)", maxProgress));
        }
        this.progress = progress;
        percent = (int) (progress * 100f / maxProgress);
        //只是重复执行ondraw方法，所以前面要重新计算percent的值
        postInvalidate(); // 可以直接在子线程中调用，而invalidate()必须在主线程（UI线程）中调用
    }

    public void setProgressMax(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //判断按下的点在按钮内
                if (calculatePointInBtn(event.getX(), event.getY())) {
                    able = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                //判断手指抬起的点在按钮内，才真正触发点击事件
                if (able && calculatePointInBtn(event.getX(), event.getY())) {
                    if (onBtnClickLisener != null) {
                        onBtnClickLisener.onClick();
                    }
                }
                break;
        }
        return true;
    }

    private boolean calculatePointInBtn(float x, float y) {
        //先判断Y坐标是否在按钮内，再判断X坐标也在按钮内
        if (y > startPointY && y < startPointY + btnFontMetrics.descent - btnFontMetrics.ascent) {
            //分两步1.先判断是否在矩形内，2.再判断是否在两个半圆内
            if (x > startPointX && x < startPointX + btnTextWidth) {
                return true;
            }
            //左边圆的圆心
            float leftCircleX = startPointX;
            float leftCircleY = startPointY + (btnFontMetrics.descent - btnFontMetrics.ascent) / 2;
//            Math.abs(leftCircleX-x)的平方 + Math.abs(leftCircleY-y)的平方 <= (btnFontMetrics.descent - btnFontMetrics.ascent) / 2的平方
            if (Math.pow(Math.abs(leftCircleX - x), 2) + Math.pow(Math.abs(leftCircleY - y), 2) <= Math.pow(btnFontMetrics.descent - btnFontMetrics.ascent, 2)) {
                return true;
            }
            //右边圆心
            leftCircleX = startPointX + startPointX + btnTextWidth;
            if (Math.pow(Math.abs(leftCircleX - x), 2) + Math.pow(Math.abs(leftCircleY - y), 2) <= Math.pow(btnFontMetrics.descent - btnFontMetrics.ascent, 2)) {
                return true;
            }
        }
        return false;
    }

    public abstract static class OnBtnClickLisener {
        public abstract void onClick();
    }

    public void setOnBtnClickLisener(OnBtnClickLisener lisener) {
        onBtnClickLisener = lisener;
    }
}