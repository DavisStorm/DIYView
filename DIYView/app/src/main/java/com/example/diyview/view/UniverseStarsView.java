package com.example.diyview.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.example.diyview.R;
import com.example.diyview.bean.Dot;
import com.example.diyview.utils.SizeUtil;

import java.util.ArrayList;
import java.util.Random;

public class UniverseStarsView extends RelativeLayout {

    private float imgPadding;
    private float dotSize;
    private int dotColor;
    private int dotNum;
    private Paint paint;
    private float imgWidth = 100f;
    private ArrayList<Dot> mData;
    private int dotDuration;
    private ValueAnimator animator1;
    private float maxOffset;
    private Path circlePath;
    private Paint paintCircle;

    public UniverseStarsView(Context context) {
        super(context);
    }

    public UniverseStarsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public UniverseStarsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setWillNotDraw(false);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UniverseStarsView, 0, 0);
        imgPadding = typedArray.getDimension(R.styleable.UniverseStarsView_imgPadding, 0);
        dotSize = typedArray.getDimension(R.styleable.UniverseStarsView_dotSize, 1);
        dotColor = typedArray.getColor(R.styleable.UniverseStarsView_myDotColor, Color.WHITE);
        dotNum = typedArray.getInteger(R.styleable.UniverseStarsView_dotNum, 200);
        dotDuration = typedArray.getInteger(R.styleable.UniverseStarsView_dotDuration, 500);
        maxOffset = typedArray.getDimension(R.styleable.UniverseStarsView_maxOffset, 100);
        typedArray.recycle();

        paint = new Paint();
        paint.setColor(dotColor);
        paint.setAntiAlias(true);

        circlePath = new Path();

        paintCircle = new Paint();
        paintCircle.setColor(getResources().getColor(R.color.colorAccent));
        paintCircle.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() > 0) {
            View child = getChildAt(0);
            imgWidth = child.getMeasuredWidth();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mData.size(); i++) {
            Dot dot = mData.get(i);
            paint.setAlpha(dot.alpha);
            canvas.drawCircle(dot.X, dot.Y, dot.radius, paint);
        }
        canvas.drawPath(circlePath, paintCircle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mData = new ArrayList<>();

        circlePath.addCircle(getWidth() / 2, getHeight() / 2, imgWidth/2 , Path.Direction.CCW);
        PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(circlePath, false);
        float[] pos = new float[2];
        float[] tan = new float[2];
        //计算Dot可能的X坐标
        final Random random = new Random();
        for (int i = 0; i < dotNum; i++) {
            pathMeasure.getPosTan(i * 1.0f / dotNum * pathMeasure.getLength(), pos, tan);
            float X = pos[0] + random.nextInt(6) - 3f; //X值随机偏移
            float Y = pos[1] + random.nextInt(6) - 3f; //Y值随机偏移
            float speed = new Random().nextFloat() * 10 + 5; //速度在5-15像素/次之间
            double angle = Math.acos((pos[0] - getWidth() * 1f / 2) / 120f);//這個120不明白怎麼來的
            Dot dot = new Dot(X, Y, 2f, speed, dotSize, maxOffset, angle);
            mData.add(dot);
        }

        animator1 = ValueAnimator.ofFloat(0f, 1f);
        animator1.setDuration(dotDuration);
        animator1.setRepeatCount(-1);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                for (Dot dot : mData) {
//                    dot.Y += dot.speed;
                    if (dot.offset > dot.maxOffset) {
                        dot.offset = 0;
                        dot.speed = random.nextInt(10) + 5;
                    }
                    dot.alpha= (int) ((1f - dot.offset / dot.maxOffset)  * 225f);
                    dot.X = (float) (getWidth() * 1f / 2 + Math.cos(dot.angle) * (imgWidth/2 + dot.offset+SizeUtil.dp2px(imgPadding,getContext())));
                    if (dot.Y > getHeight() * 1f / 2) {
                        dot.Y = (float) (Math.sin(dot.angle) * (imgWidth/2 + dot.offset+SizeUtil.dp2px(imgPadding,getContext())) + getHeight() * 1f / 2);
                    } else {
                        dot.Y = (float) (getHeight() * 1f / 2 - Math.sin(dot.angle) * (imgWidth/2 + dot.offset+SizeUtil.dp2px(imgPadding,getContext())));
                    }
                    dot.offset += dot.speed;
                }
                invalidate();
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public void startSpead() {
        animator1.start();
    }
}
