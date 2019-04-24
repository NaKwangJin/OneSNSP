package com.example.onesns;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.File;

public class FileBrowserActivity extends AppCompatActivity {
    private FileDialogAdapter fileListAdapter;
    private ListView fileList;
    private Context cont;
    private String rootPath;
    private void InitComponents(){
        fileList = (ListView)findViewById(R.id.fileDialogList);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_browser);

        cont = this;
        fileListAdapter = new FileDialogAdapter(cont);

        InitComponents();

        fileList.setAdapter(fileListAdapter);

        rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File fileinfo = new File(rootPath);
        if( fileinfo.isDirectory() ){
            return;
        }

        String[] filelists = fileinfo.list();
        for(int i = 0;i < filelists.length;i++){
            fileListAdapter.addItem(new FileDialogItem(filelists[i],"File",0));
        }
        fileListAdapter.notifyDataSetChanged();
    }
}
