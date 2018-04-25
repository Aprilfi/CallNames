package com.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: WuHongLin
 * @Description: 用于读取本地的姓名存储到数组
 * @Date: 8:42 2018\4\13 0013
 */
public class LodingUtils {

    /*
     * 保存结果的字符串
     */
    private String result;

    /**
     * 无参构造器
     */
    public LodingUtils(){}

    /**
     * 保存信息的数组
     */
    String[] littleList = null;

    /*
     * 文件状态提示字符串
     */
    private String tips;

    /**
     * 查验文件状态
     * @param filePath
     * @return 判断结束的提示信息
     */
    public String isFile(String filePath){
        //新建一个指定文件路径的file实例
        File file = new File(filePath);

            if(file.isFile() && file.canRead())
                tips = "读取成功！";
            else if(file.isDirectory())
                tips = "指向文件夹";
            else if(file.length() <= 2)
                tips = "该文件为空";

            return tips;
    }

    /**
     * 按指定路径和名称读取文件并提取关键字保存在集合中
     * @param   filePath 文件所在路径
     * @return 保存信息的String型集合
     */
    public String[] backName(String filePath){
        //新建一个指定文件的file实例
        File file = new File(filePath);
        //创建泛型为String的Araaylist
        List<String> nameList = new ArrayList<>();

        try {
            //创建基于file的输入流
            InputStream input = new FileInputStream(file);
            //通过缓冲区保存并指定流的编码
            BufferedReader bf = new BufferedReader(new InputStreamReader(input,"utf-8"));

            //读取一行
            result = bf.readLine();
            //读取剩余行数
            while(bf.ready()){
                result += bf.readLine();
            }

            //创建一个String型数组
            littleList = result.split(",");

//            //将数组复制到集合
//            for(int i = 0; i < littleList.length; i ++){
//                nameList.add(littleList[i]);
//            }i

            //使用迭代器进行测试查看
            Iterator<String> newList = nameList.iterator();
            while(newList.hasNext()){
                System.out.println(newList.next());
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
        return littleList;
    }

    public static void main(String[] args){
        String wll = new LodingUtils().backName("d:/this.txt").toString();
        System.out.println(wll);
    }

}
