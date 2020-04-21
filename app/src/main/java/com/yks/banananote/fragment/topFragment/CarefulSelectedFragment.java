package com.yks.banananote.fragment.topFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.superluo.textbannerlibrary.ITextBannerItemClickListener;
import com.superluo.textbannerlibrary.TextBannerView;
import com.yks.banananote.R;
import com.yks.banananote.adapter.BaseRecyclerAdapter;
import com.yks.banananote.adapter.ImageAdapter;
import com.yks.banananote.adapter.ImageBannerAdapter;
import com.yks.banananote.bean.CarefulSelectedFirstBean;
import com.yks.banananote.bean.GoodHotBean;
import com.yks.banananote.custom.SnapUpCountDownTimerView;
import com.yks.banananote.fragment.BaseFragment;
import com.yks.banananote.tools.Info;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.transformer.DepthPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：精选
 * 作者：zzh
 * time:2020/04/09
 */
public class CarefulSelectedFragment extends BaseFragment {

    private final int SHOW_BANNER_AD = 0;//加载广告图片
    private final int SHOW_CATEGORY_LIST = 1;//加载分类列表
    private final int SHOW_BANNER_TIP = 2;//加载由下而上的滚动tip广告
    private final int SHOW_BANNER_SECOND_AD = 3;//加载第二个广告benner
    private Handler handler;

    private List<Integer> bannerImages;
    private List<Integer> bannerSecondImages;
    private List<Integer> bannerVerticalImages;
    private List<CarefulSelectedFirstBean> categoryList;
    private List<String> bannerTips;
    private List<GoodHotBean> goodHotList;

    private Banner banner_careful_selected,banner_careful_selected_second,banner_careful_vertical;
    private RecyclerView rv_careful_selected_top,rv_goodhot;
    private TextBannerView tv_banner_tip;
    private SnapUpCountDownTimerView view_snap_countdown;

    public static CarefulSelectedFragment newInstance(String content){
        CarefulSelectedFragment carefulSelectedFragment = new CarefulSelectedFragment();
        return carefulSelectedFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = getHandler();
        //todo 加载banner图
        initBannerData();
        //todo 加载分类列表数据
        initCategoryData();
        //todo 加载banner的tip广告
        initBannerTip();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_careful_selected,null);
        banner_careful_selected = view.findViewById(R.id.banner_careful_selected);
        rv_careful_selected_top = view.findViewById(R.id.rv_careful_selected_top);
        tv_banner_tip = view.findViewById(R.id.tv_banner_tip);
        banner_careful_selected_second = view.findViewById(R.id.banner_careful_selected_second);
        banner_careful_vertical = view.findViewById(R.id.banner_careful_vertical);
        view_snap_countdown = view.findViewById(R.id.view_snap_countdown);
        rv_goodhot = view.findViewById(R.id.rv_goodhot);
        handler.sendEmptyMessage(SHOW_BANNER_AD);
        return view;
    }

    private void initBannerData(){
        //todo 顶部的广告banner
        bannerImages = new ArrayList<>();
        bannerImages.add(R.mipmap.banner1);
        bannerImages.add(R.mipmap.banner2);
        bannerImages.add(R.mipmap.banner3);
        bannerImages.add(R.mipmap.banner4);
        bannerImages.add(R.mipmap.banner5);
        bannerImages.add(R.mipmap.banner6);
        bannerImages.add(R.mipmap.banner7);
        bannerImages.add(R.mipmap.banner8);
        //todo 中间的第二个banner
        bannerSecondImages = new ArrayList<>();
        bannerSecondImages.add(R.mipmap.second_banner_1);
        bannerSecondImages.add(R.mipmap.second_banner_2);
        bannerSecondImages.add(R.mipmap.second_banner_3);
        bannerSecondImages.add(R.mipmap.second_banner_4);
        //todo 第三个banner，上下滚动
        bannerVerticalImages = new ArrayList<>();
        bannerVerticalImages.add(R.mipmap.second_banner_3);
        bannerVerticalImages.add(R.mipmap.second_banner_4);
    }

    private void initCategoryData(){
        categoryList = new ArrayList<>();
        categoryList.add(new CarefulSelectedFirstBean(R.mipmap.careful_list_0,"9.9包邮"));
        categoryList.add(new CarefulSelectedFirstBean(R.mipmap.careful_list_1,"省钱秘笈"));
        categoryList.add(new CarefulSelectedFirstBean(R.mipmap.careful_list_2,"高佣精选"));
        categoryList.add(new CarefulSelectedFirstBean(R.mipmap.careful_list_3,"辣妈星选"));
        categoryList.add(new CarefulSelectedFirstBean(R.mipmap.careful_list_4,"1元抢购"));
        categoryList.add(new CarefulSelectedFirstBean(R.mipmap.careful_list_5,"天猫超时"));
        categoryList.add(new CarefulSelectedFirstBean(R.mipmap.careful_list_6,"天猫国际"));
        categoryList.add(new CarefulSelectedFirstBean(R.mipmap.careful_list_7,"京东精选"));
        categoryList.add(new CarefulSelectedFirstBean(R.mipmap.careful_list_8,"课代表"));
        categoryList.add(new CarefulSelectedFirstBean(R.mipmap.careful_list_9,"外卖"));

        goodHotList = new ArrayList<>();
        goodHotList.add(new GoodHotBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_date.png","【卡姿兰】双头极细眉笔","【券后仅54元】薇娅都吹爆的眉笔，送100片卸妆棉~手残党0压力！","Y64","Y69","2079人已购买","5元券"));
        goodHotList.add(new GoodHotBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_bells.png","【大希地】整切牛排10片","【顺丰冷链】下单就送价值16元黑椒汁一袋，前20000名还送价值39元的煎锅一个","Y149","Y199","11万人已购买","50元券"));
        goodHotList.add(new GoodHotBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_giftbox.png","【晚安】泰国乳胶正头一只装","【拍下立减10元，到手价仅79元】线下门店同款在售价都要499元，这次不买就等着哭吧！","Y79","Y279","6525人已购买","190元券"));
    }

    private void initBannerTip(){
        bannerTips = new ArrayList<>();
        bannerTips.add("这碗酸臭味刚刚好，吃了忘不了~");
        bannerTips.add("韩都衣舍！四季款百搭休闲板鞋");
        bannerTips.add("家用必备！可孚检测血糖仪");
    }

    @Override
    public void handleMessageBack(Message msg) {
        super.handleMessageBack(msg);
        if (msg.what == SHOW_BANNER_AD){//todo 加载banner
            banner_careful_selected.setAdapter(new ImageAdapter(bannerImages));
            banner_careful_selected.setIndicator(new CircleIndicator(getContext()))
            .setIndicatorSelectedColor(Color.RED)
            .setIndicatorNormalColor(Color.WHITE)
            .setIndicatorGravity(IndicatorConfig.Direction.CENTER)
            .setDelayTime(3000)
                    .setScrollTime(800)
            .isAutoLoop(true)
            .setPageTransformer(new DepthPageTransformer())
            .setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(Object data, int position) {
                    Info.showToastTip(getContext(),"第"+(position+1)+"张图",true);
                }
            }).start();
            handler.sendEmptyMessage(SHOW_CATEGORY_LIST);
        }else if (msg.what == SHOW_CATEGORY_LIST){//todo 加载分类列表
            CategoryFirstAdapter adapter = new CategoryFirstAdapter(categoryList);
            GridLayoutManager manager = new GridLayoutManager(getContext(),5);
            rv_careful_selected_top.setLayoutManager(manager);
            rv_careful_selected_top.setAdapter(adapter);

            GoodHotAdapter goodHotAdapter = new GoodHotAdapter(goodHotList);
            Info.setRecycviewAdapter(getContext(),rv_goodhot,goodHotAdapter,false);
            goodHotAdapter.setOnItemClickListner(new BaseRecyclerAdapter.OnItemClickListner() {
                @Override
                public void onItemClickListner(View v, int position) {
                    Info.showToastTip(getContext(),goodHotList.get(position).getGoodTitle(),true);
                }
            });

            handler.sendEmptyMessage(SHOW_BANNER_TIP);
        }else if (msg.what == SHOW_BANNER_TIP){//todo 加载banner提示广告（文字）
            tv_banner_tip.setDatas(bannerTips);
            tv_banner_tip.setItemOnClickListener(new ITextBannerItemClickListener() {
                @Override
                public void onItemClick(String data, int position) {
                    Info.showToastTip(getContext(),data,true);
                }
            });
            handler.sendEmptyMessage(SHOW_BANNER_SECOND_AD);
        }else if (msg.what == SHOW_BANNER_SECOND_AD){
            banner_careful_selected_second.setAdapter(new ImageBannerAdapter(getContext(),bannerSecondImages));
            banner_careful_selected_second.isAutoLoop(true)
                    .setDelayTime(6000)
                    .setScrollTime(800)
                    .setPageTransformer(new DepthPageTransformer())
                    .setOnBannerListener((data, position) -> Info.showToastTip(getContext(),"第"+(position+1)+"张图",true)).start();

            banner_careful_vertical.setAdapter(new ImageBannerAdapter(getContext(),bannerVerticalImages));
            banner_careful_vertical.isAutoLoop(true)
                    .setDelayTime(6000)
                    .setScrollTime(800)
                    .setUserInputEnabled(false)
                    .setOnBannerListener((data, position) -> Info.showToastTip(getContext(),"第"+(position+1)+"张图",true)).start();

            //todo 设置抢购倒计时
            view_snap_countdown.setTime(23,59,59);
            view_snap_countdown.start();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        tv_banner_tip.startViewAnimator();
    }

    private class CategoryFirstAdapter extends BaseRecyclerAdapter<CarefulSelectedFirstBean> {

        CategoryFirstAdapter(List<CarefulSelectedFirstBean> mDatas) {
            super(mDatas, R.layout.item_careful_selected_first);
        }

        @Override
        protected void bindData(BaseViewHolder holder, int position, CarefulSelectedFirstBean bean) {
            ImageView iv_careful_selected_item = (ImageView) holder.getView(R.id.iv_careful_selected_item);
            TextView txt_careful_selected_item = (TextView) holder.getView(R.id.txt_careful_selected_item);

            txt_careful_selected_item.setText(bean.getIconLable());
            Glide.with(getContext()).load(bean.getIconImage()).into(iv_careful_selected_item);
        }
    }

    private class GoodHotAdapter extends BaseRecyclerAdapter<GoodHotBean>{

        protected GoodHotAdapter(List<GoodHotBean> mDatas) {
            super(mDatas, R.layout.item_careful_goodhot);
        }

        @Override
        protected void bindData(BaseViewHolder holder, int position, GoodHotBean goodHotBean) {
            ImageView iv_item_goodhot = (ImageView) holder.getView(R.id.iv_item_goodhot);

            ((TextView) holder.getView(R.id.txt_item_googhot_title)).setText(goodHotBean.getGoodTitle());
            ((TextView) holder.getView(R.id.txt_item_googhot_introduce)).setText(goodHotBean.getGoodIntroduce());
            ((TextView) holder.getView(R.id.txt_item_googhot_price_self)).setText(goodHotBean.getGoodPriceSelf());
            ((TextView) holder.getView(R.id.txt_item_googhot_price)).setText(goodHotBean.getGoodPrice());
            ((TextView) holder.getView(R.id.txt_item_googhot_buycount)).setText(goodHotBean.getGoodCount());
            ((TextView) holder.getView(R.id.txt_item_googhot_coupon)).setText(goodHotBean.getGoodCoupon());

            Glide.with(getContext()).load(goodHotBean.getGoodImageUrl()).into(iv_item_goodhot);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        tv_banner_tip.stopViewAnimator();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        view_snap_countdown.stop();
        Info.clearList(bannerImages);
        Info.clearList(bannerSecondImages);
        Info.clearList(categoryList);
        Info.clearList(bannerTips);
        Info.clearList(goodHotList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
