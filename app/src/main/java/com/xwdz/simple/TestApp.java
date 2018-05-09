package com.xwdz.simple;

import android.app.Application;

import com.xwdz.simple.hook.HookActivityHelper;

public class TestApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        HookActivityHelper.get().open();
    }
}
