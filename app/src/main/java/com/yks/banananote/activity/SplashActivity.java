package com.yks.banananote.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.yks.banananote.R;

/**
 * 描述：启动过渡页
 * 作者：zzh
 * time:2020/04/11
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        initView();
    }

    private void initView() {
        ImageView iv_splash = findViewById(R.id.iv_splash);

        //todo 1：先获取屏幕宽高
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        //todo 2：设置加载图片的大小
        Glide.with(this)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586605924092&di=2c7626400a6e3523b884c63bd296225e&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F333b4afa0d9decc03267a9e16fa7b3175ae61c9fde11-mKW7K2_fw658")
                .centerCrop()
                .override(point.x,point.y)
                .placeholder(R.mipmap.ic_splash_bg)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//todo 加入缓存中，下次进来就直接展示，不需要重新下载
                .listener(requestListener)
                .into(iv_splash);

        //todo 3：设置过度动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f,1f);
        alphaAnimation.setDuration(5000);
        iv_splash.startAnimation(alphaAnimation);
        //todo 4：监听动画，完成后跳转主页
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
//                Glide.with(SplashActivity.this).onDestroy();
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    RequestListener requestListener = new RequestListener() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
            Log.d("glide", "onException: " + e.toString()+"  model:"+model+" isFirstResource: "+isFirstResource);
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
            Log.e("glides",  "model:"+model+" isFirstResource: "+isFirstResource);
            return false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        requestListener = null;
    }
}
