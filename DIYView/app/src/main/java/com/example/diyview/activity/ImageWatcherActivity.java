package com.example.diyview.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.diyview.R;
import com.example.diyview.divview.ImageWatcher;

public class ImageWatcherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_watcher);
        ImageWatcher iw = findViewById(R.id.iw);
        iw.setImageDrawables(new int[]{
                R.drawable.img1, R.drawable.img2, R.drawable.img3,
                R.drawable.img4, R.drawable.img5, R.drawable.img6,
                R.drawable.img7, R.drawable.img8, R.drawable.img9
        });
    }
}
