package com.example.diyview.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diyview.R;
import com.example.diyview.adapter.BaseQuickAdapter;
import com.example.diyview.adapter.BaseViewHolder;
import com.example.diyview.scv.ScrollBean;

import java.util.ArrayList;
import java.util.List;

/**
* 测试ScrollView中嵌套RecyclerView的滑动问题
* */
public class ScrollVActivity extends AppCompatActivity {

    private RecyclerView rv_h1;
    private RecyclerView rv_v1;
    private RecyclerView rv_v2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        makesureTaskLogic();
    }

    private void makesureTaskLogic() {
        ArrayList<ScrollBean> scrollBeans1 = new ArrayList<>();
        ArrayList<ScrollBean> scrollBeans2 = new ArrayList<>();
        ArrayList<ScrollBean> scrollBeans3 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int color = i%2==0?R.color.blue:R.color.green;
            ScrollBean seat = new ScrollBean(color, "seat ", i + "");
            scrollBeans1.add(seat);
            scrollBeans2.add(seat);
            scrollBeans3.add(seat);
        }
        rv_h1 = findViewById(R.id.rv_h1);
        rv_v1 = findViewById(R.id.rv_v1);
        rv_v2 = findViewById(R.id.rv_v2);
        LinearLayoutManager managerH = new LinearLayoutManager(this);
        managerH.setOrientation(RecyclerView.HORIZONTAL);
        rv_h1.setLayoutManager(managerH);

        rv_v1.setLayoutManager(new LinearLayoutManager(this));

        rv_v2.setLayoutManager(new LinearLayoutManager(this));

        rv_h1.setAdapter(new CommonAdapter(scrollBeans1));
        rv_v1.setAdapter(new CommonAdapter(scrollBeans2));
        rv_v2.setAdapter(new CommonAdapter(scrollBeans3));
    }

    class CommonAdapter extends BaseQuickAdapter<ScrollBean>{

        public CommonAdapter(List<ScrollBean> data){
            super(android.R.layout.simple_list_item_1,data);
        }
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void convert(BaseViewHolder helper, ScrollBean item) {
            TextView view = helper.getView(android.R.id.text1);
            view.setText(item.getName()+item.getPosition());
            view.setBackground(getDrawable(item.getColor()));
        }
    }
}
