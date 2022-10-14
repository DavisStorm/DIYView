package com.example.diyview.annotaion;

import android.view.View;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class EventInvocationHnadler implements InvocationHandler {

    //需要调用的方法
    private Method objMethod;

    //需要调用的方法所在的对象
    private Object obj;

    public EventInvocationHnadler(Object obj, Method method) {
        this.obj = obj;
        this.objMethod = method;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return objMethod.invoke(obj,objects);
    }
}
