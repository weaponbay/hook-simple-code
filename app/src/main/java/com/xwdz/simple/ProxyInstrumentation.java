package com.xwdz.simple;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.lang.reflect.Method;

public class ProxyInstrumentation extends Instrumentation {

    private Method mCreateMethod1;
    private Method mCreateMethod2;

    private Instrumentation mReal;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProxyInstrumentation(Instrumentation instrumentation) {
        mReal = instrumentation;
        Log.e("TAG", "ProxyInstrumentation created");
//        final Class<?> clazz = instrumentation.getClass();
//        try {
//            mCreateMethod1 = clazz.getMethod("callActivityOnCreate", Activity.class, Bundle.class);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        super.callActivityOnCreate(activity, icicle);
        final Intent intent = activity.getIntent();
        if (Intent.ACTION_MAIN.equals(intent.getAction()) && intent.hasCategory(Intent.CATEGORY_LAUNCHER)) {
            Log.e("TAG", "this is launcher");
        }
//        if (null != mCreateMethod1) {
//            try {
//                mCreateMethod1.invoke(mReal, activity, icicle);
//            } catch (Throwable t) {
//                Log.e("TAG", "failed = " + t);
//            }
//        } else {

//        }
    }
}
