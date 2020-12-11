package com.example.diyview.activity;

import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diyview.R;
import com.example.diyview.adapter.BaseQuickAdapter;
import com.example.diyview.adapter.BaseViewHolder;
import com.example.diyview.bean.News;
import com.yalantis.filter.adapter.FilterAdapter;
import com.yalantis.filter.listener.FilterListener;
import com.yalantis.filter.model.FilterModel;
import com.yalantis.filter.widget.Filter;
import com.yalantis.filter.widget.FilterItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SearchFilterActivity extends AppCompatActivity {
    private Filter mFilter;
    private int[] mColors = new int[]{Color.RED, Color.BLUE, Color.YELLOW};
    private RecyclerView content_rv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfilter);

        mFilter = findViewById(R.id.filter);
        mFilter.setAdapter(new Adapter(newAList()));
        mFilter.setListener(mListener);

        //the text to show when there's no selected items
        mFilter.setNoSelectedItemText("nothing");
        mFilter.build();
        content_rv = findViewById(R.id.content_rv);
        content_rv.setLayoutManager(new LinearLayoutManager(this));
        content_rv.setAdapter(new RVAdapter(newNewsList()));
    }

    private List<News> newNewsList() {
        List<News> news = new ArrayList<>();
        news.add(new News("David","",20,0,"Firstly you need to place Filter above your RecyclerView in the layout file"));
        news.add(new News("Tom","",20,1,"After that you need to create a class that extends FilterAdapter and to pass your model class as a generic"));
        news.add(new News("Smith","",20,2,"For the sample app I created Tag model to represent the category of a question in the conversation"));
        news.add(new News("Jack","",20,2,"To receive all the events from the Filter such as selection or deselection of a filter item it’s necessary to add a FilterListener with the same model class passed as a generic"));
        news.add(new News("Woodz","",20,2,"For more usage examples please review sample app"));
        return news;
    }

    private List<? extends FilterModel> newAList() {
        List<FilterModel> mData = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            mData.add(new FilterModel() {
                @Override
                public String getText() {
                    return "position" + " " + finalI;
                }
            });
        }
        return mData;
    }

    class Adapter extends FilterAdapter<FilterModel> {

        Adapter(List<? extends FilterModel> items) {
            super(items);
        }

        @Override
        public FilterItem createView(int position, FilterModel item) {
            FilterItem filterItem = new FilterItem(SearchFilterActivity.this);

            filterItem.setStrokeColor(mColors[0]);
            filterItem.setTextColor(mColors[0]);
            filterItem.setCheckedTextColor(ContextCompat.getColor(SearchFilterActivity.this, android.R.color.white));
            filterItem.setColor(ContextCompat.getColor(SearchFilterActivity.this, android.R.color.white));
            filterItem.setCheckedColor(mColors[position]);
            filterItem.setText(item.getText());
            filterItem.deselect();

            return filterItem;
        }
    }

    class RVAdapter extends BaseQuickAdapter<News> {

        RVAdapter(List<News> data) {
            super(R.layout.adapter_searchfilter, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, News item) {
//            helper.setText()
        }
    }

    private FilterListener<FilterModel> mListener = new FilterListener<FilterModel>() {

        @Override
        public void onFiltersSelected(@NotNull ArrayList<FilterModel> filters) {

        }

        @Override
        public void onNothingSelected() {

        }

        @Override
        public void onFilterSelected(FilterModel item) {

        }

        @Override
        public void onFilterDeselected(FilterModel item) {

        }
    };
}
