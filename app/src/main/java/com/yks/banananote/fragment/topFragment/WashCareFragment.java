package com.yks.banananote.fragment.topFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 描述：洗护
 * 作者：zzh
 * time:2020/04/09
 */
public class WashCareFragment extends Fragment {

    public static WashCareFragment newInstance(String content){
        WashCareFragment washCareFragment = new WashCareFragment();
        return washCareFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
