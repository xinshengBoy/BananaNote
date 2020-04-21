package com.yks.banananote.bean;

/**
 * 描述：好货优选bean
 * 作者：zzh
 * time:2020/04/13
 */
public class GoodHotBean {

    private String goodImageUrl;//图片地址
    private String goodTitle;//标题头
    private String goodIntroduce;//简介
    private String goodPriceSelf;//券后价
    private String goodPrice;//券前价
    private String goodCount;//购买人数
    private String goodCoupon;//券

    public GoodHotBean(String goodImageUrl, String goodTitle, String goodIntroduce, String goodPriceSelf, String goodPrice, String goodCount, String goodCoupon) {
        this.goodImageUrl = goodImageUrl;
        this.goodTitle = goodTitle;
        this.goodIntroduce = goodIntroduce;
        this.goodPriceSelf = goodPriceSelf;
        this.goodPrice = goodPrice;
        this.goodCount = goodCount;
        this.goodCoupon = goodCoupon;
    }

    public String getGoodImageUrl() {
        return goodImageUrl;
    }

    public void setGoodImageUrl(String goodImageUrl) {
        this.goodImageUrl = goodImageUrl;
    }

    public String getGoodTitle() {
        return goodTitle;
    }

    public void setGoodTitle(String goodTitle) {
        this.goodTitle = goodTitle;
    }

    public String getGoodIntroduce() {
        return goodIntroduce;
    }

    public void setGoodIntroduce(String goodIntroduce) {
        this.goodIntroduce = goodIntroduce;
    }

    public String getGoodPriceSelf() {
        return goodPriceSelf;
    }

    public void setGoodPriceSelf(String goodPriceSelf) {
        this.goodPriceSelf = goodPriceSelf;
    }

    public String getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(String goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(String goodCount) {
        this.goodCount = goodCount;
    }

    public String getGoodCoupon() {
        return goodCoupon;
    }

    public void setGoodCoupon(String goodCoupon) {
        this.goodCoupon = goodCoupon;
    }
}
