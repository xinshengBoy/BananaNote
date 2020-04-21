package com.yks.banananote.fragment.topFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 描述：母婴
 * 作者：zzh
 * time:2020/04/09
 */
public class MomAndBabyFragment extends Fragment {

    public static MomAndBabyFragment newInstance(String content){
        MomAndBabyFragment momAndBabyFragment = new MomAndBabyFragment();
        return momAndBabyFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
