package com.example.diyview.activity;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;

import com.example.diyview.R;
import com.sdsmdg.harjot.vectormaster.VectorMasterView;
import com.sdsmdg.harjot.vectormaster.models.PathModel;

/**
* 可以修改VectorDrawable
 *
 * Vector写法的基本规则    https://blog.csdn.net/scott2017/article/details/51770620
 * 基本规则
 * pathData 的指令基本都是由字母跟若干数字组成，数字之间可以用空格或者逗号隔开 (其实逗号会被忽略掉，加上逗号只是一些习惯的问题)。一般来说指令字母分为大小写两种,
 *                                                              大写的字母是基于原点的坐标系(偏移量)，即绝对位置；小写字母是基于当前点坐标系(偏移量)，即相对位置。
 *
 * 详细：
 * 1.移动    M x,y (m dx, dy) 移动虚拟画笔到对应的点，但是并不绘制。一开始的时候默认是在(0,0)。
 * 2.直线    L x,y (l dx, dy) 从当前点划一条直线到对应的点。  H x (h dx) 从当前点绘制水平线，相当于l x,0     V y (v dy) 从当前点绘制垂直线，相当于l 0,y
 * 3.闭合    Z(或z) 从结束点绘制一条直线到开始点，闭合路径    例：android:pathData="M10,10 v 5 h 5 z"
 * 4.弧线    A rx,ry x-axis-rotation large-arc-flag,sweepflag x,y    或者   a rx,ry x-axis-rotation large-arc-flag,sweepflag dx,dy
 *       rx ry 椭圆半径 x-axis-rotation x轴旋转角度 large-arc-flag 为0时表示取小弧度，1时取大弧度（要长的还是短的） sweep-flag 0取逆时针方向，1取顺时针方向  x,y (dx,dy) 终点的位置
 * 5.二阶贝塞尔曲线    Q x1,y1 x,y ( q dx1,dy1 dx,dy)   T x,y ( t dx, dy)
 * 6.三阶贝塞尔曲线    C x1,y1 x2,y2 x,y ( c dx1,dy1 dx2,dy2 dx,dy)    S x1,y1 x,y ( s dx1,dy1 dx, dy)
* */
public class VectorExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_example);
        //第一个vector改变颜色
        VectorMasterView vectormaster = findViewById(R.id.vectormaster);
        PathModel outline = vectormaster.getPathModelByName("outline");
        outline.setStrokeColor(Color.parseColor("#ED4337"));
        outline.setFillColor(Color.parseColor("#ED4337"));

        //第二个改变原图属性做动画
        final VectorMasterView vectormaster2 = findViewById(R.id.vectormaster2);
        final PathModel heart = vectormaster2.getPathModelByName("heart");
        heart.setStrokeColor(Color.parseColor("#00ff00"));
        heart.setStrokeWidth(1);
//        heart.setFillType(Path.FillType.EVEN_ODD);

        vectormaster2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heart.setTrimPathEnd(0f);
                ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
                animator.setDuration(3000);
                animator.setRepeatCount(-1);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        heart.setTrimPathEnd((Float) animation.getAnimatedValue());
                        vectormaster2.update();
                    }
                });
                animator.start();
            }
        });

        final VectorMasterView heartVector = findViewById(R.id.vectormaster3);
        final PathModel heart2 = heartVector.getPathModelByName("heart");
        heart2.setStrokeColor(Color.parseColor("#ED4337"));
        heartVector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValueAnimator valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), Color.WHITE, Color.parseColor("#ED4337"));
                valueAnimator.setDuration(1000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        heart2.setFillColor((Integer) valueAnimator.getAnimatedValue());
                        heartVector.update();
                    }
                });
                valueAnimator.start();
            }
        });
    }
}
