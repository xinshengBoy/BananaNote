package com.yks.banananote.fragment.topFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 描述：美妆
 * 作者：zzh
 * time:2020/04/09
 */
public class BeautyMakeupFragment extends Fragment {

    public static BeautyMakeupFragment newInstance(String content){
        BeautyMakeupFragment beautyMakeupFragment = new BeautyMakeupFragment();
        return beautyMakeupFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
