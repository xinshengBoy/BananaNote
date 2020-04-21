package com.yks.banananote.fragment.topFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 描述：内衣
 * 作者：zzh
 * time:2020/04/09
 */
public class UnderwearFragment extends Fragment {

    public static UnderwearFragment newInstance(String content){
        UnderwearFragment underwearFragment = new UnderwearFragment();
        return underwearFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
