package com.example.onesns;

public class ChatMessageListItem {
    private String message;
    private String date;
    private String nickID;
    private int profileImgID;

    public ChatMessageListItem(String message, String date, String nickID, int profileImgID) {
        this.message = message;
        this.date = date;
        this.nickID = nickID;
        this.profileImgID = profileImgID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNickID() {
        return nickID;
    }

    public void setNickID(String nickID) {
        this.nickID = nickID;
    }

    public int getProfileImgID() {
        return profileImgID;
    }

    public void setProfileImgID(int profileImgID) {
        this.profileImgID = profileImgID;
    }
}
