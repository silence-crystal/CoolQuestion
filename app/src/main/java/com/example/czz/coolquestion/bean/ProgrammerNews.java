package com.example.czz.coolquestion.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/1.
 */

public class ProgrammerNews implements Serializable{

    private String newstitle;//标题
    private String newspublisher;//发布者
    private String newspublishtime;//时间
    private String newsdescribe;//描述
    private String newsdetails;//网址url
    private String newspic;//图片

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getNewspublisher() {
        return newspublisher;
    }

    public void setNewspublisher(String newspublisher) {
        this.newspublisher = newspublisher;
    }

    public String getNewspublishtime() {
        return newspublishtime;
    }

    public void setNewspublishtime(String newspublishtime) {
        this.newspublishtime = newspublishtime;
    }

    public String getNewsdescribe() {
        return newsdescribe;
    }

    public void setNewsdescribe(String newsdescribe) {
        this.newsdescribe = newsdescribe;
    }

    public String getNewsdetails() {
        return newsdetails;
    }

    public void setNewsdetails(String newsdetails) {
        this.newsdetails = newsdetails;
    }

    public String getNewspic() {
        return newspic;
    }

    public void setNewspic(String newspic) {
        this.newspic = newspic;
    }

    public ProgrammerNews(String newstitle, String newspublisher, String newspublishtime, String newsdescribe, String newsdetails, String newspic) {
        this.newstitle = newstitle;
        this.newspublisher = newspublisher;
        this.newspublishtime = newspublishtime;
        this.newsdescribe = newsdescribe;
        this.newsdetails = newsdetails;
        this.newspic = newspic;
    }

    public ProgrammerNews() {
    }
}
