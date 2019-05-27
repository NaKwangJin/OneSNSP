package com.example.onesns;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.StringTokenizer;
import java.util.Vector;

public class FindFriendFragment extends Fragment {
    private EditText targetUserIDEdit;
    private Button findQueryButton;
    private RESTManager restManager;
    private Handler handler;
    private Context cont;
    public FindFriendFragment() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find_friend, container, false);
    }

    private void InitComponents(){
        targetUserIDEdit = (EditText)getView().findViewById(R.id.targetIDBox);
        findQueryButton = (Button)getView().findViewById(R.id.putFindQueryButton);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.cont = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        InitComponents();

        findQueryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restManager = new RESTManager(null);
                restManager.setURL("http://fght7100.dothome.co.kr/profile.php");
                restManager.setMethod("GET");
                restManager.putArgument("mode","usrrow");
                restManager.putArgument("record","ID");
                restManager.execute();

                // Search Friend Info From Server //
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run(){
                        Vector<String> ids = new Vector<String>(1);
                        String result = RESTGlobal.result;
                        StringTokenizer slashToken = new StringTokenizer(result,"/");
                        while( slashToken.hasMoreTokens() ){
                            ids.add( DecryptionDecoder.decryptBase64( slashToken.nextToken()));
                        }
                        int val = ids.indexOf(targetUserIDEdit.getText().toString());
                        if( val == -1 ){
                            Toast.makeText(cont,"친구를 찾을 수 없습니다.",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(cont,"친구를 찾았습니다.",Toast.LENGTH_SHORT).show();
                            Intent broadInt = new Intent("UserFindBroadcast");
                            broadInt.putExtra("UserID",targetUserIDEdit.getText().toString());
                            cont.sendBroadcast(broadInt);

                            // 친구 로컬 데이터베이스에 새로 찾은 친구 추가 //
                            LocalDBManager dbManager = new LocalDBManager(cont);
                            dbManager.insertFriendsInfo(targetUserIDEdit.getText().toString(),"NONE");
                        }
                    }
                },1500);
            }
        });
    }
}
