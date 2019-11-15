package com.timeline.ui;

import com.timeline.entity.*;
import com.timeline.daoimp.CommentDaoImp;



import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TimeLineFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGTH = 400;
    private static final int FONT_SIZE = 36;
    private static final Font DEFAULT_FONT = new Font("宋体", Font.PLAIN, FONT_SIZE);
    public JDialog dialog = null;

    private List<Comment> allCommentList = new ArrayList<>();
    private List<Comment> commentList = new ArrayList<>();
    private CommentDaoImp cdl = new CommentDaoImp();

    public TimeLineFrame() throws ParseException {
        setTitle("TIMELINE");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel headLabel = new JLabel("Timeline");
        headLabel.setFont(new Font("宋体",Font.PLAIN,42));
        JButton refreshButton = new JButton("刷新");
        refreshButton.setContentAreaFilled(false);
        refreshButton.addActionListener(e -> {
            try {
                refresh();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });

        Box topBox = Box.createHorizontalBox();
        topBox.add(Box.createGlue());
        topBox.add(headLabel);
        topBox.add(Box.createGlue());
        topBox.add(refreshButton);
        topBox.add(Box.createGlue());

        JButton moreButton = new JButton("更多");
        moreButton.setContentAreaFilled(false);
        moreButton.addActionListener(e -> {
            try {
                more();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
        JButton addButton = new JButton("添加");
        addButton.addActionListener(e -> {showAddCommentDialog();dialog.setVisible(true);});


        Box bottomBox = Box.createHorizontalBox();
        bottomBox.add(Box.createGlue());
        bottomBox.add(moreButton);
        bottomBox.add(Box.createGlue());
        bottomBox.add(addButton);
        bottomBox.add(Box.createGlue());

        add(topBox, BorderLayout.NORTH);
        add(bottomBox,BorderLayout.SOUTH);
        newTenComment();
        loadComment();

    }

    //在ui中展示评论
    private void loadComment() throws ParseException {

        JScrollPane scroll = new JScrollPane();
        Box commentBox = Box.createVerticalBox();
        for(int i = 0; i < commentList.size(); i++){
            Comment comment = commentList.get(i);
            commentBox.add(CommentBoxs.createCommentBox(comment));
        }
        scroll.setViewportView(commentBox);
        add(scroll);
    }

    //清除原有的component
    private void removeContent(){
        Component[] components = getContentPane().getComponents();

        for(Component co:components) {
            if (co.getName() != "") {
                String a = co.getClass().getName();
                if (a.equals(JScrollPane.class.getName())) {
                    getContentPane().remove(co);
                }
            }
        }
    }

    //获取新的前十条评论
    private void newTenComment(){
        allCommentList = cdl.findCommentList();
        nextTenComment();
    }

    //获取后十条评论
    private void nextTenComment(){

        int lastcId = commentList.size()-1;
        int end = allCommentList.size();
        if(lastcId + 10 <allCommentList.size())
            end = lastcId+10;

        commentList = allCommentList.subList(0,end);
    }

    //刷行界面
    private void refresh() throws ParseException {
        removeContent();
        newTenComment();
        loadComment();
    }

    //查看更早的十条评论
    private void more() throws ParseException {
        removeContent();
        nextTenComment();
        loadComment();

    }

    //点击增添新的评论
    private void showAddCommentDialog(){
        dialog = new JDialog(this, "新的消息", true);
        // 设置对话框的宽高
        dialog.setSize(700, 500);
        // 设置对话框大小不可改变
        dialog.setResizable(false);
        // 设置对话框相对显示的位置
        dialog.setLocationRelativeTo(this);

        // 创建一个标签显示消息内容
        JLabel messageLabel = new JLabel("你的分享");
        messageLabel.setFont(new Font("宋体", Font.PLAIN, 24));
        Box titleBox = Box.createHorizontalBox();
        titleBox.add(Box.createGlue());
        titleBox.add(messageLabel);
        titleBox.add(Box.createGlue());

        JLabel uidLabel = new JLabel("uid:");
        uidLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        JTextField uidText = new JTextField(10);
        JLabel usernameLabel = new JLabel("username:");
        usernameLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        JTextField usernameText = new JTextField(20);
        JTextArea yourCommentText = new JTextArea(10,25);
        yourCommentText.setBackground(new Color(236, 231, 238));
        yourCommentText.setFont(new Font("宋体", Font.PLAIN, 28));
        yourCommentText.setLineWrap(true);
        yourCommentText.setWrapStyleWord(true);
        JLabel glueLabel1 = new JLabel(" ");
        glueLabel1.setFont(DEFAULT_FONT);
        JLabel glueLabel2 = new JLabel(" ");
        glueLabel2.setFont(DEFAULT_FONT);
        Box inputBox = Box.createHorizontalBox();
        inputBox.add(glueLabel1);
        inputBox.add(yourCommentText);
        inputBox.add(glueLabel2);


        Box userBox = Box.createHorizontalBox();
        userBox.add(Box.createHorizontalStrut(20));
        userBox.add(uidLabel);
        userBox.add(Box.createHorizontalStrut(10));
        userBox.add(uidText);
        userBox.add(Box.createHorizontalStrut(100));
        userBox.add(usernameLabel);
        userBox.add(Box.createHorizontalStrut(10));
        userBox.add(usernameText);
        userBox.add(Box.createHorizontalStrut(20));

        Box myBox = Box.createVerticalBox();
        myBox.add(userBox);
        myBox.add(Box.createVerticalStrut(10));
        myBox.add(inputBox);

        JScrollPane scrollPane = new JScrollPane(myBox);


        // 创建一个按钮用于上传
        JButton okButton = new JButton("上传");
        okButton.setContentAreaFilled(false);
        okButton.addActionListener(e1 -> {
            String yourComment =  yourCommentText.getText();
            Comment comment = new Comment(Integer.valueOf(uidText.getText()),usernameText.getText(),yourComment,"", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
             boolean res = new CommentDaoImp().insertComment(comment);
            if(res)
                JOptionPane.showMessageDialog(null, "添加成功！！！", "提示", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "添加失败！！！", "提示", JOptionPane.ERROR_MESSAGE);
            dialog.dispose();
        });

        // 创建一个按钮用于关闭对话框
        JButton cancelButton = new JButton("取消");
        cancelButton.setContentAreaFilled(false);
        cancelButton.addActionListener(e1 -> dialog.dispose());
        Box bottomHBox = Box.createHorizontalBox();
        bottomHBox.add(Box.createGlue());
        bottomHBox.add(okButton);
        bottomHBox.add(Box.createGlue());
        bottomHBox.add(Box.createGlue());
        bottomHBox.add(cancelButton);
        bottomHBox.add(Box.createGlue());


        // 创建对话框的内容面板, 在面板内可以根据自己的需要添加任何组件并做任意是布局
        JPanel panel = new JPanel();


        panel.setLayout(new BorderLayout());
        // 添加组件到面板
        panel.add(titleBox,BorderLayout.NORTH);
        panel.add(scrollPane,BorderLayout.CENTER);
        panel.add(bottomHBox,BorderLayout.SOUTH);

        // 设置对话框的内容面板
        dialog.setContentPane(panel);
        // 显示对话框

    }
}
