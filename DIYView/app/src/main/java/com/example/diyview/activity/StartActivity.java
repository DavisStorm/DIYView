package com.example.diyview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diyview.R;

public class StartActivity extends AppCompatActivity {

    private String[] mDirectory = new String[]{"自定义View","Kotlin"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        RecyclerView recyclerView = findViewById(R.id.rv_Content);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter());
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_start, null);
            return new MyViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.tv.setText(mDirectory[position]);
            holder.tv.setOnClickListener(v-> gotoPage(position));
        }

        private void gotoPage(int position) {
            Intent intent = new Intent();
            switch (position){
                case 0:
                    intent.setClass(StartActivity.this,FirstActivity.class);
                    break;
                case 1:
                    intent.setClass(StartActivity.this,KotlinActivity.class);
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
                 tv =itemView.findViewById(R.id.tv);
            }
        }
    }
}
