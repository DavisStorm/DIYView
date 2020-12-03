package com.example.diyview.divview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xw.repo.supl.ISlidingUpPanel;

public class CardPanelView extends View implements ISlidingUpPanel {

    public CardPanelView(Context context) {
        super(context);
    }

    public CardPanelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CardPanelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @NonNull
    @Override
    public View getPanelView() {
        return null;
    }

    @Override
    public int getPanelExpandedHeight() {
        return 0;
    }

    @Override
    public int getPanelCollapsedHeight() {
        return 0;
    }

    @Override
    public int getSlideState() {
        return 0;
    }

    @Override
    public void setSlideState(int slideState) {

    }

    @Override
    public int getPanelTopBySlidingState(int slideState) {
        return 0;
    }

    @Override
    public void onSliding(@NonNull ISlidingUpPanel panel, int top, int dy, float slidedProgress) {

    }
}
