package com.yks.banananote.bean;

import java.util.List;

/**
 * 描述：分类页面第一级分类，即为左侧的分类
 * 作者：zzh
 * time:2020/04/10
 */
public class CategoryFirstBean {

    private boolean isSelect;
    private String categoryName;
    private List<CategorySecondBean> list;

    public CategoryFirstBean(boolean isSelect, String categoryName, List<CategorySecondBean> list) {
        this.isSelect = isSelect;
        this.categoryName = categoryName;
        this.list = list;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CategorySecondBean> getList() {
        return list;
    }

    public void setList(List<CategorySecondBean> list) {
        this.list = list;
    }
}
