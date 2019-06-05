package com.example.onesns;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
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
    private ServerMsgReceiver receiver;
    private String currentSessionUserID;
    private String otherSessionUserID;

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

        SharedPreferences pref = requireContext().getSharedPreferences("UserSession",requireContext().MODE_PRIVATE);
        this.currentSessionUserID = pref.getString("UID","[NULLUSER]");

        SharedPreferences pref2 = requireContext().getSharedPreferences("TEMPOTHERID",Context.MODE_PRIVATE);
        this.otherSessionUserID = pref2.getString("OID","[NULLUSER]");

        ChatGlobal.msgListAdapter = new ChatMessageListAdapter(requireContext());
        sendMsgList.setAdapter(ChatGlobal.msgListAdapter);

        receiver = new ServerMsgReceiver();
        receiver.setUserID(currentSessionUserID);
        receiver.start();

        sendMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = sendMsgBox.getText().toString();
                RESTManager restmng = new RESTManager(requireContext());
                restmng.setURL("http://fght7100.dothome.co.kr/profile.php");
                restmng.setMethod("GET");
                restmng.putArgument("mode","addcmsg");
                restmng.putArgument("id",currentSessionUserID);
                restmng.putArgument("oid",otherSessionUserID);
                restmng.putArgument("mgn",currentSessionUserID);
                restmng.putArgument("msg",msg);
                restmng.putArgument("date","");
                restmng.execute();

                ChatGlobal.msgListAdapter.notifyDataSetChanged();
                // 전송( Send ) 버튼을 누르면 자동으로 입력란 지워주는 코드 //
                sendMsgBox.setText("");
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
        requireContext().registerReceiver(receiver,intentFilter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.receiver.setStop(true);
    }

//    @Override
//    public void onDestroyView() {
//        stopServerWatchDogs();
//        super.onDestroyView();
//    }

    public void stopServerWatchDogs(){
        this.receiver.setStop(true);
    }

}
