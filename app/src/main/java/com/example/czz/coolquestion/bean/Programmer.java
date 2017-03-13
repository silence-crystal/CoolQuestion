package com.example.czz.coolquestion.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9.
 */

public class Programmer implements Serializable{
   private String result;
    private List<ProgrammerNews> news;

    public Programmer() {
    }

    public Programmer(String result, List<ProgrammerNews> news) {
        this.result = result;
        this.news = news;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ProgrammerNews> getNews() {
        return news;
    }

    public void setNews(List<ProgrammerNews> news) {
        this.news = news;
    }
}
