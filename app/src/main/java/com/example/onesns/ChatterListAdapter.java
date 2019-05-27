package com.example.onesns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatterListAdapter extends BaseAdapter {
    private Context cont;
    private ArrayList<ChatterListItem> items = new ArrayList<ChatterListItem>();

    public ChatterListAdapter( Context cont ){
        this.cont = cont;
    }

    public void addItem( ChatterListItem item ){
        this.items.add( item );
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)cont.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View v = inflater.inflate(R.layout.chatterlist,parent,false);

        ChatterListItem item = this.items.get( position );

        TextView nickEdit = v.findViewById(R.id.userNickBox);
        TextView profileIDEdit = v.findViewById(R.id.profileIDBox);
        ImageView profileImg = v.findViewById(R.id.profileImageBox);

        nickEdit.setText( item.getName() );
        profileIDEdit.setText( item.getProfileID() );
        profileImg.setImageResource( item.getProfileImgID() );

        return v;
    }
}
