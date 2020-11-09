package com.example.diyview.listener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.diyview.utils.T;
import com.fangxu.library.footer.BaseFooterDrawer;

public class ArrowPathFooterDrawer extends BaseFooterDrawer {
    private Context context;
    private int color;
    private int pathColor;
    private ArrowPathFooterDrawer(Context context, int color, int pathColor) {
        this.context = context;
        this.color = color;
        this.pathColor = pathColor;
    }

    @Override
    public boolean shouldTriggerEvent(float v) {
        T.ss(context,"查看更多");
        return true;
    }

    @Override
    public void drawFooter(Canvas canvas, float left, float top, float right, float bottom) {
        this.footerRegion=new RectF();
        super.drawFooter(canvas, left, top, right, bottom);
    }

    public static final class Builder {
        private Context context;
        private int color;
        private int pathColor;

        public Builder(Context context, int color) {
            this.context = context;
            this.color = color;
        }

        public Builder setPathColor(int pathColor) {
            this.pathColor = pathColor;
            return this;
        }

        public ArrowPathFooterDrawer build() {
            return new ArrowPathFooterDrawer(context,color,pathColor);
        }
    }
}
