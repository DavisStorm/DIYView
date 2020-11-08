package com.example.diyview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.diyview.R;

import java.util.ArrayList;

public class WLikeAnim extends View {

    private int duration;
    private boolean animOn;
    private float animProcess = 0;
    private float STATUS;
    private float STOP = -1;
    private float STAGE_ONE = 0.2f;//中间图片变到了最大
    private float STAGE_TWO = 0.4f;//中间圆环开始放大
    private float STAGE_THREE = 0.7f;//外围星星开始闪烁
    private ValueAnimator animator;
    private Bitmap bitmap;
    private float ringWidth;
    private float ringMaxWidth;
    private float starPadding;
    private float picWidth;
    private float picHeight;
    private RectF rectF;
    private float picScaledRate;
    private Paint mPaint;
    private Paint cirlcePaint;
    private ArrayList<Point> pointList = new ArrayList<>();
    private float recF_c;
    private float smallStarRadius;
    private RectF picRectF;
    private boolean ifLike = false;
    private Paint starPaint;
    private float starDistance;
    private int starRotateDegree;
    private float bigStarRadius;
    private int smallStarRotateDegree;

    public WLikeAnim(Context context) {
        super(context);
        init(context, null);
    }

    public WLikeAnim(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public WLikeAnim(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WLikeAnim);
        duration = typedArray.getInteger(R.styleable.WLikeAnim_wlduration, 300);
        animOn = typedArray.getBoolean(R.styleable.WLikeAnim_animOn, true);
        ringWidth = typedArray.getDimension(R.styleable.WLikeAnim_ringWidth, 10);
        ringMaxWidth = typedArray.getDimension(R.styleable.WLikeAnim_ringMaxWidth, 30);
        starPadding = typedArray.getDimension(R.styleable.WLikeAnim_starPadding, 0);
        picWidth = typedArray.getDimension(R.styleable.WLikeAnim_picWidth, 10);
        picHeight = typedArray.getDimension(R.styleable.WLikeAnim_picHeight, 10);
        picScaledRate = typedArray.getFloat(R.styleable.WLikeAnim_picScaledRate, 0.3f);
        bigStarRadius = typedArray.getDimension(R.styleable.WLikeAnim_bigStarRadius, 3);
        smallStarRadius = typedArray.getDimension(R.styleable.WLikeAnim_smallStarRadius, 1);
        ifLike = typedArray.getBoolean(R.styleable.WLikeAnim_like, false);
        starRotateDegree = typedArray.getInteger(R.styleable.WLikeAnim_starRotateDegree, 0);
        smallStarRotateDegree = typedArray.getInteger(R.styleable.WLikeAnim_smallStarRotateDegree, 10);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!animator.isRunning()) {
            STATUS = STOP;
            animProcess = 0;
        }
        if (ifLike) {
            //从时间上分三阶段
            //绘手指
            float left = 0, top = 0, right = 0, bottom = 0;
            if (STATUS == STOP) {
                left = rectF.left - animProcess * picScaledRate * picWidth / 2;
                top = rectF.top - animProcess * picScaledRate * picHeight / 2;
                right = rectF.right + animProcess * picScaledRate * picWidth / 2;
                bottom = rectF.bottom + animProcess * picScaledRate * picHeight / 2;
            } else if (STATUS == STAGE_ONE) {
                left = rectF.left - (animProcess - STAGE_ONE) * picScaledRate * picWidth / 2;
                top = rectF.top - (animProcess - STAGE_ONE) * picScaledRate * picHeight / 2;
                right = rectF.right + (animProcess - STAGE_ONE) * picScaledRate * picWidth / 2;
                bottom = rectF.bottom + (animProcess - STAGE_ONE) * picScaledRate * picHeight / 2;
            } else if (STATUS == STAGE_TWO) {
                left = rectF.left;
                right = rectF.right;
                top = rectF.top;
                bottom = rectF.bottom;
                //绘圆环
                cirlcePaint.setStrokeWidth(ringWidth);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, recF_c + ringMaxWidth * animProcess, cirlcePaint);
            } else if (STATUS == STAGE_THREE) {
                left = rectF.left;
                right = rectF.right;
                top = rectF.top;
                bottom = rectF.bottom;
                if (animProcess == STAGE_THREE) {
                    starPaint.setAlpha(0);
                } else {
                    float totalTime = (1 - STAGE_THREE);
                    float timeProcess = (animProcess - STAGE_THREE) / totalTime;
                    int alphaResult = 0;
                    if (timeProcess > 0.5f) {
                        alphaResult = (int) (510 * (1 - timeProcess));
                    } else {
                        alphaResult = (int) (510 * timeProcess);
                    }
                    starPaint.setAlpha(alphaResult);
                }
                //绘外围星星,星星圆形的半径为recF_c+ringWidth+starPadding，剩下的知道角度就能确定位置，而角度是可以自己定的
                for (int i = 0; i < pointList.size(); i++) {
                    Point point = pointList.get(i);
                    canvas.drawCircle(point.x, point.y, bigStarRadius, starPaint);
                }
                canvas.save();
                canvas.rotate(smallStarRotateDegree,getWidth()/2,getHeight()/2);
                for (int i = 0; i < pointList.size(); i++) {
                    Point point = pointList.get(i);
                    canvas.drawCircle(point.x, point.y, smallStarRadius, starPaint);
                }
                canvas.save();
                canvas.rotate(0-smallStarRotateDegree,getWidth()/2,getHeight()/2);
            }
            picRectF.set(left, top, right, bottom);
            canvas.drawBitmap(bitmap, null, picRectF, mPaint);
        } else {
            canvas.drawBitmap(bitmap, null, rectF, mPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        bitmap = getBitmap(getContext(), ifLike ? R.drawable.ic_like : R.drawable.ic_unlike);
        animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(duration);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animProcess = (float) animation.getAnimatedValue();
                if (animProcess > STAGE_THREE) {
                    STATUS = STAGE_THREE;
                } else if (animProcess > STAGE_TWO) {
                    STATUS = STAGE_TWO;
                } else if (animProcess > STAGE_ONE) {
                    STATUS = STAGE_ONE;
                } else {
                    STATUS = STOP;
                }
                invalidate();
            }
        });

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        cirlcePaint = new Paint();
        cirlcePaint.setAntiAlias(true);
        cirlcePaint.setStyle(Paint.Style.STROKE);
        cirlcePaint.setColor(getResources().getColor(R.color.color_3bc805));
        starPaint = new Paint();
        starPaint.setAntiAlias(true);
        starPaint.setStyle(Paint.Style.FILL);
        starPaint.setColor(getResources().getColor(R.color.color_569ceb));
        starPaint.setAlpha(255);//0-255
        //初始化中间图片的矩形范围
        rectF = new RectF();
        rectF.left = getMeasuredWidth() / 2 - picWidth / 2;
        rectF.right = getMeasuredWidth() / 2 + picWidth / 2;
        rectF.top = getMeasuredHeight() / 2 - picHeight / 2;
        rectF.bottom = getMeasuredHeight() / 2 + picHeight / 2;
        picRectF = new RectF(this.rectF);//用于大小动态变化的中间图片
        //rectF对角线长度也是圆环的最小半径
        recF_c = this.rectF.height() / 2 / (float) Math.sin(45);
        //初始化外围8个星星圆心对象，按照从上到下顺时针的顺序初始化
        //星星圆心距离大圆心得距离
        starDistance = recF_c + ringMaxWidth + starPadding;
        //8个点，一个点相隔45角度,侧边再8个小点
        for (int i = 0; i < 8; i++) {
            Point point = new Point((int) (getMeasuredWidth() / 2 + starDistance * Math.cos((starRotateDegree + 45 * i) * Math.PI / 180)),
                    (int) (getMeasuredHeight() / 2 - starDistance * Math.sin((starRotateDegree + 45 * i) * Math.PI / 180)));
            pointList.add(point);
        }

        setOnClickListener(animOn ? new OnClickListener() {
            @Override
            public void onClick(View v) {
                ifLike = !ifLike;
                bitmap = getBitmap(getContext(), ifLike ? R.drawable.ic_like : R.drawable.ic_unlike);
                animator.start();
            }
        } : null);
    }

    /**
     * 此方法是为了解决Bitmap bmp = BitmapFactory.decodeResource(resources, R.drawable.ic_image);返回为空的异常
     * 在 5.0 以上的系统会出现空指针，原因在于此本来方法不能将 vector 转化为 bitmap ，而apk编译时为了向下兼容，会根据 vector 生产相应的 png ，
     * 而 4.4 的系统运行此代码时其实用的是 png 资源。这就是为什么 5.0 以上会报错，而 4.4 不会的原因
     */
    private static Bitmap getBitmap(Context context, int vectorDrawableId) {
        Bitmap bitmap = null;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Drawable vectorDrawable = context.getDrawable(vectorDrawableId);
            bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                    vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            vectorDrawable.draw(canvas);
        } else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), vectorDrawableId);
        }
        return bitmap;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
