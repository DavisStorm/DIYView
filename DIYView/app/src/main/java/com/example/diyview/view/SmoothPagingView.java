package com.example.diyview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.diyview.R;

import java.util.ArrayList;
import java.util.List;
/**/
public class SmoothPagingView extends ViewGroup {

    private int currentIndex = 0;
    int[] bpsId = new int[3];
    private Paint fingerPaint;
    private Path fingerPath;
    private int centerY;
    private int topPointY;
    private float topPointX;

    Point topPoint = new Point(0, 0);
    Point topPointApart = new Point(0, 0);
    Point bottomPoint = new Point(0, 0);
    Point bottomPointApart = new Point(0, 0);

    //上半部分
    private Point point1 = new Point(0, 0);
    private Point point2 = new Point(0, 0);
    private Point point3 = new Point(0, 0);
    //最右边
    private Point point4 = new Point(0, 0);
    //下半部分
    private Point point5 = new Point(0, 0);
    private Point point6 = new Point(0, 0);
    private Point point7 = new Point(0, 0);
    private int tuchuX = 60;
    private int rectWidth = 0;
    private int diyBaisaiersimple = 30;//瞎设置的贝塞尔曲线弯曲度
    private List<Bitmap> mData;
    private boolean fromLeft = true;
    private boolean fromRight = true;

    public SmoothPagingView(Context context) {
        super(context);
        init(context, null);
    }

    public SmoothPagingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SmoothPagingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        setWillNotDraw(false);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SmoothPagingView);
        bpsId[0] = typedArray.getResourceId(R.styleable.SmoothPagingView_img1, R.drawable.ic_launcher_background);
        bpsId[1] = typedArray.getResourceId(R.styleable.SmoothPagingView_img2, R.drawable.ic_launcher_background);
        bpsId[2] = typedArray.getResourceId(R.styleable.SmoothPagingView_img3, R.drawable.ic_launcher_background);
        typedArray.recycle();

        fingerPaint = new Paint();
        fingerPaint.setAntiAlias(true);
        fingerPaint.setColor(getResources().getColor(R.color.half_trans));
        fingerPaint.setStyle(Paint.Style.FILL);

        fingerPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerY = h / 2;//定义屏幕中心Y值
        topPointY = centerY;//贝塞尔曲线顶点Y坐标
        mData = new ArrayList<>();
        mData.add(BitmapFactory.decodeResource(getResources(), bpsId[0]));
        mData.add(BitmapFactory.decodeResource(getResources(), bpsId[1]));
        mData.add(BitmapFactory.decodeResource(getResources(), bpsId[2]));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画里层的图片
        Drawable mDrawableInner = getResources().getDrawable(bpsId[2]);
        mDrawableInner.setBounds(0, 0, getWidth(), getHeight());
        mDrawableInner.draw(canvas);


        if (fromLeft) {
            topPoint.set(0, 0);
            topPointApart.set(rectWidth, 0);
            bottomPoint.set(rectWidth, centerY * 2);
            bottomPointApart.set(0, centerY * 2);

            /**
             * dragHeight=dragWidth∗1.5  ，说明案例https://juejin.im/post/6873653741627637774
             */
            point1.x = rectWidth;
            point1.y = (int) (topPointY - (topPointX - rectWidth) * 1.5f);
            Log.e("fromLeft:point1.y", "" + point1.y);
            point2.x = rectWidth;
            point2.y = (topPointY - point1.y) / 2 + diyBaisaiersimple + point1.y;
            point3.x = (int) topPointX;
            point3.y = topPointY - ((topPointY - point1.y) / 2 + diyBaisaiersimple);
            point4.x = (int) topPointX;
            point4.y = topPointY;
            point5.x = (int) topPointX;
            point5.y = topPointY + (topPointY - point1.y) / 2 + diyBaisaiersimple;
            point6.x = rectWidth;
            point6.y = topPointY + (topPointY - point1.y) / 2 - diyBaisaiersimple;
            point7.x = rectWidth;
            point7.y = topPointY + topPointY - point1.y;

        } else if (fromRight) {
            topPoint.set(getWidth(), 0);
            topPointApart.set(getWidth() - rectWidth, 0);
            bottomPoint.set(getWidth() - rectWidth, centerY * 2);
            bottomPointApart.set(getWidth(), centerY * 2);

            point1.x = getWidth() - rectWidth;
            point1.y = (int) (topPointY - ((getWidth()-topPointX) - rectWidth) * 1.5f);
            Log.e("fromRight:point1.y", "" + point1.y);
            point2.x = getWidth() - rectWidth;
            point2.y = (topPointY - point1.y) / 2 + diyBaisaiersimple + point1.y;
            point3.x = (int) topPointX;
            point3.y = topPointY - ((topPointY - point1.y) / 2 + diyBaisaiersimple);
            point4.x = (int) topPointX;
            point4.y = topPointY;
            point5.x = (int) topPointX;
            point5.y = topPointY + (topPointY - point1.y) / 2 + diyBaisaiersimple;
            point6.x = getWidth() - rectWidth;
            point6.y = topPointY + (topPointY - point1.y) / 2 - diyBaisaiersimple;
            point7.x = getWidth() - rectWidth;
            point7.y = topPointY + topPointY - point1.y;
        } else {
            point1 = new Point(0, 0);
            point2 = new Point(0, 0);
            point3 = new Point(0, 0);
            point4 = new Point(0, 0);
            point5 = new Point(0, 0);
            point6 = new Point(0, 0);
            point7 = new Point(0, 0);
            rectWidth = 0;
        }

        fingerPath.moveTo(topPoint.x, topPoint.y);
        fingerPath.lineTo(topPointApart.x, topPointApart.y);
        fingerPath.lineTo(point1.x, point1.y);
        fingerPath.cubicTo(point2.x, point2.y, point3.x, point3.y, point4.x, point4.y);
        fingerPath.cubicTo(point5.x, point5.y, point6.x, point6.y, point7.x, point7.y);
        fingerPath.lineTo(bottomPoint.x, bottomPoint.y);
        fingerPath.lineTo(bottomPointApart.x, bottomPointApart.y);
        fingerPath.close();
        canvas.drawPath(fingerPath, fingerPaint);

        canvas.clipPath(fingerPath, Region.Op.DIFFERENCE);
        Drawable mDrawable = getResources().getDrawable(bpsId[0]);
        mDrawable.setBounds(0, 0, getWidth(), getHeight());
        mDrawable.draw(canvas);

        fingerPath.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                tuchuX = 60;
                fromLeft = event.getX() < getWidth() / 4;
                fromRight = event.getX() > getWidth() * 3 / 4;
                break;
            case MotionEvent.ACTION_MOVE:
                topPointY = (int) event.getY();
                if (fromLeft) {
                    topPointX = event.getX() + tuchuX;
                    rectWidth = (int) (topPointX * 0.1f);
                } else {
                    topPointX = event.getX() - tuchuX;
                    rectWidth = (int) ((getWidth() - topPointX) * 0.1f);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                tuchuX = 0;
                ValueAnimator animator = ValueAnimator.ofFloat(1, 0);
                animator.setDuration(1000);
                animator.setInterpolator(new LinearInterpolator());
                final float originTopPointX = topPointX;
                if (fromLeft && event.getX()>getWidth()/2){
                    //向右翻页 下一页
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {

                            invalidate();
                        }
                    });
                }else if (fromRight && event.getX()<getWidth()/2){
                    //向左翻页 上一页
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {

                            invalidate();
                        }
                    });
                }else {
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            if (fromLeft) {
                                topPointX = originTopPointX * (float) animation.getAnimatedValue();
                                rectWidth = (int) (topPointX * 0.1f);
                            } else {
                                topPointX = originTopPointX + (getWidth() - originTopPointX) * (1f - (float) animation.getAnimatedValue());
                                rectWidth = (int) ((getWidth()-topPointX) * 0.1f);
                            }
                            invalidate();
                        }
                    });
                }
                animator.start();
                break;
        }
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }
}
