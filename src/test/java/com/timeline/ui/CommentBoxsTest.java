package com.timeline.ui;

import com.timeline.entity.Comment;
import org.junit.After;
import org.junit.Before;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CommentBoxsTest {
    private CommentBoxs box;
    @Before

    public void before() throws  Exception{
        box = new CommentBoxs();
    }

    @After

    public void after() throws Exception{
        box = null;
    }

    @Test
    public void testCommentBoxs() throws Exception{
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        Comment comment = new Comment(13,"name","this is a text","this is a picture",dateString);
        Box commentBox = box.createCommentBox(comment);
        assertNotNull(commentBox);
    }

    @Test

    public void testConvert_just() throws ParseException {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String afterConvert = box.convert(dateString);
        assertEquals("刚刚",afterConvert);
    }
    @Test

    public void testConvert_a_few_minutes_ago() throws ParseException {
        Date currentTime = new Date();
        //比当前时间早1分钟
        Date afterDate = new Date(currentTime.getTime()-60000);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(afterDate);
        String afterConvert = box.convert(dateString);
        assertEquals("1分钟前",afterConvert);
    }

    @Test

    public void testConvert_a_few_hours_ago() throws ParseException {
        Date currentTime = new Date();
        //比当前时间早1个小时
        Date afterDate = new Date(currentTime.getTime()-60000*60);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(afterDate);
        String afterConvert = box.convert(dateString);
        assertEquals("1小时前",afterConvert);
    }

    @Test

    public void testConvert_a_few_days_ago() throws ParseException {
        Date currentTime = new Date();
        //比当前时间早1天
        Date afterDate = new Date(currentTime.getTime()-60000*60*24);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(afterDate);
        String afterConvert = box.convert(dateString);
        assertEquals("1天前",afterConvert);
    }
    public void testConvert_more_than_a_few_days_ago() throws ParseException {
        Date currentTime = new Date();
        //一个月前
        Date afterDate1 = new Date(currentTime.getTime()-60000*60*24*15);
        Date afterDate = new Date(afterDate1.getTime()-60000*60*24*16);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(afterDate);
        String afterConvert = box.convert(dateString);
        System.out.println(afterConvert);
        assertEquals(dateString,afterConvert);
    }

}