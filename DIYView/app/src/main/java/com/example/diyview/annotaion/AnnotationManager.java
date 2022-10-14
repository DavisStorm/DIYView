package com.example.diyview.annotaion;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AnnotationManager {
    public static AnnotationManager annotationManager = null;
    private AnnotationManager() {}

    public static AnnotationManager getInstance(){
        if (annotationManager ==null){
            synchronized (AnnotationManager.class){
                if (annotationManager == null){
                    annotationManager = new AnnotationManager();
                }
            }
        }
        return annotationManager;
    }
    public void inject(Object obj){
        injectSetContentView(obj);
        injectFindViewById(obj);
        injectEvents(obj);
    }

    private void injectSetContentView(Object obj) {
        Class<?> clazz = obj.getClass();
        InjectSetContentView injectFbi = clazz.getAnnotation(InjectSetContentView.class);
        if (injectFbi !=null){
            int layoutId = injectFbi.value();
            try {
                Method setContentView = clazz.getMethod("setContentView", int.class);
                setContentView.invoke(obj,layoutId);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void injectFindViewById(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            InjectFbi injectFbi = field.getAnnotation(InjectFbi.class);
            if (injectFbi !=null){
                int value = injectFbi.value();
                try {
                    Method findViewById = clazz.getMethod("findViewById", int.class);
                    Object view = findViewById.invoke(obj, value);

                    field.setAccessible(true);
                    field.set(obj,view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void injectEvents(Object obj) {
        Class<?> clazz = obj.getClass();
        Method[] Methods = clazz.getDeclaredMethods();

        //获取OnClick方法
        for (Method method: Methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation: annotations) {
                //获取注解的Class对象，通过的反射获取注解的注解
                Class<? extends Annotation> annotationClick = annotation.annotationType();
                //获取InjectOnClick等注解的注解
                InjectClickType injectClickType = annotationClick.getAnnotation(InjectClickType.class);
                if (injectClickType !=null) {
                    String lisennerSetter = injectClickType.lisennerSetter();
                    Class lisennerType = injectClickType.lisennerType();
                    String callBackMethod = injectClickType.callBackMethod();

                    //动态代理获取点击监听器对象
                    Object lisener = Proxy.newProxyInstance(lisennerType.getClassLoader(), new Class[]{lisennerType},
                            new EventInvocationHnadler(obj,method));

                    //走到这一步，需要调用id的setOnclick方法
                    try {
                        Method value = annotationClick.getDeclaredMethod("value");
                        Object ids = value.invoke(annotation);
                        if(ids instanceof int[]){
                            int[] intIds = (int[])ids;
                            for (int id: intIds) {
                                Method findViewById = clazz.getMethod("findViewById", int.class);
                                Object view = findViewById.invoke(obj, id);
                                Class<?> viewClass = view.getClass();
                                Method event = viewClass.getMethod(lisennerSetter,lisennerType);

                                event.invoke(view,lisener);

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
