package com.example.onesns;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatMessageListAdapter extends BaseAdapter {
    private Context cont;
    private ArrayList<ChatMessageListItem> lists = new ArrayList<ChatMessageListItem>();

    public ChatMessageListAdapter(Context cont){
        this.cont = cont;
    }

    public void removeAll(){
        lists.clear();
    }

    public void addItem( ChatMessageListItem item ){
        this.lists.add(item);
    }
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.chatmessagelist,parent,false);

        if( lists.size() > 0 ){
            ChatMessageListItem item = lists.get(position);
            if( item.getLeftSide() ){
                TextView rightMessageView = view.findViewById(R.id.rightMessageTipBox);
                CardView cardView = view.findViewById(R.id.rightCardView);

                rightMessageView.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);

                TextView messageView = view.findViewById(R.id.messageTipBox);
                messageView.setText(item.getMessage());

                float width = messageView.getTextSize() * item.getMessage().length();
                messageView.getLayoutParams().width = (int)width;
                messageView.requestLayout();
            }else{
                TextView leftMessageView = view.findViewById(R.id.messageTipBox);
                CardView cardView = view.findViewById(R.id.cardView);

                leftMessageView.setVisibility(View.GONE);
                cardView.setVisibility(View.GONE);

                TextView messageView = view.findViewById(R.id.rightMessageTipBox);
                messageView.setText(item.getMessage());

                float width = messageView.getTextSize() * item.getMessage().length();
                messageView.getLayoutParams().width = (int)width;
                messageView.requestLayout();
            }
        }

        return view;
    }
}
