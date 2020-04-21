package com.yks.banananote.fragment.topFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 描述：家居
 * 作者：zzh
 * time:2020/04/09
 */
public class HomeFurnishFragment extends Fragment {

    public static HomeFurnishFragment newInstance(String content){
        HomeFurnishFragment homeFurnishFragment = new HomeFurnishFragment();
        return homeFurnishFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
