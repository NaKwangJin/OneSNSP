package com.example.onesns;

import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;

public class FriendRecyclerView extends Fragment {



    public FriendRecyclerView(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_friend_recycler_view, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView =(RecyclerView)view.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(),4);
        recyclerView.setLayoutManager(layoutManager);

        List<FriendRecyclerItem> dataList = new ArrayList<>();
        for(int i = 0; i<10; i++){
            dataList.add(new FriendRecyclerItem((i+1)+"번째"));
        }

        FriendRecyclerAdapter adapter = new FriendRecyclerAdapter(dataList);
        recyclerView.setAdapter(adapter);
    }
}
