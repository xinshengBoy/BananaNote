package com.yks.banananote.bean;

/**
 * 描述：精选页面顶部在banner下的分类列表bean
 * 作者：zzh
 * time:2020/04/09
 */
public class CarefulSelectedFirstBean {

    private int iconImage;
    private String iconLable;

    public CarefulSelectedFirstBean(int iconImage, String iconLable) {
        this.iconImage = iconImage;
        this.iconLable = iconLable;
    }

    public int getIconImage() {
        return iconImage;
    }

    public void setIconImage(int iconImage) {
        this.iconImage = iconImage;
    }

    public String getIconLable() {
        return iconLable;
    }

    public void setIconLable(String iconLable) {
        this.iconLable = iconLable;
    }
}
