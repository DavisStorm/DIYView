package com.example.diyview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.doyou.cv.utils.DensityUtil;
import com.doyou.cv.utils.ToastUtils;
import com.example.diyview.R;
import com.example.diyview.divview.dragimageview.ViewDragImageActivity;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = "FirstActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
        initData();
    }

    private void initData() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float dp = DensityUtil.px2dp(displayMetrics.widthPixels);
        ToastUtils.showShortToast(this,"屏幕宽度："+dp+"dp");
        Log.d(TAG, "屏幕宽度："+dp+"dp");
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
        findViewById(R.id.tv_SlidingPanelLayout).setOnClickListener(this);
        findViewById(R.id.tv_SearchFilter).setOnClickListener(this);
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
            case R.id.tv_SlidingPanelLayout:
                intent.setClass(this, SlidingPanelLayoutActivity.class);
                break;
            case R.id.tv_SearchFilter:
                intent.setClass(this, SearchFilterActivity.class);
                break;
            case R.id.tv_Others:
                intent.setClass(this,OtherActivity.class);
                break;
        }
        startActivity(intent);
    }
}
