package com.example.diyview.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diyview.R;
import com.example.diyview.adapter.BaseQuickAdapter;
import com.example.diyview.adapter.BaseViewHolder;
import com.fangxu.library.DragContainer;
import com.fangxu.library.DragListener;

import java.util.ArrayList;

public class DragFooterViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_foot_view);
        DragContainer dragContainer = findViewById(R.id.drag_recycler_view);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        // add mock data
        ArrayList<CartoonBean> list = new ArrayList<>();
        list.add(new CartoonBean(R.drawable.a));
        list.add(new CartoonBean(R.drawable.b));
        list.add(new CartoonBean(R.drawable.c));
        list.add(new CartoonBean(R.drawable.d));
        list.add(new CartoonBean(R.drawable.f));
        recyclerView.setAdapter(new RvAdapter(list));

//        dragContainer.setFooterDrawer(new ArrowPathFooterDrawer.Builder(this, 0xff444444).setPathColor(0xffffffff).build());

        dragContainer.setDragListener(new DragListener() {
            @Override
            public void onDragEvent() {
                Toast.makeText(DragFooterViewActivity.this, "查看更多触发，跳转", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private class RvAdapter extends BaseQuickAdapter<CartoonBean> {
        public RvAdapter(ArrayList<CartoonBean> list) {
            super(R.layout.adapter_img,list);
        }

        @Override
        protected void convert(BaseViewHolder helper, CartoonBean item) {
            ((ImageView)helper.getView(R.id.img)).setImageDrawable(getDrawable(item.getDrawableId()));
        }
    }

    private class CartoonBean {
        private int drawableId;

        public CartoonBean(int drawableId) {
            this.drawableId = drawableId;
        }

        public int getDrawableId() {
            return drawableId;
        }
    }

}
