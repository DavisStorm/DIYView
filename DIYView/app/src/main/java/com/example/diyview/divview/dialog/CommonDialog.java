package com.example.diyview.divview.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.StyleRes;

import com.example.diyview.R;


/**
 * Created by john
 * time: 2017-03-28
 * desc: ${TODO}
 */

public abstract class CommonDialog {
    private Dialog mDialog;
    private Window mDialogWindow;
    private DialogViewHolder dialogVh;
    private View mRootView;

    public CommonDialog(Context context, int layoutId) {
        if (dialogVh == null)
            dialogVh = DialogViewHolder.get(context, layoutId);
        mRootView = dialogVh.getConvertView();
        if (mDialog == null)
            mDialog = new Dialog(context, R.style.dialog);
        mDialog.setContentView(mRootView);
        mDialogWindow = mDialog.getWindow();
        convert(dialogVh);
    }

    /**
     * B
     * 把弹出框view holder传出去
     */
    public abstract void convert(DialogViewHolder holder);

    public DialogViewHolder getDialogVh() {
        return dialogVh;
    }

    public static AlertDialog.Builder creatNormalDialogBuilder(Context context, String title, String message) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message);
    }

    /**
     * 显示dialog
     */
    public CommonDialog showDialog() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
        return this;
    }

    /**
     * @param light 弹出时背景亮度 值为0.0~1.0    1.0表示全黑  0.0表示全白
     * @return
     */
    public CommonDialog backgroundLight(double light) {
        if (light < 0.0 || light > 1.0)
            return this;
        WindowManager.LayoutParams lp = mDialogWindow.getAttributes();
        lp.dimAmount = (float) light;
        mDialogWindow.setAttributes(lp);
        return this;
    }

    /**
     * 从底部一直弹到中间
     */
    @SuppressLint("NewApi")
    public CommonDialog fromBottomToMiddle() {
        mDialogWindow.setWindowAnimations(R.style.window_bottom_in_bottom_out);
        return this;
    }

    /**
     * 从底部弹出
     */
    public CommonDialog fromBottom() {
        fromBottomToMiddle();
        mDialogWindow.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        return this;
    }

//    /**
//     * 从左边一直弹到中间退出也是到左边
//     */
//    public CommonDialog fromLeftToMiddle() {
//        mDialogWindow.setWindowAnimations(R.style.window_left_in_left_out);
//        mDialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        mDialogWindow.setGravity(Gravity.CENTER | Gravity.LEFT);
//        return this;
//    }
//
//    public CommonDialog fromLeft() {
//        mDialogWindow.setWindowAnimations(R.style.window_left_in_left_out);
//        mDialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        mDialogWindow.setGravity(Gravity.LEFT);
//        return this;
//    }

//    /**
//     * 从右边一直弹到中间退出也是到右边
//     *
//     * @return
//     */
//    public CommonDialog fromRightToMiddle() {
//        mDialogWindow.setWindowAnimations(R.style.window_right_in_right_out);
//        mDialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        mDialogWindow.setGravity(Gravity.RIGHT);
//        //
//        return this;
//    }

//    /**
//     * 从顶部弹出 从顶部弹出  保持在顶部
//     *
//     * @return
//     */
//    public CommonDialog fromTop() {
//        fromTopToMiddle();
//        mDialogWindow.setGravity(Gravity.CENTER | Gravity.TOP);
//        return this;
//    }


    public CommonDialog showCustomPosition(int x, int y) {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        mDialogWindow.setGravity(Gravity.BOTTOM);
        wl.x = x;
        wl.y = y;
        mDialogWindow.setAttributes(wl);
        return this;
    }


//    /**
//     * 从顶部谈到中间  从顶部弹出  保持在中间
//     *
//     * @return
//     */
//    public CommonDialog fromTopToMiddle() {
//        mDialogWindow.setWindowAnimations(R.style.window_top_in_top_out);
//        mDialogWindow.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        return this;
//    }

    /**
     * @param style 显示一个Dialog自定义一个弹出方式  具体怎么写 可以模仿上面的
     * @return
     */
    public CommonDialog showDialog(@StyleRes int style) {
        mDialogWindow.setWindowAnimations(style);
        mDialog.show();
        return this;
    }

    /**
     * @param isAnimation 如果为true 就显示默认的一个缩放动画
     * @return
     */
    public CommonDialog showDialog(boolean isAnimation) {
        mDialogWindow.setWindowAnimations(R.style.dialog_scale_animstyle);
        mDialog.show();
        return this;
    }

    /**
     * 全屏显示
     */
    public CommonDialog fullScreen() {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.height = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }


    public CommonDialog setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        mDialog.setOnKeyListener(onKeyListener);
        return this;
    }

    /**
     * 全屏宽度
     */
    public CommonDialog fullWidth() {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }

    /**
     * 全屏高度
     */
    public CommonDialog fullHeight() {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.height = ViewGroup.LayoutParams.MATCH_PARENT;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }

    /**
     * @param width  自定义的宽度
     * @param height 自定义的高度
     * @return
     */
    public CommonDialog setWidthAndHeight(int width, int height) {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.width = width;
        wl.height = height;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }

    /**
     * 设置宽度
     *
     * @param width
     * @return
     */
    public CommonDialog setWidth(int width) {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.width = width;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }

    /**
     * 设置高度
     *
     * @param height
     * @return
     */
    public CommonDialog setHeight(int height) {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.height = height;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }

    /**
     * cancel dialog
     */
    public void cancelDialog() {
        if (mDialog != null && mDialog.isShowing())
            dismiss();
    }

    /**
     * cancel dialog
     */
    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    /**
     * 设置监听
     */
    public CommonDialog setDialogDismissListener(DialogInterface.OnDismissListener listener) {
        mDialog.setOnDismissListener(listener);
        return this;
    }

    /**
     * 设置监听
     */
    public CommonDialog setOnCancelListener(DialogInterface.OnCancelListener listener) {
        mDialog.setOnCancelListener(listener);
        return this;
    }

    /**
     * 设置是否能取消
     */
    public CommonDialog setCancelAble(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }


    /**
     * 设置触摸其他地方是否能取消
     */
    public CommonDialog setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    /**
     * 设置dialog是否全局显示
     * @return
     */
    public CommonDialog setShowOverall(){
        mDialogWindow.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        return this;
    }
}
