package com.example.diyview.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.diyview.R;

public class SlidingPanelLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_panel_layout);

        initView();
    }

    private void initView() {

//        SlidingUpPanelLayout.setAdapter(new SlidingUpPanelLayout.Adapter() {
//
//            private final int mSize = mWeatherList.size();
//
//            @Override
//            public int getItemCount() {
//                return mSize;
//            }
//
//            @Override
//            public ISlidingUpPanel onCreateSlidingPanel(int position) {
//                CardPanelView panel = new CardPanelView(SlidingPanelLayoutActivity.this);
//                if (position == 0) {
//                    panel.setSlideState(EXPANDED);
//                } else {
//                    panel.setSlideState(HIDDEN);
//                }
//
//                return panel;
//            }
//
//            @Override
//            public void onBindView(final ISlidingUpPanel panel, int position) {
//                if (mSize == 0)
//                    return;
//
//                BaseWeatherPanelView BasePanel = (BaseWeatherPanelView) panel;
//                BasePanel.setWeatherModel(mWeatherList.get(position));
//                BasePanel.setClickable(true);
//                BasePanel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (panel.getSlideState() != EXPANDED) {
//                            mSlidingUpPanelLayout.expandPanel();
//                        } else {
//                            mSlidingUpPanelLayout.collapsePanel();
//                        }
//                    }
//                });
//            }
//        });
    }
}
