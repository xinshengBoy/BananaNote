package com.yks.banananote.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

/**
 * 描述：香蕉直邮
 * 作者：zzh
 * time:2020/04/09
 */
public class BananaPostFragment extends Fragment {

    public static BananaPostFragment newInstance(String content){
        BananaPostFragment bananaPostFragment = new BananaPostFragment();
        return bananaPostFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
