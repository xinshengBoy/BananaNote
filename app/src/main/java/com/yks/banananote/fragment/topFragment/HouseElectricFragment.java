package com.yks.banananote.fragment.topFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 描述：家电
 * 作者：zzh
 * time:2020/04/09
 */
public class HouseElectricFragment extends Fragment {

    public static HouseElectricFragment newInstance(String content){
        HouseElectricFragment houseElectricFragment = new HouseElectricFragment();
        return houseElectricFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
