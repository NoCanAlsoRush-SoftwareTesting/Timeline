package com.timeline.dao;

import com.timeline.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface CommentDao {

    /**
     * 获取数据库连接
     * @return connection
     */
    public Connection getConnection();

    /**
     *
     * 插入一条评论
     * 返回值：int
     * 参数：comment
     */
    public boolean insertComment(Comment comment);

    /**
     *
     * 删除一条评论
     * 返回值：void
     * 参数：uid
     */
    public boolean deleteComment(int uid);

    /**
     *
     * 查询一条评论
     * @param
     * @return commentlist
     */
    public List<Comment>  findCommentList();

    /**
     *
     * 回收资源
     * @param conn
     * @param pstmt
     * @param rs
     */
    public void close(Connection conn, PreparedStatement pstmt, ResultSet rs);


}

