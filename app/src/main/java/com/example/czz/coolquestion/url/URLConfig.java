package com.example.czz.coolquestion.url;

/**
 * Created by dell on 2017/3/9.
 */

public class URLConfig {

    //主机地址
    public static String MAIN_URL="130.0.0.227";


    //查询错题本所有错题的路径
    public static  String ERRORQUESTION_URL="http://"+MAIN_URL+":8080/CoolTopic/GetAllErrorQuestion?";
    //添加到错题本的路径
    public static String ADDERRORQUESTION_URL="http://"+MAIN_URL+":8080/CoolTopic/AddErrorQuestionCollect?";
    //删错错题的路径
    public static String DELETEERRORQUESTION_URL="http://"+MAIN_URL+":8080/CoolTopic/DeleteErrorQuestion?";
    //获取分类题目的路径
    public static String GETQUESTION_URL="http://"+MAIN_URL+":8080/CoolTopic/GetQuestionByType?";
    //获取所有编程语言的路径
    public static String GETLANGUAGE_URL="http://"+MAIN_URL+":8080/CoolTopic/GetTypeInfo";


}
