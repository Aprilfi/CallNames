package com.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * @Author: WuHongLin
 * @Description: 在32位学生里随机点名显示在主面板上
 * @Date: 9:05 2018\4\3 0003
 */
public class RandomRollCall extends Thread implements ActionListener {

    /*
     * 实例化JFrame对象
     */
    JFrame jFrame = new JFrame("t235班点名器 by ---WuHongLin");

    /*
     * 实例化JButton对象
     */
    JButton btn = new JButton("stop");

    /*
     * 实例化JTextField对象
     */
    JTextField tf = new JTextField();

    /*
     * 实例化Point对象
     */
    Point point = new Point();

    /*
     * 实例化JLabel象
     */
    JLabel jLabel = new JLabel();

    /*
     * 实例化jbutton对象
     */
    JButton btn_exit = new JButton("●");

    /**
     * 无参构造器
     */
    public RandomRollCall(){
        //设置面板布局
        jFrame.setLayout(null);
        //设置面板大小
        jFrame.setBounds(300, 400, 300, 250);
        //禁用本地边框
        jFrame.setUndecorated(true);
        //面板居中显示
        jFrame.setLocationRelativeTo(null);
        //面板背景色及前景色
        jFrame.setForeground(new Color(55,55,55));
        jFrame.getContentPane().setBackground(new Color(45,75,99));

        /*
         * 给面板添加按下监听内部匿名类
         */
        jFrame.addMouseListener(new MouseAdapter() {
            /**
             * 按下操作
             */
            @Override
            public void mousePressed(MouseEvent e) {
                //当鼠标按下时获得当前窗口的位置
                point.x = e.getX();
                point.y = e.getY();
            }
        });
        /*
         * 给面板添加拖动监听内部匿名类
         */
        jFrame.addMouseMotionListener(new MouseMotionAdapter(){
            /**
             * 拖动操作
             */
            @Override
            public void mouseDragged(MouseEvent e) {
                // 当鼠标拖动时获取当前窗口的位置
                Point p = jFrame.getLocation();
                // 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
                jFrame.setLocation(p.x + e.getX() - point.x,p.y + e.getY() - point.y);
            }
        });


        //
        //文本区
        //
        tf.setBounds(50, 60, 200, 50);
        //设置文本区的文本布局
        tf.setHorizontalAlignment(JTextField.CENTER);
        //设置前景色和背景色
        tf.setForeground(Color.white);
        tf.setBackground(new Color(45,75,99));
        //设置字体信息
        tf.setFont(new Font("仿宋", 23, 30));
        //设置不可编辑
        tf.setEditable(false);

        //
        //按钮
        //
        //设置按钮的布局大小
        btn.setBounds(80, 150, 150, 50);
        //设置字体
        btn.setFont(new Font("Times New Roman",23,30));
        //设置关闭按钮
        btn_exit.setBounds(240,0,80,30);
        btn_exit.setFont(new Font("Times New Roman", 23, 20));
        //不绘制边框
        btn_exit.setBorderPainted(false);
        //设置背景颜色
        btn_exit.setForeground(Color.white);
        btn_exit.setBackground(new Color(45,75,99));
        btn_exit.setFocusPainted(false);
        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //
        //信息栏
        //
        jLabel.setBounds(20,0,300,30);
        jLabel.setForeground(Color.lightGray);
        jLabel.setText("T235班点名器  by ---WHL");

        //将组件添加到JFrame对象
        jFrame.add(btn);
        jFrame.add(tf);
        jFrame.add(jLabel);
        jFrame.add(btn_exit);
        //设置主面板可见
        jFrame.setVisible(true);
        //给按钮添加监听事件
        btn.addActionListener(this);
    }

    /**
     * 线程run方法
     */
    public void run(){
        for(int i=1;i<=Run.studentNameList.length;i++){
            if(Chose.chose == Run.studentNameList.length){
                tf.setText(Run.studentNameList[i-1]);
            }else{
                tf.setText(Run.newStuList.get(i-1));
            }
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i==Chose.chose)i=1;
        }
    }

    /**
     * 按钮监听方法
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn){
            if(btn.getText().equals("start")){
                this.resume();
                if(Chose.chose == Run.studentNameList.length){
                    tf.setText(Run.studentNameList[(new Random().nextInt(Chose.chose-1))]);
                }else{
                    tf.setText(Run.newStuList.get(new Random().nextInt(Chose.chose-1)));
                }
                btn.setText("stop");
            }
            else if(btn.getText().equals("stop")){
                //this.stop();
                this.suspend();
                btn.setText("start");
            }
        }
    }

}
