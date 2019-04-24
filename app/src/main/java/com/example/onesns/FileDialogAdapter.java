package com.example.onesns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FileDialogAdapter extends BaseAdapter {
    private ArrayList<FileDialogItem> lists = new ArrayList<FileDialogItem>();
    private Context cont;

    public FileDialogAdapter(Context cont) {
        this.cont = cont;
    }

    public void addItem( FileDialogItem item ){
        this.lists.add( item );
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
        View view = inflater.inflate(R.layout.filedialoglist,parent,false);

        TextView filenameView = view.findViewById(R.id.filenameView);
        TextView filetypeView = view.findViewById(R.id.filetypeView);
        TextView filesizeView = view.findViewById(R.id.filesizeView);

        FileDialogItem item = lists.get(position);
        filenameView.setText( item.getFilename());
        filetypeView.setText( item.getFiletype());
        filesizeView.setText( String.valueOf( item.getFilesize()));

        return view;
    }
}
