package com.example.diyview.annotaion;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@InjectClickType(lisennerSetter = "setOnLongClickListener",lisennerType = View.OnLongClickListener.class,callBackMethod = "onLongClick")
public @interface InjectOnLongClick {
    int[] value();
}
