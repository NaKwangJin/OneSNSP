package com.example.onesns.calendarContentsList;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.onesns.FriendRecyclerAdapter;
import com.example.onesns.FriendRecyclerItem;
import com.example.onesns.MainContentFragment;
import com.example.onesns.R;

import java.util.ArrayList;
import java.util.List;

public class CalenderContents extends AppCompatActivity {
        private Button cancel;
        private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_contents);

        cancel = (Button)findViewById(R.id.cancel_btn);
        cancel.setText("닫기");


        RecyclerView recyclerView =(RecyclerView)findViewById(R.id.calendarContents);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CalenderContents.this);
        recyclerView.setLayoutManager(layoutManager);

        List<CalendarContentsItem> dataList = new ArrayList<>();
        for(int i = 0; i<10; i++){
            dataList.add(new CalendarContentsItem((i+1)+"번째"));
        }

        CalendarContentsAdapter adapter = new CalendarContentsAdapter(dataList);
        recyclerView.setAdapter(adapter);



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
