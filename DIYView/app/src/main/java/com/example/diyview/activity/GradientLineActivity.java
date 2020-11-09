package com.example.diyview.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.doyou.cv.widget.GradientLine;
import com.example.diyview.R;
import com.example.diyview.view.GradientLineMine;

public class GradientLineActivity extends WithBackPressBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradientline);
        final GradientLine gLineUp = findViewById(R.id.gLineUp);
        gLineUp.postDelayed(new Runnable() {
            @Override
            public void run() {
                gLineUp.setAnim(true);
                gLineUp.setMode(GradientLine.LineMode.DOWN);
            }
        }, 500);


        final GradientLine gLineFlat = findViewById(R.id.gLineFlat);
        gLineFlat.setAnim(true);
        gLineFlat.postDelayed(new Runnable() {
            @Override
            public void run() {
                gLineFlat.setMode(GradientLine.LineMode.FLAT);
            }
        }, 500);

        final GradientLine gLineDown = findViewById(R.id.gLineDown);
        gLineDown.setAnim(true);
        gLineDown.postDelayed(new Runnable() {
            @Override
            public void run() {
                gLineDown.setMode(GradientLine.LineMode.UP);
            }
        }, 500);

        final Button gLineBtn = findViewById(R.id.gLineBtn);
        gLineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gLineUp.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gLineUp.handleStartAnim();
                    }
                }, 500);

                gLineFlat.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gLineFlat.handleStartAnim();
                    }
                }, 500);
                gLineDown.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gLineDown.handleStartAnim();
                    }
                }, 500);
            }
        });


        final GradientLine gLineAgain = findViewById(R.id.gLineAgain);
        gLineAgain.setAnim(true, 640);
        gLineAgain.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 500);
        gLineAgain.setMode(GradientLine.LineMode.FLAT);

        final boolean[] isTure = {false};
        final Button gLineBtn2 = findViewById(R.id.gLineBtn2);
        gLineBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTure[0]) {
                    isTure[0] = false;
                    gLineAgain.setMode(GradientLine.LineMode.FLAT);
                    gLineAgain.handleStartAnim();
                } else {
                    isTure[0] = true;
                    gLineAgain.setMode(GradientLine.LineMode.UP);
                    gLineAgain.handleStartAnim();
                }
            }
        });
         final GradientLineMine gLineAgainMine = findViewById(R.id.gLineAgainMine);
         Button gLineBtn3 = findViewById(R.id.gLineBtn3);
        gLineBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gLineAgainMine.startAnimation();
            }
        });
    }
}
