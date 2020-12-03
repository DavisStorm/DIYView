package com.example.diyview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.diyview.R;
import com.example.diyview.divview.StatisticsView;

public class StatisticsViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_view);
        StatisticsView view = findViewById(R.id.statisticsView);
        view.setBottomStr(new String[]{"星期一","星期二","星期三","星期四","星期五","星期六","星期天"});
        view.setValues(new float[]{10f,90f,33f,66f,42f,99f,0f});
    }
}
