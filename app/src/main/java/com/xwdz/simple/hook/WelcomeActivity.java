package com.xwdz.simple.hook;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.xwdz.simple.R;

/**
 * @author 黄兴伟 (xwd9989@gamil.com)
 * @since 2018/5/9
 */
public class WelcomeActivity extends AppCompatActivity {

    private TextView mTextView;

    private CountDownTimer mCountDownTimer = new CountDownTimer(12000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mTextView.setText("Hook成功，欢迎来到酸菜个人站点 huangxingwei.cn 12秒后返回MainActivity = " + millisUntilFinished / 1000);
        }

        @Override
        public void onFinish() {
            finish();
            Log.e("TAG", "WelcomeActivity finished");
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG", "WelcomeActivity created success");
        setContentView(R.layout.welcome_layout);
        mTextView = findViewById(R.id.text);
        mCountDownTimer.start();
    }
}
