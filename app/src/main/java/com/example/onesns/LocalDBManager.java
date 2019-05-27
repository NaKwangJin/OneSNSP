package com.example.onesns;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;

public class LocalDBManager extends SQLiteOpenHelper {
    public static String DBNAME = "userlocaldb.db";
    public static int DBVERSION = 1;

    public LocalDBManager(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    public void insertFriendsInfo( String id,String profileImgPath ){
        SQLiteDatabase dbContext = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("profileImg",profileImgPath);

        dbContext.insert("friends",null,values);
        dbContext.close();
    }

    public void deleteFriendInfo( String id ){
        SQLiteDatabase dbContext = getWritableDatabase();
        dbContext.execSQL("DELETE FROM friends WHERE id='" + id + "';");
        dbContext.close();
    }

    public Map<String,String> selectAllFriends(){
        SQLiteDatabase dbContext = getReadableDatabase();
        Map<String,String> output = new HashMap<String,String>();

        Cursor cursor = dbContext.rawQuery("SELECT * FROM friends",null);
        while( cursor.moveToNext() ){
            output.put(cursor.getString(0),cursor.getString(1));
        }

        return output;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS friends(id TEXT PRIMARY KEY,profileImg TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
