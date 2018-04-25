package com.view;

import java.io.File;

public class Test {

    public static void main(String[] args){
        File file = new File("src/com/view/nameList.txt");
        System.out.println(file.getAbsolutePath());
    }

}
