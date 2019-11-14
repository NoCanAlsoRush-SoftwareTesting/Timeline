package com.timeline.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {
    private Comment comment;
    private int userID;
    private String userName;
    private String commentText;
    private String picture;
    private String timeStamp;

    @BeforeEach
    public void before(){
        userID = 1;
        userName = "tom";
        commentText = "hello world";
        picture = "p1";
        timeStamp = "2019-11-11";
        comment = new Comment(userID,userName,commentText,picture,timeStamp);
    }
    @AfterEach
    public void after(){
        comment = null;
    }

    @Test
    void setUserID() {
        int myId = 10;
        comment.setUserID(10);
        assertEquals(10,comment.getUserID());
    }

    @Test
    void getUserID() {
        int getId = comment.getUserID();
        assertEquals(userID,getId);
    }

    @Test
    void setUserName() {
        String myName = "lionel";
        comment.setUserName(myName);
        assertEquals(myName,comment.getUserName());

    }

    @Test
    void getUserName() {
        String getName = comment.getUserName();
        assertEquals(userName,getName);
    }

    @Test
    void setCommetText() {
        String myText = "hello ecnu";
        comment.setCommetText(myText);
        assertEquals(myText,comment.getCommetText());
    }

    @Test
    void getCommetText() {
        String getText = comment.getCommetText();
        assertEquals(commentText, getText);
    }

    @Test
    void setPicture() {
        String myPiture = "p2";
        comment.setPicture(myPiture);
        assertEquals(myPiture,comment.getPicture());
    }

    @Test
    void getPicture() {
        String getPicture = comment.getPicture();
        assertEquals(picture,getPicture);
    }

    @Test
    void setTimeStamp() {
        String myTime = "2019-11-12";
        comment.setTimeStamp(myTime);
        assertEquals(myTime,comment.getTimeStamp());
    }

    @Test
    void getTimeStamp() {
        String getTime = comment.getTimeStamp();
        assertEquals(timeStamp,getTime);
    }
}