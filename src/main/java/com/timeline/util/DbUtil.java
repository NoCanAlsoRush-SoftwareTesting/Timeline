package com.timeline.util;

import java.sql.*;

public class DbUtil {
    public static final String URL = "jdbc:mysql://localhost:3306/timeline";
    public static final String USER = "root";
    public static final String PASSWORD ="123456";

    //创建与数据库的连接
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    //关闭PreparedStatement
    public static void close(PreparedStatement pstmt){
        if(pstmt != null){
            try{
                pstmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    //关闭Connection
    public static void close(Connection conn){
        if(conn != null){
            try{
                conn.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    //关闭ResultSet
    public static void close(ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

/*
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/
}