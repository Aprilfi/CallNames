package com.view;

import com.inet.DoPort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.net.*;
import java.util.Enumeration;

/**
 * @Author: WuHongLin
 * @Description: 用于文件互传服务的view层
 * @Date: 15:33 2018\4\17 0017
 */
public class Transport extends Thread implements ActionListener {

    /*
     * 声明提示组件
     */
    private JLabel tips = new JLabel("闪电互传");

    /*
     * 声明发送时间
     */
    private int time = 0;

    private int port = 8888;

    /*
     * 声明输入框组件
     */
    private JTextField jtf = new JTextField("port");

    /*
     * 声明输入框组件
     */
    private JTextField jtf_a = new JTextField("ipv4");

    /*
     * 声明提示组件
     */
    private JButton tips_1 = new JButton("关闭");

    /*
     * 实例化JFrame对象
     */
    JFrame jFrame = new JFrame("文件互传 by ---WuHongLin");

    /*
     * 实例化Point对象
     */
    Point point = new Point();

    /*
     * 实例化jlabel对象
     */
    JTextArea jt = new JTextArea();

    /*
     * 声明发送按钮
     */
    private CircleButton btn_1 = new CircleButton("发送");

    /*
     * 声明接受按钮
     */
    private CircleButton btn_2 = new CircleButton("接收");

    public Transport() throws UnknownHostException {
        //设置窗体大小
        jFrame.setBounds(0,0,320,230);
        //设置面板布局
        jFrame.setLayout(null);
        //设置窗体居中
        jFrame.setLocationRelativeTo(null);
        jFrame.getContentPane().setBackground(new Color(45,75,99));
        jFrame.setUndecorated(true);

        //设置提示组件的布局
        tips.setBounds(95,10,180,30);
        tips.setFont(new Font("微软雅黑", 23, 30));
        tips.setForeground(Color.LIGHT_GRAY);

        tips_1.setBounds(265,0,50,40);
        tips_1.setFont(new Font("微软雅黑", 23, 15));
        tips_1.setForeground(Color.LIGHT_GRAY);
        tips_1.setFocusPainted(false);
        tips_1.setBorderPainted(false);
        tips_1.setBackground(new Color(45,75,99));
        tips_1.setMargin(new Insets(0, 0, 0, 0));// 按钮内容与边框距离

        btn_1.setBounds(40,100,100,100);
        btn_1.setFont(new Font("微软雅黑", 23, 20));
        btn_1.setForeground(Color.LIGHT_GRAY);

        btn_2.setBounds(180,100,100,100);
        btn_2.setFont(new Font("微软雅黑", 23, 20));
        btn_2.setForeground(Color.LIGHT_GRAY);

        jt.setBounds(0,49,320,40);
        jt.setEnabled(false);
        jt.setFocusable(false);
        jt.setFont(new Font("楷体",Font.BOLD|Font.ITALIC,16));
        jt.setBackground(new Color(45,75,99));
        jt.setForeground(Color.white);
        DoPort doPort = new DoPort();
        port = doPort.getDoPort();
        //获取客户端局域网IP地址
        InetAddress host = InetAddress.getLocalHost();

        String IPAddress = host.getHostAddress();
        jt.append("端口为："+port+"；地址:"+IPAddress);

        jtf.setBounds(40,70,100,30);
        jtf_a.setBounds(180,70,100,30);


        tips_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

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

        //添加组件到窗体
        jFrame.add(tips);
        jFrame.add(tips_1);
        jFrame.add(btn_1);
        jFrame.add(btn_2);
        jFrame.add(jtf);
        jFrame.add(jtf_a);
        jFrame.add(jt);

        btn_1.addActionListener(this);
        btn_2.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btn_1){
                port = Integer.parseInt(jtf.getText());
                String IPAddress = jtf_a.getText();
                System.out.println(port);
                //实例化文件选择组件
                JFileChooser jfc=new JFileChooser();
                //设置组件选项
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
                jfc.showDialog(new JLabel(), "选择");
                //实例化一个file实例
                File f = jfc.getSelectedFile();
                //获取file实例的文件名
                String filename = f.getName();
                //计算预估的发送时间
                String second = String.valueOf((int)f.length()/(2000*1024));
                jt.append("预计发送时长："+second+"s");
                try {
                    //选择与之匹配的端口，建立客户端
                    //获取客户端局域网IP地址
                    /**InetAddress host = InetAddress.getLocalHost();

                    String IPAddress = host.getHostAddress();**/
                    System.out.println(IPAddress);
                    //创建套接字对象
                    Socket socket = new Socket(IPAddress,port);
                    //读取文件
                    FileInputStream input = new FileInputStream(f);
                    //创建发送文件名的输出流
                    DataOutputStream dinput = new DataOutputStream(socket.getOutputStream());
                    //发送文件名与预估时间
                    dinput.writeUTF(filename+","+second);
                    //创建基于file的输出流
                    FileOutputStream out = (FileOutputStream)socket.getOutputStream();
                    //根据file实例的大小创建byte数组保存文件
                    byte[] all = new byte[(int)f.length()];

                    try {
                        //写入文件
                        int n = input.read(all);
                        //获得开始任务时间
                        int start = (int)System.currentTimeMillis();
                        //知道文件写入完毕结束传输
                        while(n != -1){
                            out.write(all,0,n);
                            n = input.read(all);
                        }
                        //获得任务结束时间
                        int end = (int)System.currentTimeMillis();

                        time = end - start;
                        System.out.println("发送文件所耗时间："+time+"ms");
                        jt.setText("发送完成，用时"+time/1000+"s");
                        //关闭连接
                        out.close();
                        socket.close();
                        input.close();
                        dinput.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                } catch (UnknownHostException e1) {
                    jt.setText("没有可用端口");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }else if(e.getSource() == btn_2){
                //建立服务器端
                jt.append("准备就绪");
                try {
                    //服务端口
                    System.out.println(port);
                    ServerSocket server = new ServerSocket(port);

                    //等待客户端的呼叫
                    System.out.println("准备中");
                    Socket socket = server.accept();//阻塞
                    //new RecieveThread(socket).start();
                    //读取传过来的文件名
                    DataInputStream dinput = new DataInputStream(socket.getInputStream());
                    //定义一个变量接收字符串
                    String text = dinput.readUTF();
                    String filename = text.split(",")[0];
                    String time = text.split(",")[1];
                    jt.append("预计接收时长："+time+"s");
                    //创建file实例
                    File file_1 = new File("e:/"+filename);
                    //在电脑写入空的文件
                    FileOutputStream out = new FileOutputStream(file_1);

                    System.out.println(text);
                    //读取传过来的数据文件
                    FileInputStream input = (FileInputStream)socket.getInputStream();
                    //准备读取文件的字节数组
                    byte[] in = new byte[64];

                    //读取输入流的字节总数
                    int n = input.read(in);
                    //获取系统开始写入的时间
                    int start = (int)System.currentTimeMillis();
                    while(n != -1){
                        //在空文件里写入传过来的数据
                        out.write(in,0,n);
                        //当读取完毕时结束写入
                        n = input.read(in);
                    }
                    //获取系统技术写入的时间
                    int end = (int)System.currentTimeMillis();
                    System.out.println("接收所花费的时间："+(end - start)+"ms");
                    jt.setText("耗时"+(end-start)/1000+"s,保存在"+file_1.getAbsolutePath());

                    //关闭套接字，服务器套接字对象
                    socket.close();
                    server.close();
                    input.close();
                    out.close();

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                //创建服务器套接字对象

            }
    }

    /**
     * 返回当前局域网的IPV4地址
     * @return InetAddress IP地址
     */
    public InetAddress getInetAddress(){

        try {
            //获取当前网络的所有网络接口
            Enumeration<NetworkInterface> nif = NetworkInterface.getNetworkInterfaces();

            //不为空的情况下进行循环判断
            while(nif.hasMoreElements()){
                //枚举enumeration的下一个元素
                NetworkInterface nifs = nif.nextElement();

                if (nifs.getName().startsWith("wlan")) {

                    //通过enumeration接口保存IP地址
                    Enumeration<InetAddress> id = nifs.getInetAddresses();

                    //接口内包含元素时循环判断
                    while (id.hasMoreElements()) {
                        InetAddress ids = id.nextElement();

                        if (ids.getAddress().length == 4) {
                            //返回ipv4地址
                            return ids;
                        }

                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Transport ts = null;
                try {
                    ts = new Transport();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
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

}