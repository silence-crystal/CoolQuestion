package com.example.czz.coolquestion.url;

/**
 * Created by dell on 2017/3/9.
 */

public class URLConfig {

    //主机地址
    public static String MAIN_URL="http://130.0.0.227:8080/";


    //查询错题本所有错题的路径
    public static  String ERRORQUESTION_URL=MAIN_URL+"CoolTopic/GetAllErrorQuestion?";
    //添加到错题本的路径
    public static String ADDERRORQUESTION_URL=MAIN_URL+"CoolTopic/AddErrorQuestionCollect?";
    //删错错题的路径
    public static String DELETEERRORQUESTION_URL=MAIN_URL+"CoolTopic/DeleteErrorQuestion?";
    //获取分类题目的路径
    public static String GETQUESTION_URL=MAIN_URL+"CoolTopic/GetQuestionByType?";
    //获取所有编程语言的路径
    public static String GETLANGUAGE_URL=MAIN_URL+"CoolTopic/GetTypeInfo";


}
