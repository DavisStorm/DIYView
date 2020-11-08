package com.example.diyview.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.diyview.R;

public class TtileFragment extends Fragment {

    private String title = "";
    private TextView tv;

    public TtileFragment(String title) {
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fm_title, null);
        tv = view.findViewById(R.id.tv);
        tv.setText(title);
        return view;
    }

    public void setTitle(String title) {
        this.title = title;
        if (tv!=null){
            tv.setText(title);
        }
    }
}
