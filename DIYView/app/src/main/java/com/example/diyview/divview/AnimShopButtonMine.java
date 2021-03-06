package com.example.diyview.divview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
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
    private float plusAwayFromCircle = 0.4f;//加减符号左端距离圆的长度占半径的百分比
    private float btnStrokeWidth = SizeUtil.dp2px(1, getContext());
    private int numTextColor = Color.BLACK;
    private float numTextSize = SizeUtil.sp2px(10, getContext());
    private Paint mNumPaint;
    private int mNum = 0;
    private int mNumMax = 1;
    private int carshowDuration = 1000;//加入购物车按钮显示合隐藏动画耗时1000ms
    private float carBtnProcess = 0f;
    private int arBtnAlpha = 255;
    private ValueAnimator carBtnGrowAnimator;
    private ValueAnimator carBtnNarrowAnimator;
    private ValueAnimator carBtnNarrowAlphaAnimator;
    private ValueAnimator carBtnGrowAlphaAnimator;
    private int btnLeftShowDuration = 1000;//减号按钮显示合隐藏动画耗时1000ms
    private ValueAnimator btnLeftShowAnimator;
    private float btnLeftShowPro;
    private ValueAnimator btnLeftHideAnimator;
    private PlusOneAnimLisener mPlusLisener;

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
        carshowDuration = typedArray.getInteger(R.styleable.AnimShopButtonMine_carshowDuration, carshowDuration);
        btnLeftShowDuration = typedArray.getInteger(R.styleable.AnimShopButtonMine_btnLeftShowDuration, btnLeftShowDuration);
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

        //初始化动画
        initAnimation();
    }

    private void initAnimation() {
        carBtnGrowAnimator = ValueAnimator.ofFloat(1, 0);
        carBtnGrowAnimator.setDuration(carshowDuration);
        carBtnGrowAnimator.setInterpolator(new LinearInterpolator());
        carBtnGrowAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                carBtnProcess = (float) animation.getAnimatedValue();
                invalidate();
                if (carBtnProcess == 0) {
                    carBtnGrowAlphaAnimator.start();
                }
            }
        });

        carBtnGrowAlphaAnimator = ValueAnimator.ofInt(0, 255);
        carBtnGrowAlphaAnimator.setDuration(200);
        carBtnGrowAlphaAnimator.setInterpolator(new LinearInterpolator());
        carBtnGrowAlphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                arBtnAlpha = (int) animation.getAnimatedValue();
                invalidate();
            }
        });

        carBtnNarrowAnimator = ValueAnimator.ofFloat(0, 1);
        carBtnNarrowAnimator.setDuration(carshowDuration);
        carBtnNarrowAnimator.setInterpolator(new LinearInterpolator());
        carBtnNarrowAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                carBtnProcess = (float) animation.getAnimatedValue();
                invalidate();
                if (carBtnProcess == 1) {
                    STATUS = ADD_ONE;
                    mNum = 1;
                    numChanged(mNum,true);
                    btnLeftShowAnimator.start();
                }
            }
        });
        carBtnNarrowAlphaAnimator = ValueAnimator.ofInt(255, 0);
        carBtnNarrowAlphaAnimator.setDuration(200);
        carBtnNarrowAlphaAnimator.setInterpolator(new LinearInterpolator());
        carBtnNarrowAlphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                arBtnAlpha = (int) animation.getAnimatedValue();
                if (arBtnAlpha == 0) {
                    carBtnNarrowAnimator.start();
                }
                invalidate();
            }
        });
        btnLeftShowAnimator = ValueAnimator.ofFloat(1, 0);
        btnLeftShowAnimator.setDuration(btnLeftShowDuration);
        btnLeftShowAnimator.setInterpolator(new LinearInterpolator());
        btnLeftShowAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                btnLeftShowPro = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        btnLeftHideAnimator = ValueAnimator.ofFloat(0, 1);
        btnLeftHideAnimator.setDuration(btnLeftShowDuration);
        btnLeftHideAnimator.setInterpolator(new LinearInterpolator());
        btnLeftHideAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                btnLeftShowPro = (float) animation.getAnimatedValue();
                invalidate();
                if (btnLeftShowPro == 1) {
                    STATUS = BEFOR_ADD;
                    mNum--;
                    numChanged(mNum,false);
                    carBtnGrowAnimator.start();
                    invalidate();
                }
            }
        });
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
                break;
            case ADD_ONE:
                drawLeftbtn(canvas);
                drawRightbtn(canvas);
                drawCenterNum(canvas, mNum);
                break;
            default:
                throw new NumberFormatException("num was not legal");
        }
    }

    private void drawCenterNum(Canvas canvas, int num) {
        float numWidth = mNumPaint.measureText(num + "");
        Paint.FontMetrics fontMetrics = mNumPaint.getFontMetrics();
//        float baseLine = (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent + mCenterH;
        canvas.save();
        canvas.translate(mCenterW + (mCenterW - mCenterH) * btnLeftShowPro, mCenterH);
        canvas.rotate(360 * btnLeftShowPro);
        canvas.drawText(num + "", 0 - numWidth / 2, (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent, mNumPaint);
        canvas.restore();
    }

    //画加入购物车按钮
    private void drawShopBtn(Canvas canvas) {
        mBtnRectF.set(carBtnProcess * getWidth(), 0, getWidth(), getHeight());
        canvas.drawRoundRect(mBtnRectF, mCarRadius, mCarRadius, mBtnPaint);
        Paint.FontMetrics fontMetrics = mBtnTextPaint.getFontMetrics();
        float baseline = (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent
                + (getHeight() - getPaddingBottom() - getPaddingTop()) / 2 + getPaddingTop();
        mBtnTextPaint.setAlpha(arBtnAlpha);
        canvas.drawText(carText, getPaddingLeft(), baseline, mBtnTextPaint);
    }

    //画左边按钮
    private void drawLeftbtn(Canvas canvas) {
        mLeftBtnPaint.setStyle(Paint.Style.STROKE);
        mLeftBtnPaint.setStrokeWidth(btnStrokeWidth);
        float leftCircleRadius = mCenterH - btnStrokeWidth / 2;
        float centx = mCenterH + (mCenterW * 2 - mCenterH * 2) * btnLeftShowPro;
        canvas.save();
        canvas.translate(centx, mCenterH);
        canvas.rotate(360 * btnLeftShowPro);
        canvas.drawCircle(0, 0, leftCircleRadius, mLeftBtnPaint);
        canvas.drawLine(0 - (1 - plusAwayFromCircle) * leftCircleRadius, 0,
                (1 - plusAwayFromCircle) * leftCircleRadius, 0, mLeftBtnPaint);
        canvas.restore();
    }

    //画右边按钮
    private void drawRightbtn(Canvas canvas) {
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
                            carBtnNarrowAlphaAnimator.start();
                            break;
                        case ADD_ONE:
                            if (event.getX() < mCenterH * 2 + btnStrokeWidth) {
                                //减号处弹起
                                if (mNum > 0) {
                                    mNum--;
                                    if (mNum == 0) {
                                        mNum++;
                                        btnLeftHideAnimator.start();
                                    }else {
                                        numChanged(mNum,false);
                                    }
                                    invalidate();
                                } else {
                                    throw new NumberFormatException("num was not legal");
                                }
                            } else if (event.getX() > mCenterW * 2 - mCenterH * 2 - btnStrokeWidth) {
                                //加号处弹起
                                if (mNum < mNumMax) {
                                    mNum++;
                                    numChanged(mNum,true);
                                    invalidate();
                                } else if (mNum == mNumMax) {
                                    Toast.makeText(getContext(), "最大数量为" + mNum, Toast.LENGTH_SHORT).show();
                                } else {
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

    /**
    * num:变更后的数量;plus:是否是增加操作，getId()组件Id传出去
    * */
    private void numChanged(int num,boolean plus) {
        Toast.makeText(getContext(), num + "", Toast.LENGTH_SHORT).show();
        if (plus){
            mPlusLisener.onNumPlus(num,getId());
        }
    }
    public interface PlusOneAnimLisener{
        void onNumPlus(int num,int componentId);
    }

    public void setmPlusLisener(PlusOneAnimLisener mPlusLisener) {
        this.mPlusLisener = mPlusLisener;
    }

    public int getmNum() {
        return mNum;
    }

}
