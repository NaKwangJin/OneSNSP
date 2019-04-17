package com.example.onesns;

import android.content.Context;

public class ChatterListItem {
    private String name;
    private String profileID;
    private int profileImgID;

    public ChatterListItem( String name,String profileID,int profileImgID ){
        this.name = name;
        this.profileID = profileID;
        this.profileImgID = profileImgID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileID() {
        return profileID;
    }

    public void setProfileID(String profileID) {
        this.profileID = profileID;
    }

    public int getProfileImgID() {
        return profileImgID;
    }

    public void setProfileImgID(int profileImgID) {
        this.profileImgID = profileImgID;
    }
}
