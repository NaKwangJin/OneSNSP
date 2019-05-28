package com.example.onesns;

import android.content.SharedPreferences;
import android.util.Log;

import com.facebook.share.Share;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.StringTokenizer;

public class ServerMsgReceiver extends Thread{
    private String accountID;
    private boolean isStop = false;
    private String output = "{NULL}";
    public void setUserID( String id ){
        this.accountID = id;
    }
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

                output = "{NULL}";

                if( ChatGlobal.msgListAdapter != null ){
                    while((line = reader.readLine()) != null){
                        output += line;
                    }

                    ChatGlobal.msgListAdapter.removeAll();

                    StringTokenizer token1 = new StringTokenizer(output,"/");
                    while(token1.hasMoreTokens()){
                        String[] token2 = token1.nextToken().split(",");
                        boolean isLeftSide = (token2[2].equals(accountID)) ? false : true;
                        ChatGlobal.msgListAdapter.addItem(new ChatMessageListItem(token2[3],token2[4],token2[2],isLeftSide,0));
                        //StringTokenizer token2 = new StringTokenizer(token1.nextToken(),",");
                        /*while(token2.hasMoreTokens()){
                            String selfid = token2.nextToken();
                            String otherid = token2.nextToken();
                            String mgn = token2.nextToken();
                            String msg = token2.nextToken();
                            String senddate = token2.nextToken();

                            boolean isLeftSide = (this.accountID.equals(mgn))? false : true;



                            //ChatGlobal.msgListAdapter.addItem(new ChatMessageListItem(msg,senddate,mgn,isLeftSide,0));
                            //Log.e( "AAA","LEN = " + ChatGlobal.msgListAdapter.getCount() );
                        }*/
                    }
                    ChatGlobal.msgListAdapter.notifyDataSetChanged();
                }

                httpConn.disconnect();
                Thread.sleep(3000);
            }catch(Exception e){

            }
        }
    }
}
