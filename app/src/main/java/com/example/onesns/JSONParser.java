package com.example.onesns;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

public class JSONParser {
    public Map<String,String> parse( String json_content ){
        int idx = 0;
        Map<String,String> parseMap = new HashMap<String,String>();
        Vector<String> keys = new Vector<String>();
        Vector<String> values = new Vector<String>();

        StringTokenizer token1 = new StringTokenizer(json_content,"{\"");
        while(token1.hasMoreTokens()){
            StringTokenizer token2 = new StringTokenizer(token1.nextToken(),"}");
            while(token2.hasMoreTokens()){
                StringTokenizer token3 = new StringTokenizer(token2.nextToken(),":");
                while(token3.hasMoreTokens()){
                    idx++;
                    if(idx%2 == 0){
                        values.add(token3.nextToken());
                    }else{
                        keys.add(token3.nextToken());
                    }
                }
            }
        }

        if( keys.size() == values.size() ){
            for(int i = 0;i < keys.size();i++){
                parseMap.put(keys.get(i),values.get(i));
            }
        }

        return parseMap;
    }

    public String parse(String json_content,String key){
        Map<String,String> parseMap = this.parse(json_content);
        return parseMap.get(key);
    }
}
