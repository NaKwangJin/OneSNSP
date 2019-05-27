package com.example.onesns;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.example.onesns.dialog.FriendClickDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class FriendRecyclerView extends Fragment implements FriendRecyclerAdapter.MyRecyclerViewClickListener {

    private Context cont;
    private List<FriendRecyclerItem> dataList;

    public FriendRecyclerView(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_friend_recycler_view, container, false);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.cont = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) { //activity onCreated부분
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView =(RecyclerView)view.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(view.getContext(),4);
        recyclerView.setLayoutManager(layoutManager);

        dataList = new ArrayList<>();
        /*for(int i = 0; i<10; i++){
            dataList.add(new FriendRecyclerItem((i+1)+"번째"));
        }*/

        // 로컬 친구 데이터베이스에서 목록 조회 .. //
        LocalDBManager dbManager = new LocalDBManager(this.cont);
        Map<String,String> friends = dbManager.selectAllFriends();

        for( String key : friends.keySet()){
            dataList.add(new FriendRecyclerItem(key));
        }

        FriendRecyclerAdapter adapter = new FriendRecyclerAdapter(dataList);
        recyclerView.setAdapter(adapter);

        adapter.setOnclickListener(this);

    }


    @Override
    public void onItemClicked(int position) { // 친구목록에서 아이템 클릭시 이벤트 처리
        String content = dataList.get(position).getContents();
        FriendClickDialog friendClickDialog = new FriendClickDialog(getActivity(), "content", content);
        friendClickDialog.show();

        //Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();

    }
}
