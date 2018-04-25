package com.inet;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SendThread extends Thread {

    /*
     * 定义套接字连接对象
     */
    private Socket socket;

    /*
     * 定义file实例
     */
    private File file;

    /**
     * 带参构造器
     * @param socket 套接字对象
     */
    public SendThread(Socket socket,File file){
        this.socket = socket;this.file = file;
    }

    @Override
    public void run() {
        super.run();
        try {
            //获得输出流
            OutputStream out = socket.getOutputStream();
            //将输出流封装在dataoutputstream内
            DataOutputStream dout = new DataOutputStream(out);

                //读取文件名
                String filename = file.getName();
                dout.writeUTF(filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
