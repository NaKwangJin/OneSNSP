package com.example.onesns;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class ChatRoomFragment extends Fragment{

    private Button sendMsgBtn;
    private EditText sendMsgBox;
    private ListView sendMsgList;
    private Context cont;
    private ServerMsgReceiver receiver;

    private ChatMessageListAdapter msgListAdapter;

    public ChatRoomFragment() {
        // Required empty public constructor
    }

    private void initComponents(){
        sendMsgBtn = getView().findViewById(R.id.messageSendButton);
        sendMsgList = getView().findViewById(R.id.chatRoomMessageList);
        sendMsgBox = getView().findViewById(R.id.messageSendBox);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_room, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents();

        receiver = new ServerMsgReceiver();
        receiver.start();

        msgListAdapter = new ChatMessageListAdapter(cont);
        sendMsgList.setAdapter(msgListAdapter);

        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = sendMsgBox.getText().toString();
                msgListAdapter.addItem(new ChatMessageListItem(msg,"","",false,0));
                msgListAdapter.notifyDataSetChanged();
            }
        });

        // 채팅방 프래그먼트에서 뒤로가기 를 누를 시, 실시간 서버 연동 스레드 종료 //
        // 채팅방 프래그먼트에서 뒤로가기 가 눌렸는지 확인하기 위한 브로드캐스트   //
        final BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                stopServerWatchDogs();
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("OnChatRoomExit");
        cont.registerReceiver(receiver,intentFilter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        cont = context;
    }

    @Override
    public void onPause() {
        super.onPause();
        this.receiver.setStop(true);
    }

    public void stopServerWatchDogs(){
        this.receiver.setStop(true);
    }

}
