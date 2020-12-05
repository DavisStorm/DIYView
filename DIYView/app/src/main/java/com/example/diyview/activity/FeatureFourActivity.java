package com.example.diyview.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.diyview.R;
import com.example.diyview.utils.StatusBarUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 王阳
 * @time 2020/12/6  1:24
 * @desc
 */
public class FeatureFourActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无ActionBar 3种方式，还有个在Manifest里设置activity的theme
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_feature_four);
//        getActionBar().hide();
//        StatusBarUtils.hideStatusBar(this);
        StatusBarUtils.setStatusBarColor(this, Color.RED);
    }
}
