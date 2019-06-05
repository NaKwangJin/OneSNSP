package com.example.onesns;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainContentFragment extends Fragment implements MainActivity.onKeyBackPressedListener{
    private android.widget.TextView date_display;
    private android.widget.TextView time_display;
    private ImageView friend;
    private ImageView chat;
    private ImageView search;
    private ImageView calendar;

    // 시간 관련
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private int mSecond;

//    Button linear1, linear2, linear3, linear4;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity)context).setOnKeyBackPressedListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        this.time_display = (TextView)view.findViewById(R.id.tv2);
//        this.date_display = (TextView) view.findViewById(R.id.tv1);

        friend = (ImageView)view.findViewById(R.id.friendContentbtn);
        chat = (ImageView)view.findViewById(R.id.chatContent);
        search = (ImageView)view.findViewById(R.id.searchContent);
        calendar = (ImageView)view.findViewById(R.id.calendarContent);



//      linear1 = (Button) view.findViewById(R.id.l_button_1);
//      linear2 = (Button)vi
//      ew.findViewById(R.id.l_button_2);
//        linear3 = (Button)view.findViewById(R.id.l_button_3);
//        linear4 = (Button)view.findViewById(R.id.l_button_4);
//        this.linear5 = view.findViewById(R.id.l_button_5);

        // calendar 에서 받아왔음
//        final Calendar c = Calendar.getInstance();
//        mYear  = c.get(Calendar.YEAR);
//        mMonth  = c.get(Calendar.MONTH);
//        mDay  = c.get(Calendar.DAY_OF_MONTH);
//        mHour  = c.get(Calendar.HOUR_OF_DAY);
//        mMinute = c.get(Calendar.MINUTE);
//        mSecond = c.get(Calendar.SECOND);
        // 핸들러
//        mHandler = new Handler();
//        r = new Runnable() {
//            @Override
//            public void run() {
//                updateDisplay();
//            }
//        };
//        mHandler.postDelayed(r, 1000);

        friendbtn(friend);
        chatBtn(chat);
        searchBtn(search);
        calendarBtn(calendar);



    }

    public void friendbtn(View view) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        requireActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new FriendRecyclerView())
                                .commit();

                    }
                });


    }

    public void chatBtn(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new ChatListFragment())
                        .commit();
            }
        });

    }

    public void searchBtn(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new FindFriendFragment())
                        .commit();
            }
        });

    }
    public void calendarBtn(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new CalenderActivity())
                        .commit();
            }
        });

    }

    // 날짜와 시간 업데이트
//    private void updateDisplay() {
//        final Calendar c = Calendar.getInstance();
//        mYear = c.get(Calendar.YEAR);
//        mMonth = c.get(Calendar.MONTH);
//        mDay = c.get(Calendar.DAY_OF_MONTH);
//        mHour = c.get(Calendar.HOUR_OF_DAY);
//        mMinute = c.get(Calendar.MINUTE);
//        mSecond = c.get(Calendar.SECOND);

        // 날짜
//        date_display.setText(
//                new StringBuilder()
//                        .append(mYear).append("-0")
//                        .append(mMonth + 1).append("-0")
//                        .append(mDay).append(" "));
//        // 시간
//        time_display.setText(
//                new StringBuilder()
//                        .append(pad(mHour)).append(":")
//                        .append(pad(mMinute)).append(":")
//                        .append(pad(mSecond)));
        // 1초당 업데이트
//        mHandler.postDelayed(r, 1000);
//    }






    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    @Override
    public void onBackKey() {
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        activity.onBackPressed();
    }


}
