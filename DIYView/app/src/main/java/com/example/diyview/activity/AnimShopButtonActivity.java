package com.example.diyview.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diyview.R;
import com.example.diyview.view.AnimShopButtonMine;

public class AnimShopButtonActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopbtn_anim);
        AnimShopButtonMine viewById = findViewById(R.id.mine_btn);
    }

}
