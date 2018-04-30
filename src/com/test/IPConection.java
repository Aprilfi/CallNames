package com.test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IPConection {

    public static void main(String ... args){
        InetAddress id = new IPConection().getInetAddress();
        System.out.println(id.getHostAddress());
    }
    public InetAddress getInetAddress(){

        try {
            //selected
            System.out.println("版本控制测试");
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

}
