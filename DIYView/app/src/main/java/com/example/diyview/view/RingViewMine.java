package com.example.diyview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.doyou.cv.utils.LogUtil;
import com.doyou.cv.widget.progress.ring.RingView;
import com.example.diyview.R;
import com.example.diyview.bean.RingViewMineBean;
import com.example.diyview.utils.SizeUtil;

import java.util.ArrayList;
import java.util.List;

public class RingViewMine extends View {

    private String centerText;
    private List<RingViewMineBean> mData;
    private int centerBmpId = -1;
    private float ringRadius;
    private float ringWidth;
    private Paint ringPaint;
    private RectF ringRectF;
    private RectF centerRectF;
    private Bitmap centerBmp;
    private Paint centerBmpPaint;
    private int centerX;
    private int centexY;
    private Path circlePath;
    private PathMeasure pathMeasure;
    private float biaoxianlength;
    private Paint textPaint;
    private float name_textsize;

    public RingViewMine(Context context) {
        super(context);
        init(context, null);
    }

    public RingViewMine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RingViewMine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setWillNotDraw(false);//否则不走ondraw方法
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RingViewMine);
        centerText = typedArray.getString(R.styleable.RingViewMine_center_text);
        centerBmpId = typedArray.getResourceId(R.styleable.RingViewMine_center_bmp, -1);
        ringRadius = SizeUtil.dp2px(typedArray.getDimension(R.styleable.RingViewMine_ringRadius, 0),context);
        ringWidth = SizeUtil.dp2px(typedArray.getDimension(R.styleable.RingViewMine_ringWidth, 0),context);
        biaoxianlength =  SizeUtil.dp2px(typedArray.getDimension(R.styleable.RingViewMine_biaoxianlength, 5),context);
        name_textsize = typedArray.getDimension(R.styleable.RingViewMine_name_textsize, 10);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expectSize = (int) (ringRadius * 2 + ringWidth + getPaddingLeft() + getPaddingRight());
        int width = resolveSize(expectSize, widthMeasureSpec);
        int height = resolveSize(expectSize, heightMeasureSpec);
        expectSize = Math.min(width, height);

        LogUtil.logD("201810301418", "onMeasure-->expectSize = " + expectSize + "->width = " + width + "->height = " + height
                + "->widthMeasureSpec = " + widthMeasureSpec + "->heightMeasureSpec = " + heightMeasureSpec);

        ringRadius = (expectSize - getPaddingTop() - getPaddingBottom() - ringWidth) / 2;
        setMeasuredDimension(width, expectSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ringPaint = new Paint();
        ringPaint.setAntiAlias(true);
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setStrokeWidth(ringWidth);

        centerX = getMeasuredWidth() / 2;
        float left = centerX - ringRadius;
        centexY = getMeasuredHeight() / 2 + (getPaddingTop() - getPaddingBottom()) / 2;
        float top = centexY - ringRadius;
        float right = centerX + ringRadius;
        float bottom = centexY + ringRadius;
        ringRectF = new RectF(left, top, right, bottom);

        mData = createMockData1();
        if (centerBmpId != -1) {
            centerBmp = BitmapFactory.decodeResource(getResources(), centerBmpId);
            centerRectF = new RectF(centerX - centerBmp.getWidth() / 2, centexY - centerBmp.getHeight() / 2,
                    centerX + centerBmp.getWidth() / 2, centexY + centerBmp.getHeight() / 2);
            centerBmpPaint = new Paint();
        }
        //用于计算圆上点的坐标
        circlePath = new Path();
        circlePath.addCircle(centerX, centexY, ringRadius, Path.Direction.CCW);
        pathMeasure = new PathMeasure(circlePath, true);
        //计算每段中点距离起始点的长度
        float circleWholelength = (float) (2 * Math.PI * ringRadius);
        float startPercent = 0;
        float[] pos;
        for (int i = 0; i < mData.size(); i++) {
            int percent = mData.get(i).getPercent();
            float length = circleWholelength * (startPercent + percent / 2f) / 360f;
            mData.get(i).setLength(length);
            //length用于计算中点坐标
            pos = new float[2];
            pathMeasure.getPosTan(circleWholelength-length, pos, new float[2]);
            mData.get(i).setCenterLocation(pos);
            startPercent += percent;
        }

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(name_textsize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mData == null || mData.size() == 0) {
            return;
        }
        int startAngel = 0;
        for (RingViewMineBean bean : mData) {
            ringPaint.setColor(bean.getTypeColor());
            canvas.drawArc(ringRectF, startAngel, bean.getPercent(), false, ringPaint);
            startAngel += bean.getPercent();
        }
        if (centerBmpId != -1) {
            canvas.drawBitmap(centerBmp, null, centerRectF, centerBmpPaint);
        }
//        画名称、占比文字和水平线段
        for (int i = 0; i < mData.size(); i++) {
            RingViewMineBean bean = mData.get(i);
            float[] location = bean.getCenterLocation();
            /*          计算文字的基线所在y坐标
                Paint.FontMetricsfm=mPaint.getFontMetrics();//通过文字的Metrix拿到ascent和descent计算
                基线到0的距离=  getHeight/2+(matrix.descent-matrix.ascent)-matrix.descent   //注意fontMetrics.ascent是负值
            * */
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            float textBaseLineY = bean.getCenterLocation()[1] + (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent;
            //三元运算符判断点在左边还是右边
            float textToRight = location[0] + biaoxianlength;
            float textToLeft = location[0] - biaoxianlength;
            canvas.drawLine(location[0], location[1], location[0] > centerX ? textToRight : textToLeft, location[1], textPaint);
            float textlength = textPaint.measureText(bean.getName()+" "+(bean.getPercent()/360)+"%");
            canvas.drawText(bean.getName()+" "+(int)(bean.getPercent()*100f/360)+"%",location[0] > centerX ? location[0]+biaoxianlength : location[0]-biaoxianlength-textlength,textBaseLineY,textPaint);
        }
    }

    private List<RingViewMineBean> createMockData1() {
        List<RingViewMineBean> data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int clor = -1;
            int per = 0;
            switch (i) {
                case 0:
                    clor = Color.RED;
                    per = 30;
                    break;
                case 1:
                    clor = Color.BLUE;
                    per = 170;
                    break;
                case 2:
                    clor = Color.YELLOW;
                    per = 160;
                    break;
            }
            RingViewMineBean bean = new RingViewMineBean("第" + i + "个", per, clor);
            data.add(bean);
        }
        return data;
    }
}
