package com.example.diyview.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.doyou.cv.utils.ToastUtils;
import com.doyou.cv.widget.progress.ring.RingView;
import com.example.diyview.R;
import com.example.diyview.bean.RingViewMineBean;
import com.example.diyview.view.RingViewMine;

import java.util.ArrayList;
import java.util.List;

public class RingViewActivity extends AppCompatActivity {

    private Button refeshBtn;
    private Button doubleTxtBtn;
    private RingView ringv3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringview);
        refeshBtn = findViewById(R.id.refeshBtn);
        doubleTxtBtn = findViewById(R.id.doubleTxtBtn);
        ringv3 = findViewById(R.id.ringv3);
        syncRingViewData();
        refeshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortToast(getApplicationContext(), "刷新下");
                ringv3.setCenterTxtColor(Color.rgb(255, 124, 12));
//                showRingView3();
            }
        });
        doubleTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(this,DoubleTxtActivity.class));
            }
        });
        Button legendBtn = findViewById(R.id.legendBtn);
        legendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(Intent(this,LegendActivity::class.java))
            }
        });
    }

    private void syncRingViewData() {
        RingView ringv = findViewById(R.id.ringv);
        RingView ringv1 = findViewById(R.id.ringv1);
        RingView ringv2 = findViewById(R.id.ringv2);
        ringv.showDebugView(true);
        int valueOne = 12;
        int valueTwo = 24;
        float progress = (valueOne * 1.0f / (valueOne + valueTwo)) * 100;
        ringv.setData(progress, "第一个" + ' ' + progress / 100,
                "第二个" + ' ' + (100 - progress) / 100);


        ringv1.showDebugView(true);
        float progress1 = (12 / 24f) * 100;
        ringv1.setData(progress1, "第三个" + ' ' + progress1 / 100,
                "第四个" + ' ' + (100 - progress1) / 100);
        ringv1.setListener(new RingView.OuterListener() {
            @Override
            public void onOuterClick(String label) {
                ToastUtils.showShortToast(RingViewActivity.this, label);
            }
        });
        ringv2.showDebugView(true);
    }

}
