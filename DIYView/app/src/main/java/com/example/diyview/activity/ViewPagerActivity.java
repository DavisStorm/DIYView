package com.example.diyview.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.diyview.R;
import com.example.diyview.view.LitePager;

public class ViewPagerActivity extends AppCompatActivity {

    private LitePager lp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
//        lp = findViewById(R.id.vp);
//        lp.setAdapter(new LitePager.Adapter<ImageView>() {
//            @Override
//            protected ImageView onCreateView(@NonNull ViewGroup parent) {
//                return (ImageView) LayoutInflater.from(ViewPagerActivity.this).inflate(R.layout.adapter_litepager, parent, false);
//            }
//
//            @Override
//            protected void onBindView(@NonNull ImageView view, int position) {
//                switch (position){
//                    case 0:
//                        view.setImageDrawable(getDrawable(R.drawable.a));
//                        break;
//                    case 1:
//                        view.setImageDrawable(getDrawable(R.drawable.b));
//                        break;
//                    case 2:
//                        view.setImageDrawable(getDrawable(R.drawable.c));
//                        break;
//                    case 3:
//                        view.setImageDrawable(getDrawable(R.drawable.d));
//                        break;
//                    case 4:
//                        view.setImageDrawable(getDrawable(R.drawable.f));
//                        break;
//                }
//            }
//
//            @Override
//            protected int getItemCount() {
//                return 4;
//            }
//        });
    }
}
