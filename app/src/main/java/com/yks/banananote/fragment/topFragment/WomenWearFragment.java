package com.yks.banananote.fragment.topFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 描述：女装
 * 作者：zzh
 * time:2020/04/09
 */
public class WomenWearFragment extends Fragment {

    public static WomenWearFragment newInstance(String content){
        WomenWearFragment womenWearFragment = new WomenWearFragment();
        return womenWearFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
