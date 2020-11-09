package com.example.diyview.utils;

import android.content.Context;
import android.util.TypedValue;

public class SizeUtil {

    //px和dp的转换
    public static int dp2px(float dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    // px 转dp
    public static float px2dp(float pxVal, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }
    // sp 转px
    public static float sp2px(float sp, Context context) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,sp,context.getResources().getDisplayMetrics());
    }
}
