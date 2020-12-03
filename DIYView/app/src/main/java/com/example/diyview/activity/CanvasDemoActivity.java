package com.example.diyview.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.diyview.divview.CanvasShowView;
import com.example.diyview.divview.dialog.CommonDialog;
import com.example.diyview.divview.dialog.DialogViewHolder;

public class CanvasDemoActivity extends AppCompatActivity {

    private RecyclerView rv_controler;
    private CanvasShowView canvas_zone;
    private String[] items = new String[]{"drawText", "drawARGB", "drawPoint", "drawLine", "drawRect", "drawCircle", "drawOval", "drawArc", "drawPath", "drawBitmap", "canvas其他方法"};
    private boolean[] itemsChosen = new boolean[]{true, false, false, false, false, false, false, false, false, false, false};
    private String[] itemsInDialog = new String[]{"clipPath，clipRect，clipOutPath，clipOutRect", "concat", "drawBitmapMesh，drawPicture", "drawTextOnPath", "drawTextRun", "drawVertices"};
    private CommonDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_matrix);
        initRecycleView();
        initDialog();
    }

    private void initDialog() {
        dialog = new CommonDialog(this, R.layout.dialog_grid_view) {
            @Override
            public void convert(DialogViewHolder holder) {
                RecyclerView rv_dialog = holder.getView(R.id.rv_dialog);
                rv_dialog.setLayoutManager(new LinearLayoutManager(CanvasDemoActivity.this));
                rv_dialog.setAdapter(new RecyclerView.Adapter() {
                    @NonNull
                    @Override
                    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gridview_item, null);
                        return new ThisViewHolder(inflate);
                    }

                    @Override
                    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
                        ThisViewHolder viewHolder = (ThisViewHolder) holder;
                        viewHolder.tv.setText(itemsInDialog[position]);
                        int paddingTop = SizeUtil.dp2px(10, CanvasDemoActivity.this);
                        viewHolder.tv.setPadding(0,paddingTop,0,paddingTop);
                        viewHolder.tv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancelDialog();
                                canvas_zone.refresh(items.length-1 + position);//减1是因为"canvas其他方法"这个按钮不更新CanvasShowView
                            }
                        });
                    }

                    @Override
                    public int getItemCount() {
                        return itemsInDialog.length;
                    }

                    class ThisViewHolder extends RecyclerView.ViewHolder {
                        public TextView tv;

                        public ThisViewHolder(@NonNull View itemView) {
                            super(itemView);
                            tv = itemView.findViewById(R.id.tv);
                        }
                    }
                });
            }
        }.fromBottom().fullWidth();
    }

    private void initRecycleView() {
        rv_controler = findViewById(R.id.rv_controler);
        canvas_zone = findViewById(R.id.canvas_zone);
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
                myholder.tv.setTextColor(ContextCompat.getColor(CanvasDemoActivity.this, position == 10 ? R.color.red : R.color.dark));
                myholder.tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < itemsChosen.length; i++) {
                            itemsChosen[i] = position == i;
                        }
                        notifyDataSetChanged();
                        if (position==items.length-1){
                            dialog.showDialog();
                            return;
                        }
                        canvas_zone.refresh(position);
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
    }
}
