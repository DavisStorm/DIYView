package com.example.diyview.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.doyou.cv.widget.taperchart.TaperChart;
import com.doyou.cv.widget.taperchart.TaperChartLayout;
import com.example.diyview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaperChartActivity extends AppCompatActivity {

    private TextView label5;
    private TaperChartLayout tchartLayout;
    private Button contrastBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taperchart);
        tchartLayout = findViewById(R.id.tchartLayout);
        label5 = findViewById(R.id.label5);
        contrastBtn = findViewById(R.id.contrastBtn);

        syncTaperChartData(1);
        syncTaperChartData(2);
        syncTaperChartData(3);
        syncTaperChartData(4);
        syncTaperChartLayoutData();
        syncTaperChartLayoutDataDwColor();
        syncTaperChartLayoutDataDwColorCompar();


        final boolean[] isTrue = {true};
        label5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTrue[0]) {
                    isTrue[0] = false;
                    tchartLayout.setEmpty();
                } else {
                    isTrue[0] = true;
                    syncTaperChartLayoutData();
                }
            }
        });
        contrastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(this, ContrastActivity::class.java))
            }
        });
    }

    private void syncTaperChartData(int type) {

        switch (type) {
            case 1:
                TaperChart tchartV = findViewById(R.id.tchartV);
                tchartV.isShowDebugView(false);
                tchartV.setTaperColors(Color.GREEN, Color.GRAY, Color.RED);

                List<String> keys = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    keys.add("第" + (i + 1) + "次");
                }

                List<Float> values = new ArrayList<>();
                Random random = new Random();
                for (int i = 0; i < 3; i++) {
                    values.add((i + 1) * random.nextInt(100) * 1.0f);
                }
                tchartV.setData(keys, values);
                break;
            case 2:
                TaperChart tchartV1 = findViewById(R.id.tchartV1);
                tchartV1.offSetXy(96f);
                tchartV1.isShowDebugView(true);
                tchartV1.setHintIsRatio(true);

                List<String> keys1 = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    keys1.add("第" + (i + 1) + "次");
                }

                Random random1 = new Random();
                List<Float> values1 = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    values1.add((i + 1) * random1.nextInt(100) * 1f);
                }

                String[] labels = new String[]{"男性", "女性"};
                tchartV1.setData(keys1, values1, labels);
                break;
            case 3:
                TaperChart tchartV2 = findViewById(R.id.tchartV2);
                tchartV2.offSetXy(48f);
                tchartV2.isShowDebugView(true);

                List<String> keys2 = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    keys2.add("第" + (i + 1) + "次");
                }

                Random random2 = new Random();
                List<Float> values2 = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    values2.add((i + 1) * random2.nextFloat());
                }

                String[] labels2 = new String[]{"男性", "女性"};
                tchartV2.setData(keys2, values2, labels2);
             break;
            case 4:
                TaperChart tchartV3 = findViewById(R.id.tchartV3);
                tchartV3.offSetXy(48f);
                tchartV3.isShowDebugView(true);
                tchartV3.setTaperColors(Color.GREEN, Color.CYAN);

                List<String> keys3 = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    keys3.add("第" + (i + 1) + "次");
                }

                Random random3 = new Random();
                List<Float> values3 = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    values3.add((i + 1) * random3.nextInt(100)*1.0f);
                }
                String[] labels3 = new String[]{"30min以内", "30-45min", "45min以上"};
                tchartV3.setData(keys3, values3, labels3);
            break;
        }
    }

    private void syncTaperChartLayoutData() {
        tchartLayout.getChart().setDrawTopValue(true);

        List<String> keys = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            keys.add("第"+(i + 1)+"次");
        }
        labels.add("( <30分 )");
        labels.add("( 30分-45分 )");
        labels.add("( >45分 )");

        List<Float> values = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            values.add((i + 1) * random.nextInt(100)*1f);
        }

        tchartLayout.setData(keys, values, labels);
    }

    private void syncTaperChartLayoutDataDwColor(){
        TaperChartLayout tchartLayout1 = findViewById(R.id.tchartLayout1);
        tchartLayout1.setColors(Color.GREEN,Color.LTGRAY,Color.RED);
        List<String> keys = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            keys.add("第"+(i + 1)+"次");
        }
        labels.add("( <30分 )");
        labels.add("( 30分-45分 )");
        labels.add("( >45分 )");

        List<Float> values = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            values.add((i + 1) * random.nextInt(100)*1f);
        }
        String[] btmLabels = new String[]{"第一次","第二次","第三次"};
        tchartLayout1.setData(keys, values, btmLabels,"次",labels,false);
    }

    /**
     * 峰值图对比
     */
    private void syncTaperChartLayoutDataDwColorCompar() {
        TaperChartLayout tchartLayout2 = findViewById(R.id.tchartLayout2);
        tchartLayout2.getChart().setTcMode(TaperChart.Mode.Fifth.ordinal());
        tchartLayout2.getChart().offSetXy(48f);
        tchartLayout2.getChart().isShowDebugView(false);
        tchartLayout2.getChart().setDrawTopValue(true);
        tchartLayout2.setColors(Color.GREEN, Color.BLUE);

        List<String> keys = new ArrayList<>();
        List<Float> values = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            values.add((i + 1) * random.nextInt(100)*1f);
        }
        String[] labels = new String[]{"30min以内", "30-45min", "45min以上"};

        List<String> btmLabels = new ArrayList<>();

        btmLabels.add("( <30分 )");
        btmLabels.add("( 30分-45分 )");
        btmLabels.add("( >45分 )");

        tchartLayout2.setData(keys, values, labels, "次", btmLabels,true);
//        tchartLayout2.setData(keys, values)
    }
}
