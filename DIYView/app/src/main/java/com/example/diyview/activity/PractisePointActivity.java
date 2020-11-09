package com.example.diyview.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diyview.R;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PractisePointActivity extends AppCompatActivity {

    String[] itemStrings = new String[]{"Canvas", "Matrix", "a", "a", "a", "a", "a", "a"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practise_point);

        RecyclerView rv_Content = findViewById(R.id.rv_Content);
        rv_Content.setLayoutManager(new LinearLayoutManager(this));
        rv_Content.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rv_Content.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new MyViewHolder(LayoutInflater.from(PractisePointActivity.this).inflate(android.R.layout.simple_list_item_1, null));
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
                TextView tv = ((MyViewHolder) holder).tv;
                tv.setText(itemStrings[position]);
                tv.setPadding(200, 0, 0, 0);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goPage(position);
                    }
                });
            }

            private void goPage(int position) {
                Intent intent = new Intent();
                switch (position+1) {
                    case 1:
                        intent.setClass(PractisePointActivity.this, CanvasDemoActivity.class);
                        break;
                    case 2:
                        intent.setClass(PractisePointActivity.this, MatrixDemoActivity.class);
                        break;
                    case 3:
                        intent.setClass(PractisePointActivity.this, CanvasDemoActivity.class);
                        break;
                    case 4:
                        intent.setClass(PractisePointActivity.this, CanvasDemoActivity.class);
                        break;
                }
                startActivity(intent);
            }

            @Override
            public int getItemCount() {
                return itemStrings.length;
            }

            class MyViewHolder extends RecyclerView.ViewHolder {
                public TextView tv;

                MyViewHolder(@NonNull View itemView) {
                    super(itemView);
                    tv = itemView.findViewById(android.R.id.text1);
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
            }
        });
    }
}
