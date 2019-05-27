package com.example.onesns;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.onesns.dialog.NewChatterDialog;

import java.util.Map;

public class ChatListFragment extends Fragment implements MainActivity.onKeyBackPressedListener{
    private Button chatterAddBtn;
    private ListView chatterList;
    private ChatterListAdapter chatterListAdapter;
    private Context cont;

    public ChatListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_list, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity)context).setOnKeyBackPressedListener(this);
        this.cont = context;
    }

    private void InitComponents(){
        this.chatterAddBtn = (Button)getView().findViewById(R.id.addChatterButton);
        this.chatterList = (ListView)getView().findViewById(R.id.chatterList);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitComponents();

        chatterListAdapter = new ChatterListAdapter( getActivity() );
        chatterList.setAdapter(chatterListAdapter);

        this.chatterAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 친구목록 동기화를 위해 데이터베이스로 부터 정보 추출 //
                LocalDBManager dbManager = new LocalDBManager(cont);
                Map<String,String> friends = dbManager.selectAllFriends();

                for( String key : friends.keySet()){
                    chatterListAdapter.addItem(new ChatterListItem(key,friends.get(key),0));
                }
                chatterListAdapter.notifyDataSetChanged();
            }
        });

        chatterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new ChatRoomFragment()).commit();
            }
        });
    }

    @Override
    public void onBackKey() {
        MainActivity activity = (MainActivity) getActivity();
        activity.setOnKeyBackPressedListener(null);
        activity.onBackPressed();
    }
}
