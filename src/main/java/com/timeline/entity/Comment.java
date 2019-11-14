package com.timeline.entity;

public class Comment {
    private int userID;
    private String userName;
    private String commentText;
    private String picture;
    private String timeStamp;

    public Comment(int id, String userName, String commetText, String picture, String timeStamp){
        this.userID = id;
        this.userName = userName;
        this.commentText = commetText;
        this.picture = picture;
        this.timeStamp = timeStamp;
    }

    public void setUserID(int id){
        this.userID = id;
    }
    public int getUserID(){
        return userID;
    }
    public void setUserName(String name){
        this.userName = name;
    }
    public String getUserName(){
        return userName;
    }
    public void setCommetText(String commetText){
        this.commentText = commetText;
    }
    public String getCommetText(){
        return commentText;
    }
    public void setPicture(String picture){
        this.picture = picture;
    }
    public String getPicture(){
        return picture;
    }
    public void setTimeStamp(String timeStamp){
        this.timeStamp = timeStamp;
    }
    public String getTimeStamp(){
        return timeStamp;
    }
}
