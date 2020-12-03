package com.example.diyview.divview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.diyview.R;

public class GradientLineMine extends BaseGradientLineMine {

    private Paint pathPaint;
    private int view_w;
    private int view_h;
    private Path path;
    private PathMeasure pathMeasure;
    private float length;
    private float[] point;
    private float[] tan;
    private Path resultPath;
    private int duration_mine;

    public GradientLineMine(Context context) {
        super(context);
    }

    public GradientLineMine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GradientLineMine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GradientLineMine);
        duration_mine = typedArray.getInteger(R.styleable.GradientLineMine_duration_mine, 1000);
        typedArray.recycle();
    }

    @Override
    protected void setDuration() {
        mAnimator.setDuration(duration_mine);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        view_w = getWidth();
        view_h = getHeight();

        pathPaint = new Paint();
        pathPaint.setAntiAlias(true);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(10);

        path = new Path();
        path.moveTo(0, view_h);
        path.cubicTo(view_w * 0.3f, view_h, view_w * 0.7f, 0, view_w, 0);
        pathMeasure = new PathMeasure(path, false);
        length = pathMeasure.getLength();
        point = new float[2];
        tan = new float[2];

        resultPath = new Path();
        resultPath.moveTo(0, view_h);
    }

    @Override
    protected void buildDataDuringAnim(float mPer) {
        pathMeasure.getPosTan(length * mPer, point, tan);
//        if (mPer==1f){
//          resultPath.setLastPoint(point[0],point[1]);
//        } else {
//            resultPath.lineTo(point[0],point[1]);
//        }
        resultPath.lineTo(point[0], point[1]);
        Log.e("buildDataDuringAnim: ", "X:" + point[0] + " Y:" + point[1]);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(resultPath, pathPaint);
    }

    @Override
    public void startAnimation() {
        super.startAnimation();
        resultPath.reset();
        resultPath.moveTo(0, view_h);
        mAnimator.start();
    }
}
