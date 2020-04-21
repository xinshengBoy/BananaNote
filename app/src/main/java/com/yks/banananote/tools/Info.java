package com.yks.banananote.tools;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yks.banananote.R;
import com.yks.banananote.adapter.BaseRecyclerAdapter;
import com.yks.banananote.custom.ActivityActionBar;

import java.util.List;

/**
 * 描述：
 * 作者：zzh
 * time:2020/04/10
 */
public class Info {

    /**
     * 描述：显示toast提示信息
     * @param context 上下文
     * @param tip 提示内容
     * @param isShort 展示时间长短
     */
    public static void showToastTip(Context context,String tip,boolean isShort){
        Toast.makeText(context,tip,isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG).show();
    }

    /**
     * 描述：清空list集合
     * @param list list集合
     */
    public static void clearList(List list){
        if (list != null){
            list.clear();
            list = null;
        }
    }

    /**
     * 描述：设置recycview的适配器
     * 作者：zzh
     * @param context 上下文
     * @param view recycview
     * @param adapter 适配器
     */
    public static void setRecycviewAdapter(Context context, RecyclerView view, BaseRecyclerAdapter adapter,boolean needDivider){
        //横向滚动
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(manager);
        if (needDivider) {
            view.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));//默认的分割线
        }
        view.setAdapter(adapter);
    }
    /**
     * 描述：封装标题头
     * 作者：zzh
     * @param activity activity
     * @param text 标题文字
     */
    public static void setTitle(Activity activity, String text){
        LinearLayout title_layout = activity.findViewById(R.id.bar_activity);
        ActivityActionBar.show(activity,title_layout,text,"",false);
    }
}
