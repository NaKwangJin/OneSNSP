package com.example.onesns;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RESTManager extends AsyncTask<Void,Void,String> {
    private Map<String,String> keyvalue = new HashMap<String,String>();
    private String urladdr = "";
    private String method = "";
    private String output = "";
    private Context cont;

    public RESTManager( Context cont ){
        this.cont = cont;
    }

    public void setMethod( String method ){
        this.method = method;
    }
    public void setURL( String url ){
        this.urladdr = url;
    }
    public void putArgument( String name,String var ){
        this.keyvalue.put( name,var );
    }
    public String getResult(){
        return this.output;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try{
            urladdr = urladdr + "?";
            for( String key : keyvalue.keySet() ){
                urladdr = urladdr + key + "=" + keyvalue.get(key) + "&";
            }

            URL urlconn = new URL( urladdr );
            HttpURLConnection httpConn = (HttpURLConnection)urlconn.openConnection();
            httpConn.setRequestMethod( this.method );
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);

            InputStream is = httpConn.getInputStream();

            StringBuilder builder = new StringBuilder();
            int data = 0;

            while((data = is.read()) != -1){
                builder.append((char)data);
            }

            output = builder.toString();
            httpConn.disconnect();
        }catch(Exception e){
            e.printStackTrace();
        }
        return output;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        // Check if this ID is our member or not //
        if( s.contains("\"exists\":false") ){
            Toast.makeText(cont,"회원이 아닙니다.",Toast.LENGTH_LONG).show();
        }
        if( s.contains("\"exists\":true") ){
            Intent i = new Intent(cont,MainActivity.class);
            cont.startActivity(i);
        }
    }
}
