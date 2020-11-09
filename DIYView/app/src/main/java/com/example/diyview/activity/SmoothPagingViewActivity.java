package com.example.diyview.activity;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diyview.R;
import com.example.diyview.view.RoundRecImageView;
import com.example.diyview.view.UniverseStarsView;

public class SmoothPagingViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smooth_pagin_view);
    }
}
