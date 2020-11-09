package com.example.diyview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.diyview.R;
import com.example.diyview.view.RoundRecImageView;
import com.example.diyview.view.UniverseStarsView;

public class UniverseStarsViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe_stars_view);
        initView();
    }

    private void initView() {
        RoundRecImageView imgView = findViewById(R.id.centerImg);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate_img);
        imgView.setAnimation(animation);
        imgView.startAnimation(animation);
        final UniverseStarsView starsView = findViewById(R.id.universeStarsView);
        //监听starsView加载完成
        starsView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                starsView.startSpead();
            }
        });
    }
}
