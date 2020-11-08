package com.example.diyview.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diyview.R;

public class WidgetCasesActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_menu_main);

        initListener();
    }

    private void initListener() {
        TextView selPtoBarTv = findViewById(R.id.selPtoBarTv);
        selPtoBarTv.setOnClickListener(this);
        TextView imgTxtTv = findViewById(R.id.imgTxtTv);
        imgTxtTv.setOnClickListener(this);
        TextView ringvTv = findViewById(R.id.ringvTv);
        ringvTv.setOnClickListener(this);
        TextView tcTv = findViewById(R.id.tcTv);
        tcTv.setOnClickListener(this);
        TextView htcTv = findViewById(R.id.htcTv);
        htcTv.setOnClickListener(this);
        TextView gradientTv = findViewById(R.id.gradientTv);
        gradientTv.setOnClickListener(this);
        TextView lrvTv = findViewById(R.id.lrvTv);
        lrvTv.setOnClickListener(this);
        TextView cpbvTv = findViewById(R.id.cpbvTv);
        cpbvTv.setOnClickListener(this);
        TextView spbTv = findViewById(R.id.spbTv);
        spbTv.setOnClickListener(this);
        TextView hcpvTv = findViewById(R.id.hcpvTv);
        hcpvTv.setOnClickListener(this);
        TextView matrixTv = findViewById(R.id.matrixTv);
        matrixTv.setOnClickListener(this);
        TextView gpbTv = findViewById(R.id.gpbTv);
        gpbTv.setOnClickListener(this);
        TextView hssvTv = findViewById(R.id.hssvTv);
        hssvTv.setOnClickListener(this);
        TextView horTv = findViewById(R.id.horTv);
        horTv.setOnClickListener(this);
        TextView waveTv = findViewById(R.id.waveTv);
        waveTv.setOnClickListener(this);
        TextView colorMainTv = findViewById(R.id.colorMainTv);
        colorMainTv.setOnClickListener(this);
        TextView refreshTv = findViewById(R.id.refreshTv);
        refreshTv.setOnClickListener(this);
        TextView timeRulerTv = findViewById(R.id.timeRulerTv);
        timeRulerTv.setOnClickListener(this);
        TextView circleBtnTv = findViewById(R.id.circleBtnTv);
        circleBtnTv.setOnClickListener(this);
        TextView ctmSvTv = findViewById(R.id.ctmSvTv);
        ctmSvTv.setOnClickListener(this);
        TextView vpTransTv = findViewById(R.id.vpTransTv);
        vpTransTv.setOnClickListener(this);
        TextView imgOverturnTv = findViewById(R.id.imgOverturnTv);
        imgOverturnTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selPtoBarTv:
                gotoNextPage(SectionProBarActivity.class);
                break;
            case R.id.imgTxtTv:
                gotoNextPage(ImgTxtMixtureActivity.class);
                break;
            case R.id.ringvTv:
                gotoNextPage(RingViewActivity.class);
                break;
            case R.id.tcTv:
                gotoNextPage(TaperChartActivity.class);
                break;
            case R.id.htcTv:
                gotoNextPage(ScrollTaperChartActivity.class);
                break;
            case R.id.gradientTv:
                gotoNextPage(GradientLineActivity.class);
                break;
            case R.id.lrvTv:
//                gotoNextPage(LegendRingViewActivity.class);
                break;
            case R.id.cpbvTv:
//                gotoNextPage(CircleProgressBarViewActivity.class);
                break;
            case R.id.spbTv:
//                gotoNextPage(ShadowProBarActivity.class);
                break;
            case R.id.hcpvTv:
//                gotoNextPage(HalfCircleProViewActivity.class);
                break;
            case R.id.matrixTv:
//                gotoNextPage(MatrixViewActivity.class);
                break;
            case R.id.gpbTv:
//                gotoNextPage(GradientProBarActivity.class);
                break;
            case R.id.hssvTv:
//                gotoNextPage(HorScrollSelecteViewActivity.class);
                break;
            case R.id.waveTv:
//                gotoNextPage(WaveViewActivity.class);
                break;
            case R.id.colorMainTv:
//                gotoNextPage(ColorMainActivity.class);
                break;
            case R.id.refreshTv:
//                gotoNextPage(RefreshViewActivity.class);
                break;
            case R.id.timeRulerTv:
//                gotoNextPage(TimeRulerActivity.class);
                break;
            case R.id.circleBtnTv:
//                gotoNextPage(CircleBtnActivity.class);
                break;
            case R.id.ctmSvTv:
//                gotoNextPage(CustomScrollViewActivity.class);
                break;
            case R.id.vpTransTv:
//                gotoNextPage(ViewPagerTransformerActivity.class);
                break;
            case R.id.imgOverturnTv:
//                gotoNextPage(BmpOverturnActivity.class);
                break;
        }
    }

    private void gotoNextPage(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
