package com.example.diyview.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.diyview.R;
import com.example.diyview.view.AddingShoppingCarAnimContainer;
import com.example.diyview.view.AnimShopButtonMine;


/**
 * @author lenovo
 * @date 2020/11/10 16:01
 * introduction: this is a view about adding products in the basket, and it has two DIY view,
 * one is the adding button with animation, the other one is the ViewGroup with the function
 * of the products are moved into the basket
 */
public class AnimShopButtonActivity extends AppCompatActivity implements AnimShopButtonMine.PlusOneAnimLisener {

    private AnimShopButtonMine asBtn1;
    private AnimShopButtonMine asBtn2;
    private AnimShopButtonMine asBtn3;
    private ImageView add_action_iv_move;
    private ImageView shopBasket;
    private AddingShoppingCarAnimContainer addCarContainerFm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopbtn_anim);
        asBtn1 = findViewById(R.id.mine_btn1);
        asBtn2 = findViewById(R.id.mine_btn2);
        asBtn3 = findViewById(R.id.mine_btn3);
        asBtn1.setmPlusLisener(this);
        asBtn2.setmPlusLisener(this);
        asBtn3.setmPlusLisener(this);
        add_action_iv_move = findViewById(R.id.add_action_iv_move);
        shopBasket = findViewById(R.id.shopBasket);
        addCarContainerFm = findViewById(R.id.addCarContainerFm);

    }

    @Override
    public void onNumPlus(int num, int componentId) {
        int[] startLocation = new int[2];
        int[] endLocation = new int[2];
        int width = 1;
        int basketW = shopBasket.getWidth();
        int basketH = shopBasket.getHeight();
        shopBasket.getLocationOnScreen(endLocation);
        switch (componentId) {
            case R.id.mine_btn1:
                asBtn1.getLocationOnScreen(startLocation);
                width = asBtn1.getWidth();

                break;
            case R.id.mine_btn2:
                asBtn2.getLocationOnScreen(startLocation);
                width = asBtn2.getWidth();
                break;
            case R.id.mine_btn3:
                asBtn3.getLocationOnScreen(startLocation);
                width = asBtn3.getWidth();
                break;
        }
        Drawable drawable = add_action_iv_move.getDrawable();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        addCarContainerFm.pickeProducts(bitmap, new Point(startLocation[0] + width / 2, startLocation[1]),
                new Point(endLocation[0] + basketW / 2, endLocation[1] + basketH));
    }
}
