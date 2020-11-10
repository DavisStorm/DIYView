package com.example.diyview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import com.example.diyview.R;

/**
*@author lenovo
*@date 2020/11/10 16:51
 * contain the anim when the products are added in the shop car, and the width and the height must be the size of screen
*/
public class AddingShoppingCarAnimContainer extends FrameLayout {

    private int addCarAnimDuration = 1000;//ms
    private float animationProgress = 0;
    private ValueAnimator addCarAnimator;
    private Paint paint;
    private int STATUS = 0;//0正在动画 1完成后状态

    public AddingShoppingCarAnimContainer(Context context) {
        super(context);
        initAnim(context, null);
    }

    public AddingShoppingCarAnimContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAnim(context, attrs);
    }

    public AddingShoppingCarAnimContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAnim(context, attrs);
    }

    private void initAnim(Context context, AttributeSet attrs) {
        setWillNotDraw(false);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AddingShoppingCarAnimContainer);
        addCarAnimDuration = typedArray.getInteger(R.styleable.AddingShoppingCarAnimContainer_addCarAnimDuration, addCarAnimDuration);
        typedArray.recycle();

        startPoint.set(0,0);
        endPoint.set(0,0);
        currentPoint.set(0,0);

        addCarAnimator = ValueAnimator.ofFloat(0, 1);
        addCarAnimator.setDuration(addCarAnimDuration);
        addCarAnimator.setInterpolator(new LinearInterpolator());
        addCarAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animationProgress = (float) animation.getAnimatedValue();
                currentPoint.set((int) (startPoint.x+(endPoint.x-startPoint.x)*animationProgress),(int)(startPoint.y+(endPoint.y-startPoint.y)*animationProgress));
                if (animationProgress==1) STATUS=1;
                invalidate();
            }
        });

        paint = new Paint();
        paint.setAntiAlias(true);
    }

    private Bitmap mBitmap;
    private Point startPoint = new Point();
    private Point endPoint = new Point();
    private Point currentPoint = new Point();

    public void pickeProducts(Bitmap bitmap, Point startPoint, Point endPoint) {
        this.mBitmap = bitmap;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        STATUS=0;
        addCarAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap!=null && STATUS==0){
            int width = mBitmap.getWidth();
            int height = mBitmap.getHeight();
            canvas.drawBitmap(mBitmap,currentPoint.x-width/2,currentPoint.y-height/2,paint);
        }
    }
}
