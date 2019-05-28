package com.example.onesns;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class ServerMsgReceiver extends Thread{
    private boolean isStop = false;
    public void setStop(boolean isStop){
        this.isStop = isStop;
    }
    @Override
    public void run() {
        while(!isStop){
            try{
                URL url = new URL("http://fght7100.dothome.co.kr/profile.php?mode=lookcmsg");
                HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
                httpConn.setRequestMethod("GET");
                httpConn.setDoInput(true);
                httpConn.setDoOutput(true);

                BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                String line = "";

                while((line = reader.readLine()) != null){
                    Log.e("HAHA",line);
                }

                httpConn.disconnect();
                Thread.sleep(2000);
            }catch(Exception e){

            }
        }
    }
}
