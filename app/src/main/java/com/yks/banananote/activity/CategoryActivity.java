package com.yks.banananote.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.yks.banananote.R;
import com.yks.banananote.adapter.BaseRecyclerAdapter;
import com.yks.banananote.adapter.ImageAdapter;
import com.yks.banananote.bean.CategoryFirstBean;
import com.yks.banananote.bean.CategorySecondBean;
import com.yks.banananote.tools.Info;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：分类页面
 * 作者：zzh
 * time:2020/04/10
 */
public class CategoryActivity extends BaseActivity {

    private Context mContext = CategoryActivity.this;
    private Activity mActivity = CategoryActivity.this;

    private final int SHOW_BANNER = 0;
    private final int SHOW_CATEGORY = 1;
    private Handler handler;
    private Banner banner_category;
    private  RecyclerView rv_category_first,rv_category_second;
    private CategoryFirstAdapter firstAdapter;
    private CategorySecondAdapter secondAdapter;
    private List<CategoryFirstBean> firstList = new ArrayList<>();
    private List<CategorySecondBean> secondList = new ArrayList<>();
    private List<Integer> bannerImages;

    private String firstName;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        handler = getHandler();

        initView();
    }

    private void initView() {
        Info.setTitle(mActivity,"分类");

        rv_category_first = findViewById(R.id.rv_category_first);
        rv_category_second = findViewById(R.id.rv_category_second);
        banner_category = findViewById(R.id.banner_category);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (firstList == null || firstList.size() == 0){
            initData();
            initBannerData();
        }
    }

    @Override
    public void handleMessageBack(Message msg) {
        super.handleMessageBack(msg);
        if (msg.what == SHOW_BANNER){
            banner_category.setAdapter(new ImageAdapter(bannerImages));
            banner_category.setIndicator(new CircleIndicator(mContext))
                    .setIndicatorSelectedColor(Color.RED)
                    .setIndicatorNormalColor(Color.WHITE)
                    .setIndicatorGravity(IndicatorConfig.Direction.CENTER)
                    .setDelayTime(3000)
                    .setScrollTime(800)
                    .isAutoLoop(true)
                    .setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(Object data, int position) {
                            Info.showToastTip(mContext,"第"+(position+1)+"张图",true);
                        }
                    }).start();
        }else if (msg.what == SHOW_CATEGORY){
            if (firstList.size() != 0) {
                firstName = firstList.get(0).getCategoryName();
                firstAdapter = new CategoryFirstAdapter(firstList);
                Info.setRecycviewAdapter(mContext, rv_category_first, firstAdapter, false);
                firstAdapter.setOnItemClickListner(new BaseRecyclerAdapter.OnItemClickListner() {
                    @Override
                    public void onItemClickListner(View v, int position) {
                        firstName = firstList.get(position).getCategoryName();
                        for (CategoryFirstBean bean : firstList) {
                            if (firstName.equals(bean.getCategoryName())) {
                                bean.setSelect(true);
                            } else {
                                bean.setSelect(false);
                            }
                        }
                        firstAdapter.refresh(firstList);
                        secondList = firstList.get(position).getList();
                        secondAdapter.refresh(secondList);
                    }
                });

                secondList = firstList.get(0).getList();
                secondAdapter = new CategorySecondAdapter(secondList);
                GridLayoutManager manager = new GridLayoutManager(mContext,3);
                rv_category_second.setLayoutManager(manager);
                rv_category_second.setAdapter(secondAdapter);
                secondAdapter.setOnItemClickListner(new BaseRecyclerAdapter.OnItemClickListner() {
                    @Override
                    public void onItemClickListner(View v, int position) {
                        Info.showToastTip(mContext,secondList.get(position).getItemName(),true);
                    }
                });
            }
        }
    }

    /**
     * 描述：卡券页面要显示banner
     * 作者：zzh
     */
    private void initBannerData(){
        bannerImages = new ArrayList<>();
        bannerImages.add(R.mipmap.img_sob_sound_bg1);
        bannerImages.add(R.mipmap.img_sob_sound_bg2);

        handler.sendEmptyMessage(SHOW_BANNER);
    }

    private void initData(){
        //todo 母婴
        List<CategorySecondBean> list1 = new ArrayList<>();
        list1.add(new CategorySecondBean("https://cdn.datta.store/auxapi/files/image/alcohol-fi_wkEKr7k.png","童鞋"));
        list1.add(new CategorySecondBean("https://cdn.datta.store/auxapi/files/image/papel-fi.png","童装"));
        list1.add(new CategorySecondBean("https://cdn.datta.store/auxapi/files/image/fever-fi.png","玩具书籍"));
        list1.add(new CategorySecondBean("https://cdn.datta.store/auxapi/files/image/jabon-fi.png","婴童寝居"));
        list1.add(new CategorySecondBean("https://cdn.datta.store/auxapi/files/image/fever2-fi.png","婴童洗护"));
        list1.add(new CategorySecondBean("https://cdn.datta.store/auxapi/files/image/mask-fi.png","喂养用品"));
        list1.add(new CategorySecondBean("https://cdn.datta.store/auxapi/files/image/mask-only-fi.png","出行产品"));
        list1.add(new CategorySecondBean("https://cdn.datta.store/auxapi/files/image/doctor-fi.png","孕产用品"));
        list1.add(new CategorySecondBean("https://cdn.datta.store/auxapi/files/image/virus.png","纸尿裤"));
        list1.add(new CategorySecondBean("https://cdn.datta.store/auxapi/files/image/tos-1-fi.png","婴童湿巾"));
        list1.add(new CategorySecondBean("https://cdn.datta.store/auxapi/files/image/tos2-fi.png","妈咪背包婴带"));
        list1.add(new CategorySecondBean("https://findicons.com/files/icons/2841/very_emotional_emoticons_remastered_2014/128/26_y.png","安全座椅"));
        list1.add(new CategorySecondBean("https://findicons.com/files/icons/2841/very_emotional_emoticons_remastered_2014/128/39_y.png","童桌童床"));

        firstList.add(new CategoryFirstBean(true,"母婴",list1));
        //todo 食品
        List<CategorySecondBean> list2 = new ArrayList<>();
        list2.add(new CategorySecondBean("https://findicons.com/files/icons/2841/very_emotional_emoticons_remastered_2014/128/37_y.png","休闲零食"));
        list2.add(new CategorySecondBean("https://findicons.com/files/icons/2841/very_emotional_emoticons_remastered_2014/128/14_y.png","新鲜蔬果"));
        list2.add(new CategorySecondBean("https://findicons.com/files/icons/2841/very_emotional_emoticons_remastered_2014/128/24_y.png","海鲜水产"));
        list2.add(new CategorySecondBean("https://findicons.com/files/icons/2841/very_emotional_emoticons_remastered_2014/128/55_y.png","精选肉类"));
        list2.add(new CategorySecondBean("https://findicons.com/files/icons/2841/very_emotional_emoticons_remastered_2014/128/31_y.png","进口零食"));
        list2.add(new CategorySecondBean("https://findicons.com/files/icons/2841/very_emotional_emoticons_remastered_2014/128/01_y.png","乳品冲饮"));
        list2.add(new CategorySecondBean("https://findicons.com/files/icons/2841/very_emotional_emoticons_remastered_2014/128/36_y.png","粮油米面"));
        list2.add(new CategorySecondBean("https://findicons.com/files/icons/2841/very_emotional_emoticons_remastered_2014/128/42_y.png","保健食品"));
        list2.add(new CategorySecondBean("https://findicons.com/files/icons/2841/very_emotional_emoticons_remastered_2014/128/68_y.png","滋补营养"));
        list2.add(new CategorySecondBean("https://findicons.com/files/icons/2841/very_emotional_emoticons_remastered_2014/128/23_y.png","中外名酒"));

        firstList.add(new CategoryFirstBean(false,"食品",list2));
        //todo 女装
        List<CategorySecondBean> list3 = new ArrayList<>();
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/favourite.png","连衣裙"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/phone.png","卫衣"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/search.png","外套"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/edit.png","针织毛衣"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/location_pin.png","T恤衬衫"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/suitcase.png","风衣"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/pin.png","休闲裤"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/battery.png","牛仔裤"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/house.png","毛呢外套"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/calculator.png","羽绒服"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/flipchart.png","套装"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/file.png","半身裙"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/download.png","棉衣/棉服"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/id.png","妈妈装"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/monitor.png","大码女装"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/graph.png","礼服"));
        list3.add(new CategorySecondBean("https://findicons.com/files/icons/2813/flat_jewels/128/camera.png","职业装"));

        firstList.add(new CategoryFirstBean(false,"女装",list3));
        //todo 百货
        List<CategorySecondBean> list4 = new ArrayList<>();
        list4.add(new CategorySecondBean("https://findicons.com/files/icons/2817/academic/128/globe.png","办公设备耗材"));
        list4.add(new CategorySecondBean("https://findicons.com/files/icons/2817/academic/128/books.png","驱蚊灭虫"));
        list4.add(new CategorySecondBean("https://findicons.com/files/icons/2817/academic/128/mortarboard.png","学生文具"));
        list4.add(new CategorySecondBean("https://findicons.com/files/icons/2817/academic/128/palette.png","居家布艺"));
        list4.add(new CategorySecondBean("https://findicons.com/files/icons/2817/academic/128/backpack.png","日用百货"));
        list4.add(new CategorySecondBean("https://findicons.com/files/icons/2817/academic/128/math.png","图书阅读"));
        list4.add(new CategorySecondBean("https://findicons.com/files/icons/2817/academic/128/dictionary.png","收纳储物"));
        list4.add(new CategorySecondBean("https://findicons.com/files/icons/2817/academic/128/blackboard.png","绿植园艺"));
        list4.add(new CategorySecondBean("https://findicons.com/files/icons/2817/academic/128/calculator.png","汽车用品"));
        list4.add(new CategorySecondBean("https://findicons.com/files/icons/2817/academic/128/science.png","宠物用品"));
        list4.add(new CategorySecondBean("https://findicons.com/files/icons/2817/academic/128/science.png","乐器"));

        firstList.add(new CategoryFirstBean(false,"百货",list4));
        //todo 家电
        List<CategorySecondBean> list5 = new ArrayList<>();
        list5.add(new CategorySecondBean("https://findicons.com/files/icons/2801/online_payment_icons/102/visa_six_revisions.png","大家电"));
        list5.add(new CategorySecondBean("https://findicons.com/files/icons/2801/online_payment_icons/102/western_union_six_revisions.png","厨房电器"));
        list5.add(new CategorySecondBean("https://findicons.com/files/icons/2801/online_payment_icons/102/solo_six_revisions.png","生活用具"));
        list5.add(new CategorySecondBean("https://findicons.com/files/icons/2801/online_payment_icons/102/2co_six_revisions.png","家庭影音"));
        list5.add(new CategorySecondBean("https://findicons.com/files/icons/2801/online_payment_icons/102/discover_six_revisions.png","商用电器"));
        list5.add(new CategorySecondBean("https://findicons.com/files/icons/2801/online_payment_icons/102/skrill_six_revisions.png","音响/音箱"));
        list5.add(new CategorySecondBean("https://findicons.com/files/icons/2801/online_payment_icons/102/google_wallet_six_revisions.png","其他日常用品"));

        firstList.add(new CategoryFirstBean(false,"家电",list5));
        //todo 家居
        List<CategorySecondBean> list6 = new ArrayList<>();
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/solo.png","床上用品"));
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/paypal.png","餐具"));
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/direct_debit.png","家具"));
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/mastercard.png","烹饪用具"));
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/visa_electron.png","五金卫浴"));
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/visa.png","厨房配件"));
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/ach.png","储物收纳"));
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/discover.png","灯饰光源"));
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/american_express.png","酒具"));
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/diners_club.png","茶具"));
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/switch.png","一次性用品"));
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/2co.png","杯壶"));
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/jcb.png","烘焙烧烤"));
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/delta.png","五金工具"));
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/maestro.png","开关插座"));
        list6.add(new CategorySecondBean("https://findicons.com/files/icons/2780/payment_card_glyphs/64/moneybookers.png","安防监控"));

        firstList.add(new CategoryFirstBean(false,"家居",list6));
        //todo 美妆
        List<CategorySecondBean> list7 = new ArrayList<>();
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_date.png","护肤套餐"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_bells.png","面膜"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_ornament.png","乳液面霜"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_giftbox.png","洁面奶"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_star.png","身体护理"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_house.png","精华液"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_bottles.png","卸妆水"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_cap.png","防晒霜"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_stick.png","唇部护理"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_tree.png","T区护理"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_cake.png","男士护肤"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_berries.png","粉底/隔离"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_flake.png","口红"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_snowman.png","腮红"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_candle.png","眼影"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_clock.png","香水"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_balloon.png","眉笔"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_happy_new_year.png","眼线笔"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/64/xmas_tree1.png","指甲油"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_cake.png","美甲套装"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_cap.png","彩妆套餐"));
        list7.add(new CategorySecondBean("https://findicons.com/files/icons/2763/christmas_new_year_icons/128/xmas_house.png","美容工具"));

        firstList.add(new CategoryFirstBean(false,"美妆",list7));
        //todo 洗护
        List<CategorySecondBean> list8 = new ArrayList<>();
        list8.add(new CategorySecondBean("https://findicons.com/files/icons/2759/e_commerce/128/hanger.png","家庭清洁"));
        list8.add(new CategorySecondBean("https://findicons.com/files/icons/2759/e_commerce/128/discount.png","洗发造型"));
        list8.add(new CategorySecondBean("https://findicons.com/files/icons/2759/e_commerce/128/lock.png","纸品湿巾"));
        list8.add(new CategorySecondBean("https://findicons.com/files/icons/2759/e_commerce/128/calculator.png","口腔护理"));
        list8.add(new CategorySecondBean("https://findicons.com/files/icons/2759/e_commerce/128/gift_box.png","女性护理"));
        list8.add(new CategorySecondBean("https://findicons.com/files/icons/2759/e_commerce/128/sales.png","衣物清洁"));
        list8.add(new CategorySecondBean("https://findicons.com/files/icons/2759/e_commerce/128/creditcard.png","身体护理"));
        list8.add(new CategorySecondBean("https://findicons.com/files/icons/2759/e_commerce/128/bag.png","男士个护"));

        firstList.add(new CategoryFirstBean(false,"洗护",list8));
        //todo 内衣
        List<CategorySecondBean> list9 = new ArrayList<>();
        list9.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/no.png","文胸"));
        list9.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/il.png","文胸套装"));
        list9.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/na.png","内裤"));
        list9.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/ne.png","保暖内衣"));
        list9.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/nf.png","睡衣/家居服"));
        list9.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/ng.png","袜子"));
        list9.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/nz.png","塑身衣"));
        list9.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/kp.png","背心/T恤"));
        list9.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/np.png","文胸配件"));
        list9.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/kw.png","其他"));

        firstList.add(new CategoryFirstBean(false,"内衣",list9));
        //todo 女鞋
        List<CategorySecondBean> list10 = new ArrayList<>();
        list10.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/nu.png","女士休闲鞋"));
        list10.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/fr.png","板鞋"));
        list10.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/io.png","运动鞋"));
        list10.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/gr_cy.png","高跟鞋"));
        list10.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/kz.png","靴子"));
        list10.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/fi.png","帆布鞋"));
        list10.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/fj.png","高帮鞋"));
        list10.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/fk.png","低帮鞋"));
        list10.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/fm.png","凉鞋"));
        list10.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/fo.png","拖鞋"));

        firstList.add(new CategoryFirstBean(false,"女鞋",list10));
        //todo 数码
        List<CategorySecondBean> list11 = new ArrayList<>();
        list11.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/sz.png","智能设备"));
        list11.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/sy.png","游戏机/外设"));
        list11.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/kg.png","笔记本/配件"));
        list11.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/ke.png","台式电脑"));
        list11.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/sr.png","手机配件"));
        list11.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/ki.png","电脑硬件外设"));
        list11.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/kh.png","相机"));
        list11.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/sv.png","路由器"));
        list11.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/km.png","MP3录影机"));
        list11.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/fj.png","耳机/配件"));
        list11.add(new CategorySecondBean("https://findicons.com/files/icons/2757/flags_of_the_world/256/kp.png","其他数码产品"));

        firstList.add(new CategoryFirstBean(false,"数码",list11));
        //todo 箱包
        List<CategorySecondBean> list12 = new ArrayList<>();
        list12.add(new CategorySecondBean("https://findicons.com/files/icons/2751/star_burst_social_icons/64/stumbleupon.png","女包"));
        list12.add(new CategorySecondBean("https://findicons.com/files/icons/2751/star_burst_social_icons/64/pinterest.png","男包"));
        list12.add(new CategorySecondBean("https://findicons.com/files/icons/2751/star_burst_social_icons/64/blogger.png","双肩包"));
        list12.add(new CategorySecondBean("https://findicons.com/files/icons/2751/star_burst_social_icons/64/twitter.png","旅行箱包"));
        list12.add(new CategorySecondBean("https://findicons.com/files/icons/2751/star_burst_social_icons/64/google_plus.png","钱包"));
        list12.add(new CategorySecondBean("https://findicons.com/files/icons/2751/star_burst_social_icons/64/youtube.png","腕表"));
        list12.add(new CategorySecondBean("https://findicons.com/files/icons/2751/star_burst_social_icons/64/linkedin.png","眼镜"));
        list12.add(new CategorySecondBean("https://findicons.com/files/icons/2751/star_burst_social_icons/64/facebook.png","黄金首饰"));
        list12.add(new CategorySecondBean("https://findicons.com/files/icons/2751/star_burst_social_icons/64/friendfeed.png","钻石珠宝"));
        list12.add(new CategorySecondBean("https://findicons.com/files/icons/2751/star_burst_social_icons/64/dribbble.png","珍珠翡翠"));
        list12.add(new CategorySecondBean("https://findicons.com/files/icons/2751/star_burst_social_icons/64/digg.png","时尚饰品"));

        firstList.add(new CategoryFirstBean(false,"箱包",list12));
        //todo 运动
        List<CategorySecondBean> list13 = new ArrayList<>();
        list13.add(new CategorySecondBean("https://findicons.com/files/icons/2750/free_web_icon_pack_1/128/map.png","运动鞋服"));
        list13.add(new CategorySecondBean("https://findicons.com/files/icons/2750/free_web_icon_pack_1/128/briefcase.png","运动配件"));
        list13.add(new CategorySecondBean("https://findicons.com/files/icons/2750/free_web_icon_pack_1/128/bookmark.png","运动用具"));
        list13.add(new CategorySecondBean("https://findicons.com/files/icons/2750/free_web_icon_pack_1/128/mail.png","户外鞋服"));
        list13.add(new CategorySecondBean("https://findicons.com/files/icons/2750/free_web_icon_pack_1/128/earth.png","户外装备"));
        list13.add(new CategorySecondBean("https://findicons.com/files/icons/2750/free_web_icon_pack_1/128/news.png","骑行装备"));
        list13.add(new CategorySecondBean("https://findicons.com/files/icons/2750/free_web_icon_pack_1/128/address_book.png","瑜伽"));
        list13.add(new CategorySecondBean("https://findicons.com/files/icons/2750/free_web_icon_pack_1/128/help.png","游泳"));
        list13.add(new CategorySecondBean("https://findicons.com/files/icons/2750/free_web_icon_pack_1/128/logo.png","钓鱼"));

        firstList.add(new CategoryFirstBean(false,"运动",list13));
        //todo 男装
        List<CategorySecondBean> list14 = new ArrayList<>();
        list14.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/instagram.png","T恤"));
        list14.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/calculator.png","衬衫"));
        list14.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/apariencia.png","卫衣"));
        list14.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/nyanphone.png","夹克"));
        list14.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/playmusic.png","男士外套"));
        list14.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/pantalla.png","男士西装"));
        list14.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/home.png","风衣"));
        list14.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/gimp.png","羽绒服"));
        list14.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/microphone.png","休闲裤"));
        list14.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/com_android_calendar.png","牛仔裤"));
        list14.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/contacts.png","针织衫/毛衣"));
        list14.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/media.png","爸爸装"));
        list14.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/deadbeef.png","套装"));
        list14.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/aviary.png","职业装"));

        firstList.add(new CategoryFirstBean(false,"男装",list14));
        //todo 男鞋
        List<CategorySecondBean> list15 = new ArrayList<>();
        list15.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/lightread.png","布鞋"));
        list15.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/camera.png","商务休闲"));
        list15.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/photoshoppe.png","正装鞋"));
        list15.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/googlechrome.png","休闲鞋"));
        list15.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/speaker.png","靴子"));
        list15.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/speaker.png","高帮鞋"));
        list15.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/email.png","拖鞋"));
        list15.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/play.png","凉鞋"));
        list15.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/facebookmessenger.png","帆布鞋"));
        list15.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/templerun.png","低帮鞋"));
        list15.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/googlemaps.png","其他"));
        list15.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/evernote.png","增高鞋"));
        list15.add(new CategorySecondBean("https://findicons.com/files/icons/2745/sugar/128/adobereader.png","雨鞋"));

        firstList.add(new CategoryFirstBean(false,"男鞋",list15));
        //todo 成人
        List<CategorySecondBean> list16 = new ArrayList<>();
        list16.add(new CategorySecondBean("https://findicons.com/files/icons/2748/santa_claus_icons_2/128/ded1.png","计生用品"));
        list16.add(new CategorySecondBean("https://findicons.com/files/icons/2748/santa_claus_icons_2/128/ded3.png","避孕套"));
        list16.add(new CategorySecondBean("https://findicons.com/files/icons/2748/santa_claus_icons_2/128/ded2.png","情爱玩具"));
        list16.add(new CategorySecondBean("https://findicons.com/files/icons/2748/santa_claus_icons_2/128/ded5.png","情趣内衣"));
        list16.add(new CategorySecondBean("https://findicons.com/files/icons/2748/santa_claus_icons_2/128/ded4.png","女用玩具"));

        firstList.add(new CategoryFirstBean(false,"成人",list16));
        //todo 萌宠
        List<CategorySecondBean> list17 = new ArrayList<>();
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2737/chinese_new_year_icons/512/gcds_dragon_256.png","萌猫"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2737/chinese_new_year_icons/64/gcds_orange.png","萌狗"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2737/chinese_new_year_icons/128/gcds_firecracker.png","鱼/水族"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2737/chinese_new_year_icons/128/gcds_gold_ingot.png","鸟"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2737/chinese_new_year_icons/128/gcds_fish.png","宠物美容"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2737/chinese_new_year_icons/128/gcds_fudao.png","宠物服饰"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2737/chinese_new_year_icons/128/gcds_lantern.png","宠物清洁"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2737/chinese_new_year_icons/128/gcds_red_envelope.png","护理保健"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2736/halloween_theme/128/jack_o_lantern.png","食具食品"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2736/halloween_theme/128/black_cat.png","玩具"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2736/halloween_theme/128/skull.png","兔"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2736/halloween_theme/128/bubbling_cauldron.png","豚鼠"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2736/halloween_theme/128/spider.png","宠物窝"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2736/halloween_theme/128/grim_reaper.png","飞鼠"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2736/halloween_theme/128/witch_hat.png","仓鼠"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2736/halloween_theme/128/floating_candles.png","香猪"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2736/halloween_theme/128/bones.png","龟"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2736/halloween_theme/128/haunted_house.png","其他"));
        list17.add(new CategorySecondBean("https://findicons.com/files/icons/2736/halloween_theme/128/bat_1287.png","其他宠物"));

        firstList.add(new CategoryFirstBean(false,"萌宠",list17));
        //todo 卡券
        List<CategorySecondBean> list18 = new ArrayList<>();
        list18.add(new CategorySecondBean("https://findicons.com/files/icons/2715/community_icons_and_forum_rank_icons/128/moderator.png","娱乐充值"));
        list18.add(new CategorySecondBean("https://findicons.com/files/icons/2715/community_icons_and_forum_rank_icons/128/golden_member.png","纺织面料"));
        list18.add(new CategorySecondBean("https://findicons.com/files/icons/2715/community_icons_and_forum_rank_icons/128/admin.png","动漫周边"));
        list18.add(new CategorySecondBean("https://findicons.com/files/icons/2715/community_icons_and_forum_rank_icons/128/banned.png","医疗器械"));
        list18.add(new CategorySecondBean("https://findicons.com/files/icons/2715/community_icons_and_forum_rank_icons/128/musician.png","搬运包装"));
        list18.add(new CategorySecondBean("https://findicons.com/files/icons/2715/community_icons_and_forum_rank_icons/128/donator.png","农用机械"));
        list18.add(new CategorySecondBean("https://findicons.com/files/icons/2715/community_icons_and_forum_rank_icons/128/vip.png","国内景点门票"));
        list18.add(new CategorySecondBean("https://findicons.com/files/icons/2715/community_icons_and_forum_rank_icons/128/stuff.png","个性定制服务"));
        list18.add(new CategorySecondBean("https://findicons.com/files/icons/2715/community_icons_and_forum_rank_icons/128/basic.png","本地生活服务"));
        list18.add(new CategorySecondBean("https://findicons.com/files/icons/2715/community_icons_and_forum_rank_icons/128/guest.png","其他"));

        firstList.add(new CategoryFirstBean(false,"卡券",list18));

        handler.sendEmptyMessage(SHOW_CATEGORY);
    }
    private class CategoryFirstAdapter extends BaseRecyclerAdapter<CategoryFirstBean> {

        CategoryFirstAdapter(List<CategoryFirstBean> mDatas) {
            super(mDatas, R.layout.item_category_first);
        }

        @Override
        protected void bindData(BaseViewHolder holder, int position, CategoryFirstBean bean) {
            TextView txt_category_first_item = (TextView) holder.getView(R.id.txt_category_first_item);

            txt_category_first_item.setText(bean.getCategoryName());
            if (bean.isSelect()){
                txt_category_first_item.setTextColor(Color.WHITE);
                txt_category_first_item.setBackground(getResources().getDrawable(R.drawable.homepage_bottom_login_btn_bg));
            }else {
                txt_category_first_item.setTextColor(Color.BLACK);
                txt_category_first_item.setBackground(null);
            }
        }
    }

    private class CategorySecondAdapter extends BaseRecyclerAdapter<CategorySecondBean>{

        CategorySecondAdapter(List<CategorySecondBean> mDatas) {
            super(mDatas, R.layout.item_category_second);
        }

        @Override
        protected void bindData(BaseViewHolder holder, int position, CategorySecondBean bean) {
            ImageView iv_careful_selected_item = (ImageView) holder.getView(R.id.iv_careful_selected_item);
            TextView txt_careful_selected_item = (TextView) holder.getView(R.id.txt_careful_selected_item);

            txt_careful_selected_item.setText(bean.getItemName());
            Glide.with(mContext)
                    .load(bean.getImgUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(requestListener)
                    .into(iv_careful_selected_item);
            if (firstName.equals("卡券")){
                banner_category.setVisibility(View.VISIBLE);
            }else {
                banner_category.setVisibility(View.GONE);
            }
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

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Info.clearList(firstList);
        Info.clearList(secondList);
        Glide.get(mContext).clearMemory();
        handler.removeCallbacksAndMessages(null);
    }
}
