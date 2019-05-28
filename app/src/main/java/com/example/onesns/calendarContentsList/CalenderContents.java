package com.example.onesns.calendarContentsList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.onesns.EncryptionEncoder;
import com.example.onesns.FriendRecyclerAdapter;
import com.example.onesns.FriendRecyclerItem;
import com.example.onesns.MainContentFragment;
import com.example.onesns.R;
import com.example.onesns.RESTGlobal;
import com.example.onesns.RESTManager;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CalenderContents extends AppCompatActivity {
        private Button cancel;
        private Intent intent;
        private Context cont;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_contents);

        this.cont = this;

        cancel = (Button)findViewById(R.id.cancel_btn);
        cancel.setText("닫기");


        RecyclerView recyclerView =(RecyclerView)findViewById(R.id.calendarContents);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CalenderContents.this);
        recyclerView.setLayoutManager(layoutManager);

        final List<CalendarContentsItem> dataList = new ArrayList<>();
        /*for(int i = 0; i<10; i++){
            dataList.add(new CalendarContentsItem((i+1)+"번째"));
        }*/
        // Get UserID From Current Session //
        SharedPreferences pref = cont.getSharedPreferences("UserSession",MODE_PRIVATE);
        String currentSessionID = pref.getString("UID","[NULLUSER]");
        // Call the user Calc Data From DB //
        RESTManager restmng = new RESTManager(cont);
        restmng.setURL("http://fght7100.dothome.co.kr/profile.php");
        restmng.setMethod("GET");
        restmng.putArgument("mode","getcal");
        restmng.putArgument("id", EncryptionEncoder.encryptBase64(currentSessionID));
        restmng.execute();

        final CalendarContentsAdapter adapter = new CalendarContentsAdapter(dataList);
        recyclerView.setAdapter(adapter);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String caldata = RESTGlobal.result;
                StringTokenizer token1 = new StringTokenizer(caldata,"/");
                while( token1.hasMoreTokens() ){
                    String repstr = token1.nextToken().replaceAll(":"," - ");
                    dataList.add(new CalendarContentsItem(repstr));
                }
                adapter.notifyDataSetChanged();
            }
        },1000);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
