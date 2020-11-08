package com.example.diyview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.diyview.R;

public class MyDragImageView extends View {

    private Drawable mDrawable;

    public MyDragImageView(Context context) {
        super(context);
    }

    public MyDragImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.MyDragImageView, 0, 0);
        mDrawable = getResources().getDrawable(typedArray.getResourceId(R.styleable.MyDragImageView_mydrawable,-1));
        typedArray.recycle();

    }

    public MyDragImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//        MeasureSpec.makeMeasureSpec()
//        setMeasuredDimension();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mDrawable.draw(canvas);
//        canvas.drawbit
    }
}
