package com.xwdz.simple.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * @author 黄兴伟 (xwd9989@gamil.com)
 * @since 2018/5/9
 */
public class ProxyInstrumentation extends Instrumentation {

    private OnActivityCreateListener mOnActivityCreateListener;

    public ProxyInstrumentation() {
        Log.e("TAG", "ProxyInstrumentation created");
    }


    public void setActivityCreateListener(OnActivityCreateListener onActivityCreateListener) {
        this.mOnActivityCreateListener = onActivityCreateListener;
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        super.callActivityOnCreate(activity, icicle);
        final Intent intent = activity.getIntent();
        if (Intent.ACTION_MAIN.equals(intent.getAction()) && intent.hasCategory(Intent.CATEGORY_LAUNCHER)) {
            if (mOnActivityCreateListener != null) {
                mOnActivityCreateListener.onHookActivityCreated(activity, icicle);
            }
        }
    }

    /*回调接口*/
    public interface OnActivityCreateListener {

        void onHookActivityCreated(Activity activity, Bundle icicle);
    }
}
