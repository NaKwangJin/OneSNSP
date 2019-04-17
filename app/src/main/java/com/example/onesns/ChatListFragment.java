package com.example.onesns;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ChatListFragment extends Fragment {
    private Button chatterAddBtn;
    private ListView chatterList;
    private ChatterListAdapter chatterListAdapter;

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
                final NewChatterDialog chatterAddDialog = new NewChatterDialog( getActivity(),"New Chatter");
                chatterAddDialog.show();

                chatterAddDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        // DevYCY: If user clicked 'Cancel' Dialog Button...
                        if( chatterAddDialog.isCancelDismiss ){
                            return;
                        }else{
                            chatterListAdapter.addItem(new ChatterListItem( chatterAddDialog.userNickname,chatterAddDialog.profileID,R.drawable.ic_launcher_foreground));
                            chatterListAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
    }
}
