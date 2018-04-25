package com.view;

import com.utils.LodingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: WuHongLin
 * @Description: 输入点名人的姓名将其排除在外，然后进入点名器中
 * @Date: 11:29 2018\4\3 0003
 */
public class Run implements ActionListener{

    /*
     * 实例化JFrame的对象
     */
    JFrame jFrame = new JFrame("T235班点名器 ---by WHL");

    /*
     * 实例化JLabel对象
     */
    JLabel jLabel = new JLabel("请输入你的姓名：");

    /*
     * 实例化jtextfield对象
     */
    JTextField tf = new JTextField();

    /*
     * 实例化JButton对象
     */
    JButton jButton = new JButton("确定");

    /*
     * 声明存储学生姓名的数组
     */
    static String[] studentNameList
            = new LodingUtils()
            .backName(System.getProperty("exe.path") + " Files/t235callnames/file/nameList.txt");

    /*
     * 声明一个储存操作后姓名的集合
     */
    static List<String> newStuList = null;

    /*
     *
     */

    /**
     * 无参构造器
     */
    public Run(){
        //设置窗体大小
        jFrame.setBounds(0,0,340,130);
        //设置面板布局
        jFrame.setLayout(null);
        //设置窗体居中
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //设置按钮的大小
        jButton.setBounds(120,50,80,30);

        //设置提示文本的布局
        jLabel.setBounds(10,15,170,20);
        jLabel.setFont(new Font("仿宋", 23, 20));

        //设置文本域的布局
        tf.setBounds(190,10,120,30);
        tf.setFont(new Font("仿宋", 23, 20));
        tf.setForeground(Color.BLACK);

        //将按钮添加到窗体中
        jFrame.add(jLabel);
        jFrame.add(jButton);
        jFrame.add(tf);
        //给按钮添加监听方式
        jButton.addActionListener(this);
        //显示窗体
        jFrame.setVisible(true);
    }

    /**
     * 实现监听器继承方法
     */
    @Override
    public void actionPerformed(ActionEvent e) {
            //声明一个
            if(e.getSource() == jButton){
            //将数组转换为List集合
            List<String> list = Arrays.asList(studentNameList);
            //将list转换为ArrayList
            newStuList = new ArrayList<String>(list);
            if(!studentListSearch(newStuList,tf.getText())){
                JOptionPane.showMessageDialog(null,
                        "姓名不存在",
                        "错误",
                        JOptionPane.ERROR_MESSAGE);
            }else{
                int res = JOptionPane.showConfirmDialog(null,
                        "操作成功是否继续",
                        "成功",
                        JOptionPane.YES_NO_OPTION);
                //点击确定则删除姓名
                if(res==JOptionPane.YES_OPTION){
                    //删除此姓名
                    removeName(newStuList,tf.getText());
                    //隐藏这个窗口
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
                }
            }
        }
    }

    /**
     * 移除数组中的姓名
     * @param arr 数组
     * @return List<String> 移除后的数组
     */
    public List<String> removeName(List<String> arr,String stuName){

        arr.remove(stuName);
        return arr;
    }

    /**
     * 判断数组中是否含这个值
     * @param arr 字符型数组
     * @param stuName 姓名
     * @return boolean
     */
    public boolean studentListSearch(List<String> arr,String stuName){
        if(arr.contains(stuName)){
            System.out.println("有");
            return true;
        } else{
            System.out.println("无");
            return false;
        }

    }

}
