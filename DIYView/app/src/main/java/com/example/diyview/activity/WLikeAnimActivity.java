package com.example.diyview.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diyview.R;
import com.sackcentury.shinebuttonlib.ShineButton;

public class WLikeAnimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_wlike);
        ShineButton po_image1 = (ShineButton) findViewById(R.id.po_image1);
        po_image1.init(this);
        ShineButton po_image2 = (ShineButton) findViewById(R.id.po_image2);
        po_image2.init(this);
        ShineButton po_image3 = (ShineButton) findViewById(R.id.po_image3);
        po_image3.init(this);
    }
}
