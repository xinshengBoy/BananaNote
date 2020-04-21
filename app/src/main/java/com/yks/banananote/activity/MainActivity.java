package com.yks.banananote.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.tabs.TabLayout;
import com.yks.banananote.R;
import com.yks.banananote.fragment.BusinessSchoolFragment;
import com.yks.banananote.fragment.BananaPostFragment;
import com.yks.banananote.fragment.MyselfFragment;
import com.yks.banananote.fragment.HomePageFragment;
import com.yks.banananote.tools.Info;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 描述：主页
 * 作者：zzh
 * tablayout参考文档：https://www.jianshu.com/p/39a66373498c
 */
public class MainActivity extends AppCompatActivity {

    private Context mContext = MainActivity.this;
    private ViewPager vp_content;
    private TabLayout tl_tab;
    private List<String> tabIndicators;
    private List<Integer> tabImageIcons;
    private List<Fragment> tabFragment;
    private ContentPageAdapter contentPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        vp_content = findViewById(R.id.vp_content);
        tl_tab = findViewById(R.id.tl_tab);

        initContent();
        initTab();
        showADDialog();
    }

    private void initContent(){
        tabIndicators = new ArrayList<>();
        tabIndicators.add("首页");
        tabIndicators.add("商学院");
        tabIndicators.add("香蕉直邮");
        tabIndicators.add("花粉社区");
        tabIndicators.add("我的");

        tabImageIcons = new ArrayList<>();
        tabImageIcons.add(R.drawable.icon_homepage);
        tabImageIcons.add(R.drawable.icon_business_school);
        tabImageIcons.add(R.drawable.icon_post);
        tabImageIcons.add(R.drawable.icon_pollen_comminity);
        tabImageIcons.add(R.drawable.icon_myself);

        tabFragment = new ArrayList<>();
        tabFragment.add(HomePageFragment.newInstance(tabIndicators.get(0)));
        tabFragment.add(BusinessSchoolFragment.newInstance(tabIndicators.get(1)));
        tabFragment.add(BananaPostFragment.newInstance(tabIndicators.get(2)));
        tabFragment.add(BananaPostFragment.newInstance(tabIndicators.get(3)));
        tabFragment.add(MyselfFragment.newInstance(tabIndicators.get(4)));

        contentPageAdapter = new ContentPageAdapter(getSupportFragmentManager());
        vp_content.setAdapter(contentPageAdapter);
    }

    private void initTab(){
        tl_tab.setTabMode(TabLayout.MODE_FIXED);//模式，有自适应，有固定位置，有可滑动
        tl_tab.setSelectedTabIndicatorHeight(0);
        ViewCompat.setElevation(tl_tab,10);
        tl_tab.setupWithViewPager(vp_content);
        for (int i=0;i<tabIndicators.size();i++){
            TabLayout.Tab itemTab = tl_tab.getTabAt(i);
            if (itemTab != null) {
                itemTab.setCustomView(R.layout.item_tab_layout);
                ImageView iv_menu_item = itemTab.getCustomView().findViewById(R.id.iv_menu_item);
                TextView tv_menu_item = itemTab.getCustomView().findViewById(R.id.tv_menu_item);

                iv_menu_item.setImageResource(tabImageIcons.get(i));
                tv_menu_item.setText(tabIndicators.get(i));
            }
        }

        if (tl_tab != null) {
            Objects.requireNonNull(Objects.requireNonNull(tl_tab.getTabAt(0)).getCustomView()).setSelected(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private class ContentPageAdapter extends FragmentPagerAdapter{

        ContentPageAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return tabFragment.get(position);
        }

        @Override
        public int getCount() {
            return tabFragment.size();
        }
    }

    private void showADDialog(){
        final Dialog dialog = new Dialog(mContext,R.style.fullscreen_dialog);
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.layout_advertisement,null);
        ImageView iv_ad = dialogView.findViewById(R.id.iv_ad);
        ImageView iv_ad_close = dialogView.findViewById(R.id.iv_ad_close);

        Glide.with(mContext).load("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1413850288,4178035487&fm=26&gp=0.jpg").diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_ad);
        //点击事件
        dialogView.setOnClickListener(view -> dialog.dismiss());
        iv_ad.setOnClickListener(view -> {
            Info.showToastTip(mContext,"进入广告",true);
            dialog.dismiss();
        });
        iv_ad_close.setOnClickListener(view -> dialog.dismiss());

        dialog.setContentView(dialogView);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(true);//点击其他区域可消失
        dialog.setCancelable(true);//可取消
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }
}
