package com.yks.banananote.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yks.banananote.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 描述：抢购倒计时
 * 作者：zzh
 * time:2020/04/13
 */
@SuppressLint("HandlerLeak")
public class SnapUpCountDownTimerView extends LinearLayout {

    private LinearLayout ll_hour,ll_min,ll_sec;
    private TextView tv_hour_decade;
    private TextView tv_hour_unit;
    private TextView tv_min_decade;
    private TextView tv_min_unit;
    private TextView tv_sec_decade;
    private TextView tv_sec_unit;

    private Context context;

    private int hour_decade;
    private int hour_unit;
    private int min_decade;
    private int min_unit;
    private int sec_decade;
    private int sec_unit;

    private Timer timer;
    private int snapViewSize;
    private int snapViewColor;
    private int snapColumnColor;
    private int snapViewBackgroundColor;

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            countDown();
        }
    };

    public SnapUpCountDownTimerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_countdowntimer, this);

        ll_hour = view.findViewById(R.id.ll_hour);
        tv_hour_decade = view.findViewById(R.id.tv_hour_decade);
        tv_hour_unit = view.findViewById(R.id.tv_hour_unit);
        ll_min = view.findViewById(R.id.ll_min);
        tv_min_decade = view.findViewById(R.id.tv_min_decade);
        tv_min_unit = view.findViewById(R.id.tv_min_unit);
        ll_sec = view.findViewById(R.id.ll_sec);
        tv_sec_decade = view.findViewById(R.id.tv_sec_decade);
        tv_sec_unit = view.findViewById(R.id.tv_sec_unit);
        TextView colon_hour = view.findViewById(R.id.colon_hour);
        TextView colon_minute = view.findViewById(R.id.colon_minute);

        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.SnapUpCountDownTimerView);
        snapViewSize = array.getInteger(R.styleable.SnapUpCountDownTimerView_snapViewSize, 12);
        snapViewColor = array.getColor(R.styleable.SnapUpCountDownTimerView_snapViewColor, Color.BLACK);
        snapColumnColor = array.getColor(R.styleable.SnapUpCountDownTimerView_snapColumnColor, Color.BLACK);
        snapViewBackgroundColor = array.getColor(R.styleable.SnapUpCountDownTimerView_snapViewBackgroundColor,Color.WHITE);

        ll_hour.setBackgroundColor(snapViewBackgroundColor);
        tv_hour_decade.setTextSize(snapViewSize);
        tv_hour_decade.setTextColor(snapViewColor);
        tv_hour_unit.setTextSize(snapViewSize);
        tv_hour_unit.setTextColor(snapViewColor);
        ll_min.setBackgroundColor(snapViewBackgroundColor);
        tv_min_decade.setTextSize(snapViewSize);
        tv_min_decade.setTextColor(snapViewColor);
        tv_min_unit.setTextSize(snapViewSize);
        tv_min_unit.setTextColor(snapViewColor);
        ll_sec.setBackgroundColor(snapViewBackgroundColor);
        tv_sec_decade.setTextSize(snapViewSize);
        tv_sec_decade.setTextColor(snapViewColor);
        tv_sec_unit.setTextSize(snapViewSize);
        tv_sec_unit.setTextColor(snapViewColor);

        colon_hour.setTextSize(snapViewSize);
        colon_hour.setTextColor(snapColumnColor);
        colon_minute.setTextSize(snapViewSize);
        colon_minute.setTextColor(snapColumnColor);
    }


    public void start() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            }, 0, 1000);
        }
    }


    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }


    public void setTime(int hour, int min, int sec) {

        if (hour >= 60 || min >= 60 || sec >= 60 || hour < 0 || min < 0
                || sec < 0) {
            throw new RuntimeException("时间格式错误,请检查你的代码");
        }

        hour_decade = hour / 10;
        hour_unit = hour - hour_decade * 10;

        min_decade = min / 10;
        min_unit = min - min_decade * 10;

        sec_decade = sec / 10;
        sec_unit = sec - sec_decade * 10;

        tv_hour_decade.setText(hour_decade + "");
        tv_hour_unit.setText(hour_unit + "");
        tv_min_decade.setText(min_decade + "");
        tv_min_unit.setText(min_unit + "");
        tv_sec_decade.setText(sec_decade + "");
        tv_sec_unit.setText(sec_unit + "");
    }


    private void countDown() {
        if (isCarry4Unit(tv_sec_unit)) {
            if (isCarry4Decade(tv_sec_decade)) {
                if (isCarry4Unit(tv_min_unit)) {
                    if (isCarry4Decade(tv_min_decade)) {
                        if (isCarry4Unit(tv_hour_unit)) {
                            if (isCarry4Decade(tv_hour_decade)) {
                                Toast.makeText(context, "计数完成",
                                        Toast.LENGTH_SHORT).show();
                                stop();
                                setTime(0, 0, 0);//重置为0
                            }
                        }
                    }
                }
            }
        }
    }


    private boolean isCarry4Decade(TextView tv) {

        int time = Integer.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 5;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }
    }


    private boolean isCarry4Unit(TextView tv) {

        int time = Integer.valueOf(tv.getText().toString());
        time = time - 1;
        if (time < 0) {
            time = 9;
            tv.setText(time + "");
            return true;
        } else {
            tv.setText(time + "");
            return false;
        }
    }
}
