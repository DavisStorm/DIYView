package com.example.diyview.activity;

import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.diyview.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StartActivity extends AppCompatActivity {

    private String[] mDirectory = new String[]{"自定义View", "Kotlin","版本新特性"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        RecyclerView recyclerView = findViewById(R.id.rv_Content);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter());
        setBackgroundAlphaWithAnim(0,1);
    }
    //设置window背景动画
    public void setBackgroundAlphaWithAnim(float alphaFrom,float alphaTo){
        ValueAnimator alphaAnim=ValueAnimator.ofFloat(alphaFrom,alphaTo);
        alphaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override
            public void onAnimationUpdate(ValueAnimator animation){
                Float alpha=(Float)animation.getAnimatedValue();
                WindowManager.LayoutParams lp=getWindow().getAttributes();
                lp.alpha=alpha;
                getWindow().setAttributes(lp);
            }
        });
        alphaAnim.setDuration(1800);
        alphaAnim.setEvaluator(new FloatEvaluator());
        alphaAnim.start();
    }


    public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_start, null);
            return new MyViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.tv.setText(mDirectory[position]);
            holder.tv.setOnClickListener(v -> gotoPage(position));
        }

        private void gotoPage(int position) {
            Intent intent = new Intent();
            switch (position) {
                case 0:
                    intent.setClass(StartActivity.this, FirstActivity.class);
                    break;
                case 1:
                    intent.setClass(StartActivity.this, KotlinActivity.class);
                    break;
                case 2:
                    intent.setClass(StartActivity.this, NewFeatureActivity.class);
                    break;
            }
            startActivity(intent);
        }

        @Override
        public int getItemCount() {
            return mDirectory.length;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv);
            }
        }
    }
}
