package com.yks.banananote.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

/**
 * 描述：我的
 * 作者：zzh
 * time:2020/04/09
 */
public class MyselfFragment extends Fragment {

    public static MyselfFragment newInstance(String content){
        MyselfFragment myselfFragment = new MyselfFragment();
        return myselfFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
