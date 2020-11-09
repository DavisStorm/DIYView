package com.example.diyview.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diyview.R;
import com.example.diyview.view.SectionProBarMine;

public class SectionProMineBarActivity extends AppCompatActivity {

    private SeekBar normalSb;
    private SeekBar gradientSb;
    private SectionProBarMine normalSpb;
    private SectionProBarMine gradientSpb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_promine);
        normalSb = findViewById(R.id.normalSb);
        normalSpb = findViewById(R.id.normalSpb);
        normalSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                normalSpb.setProgress((int) (progress / 100f * normalSpb.getMax()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        gradientSb = findViewById(R.id.gradientSb);
        gradientSpb = findViewById(R.id.gradientSpb);
        gradientSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gradientSpb.setProgress((int) (progress / 100f * gradientSpb.getMax()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}
