package com.inet;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class RecieveThread extends Thread {

    /*
     * 定义套接字对象
     */
    private Socket socket;

    /*
     * 定义文件名变量
     */
    String filename;

    /**
     * 带参构造器
     * @param socket 套接字对象
     */
    public RecieveThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();
        try {
            //获取输入流对象
            InputStream input = socket.getInputStream();
            //将输入流对象封装在datainputstream内
            DataInputStream dinput = new DataInputStream(input);

            filename = dinput.readUTF();

            System.out.println("收到来自对方的信息："+filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
