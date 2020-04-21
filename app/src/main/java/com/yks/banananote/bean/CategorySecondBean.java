package com.yks.banananote.bean;

/**
 * 描述：分类页面具体每个item的bean
 * 作者：zzh
 * time:2020/04/10
 */
public class CategorySecondBean {

    private String imgUrl;
    private String itemName;

    public CategorySecondBean(String imgUrl, String itemName) {
        this.imgUrl = imgUrl;
        this.itemName = itemName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
