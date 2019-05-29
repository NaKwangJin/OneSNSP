package com.example.onesns;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class
RESTManager extends AsyncTask<Void,Void,String>{
    private Map<String,String> keyvalue = new HashMap<String,String>();
    private String urladdr = "";
    private String method = "";
    private String output = "";
    private Context cont;

    private HttpURLConnection httpConn = null;

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

    @Override
    protected String doInBackground(Void... voids){
        try{
            urladdr = urladdr + "?";
            for( String key : keyvalue.keySet() ){
                urladdr = urladdr + key + "=" + keyvalue.get(key) + "&";
            }

            URL urlconn = new URL( urladdr );
            httpConn = (HttpURLConnection)urlconn.openConnection();
            httpConn.setRequestMethod( this.method );
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);

            InputStream is = httpConn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));

            StringBuilder builder = new StringBuilder();
            String line = "";
            while((line = br.readLine())!=null){
                builder.append(line);
            }
            output = builder.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return output;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        RESTGlobal.result = s;
        // 실행이 완료된 상태에서 HTTP 연결을 끊어야 Unexpected end of Stream 오류가 발생하지않음 //
        httpConn.disconnect();
        // Check if this ID is our member or not //
        if( s.contains("\"exists\":false") ){
            Toast.makeText(cont,"회원이 아닙니다.",Toast.LENGTH_LONG).show();
        }
        if( s.contains("\"exists\":true") ){
            SharedPreferences sp = cont.getSharedPreferences( "UserSession",cont.MODE_PRIVATE );
            SharedPreferences.Editor spe = sp.edit();

            if( this.keyvalue.get("id") != "" && this.keyvalue.get("id") != null ){
                spe.putString("UID",DecryptionDecoder.decryptBase64(this.keyvalue.get("id")));
            }

            JSONParser parser = new JSONParser();
            String email = parser.parse(s,"email");

            if( email != "" && email != null ){
                spe.putString("UEMAIL",DecryptionDecoder.decryptBase64(email));
            }

            spe.commit();

            Intent i = new Intent(cont,MainActivity.class);
            cont.startActivity(i);
        }
    }
}
