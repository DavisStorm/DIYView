package com.example.diyview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.diyview.R;
import com.example.diyview.view.dragimageview.ViewDragImageActivity;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_MeasureView).setOnClickListener(this);
        findViewById(R.id.tv_StatisticsView).setOnClickListener(this);
        findViewById(R.id.tv_DragImageView).setOnClickListener(this);
        findViewById(R.id.tv_CircleDotProgressBar).setOnClickListener(this);
        findViewById(R.id.tv_LitePager).setOnClickListener(this);
        findViewById(R.id.tv_DragFooterView).setOnClickListener(this);
        findViewById(R.id.tv_UniverseStarsView).setOnClickListener(this);
        findViewById(R.id.tv_SmoothPagingView).setOnClickListener(this);
        findViewById(R.id.tv_LoadingView).setOnClickListener(this);
        findViewById(R.id.tv_ImageWatcher).setOnClickListener(this);
        findViewById(R.id.tv_VectorMaster).setOnClickListener(this);
        findViewById(R.id.tv_SuperLike).setOnClickListener(this);
        findViewById(R.id.tv_WLikeAnim).setOnClickListener(this);
        findViewById(R.id.tv_Practise_Point).setOnClickListener(this);
        findViewById(R.id.tv_Widget_Cases).setOnClickListener(this);
        findViewById(R.id.tv_GradientTextView).setOnClickListener(this);
        findViewById(R.id.tv_AnimShopButton).setOnClickListener(this);
        findViewById(R.id.tv_Others).setOnClickListener(this);
        findViewById(R.id.tv_ViewDragHelper).setOnClickListener(this);
        findViewById(R.id.tv_HorizontalScrollFrameLayout).setOnClickListener(this);
        findViewById(R.id.tv_SlidingView).setOnClickListener(this);
        findViewById(R.id.tv_ViewDragImage).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.tv_MeasureView:
                intent.setClass(this, MeasureViewActivity.class);
                break;
            case R.id.tv_StatisticsView:
                intent.setClass(this,StatisticsViewActivity.class);
                break;
            case R.id.tv_DragImageView:
                intent.setClass(this,DragImageViewActivity.class);
                break;
            case R.id.tv_CircleDotProgressBar:
                intent.setClass(this,CircleDotProgressBarActivity.class);
                break;
            case R.id.tv_LitePager:
                intent.setClass(this,ViewPagerActivity.class);
                break;
            case R.id.tv_DragFooterView:
                intent.setClass(this,DragFooterViewActivity.class);
                break;
            case R.id.tv_UniverseStarsView:
                intent.setClass(this,UniverseStarsViewActivity.class);
                break;
            case R.id.tv_SmoothPagingView:
                intent.setClass(this,SmoothPagingViewActivity.class);
                break;
            case R.id.tv_LoadingView:
                intent.setClass(this,LoadingViewActivity.class);
                break;
            case R.id.tv_ImageWatcher:
                intent.setClass(this,ImageWatcherActivity.class);
                break;
            case R.id.tv_VectorMaster:
                intent.setClass(this,VectorExampleActivity.class);
                break;
            case R.id.tv_SuperLike:
                intent.setClass(this,SuperLikeActivity.class);
                break;
            case R.id.tv_WLikeAnim:
                intent.setClass(this,WLikeAnimActivity.class);
                break;
            case R.id.tv_Practise_Point:
                intent.setClass(this,PractisePointActivity.class);
                break;
            case R.id.tv_Widget_Cases:
                intent.setClass(this,WidgetCasesActivity.class);
                break;
            case R.id.tv_GradientTextView:
                intent.setClass(this,GradientTextViewActivity.class);
                break;
            case R.id.tv_AnimShopButton:
                intent.setClass(this,AnimShopButtonActivity.class);
                break;
            case R.id.tv_ViewDragHelper:
                intent.setClass(this,ViewDraghelperActivity.class);
                break;
            case R.id.tv_HorizontalScrollFrameLayout:
                intent.setClass(this,HorizontalScrollFrameLayoutActivity.class);
                break;
            case R.id.tv_SlidingView:
                intent.setClass(this,SlidingViewActivity.class);
                break;
            case R.id.tv_ViewDragImage:
                intent.setClass(this, ViewDragImageActivity.class);
                break;
            case R.id.tv_Others:
                intent.setClass(this,OtherActivity.class);
                break;
        }
        startActivity(intent);
    }
}
