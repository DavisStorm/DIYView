package com.example.diyview.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diyview.R;

public class LightingActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private ImageView iv;
    private SeekBar sb_rotate;
    private SeekBar sb_saturation;
    private SeekBar sb_scale;
    private Bitmap bitmap;

    private float hue;
    private float saturation;
    private float lum;
    private final float MID_VALUE = 100.0F;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lighting);
        initView();
    }

    private void initView() {
//        iv = (ImageView) findViewById(R.id.iv_lighting_activity);
//        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.text);
//        iv.setImageBitmap(bitmap);
//        sb_rotate = (SeekBar) findViewById(R.id.sb_rotate_lighting_activity);
//        sb_saturation = (SeekBar) findViewById(R.id.sb_saturation_lighting_activity);
//        sb_scale = (SeekBar) findViewById(R.id.sb_scale_lighting_activity);

        sb_scale.setOnSeekBarChangeListener(this);
        sb_saturation.setOnSeekBarChangeListener(this);
        sb_rotate.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
//            case R.id.sb_rotate_lighting_activity:
//                hue = (progress - MID_VALUE) * 1.0f/MID_VALUE * 180;
//                break;
//            case R.id.sb_saturation_lighting_activity:
//                saturation = progress * 1.0f / MID_VALUE;
//                break;
//            case R.id.sb_scale_lighting_activity:
//                lum = progress * 1.0F / MID_VALUE;
//                break;
        }
        iv.setImageBitmap(handleImageEffect(bitmap,hue,saturation,lum));
    }

    private Bitmap handleImageEffect(Bitmap bitmap, float hue, float saturation, float lum) {
        Bitmap b = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(b);
        Paint paint = new Paint();
        //色调
        ColorMatrix rotateMatrix = new ColorMatrix();
        rotateMatrix.setRotate(0,hue);
        rotateMatrix.setRotate(1,hue);
        rotateMatrix.setRotate(2,hue);
        //饱和度
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);
        //亮度
        ColorMatrix scaleMatrix = new ColorMatrix();
        scaleMatrix.setScale(lum,lum,lum,1);


        ColorMatrix imgMatrix = new ColorMatrix();
        imgMatrix.postConcat(rotateMatrix);
        imgMatrix.postConcat(saturationMatrix);
        imgMatrix.postConcat(scaleMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imgMatrix));
        canvas.drawBitmap(bitmap,0,0,paint);
        return b;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}
