package com.example.diyview.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.diyview.R;
import com.example.diyview.activity.fragment.TtileFragment;
import com.example.diyview.view.AnimShopButtonMine;
import com.example.diyview.view.GradientTextView;

public class AnimShopButtonActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopbtn_anim);
        AnimShopButtonMine mine = findViewById(R.id.mine_btn);
    }

}
