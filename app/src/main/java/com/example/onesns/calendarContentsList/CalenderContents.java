package com.example.onesns.calendarContentsList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.onesns.FriendRecyclerAdapter;
import com.example.onesns.FriendRecyclerItem;
import com.example.onesns.R;

import java.util.ArrayList;
import java.util.List;

public class CalenderContents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_contents);

        RecyclerView recyclerView =(RecyclerView)findViewById(R.id.calendarContents);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CalenderContents.this);
        recyclerView.setLayoutManager(layoutManager);

        List<CalendarContentsItem> dataList = new ArrayList<>();
        for(int i = 0; i<10; i++){
            dataList.add(new CalendarContentsItem((i+1)+"번째"));
        }

        CalendarContentsAdapter adapter = new CalendarContentsAdapter(dataList);
        recyclerView.setAdapter(adapter);


    }
}
