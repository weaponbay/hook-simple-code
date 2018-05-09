package com.xwdz.simple.hook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author 黄兴伟 (xwd9989@gamil.com)
 * @since 2018/5/9
 */
public class HookActivityHelper {

    private static final HookActivityHelper INSTANCE = new HookActivityHelper();
    private ProxyInstrumentation mProxyInstrumentation;

    public static HookActivityHelper get() {
        return INSTANCE;
    }


    public void open() {
        mProxyInstrumentation.setActivityCreateListener(new ProxyInstrumentation.OnActivityCreateListener() {
            @Override
            public void onHookActivityCreated(Activity activity, Bundle icicle) {
                Intent intent = new Intent(activity, WelcomeActivity.class);
                activity.startActivity(intent);
            }
        });
    }


    private HookActivityHelper() {
        try {
            Class<?> clazz = Class.forName("android.app.ActivityThread");
            Method currentActivityThread = clazz.getDeclaredMethod("currentActivityThread");
            Object object = currentActivityThread.invoke(null);


            Field field = clazz.getDeclaredField("mInstrumentation");
            field.setAccessible(true);
            mProxyInstrumentation = new ProxyInstrumentation();
            field.set(object, mProxyInstrumentation);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
