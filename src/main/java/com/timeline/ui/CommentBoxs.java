package com.timeline.ui;

import com.timeline.entity.*;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//评论列表
public class CommentBoxs {
    private static final Font COMMENT_FONT = new Font("宋体", Font.PLAIN, 20);

    public static Box createCommentBox(Comment comment) throws ParseException {

        JLabel userLabel = new JLabel();
        userLabel.setText(comment.getUserName());
        userLabel.setFont(COMMENT_FONT);
        JLabel timeLabel = new JLabel();
        String timestamp = comment.getTimeStamp();
        String time = convert(timestamp);
        timeLabel.setText(time);
        timeLabel.setFont(COMMENT_FONT);

        Box headBox = Box.createHorizontalBox();
        headBox.add(Box.createHorizontalStrut(20));
        headBox.add(userLabel);
        headBox.add(Box.createGlue());
        headBox.add(timeLabel);
        headBox.add(Box.createHorizontalStrut(20));

        JTextArea commentText = new JTextArea();
        commentText.setText(comment.getCommetText());
        commentText.setBackground(new Color(213, 228, 238));
        commentText.setFont(COMMENT_FONT);
        commentText.setLineWrap(true);
        commentText.setWrapStyleWord(true);

        Box textBox = Box.createHorizontalBox();
        textBox.add(commentText);

        Box commentBox = Box.createVerticalBox();
        commentBox.add(headBox);
        commentBox.add(textBox);

        return commentBox;
    }

    public static String convert(String dateTimeStamp) throws ParseException {
        String result;
        int  minute = 1000 * 60;
        int hour = minute * 60;
        int day = hour * 24;
        int halfamonth = day * 15;
        int month = day * 30;
        long now = new java.util.Date().getTime();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Date datetime = sdf.parse(dateTimeStamp);
        final long time = datetime.getTime();

        long diffValue = now - time;
        if(diffValue < 0){
            return "";
        }
        long dayC = diffValue / day;
        long hourC = diffValue / hour;
        long minC = diffValue / minute;
        if(dayC>=1){
            result=""+ dayC +"天前";
        }
        else if(hourC>=1){
            result=""+ hourC +"小时前";
        }
        else if(minC>=1){
            result=""+ minC +"分钟前";
        }else{
            result="刚刚";
        }
        return result;

    }

}
