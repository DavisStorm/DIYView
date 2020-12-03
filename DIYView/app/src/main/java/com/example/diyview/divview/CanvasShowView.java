package com.example.diyview.divview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.diyview.R;
import com.example.diyview.utils.SizeUtil;

public class CanvasShowView extends View {
    private int type = 0;
    private Paint textPaint;
    private Paint pointPaint;
    private Paint linePaint;
    private Paint rectPaint;
    private Paint circlePaint;
    private Paint ovalPaint;
    private Paint arcPaint;
    private Paint pathPaint;
    private Paint bitmapPaint;
    private float startX;
    private float startY;
    private Bitmap mBitmap;

    public CanvasShowView(Context context) {
        super(context);
    }

    public CanvasShowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasShowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        textPaint = new Paint();
        pointPaint = new Paint();
        linePaint = new Paint();
        rectPaint = new Paint();
        circlePaint = new Paint();
        ovalPaint = new Paint();
        arcPaint = new Paint();
        pathPaint = new Paint();
        bitmapPaint = new Paint();
        initDrawBitmapMesh();
    }

    //将图像分成多少格
    private int WIDTH = 200;
    private int HEIGHT = 200;
    //交点坐标的个数
    private int COUNT = (WIDTH + 1) * (HEIGHT + 1);
    //用于保存COUNT的坐标
    //x0, y0, x1, y1......
    private float[] verts = new float[COUNT * 2];
    //用于保存原始的坐标
    private float[] orig = new float[COUNT * 2];
    //作用范围半径
    private int r = 100;

    private void initDrawBitmapMesh() {
        int index = 0;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img10);
        float bmWidth = mBitmap.getWidth();
        float bmHeight = mBitmap.getHeight();

        for (int i = 0; i < HEIGHT + 1; i++) {
            float fy = bmHeight * i / HEIGHT;
            for (int j = 0; j < WIDTH + 1; j++) {
                float fx = bmWidth * j / WIDTH;
                //X轴坐标 放在偶数位
                verts[index * 2] = fx;
                orig[index * 2] = verts[index * 2];
                //Y轴坐标 放在奇数位
                verts[index * 2 + 1] = fy;
                orig[index * 2 + 1] = verts[index * 2 + 1];
                index += 1;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        switch (type) {
            case 0:
                drawText(canvas);
                break;
            case 1:
                canvas.drawARGB(255, 139, 197, 186);
                break;
            case 2:
                drawPoint(canvas);
                break;
            case 3:
                drawLine(canvas);
                break;
            case 4:
                drawRect(canvas);
                break;
            case 5:
                drawCircle(canvas);
                break;
            case 6:
                drawOval(canvas);
                break;
            case 7:
                drawArc(canvas);
                break;
            case 8:
                drawPath(canvas);
                break;
            case 9:
                drawBitmap(canvas);
                break;
            case 10:
                clipFuc(canvas);
                break;
            case 11:
                concatFuc(canvas);
                break;
            case 12:
                drawBitmapMesh(canvas);
                break;
            case 13:
                drawTextOnPath(canvas);
                break;
            case 14:
                drawTextRun(canvas);
                break;
            case 15:
                drawVertices(canvas);
                break;
        }
    }

    private void drawTextRun(Canvas canvas) {
        //isRtl：是否是从右往左绘制，true是；false：从左往右。
        Paint paint = new Paint();
        paint.setTextSize(40);
        paint.setAntiAlias(true);
        CharSequence textRun = "这是一首简单的歌这是一首简单的歌这是一首简单的歌";
        canvas.drawTextRun(textRun, 0, 10, 0,textRun.length(),200, 600, true,paint);
        Matrix matrix = new Matrix();

    }

    private void drawTextOnPath(Canvas canvas) {
        Path path = new Path();
        path.moveTo(50,50);
        path.lineTo(500, 50);
        path.lineTo(850, 500);
        path.lineTo(1000, 800);
        path.lineTo(30, 700);
        path.lineTo(70, 70);
        path.close();
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(20);
        Paint paint1 = new Paint();
        paint1.setColor(Color.GRAY);
        canvas.drawPath(path,paint1);
        canvas.drawTextOnPath("这是一首简单的歌这是一首简单的歌这是一首简单的歌",path,200,200,paint);
    }

    float[] vertsValues = new float[]{50, 50, 500, 50, 850, 500, 1000, 800, 30, 700, 70, 70};
    int[] vertsColors = new int[]{Color.BLUE, Color.RED, Color.YELLOW, Color.GRAY, Color.GREEN, Color.MAGENTA,
            0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000};
    //绘制顶点数组，解析为三角形(基于模式)
    private void drawVertices(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawVertices(Canvas.VertexMode.TRIANGLES, vertsValues.length, vertsValues, 0, null, 0, vertsColors, 0,
                null, 0, 0, paint);
    }

    private void drawDoubleRoundRect(Canvas canvas) {
        RectF rectf1 = new RectF(50, 50, 1000, 800);
        RectF rectf2 = new RectF(200, 200, 600, 400);
//        canvas.drawDoubleRoundRect(rectf1,500,400,rectf2,100,80,new Paint());    error：No virtual method drawDoubleRoundRect
    }

    private void drawBitmapMesh(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(40);
        canvas.drawText("拖动瘦脸", 500, 1000, paint);
        canvas.drawBitmapMesh(mBitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                //调用warp方法根据触摸屏事件的坐标点来扭曲verts数组
                warp(startX, startY, event.getX(), event.getY());
                break;
        }
        return true;
    }

    private void warp(float startX, float startY, float endX, float endY) {
        //计算拖动距离
        float ddPull = (endX - startX) * (endX - startX) + (endY - startY) * (endY - startY);
        float dPull = (float) Math.sqrt(ddPull);
        //文献中提到的算法，并不能很好的实现拖动距离 MC 越大变形效果越明显的功能，下面这行代码则是我对该算法的优化
        dPull = getWidth() - dPull >= 0.0001f ? getWidth() - dPull : 0.0001f;

        for (int i = 0; i < COUNT * 2; i += 2) {
            //计算每个坐标点与触摸点之间的距离
            float dx = verts[i] - startX;
            float dy = verts[i + 1] - startY;
            float dd = dx * dx + dy * dy;
            float d = (float) Math.sqrt(dd);

            //文献中提到的算法同样不能实现只有圆形选区内的图像才进行变形的功能，这里需要做一个距离的判断
            if (d < r) {
                //变形系数，扭曲度
                double e = (r * r - dd) * (r * r - dd) / ((r * r - dd + dPull * dPull) * (r * r - dd + dPull * dPull));
                double pullX = e * (endX - startX);
                double pullY = e * (endY - startY);
                verts[i] = (float) (verts[i] + pullX);
                verts[i + 1] = (float) (verts[i + 1] + pullY);
            }
        }
        invalidate();
    }

    private void concatFuc(Canvas canvas) {
        Matrix matrix = new Matrix();
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLUE);

        canvas.drawPoint(100, 200, paint);
        canvas.drawCircle(100, 200, 100, paint);

        //用黑色 绘制 放大 0.9倍
        paint.setColor(Color.BLACK);
        canvas.drawPoint(190, 190, paint);

        //使用矩阵放大一倍
        canvas.concat(matrix);
        //canvas.setMatrix(mMatrix);//setMatrix效果一样

        //按部就班
        paint.setColor(Color.RED);
        canvas.drawCircle(200, 200, 200, paint);
        canvas.drawPoint(200, 200, paint);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void clipFuc(Canvas canvas) {
        Path path1 = new Path();
        path1.moveTo(700, 50);
        path1.rLineTo(200, 0);
        path1.rLineTo(-100, 100);
        canvas.clipOutPath(path1);
        canvas.drawARGB(255, 0, 0, 255);
        RectF rectF = new RectF(50, 50, 300, 300);
        canvas.clipRect(rectF);
        canvas.drawARGB(255, 255, 0, 0);
        canvas.restore();
        Path path = new Path();
        path.moveTo(400, 50);
        path.rLineTo(200, 0);
        path.rLineTo(-100, 100);
        path.close();
        canvas.clipPath(path);
        canvas.drawARGB(255, 0, 255, 0);
        canvas.restore();
    }

    private void drawBitmap(Canvas canvas) {
        bitmapPaint.setAntiAlias(true);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img6);
        canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
        RectF rectF = new RectF(50, 1300, 500, 1600);
        canvas.drawBitmap(bitmap, null, rectF, bitmapPaint);
        RectF rectF2 = new RectF(550, 1300, 1040, 1600);
        Rect rectFSrc = new Rect(50, 50, 500, 500);
        canvas.drawBitmap(bitmap, rectFSrc, rectF2, bitmapPaint);
        bitmapPaint = new Paint();
//        canvas.clipPath();
//        canvas.clipRect();
//        canvas.clipOutPath();
//        canvas.clipOutRect();
//        canvas.concat();
//        canvas.disableZ();
//        canvas.drawBitmapMesh();
//        canvas.drawDoubleRoundRect();
//        canvas.drawPicture();
//        canvas.drawTextOnPath();
//        canvas.drawRenderNode();
//        canvas.drawTextRun();
//        canvas.drawVertices();
    }

    private void drawPath(Canvas canvas) {
        pathPaint.setAntiAlias(true);
        pathPaint.setStrokeWidth(8);
        pathPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(50, 50);
        path.lineTo(50, 100);
        path.offset(10, -10);
        path.addOval(60, 50, 120, 130, Path.Direction.CCW);
        path.lineTo(180, 90);
        path.arcTo(180, 60, 380, 120, 180, 90, true);
        path.addRect(280, 90, 380, 150, Path.Direction.CCW);
        path.lineTo(480, 110);
        RectF bounds = new RectF();
        path.computeBounds(bounds, true);//计算path整体的左上右下，
        Log.e("drawPath: ", "bounds.left:" + bounds.left + " bounds.top:" + bounds.top + " bounds.right:" + bounds.right + " bounds.bottom:" + bounds.bottom);
//        path.transform();//Transform the points in this path by matrix.
        path.quadTo(480, 180, 680, 180);//二阶贝塞尔
        path.addCircle(680, 180, 10, Path.Direction.CCW);
        path.cubicTo(900, 180, 760, 280, 980, 280);//三阶贝塞尔
//        path.rLineTo();    //Same as LineTo,just the coordinates are considered relative to the current point on this contour
//        path.rCubicTo();   //Same as cubicTo,just the coordinates are considered relative to the current point on this contour
//        path.rQuadTo();    //Same as quadTo,just the coordinates are considered relative to the current point on this contour
        canvas.drawPath(path, pathPaint);

        pathPaint.setStyle(Paint.Style.FILL);
        Path pathUNION = new Path();
        pathUNION.addRect(350, 120, 450, 200, Path.Direction.CCW);
        path.op(pathUNION, Path.Op.UNION);
        path.offset(0, 1000);
        pathUNION.offset(0, 1000);
        canvas.drawPath(path, pathPaint);
        pathPaint = new Paint();
    }

    private void drawArc(Canvas canvas) {
        arcPaint.setAntiAlias(true);
        int centerX = 500;
        int centerY = 900;
        canvas.drawLine(centerX, 300, centerX, 1500, arcPaint);
        canvas.drawLine(0, centerY, 1000, centerY, arcPaint);//模拟（500，900）坐标系查看Arc效果
        canvas.drawArc(centerX + 50, centerY + 50, centerX + 500, centerY + 200, 0, 90, true, arcPaint);
        canvas.drawArc(centerX - 450, centerY + 50, centerX - 50, centerY + 200, 90, 90, false, arcPaint);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(8);
        canvas.drawArc(centerX - 450, centerY - 200, centerX - 50, centerY - 50, 180, 90, true, arcPaint);
        canvas.drawArc(centerX + 50, centerY - 200, centerX + 450, centerY - 50, 180, 90, false, arcPaint);
        arcPaint = new Paint();
    }

    private void drawOval(Canvas canvas) {
        canvas.drawOval(50, 50, 600, 200, ovalPaint);
        ovalPaint.setStyle(Paint.Style.STROKE);
        ovalPaint.setStrokeWidth(20);
        canvas.drawOval(50, 400, 600, 800, ovalPaint);
        ovalPaint = new Paint();
    }

    private void drawCircle(Canvas canvas) {
        circlePaint.setAntiAlias(true);
        canvas.drawCircle(250, 250, 200, circlePaint);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(10);
        canvas.drawCircle(250, 750, 200, circlePaint);
        circlePaint = new Paint();
    }

    private void drawRect(Canvas canvas) {
        canvas.drawRect(50, 50, 500, 200, rectPaint);

        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(50);
        rectPaint.setAntiAlias(true);
        canvas.drawRect(50, 250, 500, 400, rectPaint);

        RectF rectF2 = new RectF(600, 500, 1050, 650);
        canvas.drawRoundRect(rectF2, 50, 50, rectPaint);

        RectF rectF = new RectF(50, 500, 500, 650);
        canvas.drawRoundRect(rectF, 50, 500, rectPaint);

        rectPaint = new Paint();
    }

    private void drawLine(Canvas canvas) {
        canvas.drawLine(50, 50, 500, 200, linePaint);
        linePaint.setAntiAlias(true);
        canvas.drawLine(50, 100, 500, 250, linePaint);

        linePaint.setStrokeWidth(100);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setFakeBoldText(true);
        linePaint.setUnderlineText(true);
        canvas.drawLine(400, 400, 800, 800, linePaint);

        float[] floats = {200f, 500f, 400f, 500f, 600f, 500f, 800f, 700f};//[x0 y0 x1 y1 x2 y2 ...]
        canvas.drawLines(floats, 1, 4, linePaint);//只画了线 ...500f,400f,500f,600f...
        linePaint = new Paint();
    }

    private void drawPoint(Canvas canvas) {
        pointPaint.setColor(ContextCompat.getColor(getContext(), R.color.red));

        canvas.save();
        canvas.translate(-10, -10);

        canvas.save();//锁画布(为了保存之前的画布状态)
        canvas.translate(100, 100);//把当前画布的原点移动距离(100,100),后面的操作都以控件坐标(90,90)作为canvas的坐标点(0,0)，两点重合
        pointPaint.setStrokeWidth(20); //StrokeWidth笔划宽度，设置线宽，在其他地方StrokeWidth也可能叫边框宽度，我想实际上和这里原理是一样的吧
        canvas.drawPoint(0, 0, pointPaint);
        canvas.drawPoint(-50, -50, pointPaint);
        canvas.restore();//把当前画布返回（调整）到上一个save()状态之后

        canvas.translate(100, 100);
        pointPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(50, 100, pointPaint);

        pointPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(100, 100, pointPaint);

        pointPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(300, 100, pointPaint);

        pointPaint.setStrokeCap(Paint.Cap.ROUND);
        pointPaint.setStrokeWidth(100);
        canvas.drawLine(150, 150, 250, 150, pointPaint);
        pointPaint.setAntiAlias(true);
        canvas.drawLine(150, 250, 250, 250, pointPaint);

        pointPaint = new Paint();

    }


    private void drawText(Canvas canvas) {
        textPaint.setTextSize(SizeUtil.dp2px(20, getContext()));
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.purple));
        textPaint.setAntiAlias(true);
        canvas.drawText(new char[]{"绘".charAt(0), "几".charAt(0), "字".charAt(0), "符".charAt(0), "吗".charAt(0)}, 0, 4,
                SizeUtil.dp2px(10, getContext()), SizeUtil.dp2px(20, getContext()), textPaint);

//        canvas.translate(0,DensityUtil.dp2px(40,getContext()));//移动后canvas画布连同坐标一起移动了
        canvas.drawText("绘2字符吗", 1, 3, 0, SizeUtil.dp2px(40, getContext()), textPaint);

//        canvas.save();
//        canvas.translate(0,DensityUtil.dp2px(60,getContext()));
        canvas.drawText("绘3字符吗", 1, 4, 0, SizeUtil.dp2px(60, getContext()), textPaint);

        canvas.drawLine(300, SizeUtil.dp2px(90, getContext()), 300, SizeUtil.dp2px(120, getContext()), textPaint);
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.orange));
        textPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("字符串左对齐", 300, SizeUtil.dp2px(90, getContext()), textPaint);
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("字符串居中对齐", 300, SizeUtil.dp2px(110, getContext()), textPaint);
        textPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("字符串右对齐", 300, SizeUtil.dp2px(130, getContext()), textPaint);
        textPaint.setTextAlign(Paint.Align.LEFT);

        textPaint.setUnderlineText(true);
        canvas.drawText("下划线字符串", 300, SizeUtil.dp2px(150, getContext()), textPaint);
        textPaint.setUnderlineText(false);

        //这里canvas.save();和canvas.restore();是两个相互匹配出现的，作用是用来保存画布的状态和取出保存的状态的，成对出现,成对出现，成对出现
        canvas.save();
        textPaint.setFakeBoldText(true);
        canvas.translate(300, SizeUtil.dp2px(150, getContext()));
        canvas.drawText("粗体字符串", 0, SizeUtil.dp2px(20, getContext()), textPaint);
        canvas.restore();
        textPaint.setFakeBoldText(false);

        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.green));
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, textPaint);
        textPaint.setTextSize(SizeUtil.dp2px(13, getContext()));
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.red));
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("paint.getFontMetrics().descent即是g下半部分的高度", getWidth() / 2, getHeight() / 2 - 100, textPaint);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.blue));
        textPaint.setTextSize(SizeUtil.dp2px(17, getContext()));
        canvas.drawText("ggg(默认)", 0, getHeight() / 2, textPaint);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        textPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("ggg(减去fontMetrics.descent)", getWidth(), getHeight() / 2 - fontMetrics.descent, textPaint);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextAlign(Paint.Align.LEFT);

        canvas.save();
        canvas.drawCircle(500, getHeight() / 2 + 450, 10, textPaint);
        canvas.rotate(20, 500, getHeight() / 2 + 450);
        canvas.drawText("画布绕绘制起点位置旋转20度", 500, getHeight() / 2 + 450, textPaint);
        canvas.restore();
        canvas.save();
        canvas.rotate(20, 800, getHeight() / 2 + 450);
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.red));
        textPaint.setFakeBoldText(true);
        canvas.drawText("画布绕文本中间位置旋转20度", 500, getHeight() / 2 + 450, textPaint);
        canvas.restore();
        textPaint = new Paint();//求简便

    }

    public void refresh(int position) {
        type = position;
        invalidate();
    }
}
