package com.timeline.ui;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.awt.*;
import static java.lang.Thread.sleep;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import static org.junit.jupiter.api.Assertions.*;

public class TimeLineFrameTest {

    private TimeLineFrame timeLineFrame;

   @Before
    public void before() throws Exception{
        timeLineFrame = new TimeLineFrame();
    }

    @After
    public void after() throws Exception{
        timeLineFrame.dispose();
    }


    private int scrollNum(){
        int num=0;
        Component[] components = timeLineFrame.getContentPane().getComponents();

        for (Component co:components){
            if(co.getName() != ""){
                String s = co.getClass().getName();
                if(s.equals((JScrollPane.class.getName()))){
                    num++;
                }
            }
        }
        return num;
    }

    public void testGetCommentList() throws Exception{
        timeLineFrame.refresh();
        assertEquals(10,timeLineFrame.getCommentList().size());
    }

    /*
    测试refresh()
     */
    public void testRefresh()throws Exception{
        try {
            Method method = timeLineFrame.getClass().getDeclaredMethod("refresh");
            method.setAccessible(true);
            method.invoke(timeLineFrame,null);
            sleep(1000);
            assertEquals(1,scrollNum());
        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        }
    }

    /*
    测试initData()
     */

    /*public void testInitData() throws Exception {
        timeLineFrame.init();
        sleep(1000);
        assertEquals(1,scrollNum());
    }*/

    /*
    测试loadComment()
     */
    @Test
    public void testLoadComment() throws Exception{
        try {
            timeLineFrame.refresh();
            Method method = timeLineFrame.getClass().getDeclaredMethod("loadComment");
            method.setAccessible(true);
            method.invoke(timeLineFrame,null);
            sleep(1000);
            assertEquals(1,scrollNum());
        }catch (NoSuchMethodException e){
        }catch (IllegalAccessException e){
        }catch (InvocationTargetException e){}
    }


    /*
    测试获取以往的评论
     */
    @Test
    public void testMore() throws Exception{
        try {
            timeLineFrame.refresh();
            Method method = timeLineFrame.getClass().getDeclaredMethod("more");
            method.setAccessible(true);
            method.invoke(timeLineFrame,null);
            sleep(1000);
            assertEquals(20,scrollNum());
        }catch (NoSuchMethodException e){
        }catch (IllegalAccessException e){
        }catch (InvocationTargetException e){}
    }

    /*
    测试removeContent()
     */
    @Test
    public void testRemoveContent() throws Exception{
        try {
            Method method = timeLineFrame.getClass().getDeclaredMethod("removeContent");
            method.setAccessible(true);
            method.invoke(timeLineFrame,null);
            sleep(1000);
            assertEquals(0,scrollNum());
        }catch (NoSuchMethodException e){
        }catch (IllegalAccessException e){
        }catch (InvocationTargetException e){}
    }

    /*
    测试showAddCommentDialog()
     */
    @Test
    public void testShowAddCommentDialog() throws Exception {
        try {
            Method method = timeLineFrame.getClass().getDeclaredMethod("showAddCommentDialog");
            method.setAccessible(true);
            method.invoke(timeLineFrame,null);
            assertNotNull(timeLineFrame.dialog);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}