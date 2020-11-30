package com.example.diyview.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diyview.R;
import com.example.diyview.view.HorizontalScrollFrameLayout;


public class HorizontalScrollFrameLayoutActivity extends AppCompatActivity{

    private RadioGroup rgPayHome;
    private HorizontalScrollFrameLayout ll_horizental_scroll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontalscroll_framelayout);
        rgPayHome = findViewById(R.id.rg_pay_home);
        ll_horizental_scroll = findViewById(R.id.ll_horizental_scroll);
        rgPayHome.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_terminal:
                        ll_horizental_scroll.scrollToPosition(0);
                        break;
                    case R.id.rb_no_terminal:
                        ll_horizental_scroll.scrollToPosition(1);
                        break;
                }
            }
        });
        ll_horizental_scroll.setmPagerChangeLisener(new HorizontalScrollFrameLayout.OnPagerChangeLisener(){
            @Override
            protected void onPageChange(int position) {
                switch (position){
                    case 0:
                        rgPayHome.check(R.id.rb_terminal);
                        break;
                    case 1:
                        rgPayHome.check(R.id.rb_no_terminal);
                        break;
                }
            }
        });
        findViewById(R.id.tv_home_menu1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                Toast.makeText(HorizontalScrollFrameLayoutActivity.this, tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tv_home_menu2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                Toast.makeText(HorizontalScrollFrameLayoutActivity.this, tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tv_home_menu3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                Toast.makeText(HorizontalScrollFrameLayoutActivity.this, tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.tv_home_menu4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                Toast.makeText(HorizontalScrollFrameLayoutActivity.this, tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
