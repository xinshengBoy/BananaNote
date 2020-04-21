package com.yks.banananote.fragment.topFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 描述：百货
 * 作者：zzh
 * time:2020/04/09
 */
public class DepartmentStoreFragment extends Fragment {

    public static DepartmentStoreFragment newInstance(String content){
        DepartmentStoreFragment departmentStoreFragment = new DepartmentStoreFragment();
        return departmentStoreFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
