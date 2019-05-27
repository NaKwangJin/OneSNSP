package com.example.onesns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ChatMessageListAdapter extends BaseAdapter {
    private Context cont;
    private ArrayList<ChatMessageListItem> lists = new ArrayList<ChatMessageListItem>();

    public ChatMessageListAdapter(Context cont){
        this.cont = cont;
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

        return view;
    }
}
