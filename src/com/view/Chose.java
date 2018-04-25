package com.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;


/**
 * @Author: WuHongLin
 * @Description: 选择使用人的分类
 * @Date: 16:22 2018\4\3 0003
 */
public class Chose implements ActionListener {

    /*
     * 实例化JFrame
     */
    JFrame jFrame = new JFrame();

    /*
     * 实例化jPanel
     */
    JPanel jPanel = new JPanel();

    /*
     * 实例化jbutton
     */
    JButton btn_transport = new JButton("闪电互传");

    /*
     * 实例化jlabel
     */
    JLabel jLabel = new JLabel("make your choice");

    /*
     * 实例化第一个按钮
     */
    JButton btna = new JButton("教师");

    /*
     * 实例化第二个按钮
     */
    JButton btnb = new JButton("学生");

    static int chose = 0;

    /**
     * 无参构造器
     */
    public Chose(){
        //设置窗体大小
        jFrame.setBounds(0,0,280,230);
        //设置面板布局
        jFrame.setLayout(null);
        //设置窗体居中
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置提示组件的布局
        jLabel.setBounds(50,40,180,30);
        jLabel.setFont(new Font("仿宋", 23, 20));

        //设置两个按钮的布局
        btna.setBounds(35,120,80,30);
        btna.setFont(new Font("仿宋", 23, 15));
        btnb.setBounds(155,120,80,30);
        btnb.setFont(new Font("仿宋", 23, 15));

        //将组件添加到窗体内
        jFrame.add(jLabel);
        jFrame.add(btna);
        jFrame.add(btnb);
        jFrame.add(btn_transport);

        btn_transport.setBounds(0,0,280,30);
        btn_transport.setFont(new Font("微软雅黑",23,15));
        btn_transport.setFocusPainted(false);
        btn_transport.setBorderPainted(false);
        btn_transport.setForeground(Color.darkGray);
        btn_transport.setBackground(Color.lightGray);
        btn_transport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Transport ts = new Transport();
                        com.sun.awt.AWTUtilities.setWindowShape(
                                //窗口圆角设置
                                ts.jFrame,new RoundRectangle2D.Double(
                                        0.0D, 0.0D,
                                        ts.jFrame.getWidth(),
                                        ts.jFrame.getHeight(),
                                        35.0D,35.0D));
                        ts.jFrame.setVisible(true);
                        //启动线程
                        ts.start();
                    }
                });
            }
        });
        //设置面板可见
        jFrame.setVisible(true);
        //为按钮添加监听事件
        btna.addActionListener(this);
        btnb.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btna){
                this.chose = Run.studentNameList.length;
            jFrame.setVisible(false);

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    RandomRollCall rrc = new RandomRollCall();
                    com.sun.awt.AWTUtilities.setWindowShape(
                            //窗口圆角设置
                            rrc.jFrame,new RoundRectangle2D.Double(
                                    0.0D, 0.0D,
                                    rrc.jFrame.getWidth(),
                                    rrc.jFrame.getHeight(),
                                    35.0D,35.0D));
                    rrc.jFrame.setVisible(true);
                    //启动线程
                    rrc.start();
                }
            });
        }else if(e.getSource() == btnb){
            jFrame.setVisible(false);
                this.chose = Run.studentNameList.length-1;
            Run run = new Run();
        }
    }

    public static void main(String[] args){
        new SelectData();

    }
}
