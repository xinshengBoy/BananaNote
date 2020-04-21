package com.yks.banananote.fragment.topFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 描述：数码
 * 作者：zzh
 * time:2020/04/09
 */
public class DigitalFragment extends Fragment {

    public static DigitalFragment newInstance(String content){
        DigitalFragment digitalFragment = new DigitalFragment();
        return digitalFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
