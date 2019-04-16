package com.example.onesns;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ChatListFragment extends Fragment {
    private Button chatterAddBtn;

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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitComponents();

        this.chatterAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewChatterDialog chatterAddDialog = new NewChatterDialog( getActivity(),"New Chatter");
                chatterAddDialog.show();
            }
        });
    }
}
