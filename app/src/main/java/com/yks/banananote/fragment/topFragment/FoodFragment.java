package com.yks.banananote.fragment.topFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 描述：食品
 * 作者：zzh
 * time:2020/04/09
 */
public class FoodFragment extends Fragment {

    public static FoodFragment newInstance(String content){
        FoodFragment foodFragment = new FoodFragment();
        return foodFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
