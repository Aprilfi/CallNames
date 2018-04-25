package com.view;

import com.utils.LodingUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

/**
 * @Author: WuHongLin
 * @Description: 选择文本文件进行姓名编排
 * @Date: 16:04 2018\4\13 0013
 */
@SuppressWarnings("serial")
public class SelectData extends JFrame implements ActionListener{

    /**
     * 声明按钮组件
     */
    JButton open=null;

    /**
     * 声明组件
     */
    JLabel jLabel = null;

    /*
     * 文件路径
     */
    private String filePath;


    public static void main(String[] args) {
        new SelectData();
    }


    public SelectData(){
        //实例化按钮
        open=new JButton("open");
        open.setBounds(0,0,180,60);
        open.setForeground(Color.WHITE);
        open.setBackground(new Color(45,75,99));
        open.setFont(new Font("仿宋", 23, 30));
        open.setBorderPainted(false);
        open.setFocusPainted(false);

        //实例化组件
        jLabel=new JLabel("________");
        //设置组件的前景色
        jLabel.setForeground(Color.lightGray);

        jLabel.setBounds(40,80,190,70);
        jLabel.setFont(new Font("楷体", 23, 25));

        //设置窗体标题
        this.setTitle("选择文件");
        //添加按钮到窗体
        this.add(open);
        this.getContentPane().setBackground(new Color(45,75,99));
        this.add(jLabel);
        //获取chose的窗体坐标
        Point p = new Chose().jFrame.getLocation();

        //设置窗体布局
        this.setBounds(p.x+280, p.y, 190, 230);
        //this.setLocationRelativeTo(null);
        this.setLayout(null);
        //设置窗体可见
        this.setVisible(true);
        //设置窗体退出方式
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //为按钮添加监听事件
        open.addActionListener(this);
    }

    /**
     * 按钮监听事件
     * @param e 监听器
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //实例化文件选择组件
        JFileChooser jfc=new JFileChooser();
        //设置组件选项
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        jfc.showDialog(new JLabel(), "选择");
        //实例化一个file实例
        File file=jfc.getSelectedFile();

        //如果file实例不为空,则赋值给filePath
        if(null != file){
            //得到文件的绝对路径
            filePath = file.getAbsolutePath();
            //利用工具类查验文件
            String tips = new LodingUtils().isFile(filePath);

            //修改姓名数组
            Run.studentNameList = null;
            Run.studentNameList = new LodingUtils().backName(filePath);
            Chose.chose = Run.studentNameList.length;
            System.out.println(Chose.chose);
            //更新组件的显示
            jLabel.setText(tips);
        }

    }


}