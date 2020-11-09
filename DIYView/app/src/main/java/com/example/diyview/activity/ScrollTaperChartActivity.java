package com.example.diyview.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.doyou.cv.widget.taperchart.HorTaperChart;
import com.example.diyview.R;

import java.util.ArrayList;
import java.util.Random;

public class ScrollTaperChartActivity extends WithBackPressBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taperchart_scroll);
//        HorTaperChart hor_tchart = findViewById(R.id.hor_tchart);
//        hor_tchart.offSetXy(48f);
//
//        ArrayList<String> keys = new ArrayList<>();
//        for (int i = 0; i < 12; i++) {
//            keys.add("第"+(i + 1)+"次");
//        }
//
//        Random random = new Random();
//        ArrayList<Float> values = new ArrayList<>();
//        for (int i = 0; i < 12; i++) {
//            values.add(random.nextInt(100)*1f);
//        }
//        hor_tchart.setData(keys, values);
    }
}
