package com.example.onesns;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainContentFragment extends Fragment {
    private android.widget.TextView date_display;
    private android.widget.TextView time_display;
    // 시간 관련
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private int mSecond;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 0;

    // 핸들러 변수
    private Runnable r;
    private Handler mHandler;

    public MainContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.time_display = (TextView)view.findViewById(R.id.tv2);
        this.date_display = (TextView) view.findViewById(R.id.tv1);
        // calendar 에서 받아왔음
        final Calendar c = Calendar.getInstance();
        mYear  = c.get(Calendar.YEAR);
        mMonth  = c.get(Calendar.MONTH);
        mDay  = c.get(Calendar.DAY_OF_MONTH);
        mHour  = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        mSecond = c.get(Calendar.SECOND);
        // 핸들러
        mHandler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                updateDisplay();
            }
        };
        mHandler.postDelayed(r, 1000);
    }

    // 날짜와 시간 업데이트
    private void updateDisplay() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        mSecond = c.get(Calendar.SECOND);

        // 날짜
        date_display.setText(
                new StringBuilder()
                        .append(mYear).append("-0")
                        .append(mMonth + 1).append("-0")
                        .append(mDay).append(" "));
        // 시간
        time_display.setText(
                new StringBuilder()
                        .append(pad(mHour)).append(":")
                        .append(pad(mMinute)).append(":")
                        .append(pad(mSecond)));
        // 1초당 업데이트
        mHandler.postDelayed(r, 1000);
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}
