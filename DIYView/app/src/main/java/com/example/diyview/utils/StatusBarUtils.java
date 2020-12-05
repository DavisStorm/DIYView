package com.example.diyview.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author 王阳
 * @time 2020/12/6  1:36
 * @desc
 */
public class StatusBarUtils {

    public static void hideStatusBar(Activity activity){
        Window window = activity.getWindow();
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP) {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);//可能是设置content布局全屏
            window.setStatusBarColor(Color.TRANSPARENT);//可能是将状态栏背景去掉，但是前景还在

        }else if(Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    public static void setStatusBarColor(Activity activity,int color){
        Window window = activity.getWindow();
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(color);
        }else if(Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT) {
            int statusBarHeight = activity.getResources()
                    .getIdentifier("status_bar_height", "dimen", "android");
            ViewGroup decorView = (ViewGroup) window.getDecorView();
            View statusView = new View(activity);
            statusView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,statusBarHeight));
            statusView.setBackgroundColor(color);
            decorView.addView(statusView);
        }
    }
}
