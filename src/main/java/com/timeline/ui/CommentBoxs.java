package com.timeline.ui;

import com.timeline.entity.*;

import javax.swing.*;
import java.awt.*;

//评论列表
public class CommentBoxs {
    private static final Font COMMENT_FONT = new Font("宋体", Font.PLAIN, 20);

    public static Box createCommentBox(Comment comment) {

        JLabel userLabel = new JLabel();
        userLabel.setText(comment.getUserName());
        userLabel.setFont(COMMENT_FONT);
        JLabel timeLabel = new JLabel();
        timeLabel.setText(comment.getTimeStamp());
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

}
