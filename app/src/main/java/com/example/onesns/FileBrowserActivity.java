package com.example.onesns;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.StringTokenizer;
import java.util.Vector;

public class FileBrowserActivity extends AppCompatActivity {
    private FileDialogAdapter fileListAdapter;
    private ListView fileList;
    private Context cont;
    private String rootpath;
    private Vector<String> curpath;
    private File curfile;
    private File[] curfiles;
    private void InitComponents(){
        fileList = (ListView)findViewById(R.id.fileDialogList);
    }

    private String StringVectorToString( Vector<String> strvec ){
        StringBuffer buf = new StringBuffer();
        if( strvec.size() <= 0 ) return null;

        for(int i = 0;i < strvec.size();i++ ){
            buf.append(strvec.get(i));
        }

        return buf.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_browser);

        cont = this;
        fileListAdapter = new FileDialogAdapter(cont);
        curpath = new Vector<String>(5);

        InitComponents();

        fileList.setAdapter(fileListAdapter);

        rootpath = Environment.getRootDirectory().getPath();
        curpath.add(rootpath + "/");
        curfile = new File( StringVectorToString( curpath ) );
        curfiles = curfile.listFiles();

        if( curfiles != null ){
            for(int i = 0;i < curfiles.length;i++){
                boolean isDir = curfiles[i].isDirectory();
                fileListAdapter.addItem(new FileDialogItem( curfiles[i].getName(),"FILE",0,isDir));
            }
            fileListAdapter.notifyDataSetChanged();
        }

        fileList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FileDialogItem item = ((FileDialogItem)fileListAdapter.getItem(position));

                if( item.getFilename() == "/" ){
                    if( !curpath.equals(rootpath + "/") ){
                        curpath.remove( curpath.size() - 1 );
                        curfile = new File(StringVectorToString(curpath));
                        curfiles = curfile.listFiles();

                        if( curfiles != null ){
                            fileListAdapter.clearAllItem();
                            fileListAdapter.addItem(new FileDialogItem("/","",0,true));
                            for(int i = 0;i < curfiles.length;i++){
                                boolean isDir = curfiles[i].isDirectory();
                                fileListAdapter.addItem(new FileDialogItem( curfiles[i].getName(),"FILE",0,isDir));
                            }
                            fileListAdapter.notifyDataSetChanged();
                        }
                    }
                }
                if( item.isDir() ){
                    curpath.add(item.getFilename());
                    curfile = new File(StringVectorToString(curpath));
                    curfiles = curfile.listFiles();

                    if( curfiles != null ){
                        fileListAdapter.clearAllItem();

                        Log.e("RRR",StringVectorToString( curpath ));
                        fileListAdapter.addItem(new FileDialogItem("/", "", 0, true));

                        for(int i = 0;i < curfiles.length;i++){
                            boolean isDir = curfiles[i].isDirectory();
                            fileListAdapter.addItem(new FileDialogItem( curfiles[i].getName(),"FILE",0,isDir));
                        }
                        fileListAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
