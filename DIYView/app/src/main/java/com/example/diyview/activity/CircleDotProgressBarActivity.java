package com.example.diyview.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.diyview.R;
import com.example.diyview.divview.CircleDotProgressBar;
import com.example.diyview.divview.CircleDotProgressBardiy;

import java.util.Timer;
import java.util.TimerTask;

public class CircleDotProgressBarActivity extends AppCompatActivity {

    private CircleDotProgressBar bar;
    private CircleDotProgressBardiy barDiy;
    private int progress=0;
    private int maxProgress=100;
    private Timer timer;
    private Tick tick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_dot_progress_bar);
        bar = findViewById(R.id.circleDotProgressBar);
        bar.setProgressMax(maxProgress);
        barDiy = findViewById(R.id.circleDotProgressBarDiy);
        barDiy.setProgressMax(maxProgress);
        tick = new Tick();
        timer = new Timer();
        timer.schedule(tick,0,200);
        barDiy.setOnBtnClickLisener(new CircleDotProgressBardiy.OnBtnClickLisener(){

            @Override
            public void onClick() {
                Toast.makeText(CircleDotProgressBarActivity.this,"一键加速",Toast.LENGTH_LONG).show();
                timer.cancel();
                Tick tick = new Tick();
                Timer timer = new Timer();
                timer.schedule(tick,0,20);
            }
        });
    }
    class Tick extends TimerTask{
        @Override
        public void run() {
            bar.post(new Runnable() {
                @Override
                public void run() {
                    progress++;
                    bar.setProgress(progress%maxProgress);
                    barDiy.setProgress(progress%maxProgress);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tick.cancel();
        timer.cancel();
    }
}
