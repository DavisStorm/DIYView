package com.example.diyview.divview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.diyview.R;

/**
 * 一行3张图片，有n行的图片查看控件
 */
public class ImageWatcher extends View {

    private int[] mData;
    private int imgNum;
    private int maxLines;
    private float dividerWidth;
    private float imgItemW;
    private Rect[][] rect;
    private Bitmap[][] bitmap;
    private int lines;
    private int defaultSize = 600;


    public ImageWatcher(Context context) {
        super(context);
        init(context, null);
    }

    public ImageWatcher(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ImageWatcher(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageWatcher);
        maxLines = typedArray.getInteger(R.styleable.ImageWatcher_maxLines, 1);
        dividerWidth = typedArray.getDimension(R.styleable.ImageWatcher_dividerWidth, 10);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        if (wMode == MeasureSpec.AT_MOST && hMode == MeasureSpec.AT_MOST){
            wSize = defaultSize;
            hSize = defaultSize;
        }else if (wMode == MeasureSpec.EXACTLY && hMode == MeasureSpec.EXACTLY){
            wSize = wSize > hSize ? hSize : wSize;
            hSize = wSize;
        }else if (wMode == MeasureSpec.EXACTLY){
            hSize= wSize;
        }else if (hSize == MeasureSpec.EXACTLY){
           wSize =  hSize;
        }
//        int newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(wSize,MeasureSpec.EXACTLY);  ViewGroup才需要mode
//        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(hSize,MeasureSpec.EXACTLY);
        setMeasuredDimension(wSize,hSize);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mData == null || mData.length == 0) return;
        imgNum = mData.length;
        imgItemW = (getWidth() - dividerWidth * 2) / 3;
        buildParams();
    }

    private void buildParams() {
        lines = 0;
        int lastlinenum = imgNum % 3;
        if (lastlinenum != 0) {
            lines = (int) imgNum / 3 > maxLines - 1 ? maxLines : ((int) (imgNum / 3) + 1);
        } else {
            lines = imgNum / 3 > maxLines ? maxLines : imgNum / 3;
        }
        rect = new Rect[lines][3];
        bitmap = new Bitmap[lines][3];
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < 3; j++) {
                Rect rectitem = new Rect();
                rectitem.set((int) (dividerWidth * j + imgItemW * j), (int) (dividerWidth * i + imgItemW * i),
                        (int) (dividerWidth * j + imgItemW * (j + 1)), (int) (dividerWidth * i + imgItemW * (i + 1)));
                rect[i][j] = rectitem;
                bitmap[i][j] = BitmapFactory.decodeResource(getResources(), mData[i * 3 + j]);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < 3; j++) {
                canvas.drawBitmap(bitmap[i][j], null, rect[i][j], new Paint());
                canvas.save();
            }
        }
    }

    public void setImageDrawables(int[] resIds) {
        if (resIds == null || resIds.length == 0) return;
        mData = resIds;
//        buildParams();
        invalidate();
    }
}
