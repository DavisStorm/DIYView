package com.example.diyview.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.doyou.cv.widget.progress.horbar.SectionProBar;
import com.example.diyview.R;

public class SectionProBarActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SectionProBar normalSpb;
    private SectionProBar gradientSpb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_probar);

        SeekBar normalSb =findViewById(R.id.normalSb);
        SeekBar gradientSb =findViewById(R.id.gradientSb);
        Button btn_gotomine =findViewById(R.id.btn_gotomine);
        normalSb.setOnSeekBarChangeListener(this);
        gradientSb.setOnSeekBarChangeListener(this);

        normalSpb = findViewById(R.id.normalSpb);
        normalSpb.setProgress(0); // 注意：不要忘记设置xml中的style，不然进度没有效果

        gradientSpb = findViewById(R.id.gradientSpb);
        gradientSpb.setGradientBgColor(Color.rgb(15, 252, 255), Color.rgb(0, 150, 255));
        gradientSpb.setGradientProColor(Color.rgb(255, 104, 83), Color.rgb(100, 122, 219));
        gradientSpb.setProgress(0);

        final SectionProBar animSpb=findViewById(R.id.animSpb);
        animSpb.setGradientBgColor(Color.rgb(15, 252, 255), Color.rgb(0, 150, 255));
        animSpb.setGradientProColor(Color.rgb(255, 104, 83), Color.rgb(100, 122, 219));
        animSpb.setProgressAnim(80f);

        Button animBtn=findViewById(R.id.animBtn);
        animBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animSpb.setProgressAnim(80f);
            }
        });
        btn_gotomine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SectionProBarActivity.this,SectionProMineBarActivity.class));
            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.normalSb:
                normalSpb.setProgress(progress);
                break;
            case R.id.gradientSb:
                gradientSpb.setProgress(progress);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) { }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) { }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
