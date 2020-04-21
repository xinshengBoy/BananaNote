package com.yks.banananote.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * 描述：基础fragment类，主要是添加handler
 * 作者：zzh
 * time:2020/04/10
 */
public class BaseActivity extends AppCompatActivity {

    private MyHandler handler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new MyHandler(BaseActivity.this);
    }

    private class MyHandler extends Handler {
        final WeakReference<Activity> mWeakReference;

        MyHandler(Activity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final Activity activity = mWeakReference.get();
            if (activity != null) {
                handleMessageBack(msg);
            }
        }
    }

    public void handleMessageBack(Message msg){

    }

    public MyHandler getHandler(){
        return handler;
    }
}
