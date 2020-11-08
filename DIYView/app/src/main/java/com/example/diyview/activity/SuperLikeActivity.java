package com.example.diyview.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diyview.R;
import com.sum.slike.BitmapProvider;
import com.sum.slike.SuperLikeLayout;

import java.util.Timer;
import java.util.TimerTask;

public class SuperLikeActivity extends AppCompatActivity {

    private SuperLikeLayout superLikeLayout;
    private Timer tikAnim;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_like);

        superLikeLayout = findViewById(R.id.sl_like);
//        BitmapProvider.Provider provider = new BitmapProvider.Builder(this).build();
//        superLikeLayout.setProvider(provider);
//        superLikeLayout.launch(500, 500);


        BitmapProvider.Provider provider = new BitmapProvider.Builder(this)
                .setDrawableArray(new int[]{R.mipmap.emoji_1, R.mipmap.emoji_2, R.mipmap.emoji_3, R.mipmap.emoji_4, R.mipmap.emoji_5, R.mipmap.emoji_6,
                        R.mipmap.emoji_7, R.mipmap.emoji_8})
                .setNumberDrawableArray(new int[]{R.mipmap.multi_digg_num_0, R.mipmap.multi_digg_num_1, R.mipmap.multi_digg_num_2, R.mipmap.multi_digg_num_3,
                        R.mipmap.multi_digg_num_4, R.mipmap.multi_digg_num_5, R.mipmap.multi_digg_num_6, R.mipmap.multi_digg_num_7,
                        R.mipmap.multi_digg_num_8,})
                .setLevelDrawableArray(new int[]{R.mipmap.multi_digg_word_level_1, R.mipmap.multi_digg_word_level_2, R.mipmap.multi_digg_word_level_3})
                .build();
        superLikeLayout.setProvider(provider);
//        superLikeLayout.launch(500, 500);
        superLikeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        tikAnim = new Timer("superLikeLayout");
                        tikAnim.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Log.e("ACTION_DOWN run: ", "run");
                                superLikeLayout.launch(500, 500);
                            }
                        }, 1);
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e("ACTION_DOWN stop: ", "stop");
                        tikAnim.cancel();
                        break;
                }
                return true;
            }
        });

    }
}
