package com.yks.banananote.fragment.topFragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 描述：卡券
 * 作者：zzh
 * time:2020/04/09
 */
public class CardTicketFragment extends Fragment {

    public static CardTicketFragment newInstance(String content){
        CardTicketFragment cardTicketFragment = new CardTicketFragment();
        return cardTicketFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
