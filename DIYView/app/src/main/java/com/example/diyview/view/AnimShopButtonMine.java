package com.example.diyview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.diyview.R;
import com.example.diyview.utils.SizeUtil;

import androidx.annotation.Nullable;

/**
 * @author lenovo
 * @date 2020/11/6 15:00
 * 1.动画分为 平移 旋转 透明度
 */
public class AnimShopButtonMine extends View {

    private int mCarColor = Color.BLUE;
    private int carTextColor = Color.WHITE;
    private String carText;
    private float mCarRadius = SizeUtil.dp2px(20, getContext());
    private RectF mBtnRectF;
    private Paint mBtnPaint;
    private Paint mBtnTextPaint;
    private float mCarTextSize = SizeUtil.sp2px(10, getContext());
    private int STATUS = 0;
    private final int BEFOR_ADD = 0;
    private final int ADD_ONE = 1;
    boolean touched = false;
    private int mCenterH;
    private int mCenterW;
    private int leftBtnCircleColor = Color.GRAY;
    private int rightBtnCircleCorlo = Color.BLUE;
    private int rightBtnPlusColor = Color.BLACK;
    private Paint mLeftBtnPaint;
    private Paint mRightBtnCirclePaint;
    private Paint mRightBtnPlusPaint;
    private float plusAwayFromCircle = 0.4f;//加减符号左端距离圆占半径的百分比
    private float btnStrokeWidth = SizeUtil.dp2px(1, getContext());
    private int numTextColor = Color.BLACK;
    private float numTextSize = SizeUtil.sp2px(10, getContext());
    private Paint mNumPaint;
    private int mNum = 0;
    private int mNumMax = 1;

    public AnimShopButtonMine(Context context) {
        super(context);
        init(context, null);
    }

    public AnimShopButtonMine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public AnimShopButtonMine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnimShopButtonMine);
        mCarColor = typedArray.getColor(R.styleable.AnimShopButtonMine_carColor, mCarColor);
        carTextColor = typedArray.getColor(R.styleable.AnimShopButtonMine_carTextColor, carTextColor);
        carText = typedArray.getString(R.styleable.AnimShopButtonMine_carText);
        mCarRadius = typedArray.getDimension(R.styleable.AnimShopButtonMine_carRadius, mCarRadius);
        mCarTextSize = typedArray.getDimension(R.styleable.AnimShopButtonMine_carTextSize, mCarTextSize);
        leftBtnCircleColor = typedArray.getColor(R.styleable.AnimShopButtonMine_leftBtnCircleColor, leftBtnCircleColor);
        rightBtnCircleCorlo = typedArray.getColor(R.styleable.AnimShopButtonMine_rightBtnCircleCorlo, rightBtnCircleCorlo);
        rightBtnPlusColor = typedArray.getColor(R.styleable.AnimShopButtonMine_rightBtnPlusColor, rightBtnPlusColor);
        btnStrokeWidth = typedArray.getDimension(R.styleable.AnimShopButtonMine_btnStrokeWidth, btnStrokeWidth);
        numTextColor = typedArray.getColor(R.styleable.AnimShopButtonMine_numTextColor, numTextColor);
        numTextSize = typedArray.getDimension(R.styleable.AnimShopButtonMine_numTextSize, numTextSize);
        mNumMax = typedArray.getInteger(R.styleable.AnimShopButtonMine_numMax, mNumMax);
        typedArray.recycle();

        //购物车按钮
        mBtnRectF = new RectF(0, 0, getWidth(), getHeight());
        mBtnPaint = createAPaint(mCarColor, 0);
        //购物车按钮文字
        mBtnTextPaint = createAPaint(carTextColor, mCarTextSize);
        //左边按钮
        mLeftBtnPaint = createAPaint(leftBtnCircleColor, 0);
        //右边按钮
        mRightBtnCirclePaint = createAPaint(rightBtnCircleCorlo, 0);
        mRightBtnPlusPaint = createAPaint(rightBtnPlusColor, 0);
        //中间文字
        mNumPaint = createAPaint(numTextColor, numTextSize);
    }

    private Paint createAPaint(int color, float textSize) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(false);
        paint.setColor(color);
        if (textSize != 0) {
            paint.setTextSize(textSize);
        }
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        if (wMode == MeasureSpec.AT_MOST) {
            wSize = (int) mBtnTextPaint.measureText(carText) + getPaddingLeft() + getPaddingRight();
        }
        if (hMode == MeasureSpec.AT_MOST) {
            Paint.FontMetrics fontMetrics = mBtnTextPaint.getFontMetrics();
            hSize = (int) (fontMetrics.descent - fontMetrics.ascent) + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(wSize, hSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenterH = getHeight() / 2;
        mCenterW = getWidth() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int x = 0;
        int y = 0;
        switch (STATUS) {
            case BEFOR_ADD:
                drawShopBtn(canvas);
                //TODO 加入动画
                break;
            case ADD_ONE:
                drawLeftbtn(canvas, x, y);
                drawRightbtn(canvas, x, y);
                drawCenterNum(canvas, x, y, mNum);
                break;
            default:
                throw new NumberFormatException("num was not legal");
        }

        //写中间数字
        //画右边按钮
    }

    private void drawCenterNum(Canvas canvas, int x, int y, int num) {
        float numWidth = mNumPaint.measureText(num + "");
        Paint.FontMetrics fontMetrics = mNumPaint.getFontMetrics();
        float baseLine = (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent + mCenterH;
        canvas.drawText(num + "", mCenterW - numWidth / 2, baseLine, mNumPaint);
    }

    //画加入购物车按钮
    private void drawShopBtn(Canvas canvas) {
        mBtnRectF.set(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(mBtnRectF, mCarRadius, mCarRadius, mBtnPaint);
        Paint.FontMetrics fontMetrics = mBtnTextPaint.getFontMetrics();
        float baseline = (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent
                + (getHeight() - getPaddingBottom() - getPaddingTop()) / 2 + getPaddingTop();
        canvas.drawText(carText, getPaddingLeft(), baseline, mBtnTextPaint);
    }

    //画左边按钮
    private void drawLeftbtn(Canvas canvas, int x, int y) {
        mLeftBtnPaint.setStyle(Paint.Style.STROKE);
        mLeftBtnPaint.setStrokeWidth(btnStrokeWidth);
        float leftCircleRadius = mCenterH - btnStrokeWidth / 2;
        canvas.drawCircle(mCenterH, mCenterH, leftCircleRadius, mLeftBtnPaint);
        canvas.drawLine(leftCircleRadius * plusAwayFromCircle + btnStrokeWidth / 2, mCenterH,
                mCenterH * 2 - leftCircleRadius * plusAwayFromCircle - btnStrokeWidth / 2, mCenterH, mLeftBtnPaint);
    }

    //画右边按钮
    private void drawRightbtn(Canvas canvas, int x, int y) {
        float rightCircleRadius = mCenterH - btnStrokeWidth / 2;
        mRightBtnPlusPaint.setStrokeWidth(btnStrokeWidth);
        canvas.drawCircle(getWidth() - mCenterH, mCenterH, rightCircleRadius, mRightBtnCirclePaint);
        //划横线
        canvas.drawLine(mCenterW * 2 - mCenterH - rightCircleRadius * (1 - plusAwayFromCircle), mCenterH,
                mCenterW * 2 - btnStrokeWidth / 2 - rightCircleRadius * plusAwayFromCircle, mCenterH, mRightBtnPlusPaint);
        //画竖线
        canvas.drawLine(mCenterW * 2 - mCenterH, btnStrokeWidth / 2 + rightCircleRadius * plusAwayFromCircle,
                mCenterW * 2 - mCenterH, mCenterH + rightCircleRadius * (1 - plusAwayFromCircle), mRightBtnPlusPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touched = true;
                break;
            case MotionEvent.ACTION_UP:
                if (touched) {
                    touched = false;
                    switch (STATUS) {
                        case BEFOR_ADD:
                            STATUS = ADD_ONE;
                            mNum = 1;
                            invalidate();
                            break;
                        case ADD_ONE:
                            if(getX()<mCenterH*2+getPaddingLeft()) {
                                //减号处弹起
                                if(mNum>0) {
                                    mNum--;
                                    invalidate();
                                }else if(mNum==0) {
                                    STATUS = BEFOR_ADD;
                                    invalidate();
                                }else {
                                    throw new NumberFormatException("num was not legal");
                                }
                            }else if(getX()>mCenterH*2) {
                                //加号处弹起
                                if (mNum < mNumMax) {
                                    mNum++;
                                    invalidate();
                                }else if(mNum == mNumMax) {
                                    Toast.makeText(getContext(), "最大数量为" + mNum, Toast.LENGTH_SHORT).show();
                                }else {
                                    throw new NumberFormatException("num was not legal");
                                }
                            }

                            break;
                    }
                }
                break;
        }
        return true;
    }
}
