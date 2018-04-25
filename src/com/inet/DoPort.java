package com.inet;

import java.net.*;

/**
 * @Author: WuHongLin
 * @Description: 获取本机ip及端口
 * @Date: 19:44 2018\4\17 0017
 */
public class DoPort {

    /*
     * 声明ip地址对象
     */
    private InetAddress inet = null;

    /*
     * 声明可用端口
     */
    private int doPort = 8888;


    /**
     * 获取本机ip地址对象
     * @return ip对象
     */
    public InetAddress getinet(){
        try {
            //获取本机ip地址
            inet = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return inet;
    }

    /**
     * 获取本机可用端口
     * @return 端口号
     */
    public int getDoPort(){
        for (int i = 5001; i < 65536; i++) {

            try {
                DatagramSocket ds=new DatagramSocket(i);
                System.out.println("可用 port:"+i);
                doPort = i;
                break;
            } catch (SocketException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
                System.out.println("已占用 port:"+i);
            }
        }
        return doPort;
    }

}
