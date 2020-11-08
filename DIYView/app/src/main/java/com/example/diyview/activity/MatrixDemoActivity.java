package com.example.diyview.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diyview.R;
import com.example.diyview.utils.SizeUtil;
import com.example.diyview.view.CanvasShowView;
import com.example.diyview.view.MatrixShowView;
import com.example.diyview.view.dialog.CommonDialog;
import com.example.diyview.view.dialog.DialogViewHolder;

public class MatrixDemoActivity extends AppCompatActivity {

    private RecyclerView rv_controler;
    private MatrixShowView matrix_zone;
    private LinearLayout controlbar;
    private String[] items = new String[]{"setXXX", "preXXX", "postXXX", "setContact", "mapRadius", "mapPoint", "example"};
    private boolean[] itemsChosen = new boolean[]{true, false, false, false, false, false, false};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_canvas);
        initRecycleView();
    }

    private void initRecycleView() {
        rv_controler = findViewById(R.id.rv_controler);
        matrix_zone = findViewById(R.id.matrix_zone);
        controlbar = findViewById(R.id.controlbar);
        rv_controler.setLayoutManager(new GridLayoutManager(this, 4));
        rv_controler.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gridview_item, null);
                return new MyViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
                MyViewHolder myholder = (MyViewHolder) holder;
                myholder.tv.setText(items[position]);
                myholder.tv.setBackgroundColor(getResources().getColor(itemsChosen[position] ? R.color.color_569ceb : R.color.grey));
                myholder.tv.setTextColor(ContextCompat.getColor(MatrixDemoActivity.this, position == 10 ? R.color.red : R.color.dark));
                myholder.tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < itemsChosen.length; i++) {
                            itemsChosen[i] = position == i;
                        }
                        notifyDataSetChanged();
                        matrix_zone.refresh(position);
                        controlbar.setVisibility(position == 6 ? View.VISIBLE : View.GONE);
                    }
                });
            }

            @Override
            public int getItemCount() {
                return items.length;
            }

            class MyViewHolder extends RecyclerView.ViewHolder {
                public TextView tv;

                public MyViewHolder(@NonNull View itemView) {
                    super(itemView);
                    tv = itemView.findViewById(R.id.tv);
                }
            }
        });
        RadioGroup gb = findViewById(R.id.gb_parent);
        gb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        matrix_zone.setCenterLocation(1);
                        break;
                    case R.id.rb_2:
                        matrix_zone.setCenterLocation(2);
                        break;
                    case R.id.rb_3:
                        matrix_zone.setCenterLocation(3);
                        break;
                }
            }
        });
        SeekBar pro_bar1 = findViewById(R.id.pro_bar1);
        pro_bar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                matrix_zone.setTranExample(progress - 300);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekBar pro_bar2 = findViewById(R.id.pro_bar2);
        pro_bar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                matrix_zone.setScaleExample((progress - 100) / 100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekBar pro_bar3 = findViewById(R.id.pro_bar3);
        pro_bar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                matrix_zone.setSkewExample((progress - 100) / 100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekBar pro_bar4 = findViewById(R.id.pro_bar4);
        pro_bar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                matrix_zone.setRotateExample((progress - 180) / 180 * 2);
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
