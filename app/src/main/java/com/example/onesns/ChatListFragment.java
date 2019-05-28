package com.example.onesns;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.onesns.dialog.NewChatterDialog;

import java.util.Map;
import java.util.StringTokenizer;

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
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final RESTManager restManager = new RESTManager(cont);
                restManager.setURL("http://fght7100.dothome.co.kr/profile.php");
                restManager.setMethod("GET");
                restManager.putArgument("mode","lookcmsg");
                restManager.execute();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences pref = cont.getSharedPreferences("UserSession",cont.MODE_PRIVATE);
                        String ownID = pref.getString("UID","++NONE--");
                        String otherID = ((ChatterListItem)chatterListAdapter.getItem(position)).getName();
                        String chatdb = RESTGlobal.result;
                        if( chatdb.contains(ownID) &&
                                chatdb.contains( otherID )){
                            // Nothing To Do //
                        }else{
                            RESTManager restmng = new RESTManager(cont);
                            restmng.setURL("http://fght7100.dothome.co.kr/profile.php");
                            restmng.setMethod("GET");
                            restmng.putArgument("mode","addcmsg");
                            restmng.putArgument("id",EncryptionEncoder.encryptBase64(ownID));
                            restmng.putArgument("oid",EncryptionEncoder.encryptBase64(otherID));
                            restmng.putArgument("mgn",EncryptionEncoder.encryptBase64(ownID));
                            restmng.putArgument("msg","");
                            restmng.putArgument("date","");
                            restmng.execute();
                        }
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new ChatRoomFragment(),"CHATROOMFRAGMENT").commit();
                    }
                },2500);
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
