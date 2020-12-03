package com.example.diyview.divview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.diyview.R;

import java.util.Arrays;

/**
 * 在自定义 View 控件时随处可见 Matrix 的身影，主要用于坐标转换映射，我们可以通过 Matrix 矩阵来控制视图的变换   代码来自：https://www.jianshu.com/p/6cd77d511510
 */
public class MatrixShowView extends View {
    private int type = 0;
    private Paint textPaint;
    private int radius;
    private float[] point;
    private int centerLocation = 3;
    private RectF rectFExample;

    public MatrixShowView(Context context) {
        super(context);
    }

    public MatrixShowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MatrixShowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        textPaint = new Paint();
        matrixExample = new Matrix();
        point = new float[]{600, 600};
        radius = 300;
        rectFExample = new RectF(point[0] - radius, point[1] - radius, point[0] + radius, point[1] + radius);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        switch (type) {
            case 0:
                /*  1.将原点平移到该点。
                    2.做缩放、错切、旋转操作。
                    3.原点平移到原来的原点处。
                * */
                setXXX(canvas);
                break;
            case 1:
                preXXX(canvas);
                break;
            case 2:
                postXXX(canvas);
                break;
            case 3:
                setContact(canvas);
                break;
            case 4:
                mapRadius(canvas);
                break;
            case 5:
                mapPoint(canvas);
                break;
            case 6:
                example(canvas);
                break;
            case 7:
                mapVectors(canvas);
                break;
            case 8:
                mapRect(canvas);
                break;
        }
    }

    private Matrix matrixExample;
    private float tranExample = 0;
    private float scaleExample = 0;
    private float rotateExample = 0;
    private float skewExample = 0;

    private void example(Canvas canvas) {
        matrixExample.reset();
        rectFExample.set(point[0] - radius, point[1] - radius, point[0] + radius, point[1] + radius);
        float currentX = point[0];
        float currentY = point[1];
        switch (centerLocation) {
            case 1:
                currentX = 0;
                currentY = 0;
                break;
            case 2:
                currentX = point[0] - radius;
                currentY = point[1] - radius;
                break;
            case 3:
                currentX = point[0];
                currentY = point[1];
                break;
        }
        //先计算平移，在缩放，再错切，在旋转
        matrixExample.setTranslate(tranExample, 0);
        currentX += tranExample;
        matrixExample.postScale(1 + scaleExample, 1 + scaleExample, currentX, currentY);
        matrixExample.postSkew( skewExample, skewExample, currentX, currentY);
        matrixExample.postRotate(rotateExample,currentX, currentY);
        matrixExample.mapRect(rectFExample);
        textPaint.setColor(Color.BLUE);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(20);
        canvas.drawRect(rectFExample, textPaint);
    }

    private void mapVectors(Canvas canvas) {
        float[] vector = {2, 3};
        float[] point = {2, 3};
        Matrix matrixTranslate = new Matrix();
        matrixTranslate.setTranslate(2, 3);
        matrixTranslate.mapVectors(vector);
        matrixTranslate.mapPoints(point);

        // 输出：vector = [2.0,3.0]
        System.out.println("vector = " + Arrays.toString(vector));
        // 输出：point = [4.0,6.0]

    }

    /**
     * 做相反的运算。得到变化前的状态。例：图形旋转一定角度后再恢复旋转前的状态。matrixOri.invert(matrixInvert)方法可翻译为：
     * 将matrixOri这个矩阵反转后存放在matrixInvert这个矩阵中， matrixInvert这个矩阵中原来的值将被覆盖。
     */
    private void invert(Canvas canvas) {
        // ==========================================
        // 移动
        // ==========================================
        // [1.0, 0.0, Δx]            [1.0, 0.0, -Δx]
        // [0.0, 1.0, Δy]  invert -> [0.0, 1.0, -Δy]
        // [0.0, 0.0, 1.0]           [0.0, 0.0, 1.0]

        Matrix matrixTrans = new Matrix();
        matrixTrans.setTranslate(2, 3);

        // [1.0, 0.0, 2.0]
        // [0.0, 1.0, 3.0]
        // [0.0, 0.0, 1.0]
        System.out.println("matrixTrans = " + matrixTrans);

        matrixTrans.invert(matrixTrans);

        // [1.0, 0.0, -2.0]
        // [0.0, 1.0, -3.0]
        // [0.0, 0.0,  1.0]
        System.out.println("matrixTrans = " + matrixTrans);

        // ==========================================
        // 缩放
        // ==========================================
        // [sx,   0,  -px]             [1/sx,    0,  px/2]
        // [0,   sy,  -py]  invert ->  [0,    1/sy,  py/2]
        // [0.0, 0.0, 1.0]             [0.0,   0.0,   1.0]

        Matrix matrixScale = new Matrix();
        matrixScale.setScale(2, 2, 12, 7);

        // [2.0, 0.0, -12.0]
        // [0.0, 2.0, -7.0]
        // [0.0, 0.0, 1.0]
        System.out.println("matrixScale = " + matrixScale);

        matrixScale.invert(matrixScale);

        // [0.5, 0.0, 6.0]
        // [0.0, 0.5, 3.5]
        // [0.0, 0.0, 1.0]
        System.out.println("matrixScale = " + matrixScale);
    }

    /**
     * 判断一个矩阵是否为单位矩阵
     */
    private void isIdentity(Canvas canvas) {
        Matrix matrix = new Matrix();

        // 输出：matrix is identity:true
        System.out.println("matrix is identity:" + matrix.isIdentity());

        matrix.setTranslate(1, 2);

        // 输出：matrix is identity:false
        System.out.println("matrix is identity:" + matrix.isIdentity());
    }

    /**
     * 根据src坐标到dst坐标的变换关系，生成对应的Matrix矩阵。
     */
    private void setPolyToPoly(Canvas canvas) {
        // ==================================================================================================
        // setPolyToPoly(float[] src,     变换前的点数组，内容为[x0, y0, x1, y1, ...]
        //               int srcIndex,    第一个变化的点在src数组中的下标
        //               float[] dst,     src变换后的点数组，内容为[x0‘, y0’, x1’, y1’, ...]，与src数组一一对应
        //               int dstIndex,    变化后的第一个点在dst数组中存储的位置
        //               int pointCount   一次一共需要变换多少个点，取值范围[0,4]
        //               )
        // ==================================================================================================

        float[] src = {1, 2};
        float[] dst = {2, 4};

        //          [1.0, 0.0, 0.0]
        // matrix = [0.0, 1.0, 0.0]
        //          [0.0, 0.0, 1.0]
        Matrix matrix = new Matrix();
        System.out.println("matrix = " + matrix.toShortString());

        matrix.setPolyToPoly(src, 0, dst, 0, 1);

        //          [1.0, 0.0, 1.0]
        // matrix = [0.0, 1.0, 2.0]
        //          [0.0, 0.0, 1.0]
        System.out.println("matrix = " + matrix.toShortString());

        // 验证这个生成的matrix是否正确
        // [1.0, 0.0, 1.0]   [1]   [2.0]
        // [0.0, 1.0, 2.0] * [2] = [4.0]
        // [0.0, 0.0, 1.0]   [1]   [1.0]
    }

    private void setRectToRect(Canvas canvas) {
        Matrix matrix = new Matrix();
        RectF rectFSrc = new RectF(100, 100, 200, 400);
        RectF rectFDst = new RectF(100, 100, 400, 200);
        matrix.setRectToRect(rectFSrc, rectFDst, Matrix.ScaleToFit.FILL);
    }

    private void mapRect(Canvas canvas) {
        // ============================================
        // mapRect(RectF rect)
        // ============================================
        // 结果存放在rect中，原rect将被覆盖
        RectF rectF = new RectF(100, 100, 200, 200);
        // 输出：rectFbefore = RectF(100.0, 100.0, 200.0, 200.0)
        System.out.println("rectFbefore = " + rectF);
        Matrix matrixRectF = new Matrix();
        matrixRectF.setScale(2, 2);
        matrixRectF.mapRect(rectF);
        // 输出：rectFafter = RectF(200.0, 200.0, 400.0, 400.0)
        System.out.println("rectFafter = " + rectF);

        // ============================================
        // mapRect(RectF dst,RectF src)
        // ============================================
        // 结果存放在dst中，原src会保留。其它与mapRect(RectF rect)方法相同
    }

    private void mapPoint(Canvas canvas) {
        // =======================
        // mapPoints(float[] pts)
        // =======================
        // 运算后的结果会保存在pts数组中，原pts数组中的内容会被覆盖

        // 1.《点的移动》,对于任意点(Xn,Yn),x轴方向平移dx,y轴方向平移dy后有：
        //  Xn = Xn + dx
        //  Yn = Yn + dy
        float[] ptsTrans = {6, 2};
        Matrix matrixTrans = new Matrix();
        matrixTrans.setTranslate(-2, 2);
        matrixTrans.mapPoints(ptsTrans);
        // 输出：trans=[4.0, 4.0]
        System.out.println("trans=" + Arrays.toString(ptsTrans));

        // 2.《点的放大》，对于任意点(Xn,Yn),绕点(px,py)x轴、y轴方向分别放大sx倍、sy倍后，有：
        //  Xn = Xn * sx + (px - px * sx)
        //  Yn = Yn * sy + (py - sy * py)
        float[] ptsScale = {2, 3};
        Matrix matrixScale = new Matrix();
        matrixScale.setScale(3, 6, 2, 2);
        matrixScale.mapPoints(ptsScale);
        // 输出：scale=[2.0, 8.0]
        System.out.println("scale=" + Arrays.toString(ptsScale));

        // 3.《点的旋转》，对于任意点(Xn,Yn),绕点(px,py)旋转a度后，有：
        //  Xn = (Xn - px) * cos(a) - (Yn - py) * sin(a) + px
        //  Yn = (Xn - px) * sin(a) + (Yn - py) * cos(a) + py
        float[] ptsRotate = {6, 6};
        Matrix matrixRotate = new Matrix();
        matrixRotate.preRotate(90, 2, 3);
        matrixRotate.mapPoints(ptsRotate);
        // 输出：rotate=[-1.0,7.0]
        System.out.println("rotate=" + Arrays.toString(ptsRotate));

        // 4.《点的错切》,对于任意点(Xn,Yn),绕点(px,py)x轴、y轴方向分别错切kx、ky后，有：
        //  Xn = Xn + kx(Yn - py)
        //  Yn = Yn + ky(Xn - px)
        float[] ptsSkew = {300, 200};
        canvas.drawCircle(ptsSkew[0], ptsSkew[1], 100, textPaint);
        Matrix matrixSkew = new Matrix();
        matrixSkew.setSkew(100, 100, 290, 190);
        matrixSkew.mapPoints(ptsSkew);
        // 输出：skew=[-9.0,-7.0]
        System.out.println("skew=" + Arrays.toString(ptsSkew));
        canvas.drawCircle(ptsSkew[0], ptsSkew[1], 100, textPaint);

        // ===================================
        // mapPoints(float[] dst, float[] src)
        // ===================================
        // 运算后的结果保存在dst数组中，原src数组中的内容会保留

        float[] src = {2, 3, 3, 3};
        float[] dst = new float[src.length];
        Matrix matrixDstSrc = new Matrix();
        matrixDstSrc.setTranslate(2, 3);
        matrixDstSrc.mapPoints(dst, src);

        // 输出：dst=[4.0,6.0,5.0,6.0]
        System.out.println("dst=" + Arrays.toString(dst));
        // 输出：src=[2.0,3.0,3.0,3.0]
        System.out.println("src=" + Arrays.toString(src));

        // ==============================================================================
        // mapPoints(float[] dst,   ---- 计算结果存放数组
        //           int dstIndex,  ---- dst数组存放计算结果时起始下标
        //           float[] src,   ---- 计算的源数组
        //           int srcIndex,  ---- 源数组计算时起始下标
        //           int pointCount ---- 从起始下标开始一共要计算多少个点
        //           )
        // ==============================================================================
        // 运算后的结果保存在dst数组中

        float[] src1 = {2, 3, 3, 3, 2, 3};
        float[] dst1 = new float[]{6, 6, 6, 6, 6, 6};
        Matrix matrixDstSrc1 = new Matrix();
        matrixDstSrc1.setTranslate(1, 1);

        // 1）从src1下标为2的位置开始计算，计算1个点，注意，是一个点，不是一个长度；计算的结果只保存计算的点，未计算的点将舍弃，即结果为：[4.0,4.0]
        // 2）将src1计算后的结果，从dst1下标为2的位置开始放置
        // 注意，从存放数组开始的位置存放计算结果时，如果长度不够，将抛出 ArrayIndexOutOfBoundsException 异常
//        matrixDstSrc1.mapPoints(dst1,5,src1,2,1);

        // 输出：dst=[0.0,0.0,2.0,3.0,4.0,4.0]
        System.out.println("dst1=" + Arrays.toString(dst1));
        // 输出：src=[2.0,3.0,3.0,3.0,2.0,3.0]
        System.out.println("src1=" + Arrays.toString(src1));
    }

    /**
     * mapRadius/mapPoints/mapRect/mapVectors    可翻译为将矩阵映射到（作用于）点、矩形、半径、向量
     */
    private void mapRadius(Canvas canvas) {
// 一个半径为100.0f的圆，放大1倍后，半径也将增大一倍。据说用在画布中的圆随画布大小变化时
        float radius = 100.0f;
        canvas.drawCircle(200, 200, radius, textPaint);
        float radiusAfterMatrix;
        Matrix matrixRadius = new Matrix();
        matrixRadius.setScale(2, 2);
        radiusAfterMatrix = matrixRadius.mapRadius(radius);
        // 输出：radius=200.0
        System.out.println("radius=" + radiusAfterMatrix);
        canvas.drawCircle(600, 600, radiusAfterMatrix, textPaint);
    }

    private void setContact(Canvas canvas) {
        // [1.0, 0.0, 0.0]
        // [0.0, 1.0, 0.0]
        // [0.0, 0.0, 1.0]
        Matrix matrix = new Matrix();
        Matrix matrix1 = new Matrix();
        Matrix matrix2 = new Matrix();

        // [1.0, 0.0, 0.0]    [2.0, 3.0, 4.0]
        // [0.0, 1.0, 0.0] -> [2.0, 0.0, 0.0]
        // [0.0, 0.0, 1.0]    [1.0, 1.0, 1.0]
        matrix1.setValues(new float[]{2.0f, 3.0f, 4.0f,
                2.0f, 0.0f, 0.0f,
                1.0f, 1.0f, 1.0f});

        // [1.0, 0.0, 0.0]    [2.0, 5.0, 4.0]
        // [0.0, 1.0, 0.0] -> [3.0, 0.0, 0.0]
        // [0.0, 0.0, 1.0]    [1.0, 2.0, 1.0]
        matrix2.setValues(new float[]{2.0f, 5.0f, 4.0f,
                3.0f, 0.0f, 0.0f,
                1.0f, 2.0f, 1.0f});

        // [2.0, 3.0, 4.0]            [2.0, 5.0, 4.0]            [17.0, 18.0, 12.0]
        // [2.0, 2.0, 0.0](matrix1) * [3.0, 0.0, 0.0](matrix2) = [4.0,  10.0, 8.0 ] (matrix)
        // [1.0, 1.0, 1.0]            [1.0, 2.0, 1.0]            [6.0,  7.0,  5.0 ]
        matrix.setConcat(matrix1, matrix2);
    }

    /**
     * 不会重置Matrix，相当于当前操作矩阵(A)右乘参数矩阵(B)，即BA，例：
     */
    private void postXXX(Canvas canvas) {
        // [1.0, 0.0, 0.0]
        // [0.0, 1.0, 0.0]
        // [0.0, 0.0, 1.0]
        Matrix matrix = new Matrix();

        // [1.0, 0.0, 0.0]    [2.0, 3.0, 4.0]
        // [0.0, 1.0, 0.0] -> [2.0, 0.0, 0.0]
        // [0.0, 0.0, 1.0]    [1.0, 1.0, 1.0]
        matrix.setValues(new float[]{2.0f, 3.0f, 4.0f,
                2.0f, 0.0f, 0.0f,
                1.0f, 1.0f, 1.0f});

        // [2.0, 0.0, 0.0]   [2.0, 3.0, 4.0]            [4.0, 6.0, 8.0]
        // [0.0, 2.0, 0.0] * [2.0, 0.0, 0.0] (matrix) = [4.0, 0.0, 0.0](matrix)
        // [0.0, 0.0, 1.0]   [1.0, 1.0, 1.0]            [1.0, 1.0, 1.0]
        matrix.postScale(2, 2);
    }

    /**
     * 不会重置Matrix，相当于当前操作矩阵(A)左乘参数矩阵(B)，即AB。例：
     * pre和post的区别：
     * pre XXX：左乘：是当前矩阵左乘Matrix所构造的变换矩阵： M` = M*A
     * postXXX：右乘：是当前矩阵右乘Matrix所构造的变换矩阵：M` = A*M
     * matrix0.postTranslate(100f, 100f);//上一步所得矩阵M`,右乘所创建的一个向右、向下平移各100px的矩阵A  M``= A*M`
     * matrix0.preTranslate(-100f, -100f);//上一步所得矩阵M``,左乘所创建的一个向左、向上各平移-100px的矩阵B M```=M`*B
     */
    private void preXXX(Canvas canvas) {
        // [1.0, 0.0, 0.0]
        // [0.0, 1.0, 0.0]
        // [0.0, 0.0, 1.0]
        Matrix matrix = new Matrix();

        // [1.0, 0.0, 0.0]    [2.0, 3.0, 4.0]
        // [0.0, 1.0, 0.0] -> [2.0, 0.0, 0.0]
        // [0.0, 0.0, 1.0]    [1.0, 1.0, 1.0]
        matrix.setValues(new float[]{2.0f, 3.0f, 4.0f,
                2.0f, 0.0f, 0.0f,
                1.0f, 1.0f, 1.0f});

        // [2.0, 3.0, 4.0]           [2.0, 0.0, 0.0]   [4.0, 6.0, 4.0]
        // [2.0, 0.0, 0.0](matrix) * [0.0, 2.0, 0.0] = [4.0, 0.0, 0.0](matrix)
        // [1.0, 1.0, 1.0]           [0.0, 0.0, 1.0]   [2.0, 2.0, 1.0]
        matrix.preScale(2, 2);
    }

    /**
     * 首先会将该Matrix重置为单位矩阵，即相当于首先会调用reset()方法，然后再设置该Matrix中对应功能的值
     */
    private void setXXX(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(35);
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        Matrix matrix = new Matrix();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.matrix);
        canvas.drawBitmap(bitmap, null, new RectF(0, 0, getWidth(), 300), paint);
        canvas.drawText("matrix.tostring(): " + matrix.toShortString(), 50, 350, paint);
        canvas.drawText("scale是缩放，skew是错切，trans是平移，persp代表透视 ", 50, 1350, paint);

        RectF rectF = new RectF(50, 400, 250, 600);
        paint.setColor(Color.BLUE);
        canvas.drawRect(rectF, paint);

        //首先会将该Matrix重置为单位矩阵，即相当于首先会调用reset()方法，然后再设置该Matrix中对应功能的值
        // [1.0, 0.0, 0.0]
        // [0.0, 1.0, 0.0]
        // [0.0, 0.0, 1.0]
        Matrix matrix2 = new Matrix();
        // [1.0, 0.0, 0.0]    [2.0, 3.0, 4.0]
        // [0.0, 1.0, 0.0] -> [2.0, 0.0, 0.0]
        // [0.0, 0.0, 1.0]    [1.0, 1.0, 1.0]
        matrix2.setValues(new float[]{2.0f, 3.0f, 4.0f,
                2.0f, 0.0f, 0.0f,
                1.0f, 1.0f, 1.0f});

        // [2.0, 3.0, 4.0]    [1.0, 0.0, 0.0]    [2.0, 0.0, 0.0]
        // [2.0, 2.0, 0.0] -> [0.0, 1.0, 0.0] -> [0.0, 2.0, 0.0]
        // [1.0, 1.0, 1.0]    [0.0, 0.0, 1.0]    [0.0, 0.0, 1.0]
        matrix2.setScale(1.5f, 1.5f);
        RectF rectF1 = new RectF(300, 400, 500, 600);
        matrix2.mapRect(rectF1);
        Log.e("setXXX1: ", rectF1.toString());
        Log.e("setXXX1: ", "matrix.tostring(): " + matrix2.toShortString());
        canvas.drawRect(rectF1, paint);

        Matrix matrix3 = new Matrix();
        matrix3.setScale(1.5f, 1.5f, 100f, 100f);
        RectF rectF3 = new RectF(300, 600, 500, 800);
        matrix3.mapRect(rectF3);
        Log.e("setXXX2: ", rectF3.toString());
        Log.e("setXXX2: ", "matrix.tostring(): " + matrix3.toShortString());
        canvas.drawRect(rectF3, paint);

        Matrix matrix4 = new Matrix();
        canvas.save();
        RectF rectF4 = new RectF(50, 700, 250, 900);
        matrix4.setRotate(45, 100, 800);
        matrix4.mapRect(rectF4);
        Log.e("setXXX4: ", rectF4.toString());
        canvas.drawRect(rectF4, paint);
        canvas.restore();
    }

    public void refresh(int type) {
        this.type = type;
        invalidate();
    }

    public void setCenterLocation(int centerLocation) {
        this.centerLocation = centerLocation;
    }

    public void setTranExample(float tranExample) {
        this.tranExample = tranExample;
        invalidate();
    }

    public void setScaleExample(float scaleExample) {
        this.scaleExample = scaleExample;
        invalidate();
    }

    public void setRotateExample(float rotateExample) {
        this.rotateExample = rotateExample;
        invalidate();
    }

    public void setSkewExample(float skewExample) {
        this.skewExample = skewExample;
        invalidate();
    }

}
