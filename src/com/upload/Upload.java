package com.upload;

import jdk.internal.util.xml.impl.Input;

import java.io.*;

public class Upload {

    public static void main(String[] args){
        File f = new File("D:/callnames_setup.exe");

        try {
            InputStream input = new FileInputStream(f);

            byte[] all = new byte[(int)f.length()];

            try {
                input.read(all);

                File f2 = new File("D:/well.exe");

                OutputStream out = new FileOutputStream(f2);

                out.write(all);

                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
