package com.yks.banananote.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.yks.banananote.R;
import com.yks.banananote.activity.CategoryActivity;
import com.yks.banananote.fragment.topFragment.BeautyMakeupFragment;
import com.yks.banananote.fragment.topFragment.CardTicketFragment;
import com.yks.banananote.fragment.topFragment.CarefulSelectedFragment;
import com.yks.banananote.fragment.topFragment.DepartmentStoreFragment;
import com.yks.banananote.fragment.topFragment.DigitalFragment;
import com.yks.banananote.fragment.topFragment.FoodFragment;
import com.yks.banananote.fragment.topFragment.HomeFurnishFragment;
import com.yks.banananote.fragment.topFragment.HouseElectricFragment;
import com.yks.banananote.fragment.topFragment.MomAndBabyFragment;
import com.yks.banananote.fragment.topFragment.UnderwearFragment;
import com.yks.banananote.fragment.topFragment.WashCareFragment;
import com.yks.banananote.fragment.topFragment.WomenWearFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：主页
 * 作者：zzh
 * time:2020/04/09
 * tablayout参考文档：https://www.jianshu.com/p/39a66373498c
 */
public class HomePageFragment extends BaseFragment implements View.OnClickListener {

    private TabLayout tl_tab_top;
    private ViewPager vp_content_top;
    private List<String> tabIndicators;
    private List<Fragment> tabFragments;
    private ContentPagerAdapter contentPagerAdapter;

    private EditText et_homepage_search;
    private ImageView iv_scan,iv_customer_service,iv_home_category;

    public static HomePageFragment newInstance(String content){
        HomePageFragment homePageFragment = new HomePageFragment();
        return homePageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tab_layout_top,null);

        tl_tab_top = view.findViewById(R.id.tl_tab_top);
        vp_content_top = view.findViewById(R.id.vp_content_top);
        et_homepage_search = view.findViewById(R.id.et_homepage_search);
        iv_scan = view.findViewById(R.id.iv_scan);
        iv_customer_service = view.findViewById(R.id.iv_customer_service);
        iv_home_category = view.findViewById(R.id.iv_home_category);

        initContent();
        initTab();

        iv_scan.setOnClickListener(this);
        iv_customer_service.setOnClickListener(this);
        iv_home_category.setOnClickListener(this);
        return view;
    }

    private void initContent(){
        tabIndicators = new ArrayList<>();
        tabIndicators.add("精选");
        tabIndicators.add("母婴");
        tabIndicators.add("食品");
        tabIndicators.add("女装");
        tabIndicators.add("百货");
        tabIndicators.add("家电");
        tabIndicators.add("家居");
        tabIndicators.add("美妆");
        tabIndicators.add("洗护");
        tabIndicators.add("内衣");
        tabIndicators.add("数码");
        tabIndicators.add("卡券");

        tabFragments = new ArrayList<>();
        tabFragments.add(CarefulSelectedFragment.newInstance(tabIndicators.get(0)));
        tabFragments.add(MomAndBabyFragment.newInstance(tabIndicators.get(1)));
        tabFragments.add(FoodFragment.newInstance(tabIndicators.get(2)));
        tabFragments.add(WomenWearFragment.newInstance(tabIndicators.get(3)));
        tabFragments.add(DepartmentStoreFragment.newInstance(tabIndicators.get(4)));
        tabFragments.add(HouseElectricFragment.newInstance(tabIndicators.get(5)));
        tabFragments.add(HomeFurnishFragment.newInstance(tabIndicators.get(6)));
        tabFragments.add(BeautyMakeupFragment.newInstance(tabIndicators.get(7)));
        tabFragments.add(WashCareFragment.newInstance(tabIndicators.get(8)));
        tabFragments.add(UnderwearFragment.newInstance(tabIndicators.get(9)));
        tabFragments.add(DigitalFragment.newInstance(tabIndicators.get(10)));
        tabFragments.add(CardTicketFragment.newInstance(tabIndicators.get(11)));

        assert getFragmentManager() != null;
        contentPagerAdapter = new ContentPagerAdapter(getFragmentManager());
        vp_content_top.setAdapter(contentPagerAdapter);
    }

    private void initTab(){
        tl_tab_top.setTabMode(TabLayout.MODE_SCROLLABLE);//可滑动
        tl_tab_top.setTabTextColors(getResources().getColor(R.color.colorGray2),getResources().getColor(R.color.colorDeepRed));//文字颜色，第一个是未选中文字颜色，第二个是选中文字颜色
        tl_tab_top.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorDeepRed));//选择的tab的下划线的颜色
        ViewCompat.setElevation(tl_tab_top,0);//每个tab之间的间距
        tl_tab_top.setupWithViewPager(vp_content_top);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_scan:
                Toast.makeText(view.getContext(),"扫描",Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_customer_service:
                Toast.makeText(view.getContext(),"客服",Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_home_category:
                startActivity(new Intent(getActivity(), CategoryActivity.class));
                break;
        }
    }

    private class ContentPagerAdapter extends FragmentPagerAdapter{

        ContentPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }

}
