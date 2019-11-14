package com.timeline.daoimp;


import com.timeline.entity.Comment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CommentDaoImpTest {

    CommentDaoImp commentDaoImp;
    Connection conn = mock(Connection.class);
    PreparedStatement pstmt = mock(PreparedStatement.class);

    class CommentDaoImpFake extends CommentDaoImp{
        public  Connection getConnection(){
            return conn;
        }
    }


    @BeforeEach
    public void before(){
        commentDaoImp = new CommentDaoImpFake();
    }
    @AfterEach
    public void after(){
        commentDaoImp = null;
    }

    @Test
    void insertCommentTest()  throws Exception{

        //creating testing comment
        int userId = 9;
        String userName =  "jerry";
        String commentText = "goodbye";
        String picture = "p10";
        String timeStamp = "2019-11-11";
        Comment comment = new Comment(userId,userName,commentText,picture,timeStamp);

        //stubbing method
        when(conn.prepareStatement(anyString())).thenReturn(pstmt);

        //calling the method
        boolean res = commentDaoImp.insertComment(comment);
        assert(res);

        //creating argument captors
        ArgumentCaptor<Integer> intArgCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> intArgCaptor2 = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<String> stringArgCaptor = ArgumentCaptor.forClass(String.class);

        //verifying mock pstmt executes setInt once and executes setString 4 times
        verify(pstmt).setInt(intArgCaptor.capture(),intArgCaptor2.capture());
        verify(pstmt, new Times(4)).setString(intArgCaptor.capture(),stringArgCaptor.capture());

        //verifying each argument
        assertEquals(userId,intArgCaptor2.getAllValues().get(0));
        assertEquals(userName,stringArgCaptor.getAllValues().get(0));
        assertEquals(commentText,stringArgCaptor.getAllValues().get(1));
        assertEquals(picture,stringArgCaptor.getAllValues().get(2));
        assertEquals(timeStamp,stringArgCaptor.getAllValues().get(3));

        //verify mock resource were closed
        verify(pstmt).executeUpdate();
        verify(pstmt).close();
        verify(conn).close();
    }

    @Test
    void deleteCommentTest() throws Exception {

        //creating testing id
        int usrId = 9;

        //stubbing method
        when(conn.prepareStatement(anyString())).thenReturn(pstmt);

        //calling testing method
        boolean res = commentDaoImp.deleteComment(usrId);
        assert(res);

        //creating argument captor
        ArgumentCaptor<Integer> intArgCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> intArgCaptor2 = ArgumentCaptor.forClass(Integer.class);

        //verifying arguments
        verify(pstmt).setInt(intArgCaptor.capture(),intArgCaptor2.capture());
        assertEquals(usrId,intArgCaptor2.getAllValues().get(0));

        //verifying resource were closed
        verify(pstmt).executeUpdate();
        verify(pstmt).close();
        verify(conn).close();
    }

    @Test
    void findCommentList() throws  Exception{

        ResultSet rs = Mockito.mock(ResultSet.class);
        List<Comment> commentList;

        int userId = 9;
        String userName = "lionel";
        String commentText = "hello world";
        String picture = "p1";
        String timeStamp = "2019-11-11";

        //stubbing method
        when(conn.prepareStatement(anyString())).thenReturn(pstmt);
        when(pstmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("uid")).thenReturn(userId);
        when(rs.getString("username")).thenReturn(userName);
        when(rs.getString("comment")).thenReturn(commentText);
        when(rs.getString("picture")).thenReturn(picture);
        when(rs.getString("timestamp")).thenReturn(timeStamp);

        //calling testing method
        commentList = commentDaoImp.findCommentList();
        assertNotNull(commentList);

        //verifying the times getInt and getString rs executed
        verify(rs).getInt(anyString());
        verify(rs,times(4)).getString(anyString());

        //verifying resource were closed
        verify(pstmt).executeQuery();
        verify(rs).close();
        verify(pstmt).close();
        verify(conn).close();
    }
}