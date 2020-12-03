package com.example.diyview.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.diyview.R;
import com.example.diyview.activity.fragment.TtileFragment;
import com.example.diyview.divview.GradientTextView;

public class GradientTextViewActivity extends AppCompatActivity implements View.OnClickListener {

    private GradientTextView gradientTv;
    private String[] titles = new String[]{"直播", "资讯", "热点", "趣事", "动漫", "川普"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_gradient);
        gradientTv = findViewById(R.id.gradientTv);
        findViewById(R.id.btn_leftToRight).setOnClickListener(this);
        findViewById(R.id.btn_rightToLeft).setOnClickListener(this);

        //初始化tab视图
        final LinearLayout ll_title = findViewById(R.id.ll_title);
        GradientTextView gradientTextView;
        for (String title: titles) {
            gradientTextView = new GradientTextView(this);
            gradientTextView.setText(title);
            gradientTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            gradientTextView.setmChangeColor(ContextCompat.getColor(this,R.color.red));
            gradientTextView.setPadding(30,20,30,20);
            ll_title.addView(gradientTextView);
        }
        //初始化vp视图
        ViewPager vp_Content = findViewById(R.id.vp_Content);
        setAdapter(vp_Content);
        vp_Content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("onPageScrolled: ", "position: " + position + "    positionOffset: " + positionOffset);
                if (position+1!=titles.length){
                    GradientTextView gdtv = (GradientTextView) ll_title.getChildAt(position+1);
                    gdtv.setDIRECTION(GradientTextView.Direction.LEFT_TO_RIGHT);
                    gdtv.setMCurrentProgress(positionOffset);
                }
                GradientTextView pre_gdtv = (GradientTextView) ll_title.getChildAt(position);
                pre_gdtv.setDIRECTION(GradientTextView.Direction.RIGHT_TO_LEFT);
                pre_gdtv.setMCurrentProgress(positionOffset);

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setAdapter(ViewPager vp_Content) {
        vp_Content.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return titles.length;
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return new TtileFragment(titles[position]);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_leftToRight:
                gradientTv.setDIRECTION(GradientTextView.Direction.LEFT_TO_RIGHT);
                ObjectAnimator animator = ObjectAnimator.ofFloat(gradientTv, "mCurrentProgress", 0, 1);
                animator.setDuration(2000);
                animator.setInterpolator(new LinearInterpolator());
                animator.start();
                break;
            case R.id.btn_rightToLeft:
                gradientTv.setDIRECTION(GradientTextView.Direction.RIGHT_TO_LEFT);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(gradientTv, "mCurrentProgress", 1, 0);
                animator1.setDuration(2000);
                animator1.setInterpolator(new LinearInterpolator());
                animator1.start();
                break;
        }
    }
}
