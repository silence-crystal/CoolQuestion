package com.example.czz.coolquestion.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */

public class Know implements Serializable{


    /**
     * knowledgeList : [{"knowledgecontent":"java的基础知识","knowledgedescribe":"java的一些小知识","knowledgeid":2,"knowledgetitle":"jassss2222","tid":1},{"knowledgecontent":"测试内容","knowledgedescribe":"测试啊啊啊","knowledgeid":5,"knowledgetitle":"测试","tid":1}]
     * result : success
     */

    private String result;
    private List<KnowledgeListBean> knowledgeList;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<KnowledgeListBean> getKnowledgeList() {
        return knowledgeList;
    }

    public void setKnowledgeList(List<KnowledgeListBean> knowledgeList) {
        this.knowledgeList = knowledgeList;
    }

    public static class KnowledgeListBean implements Serializable{
        /**
         * knowledgecontent : java的基础知识
         * knowledgedescribe : java的一些小知识
         * knowledgeid : 2
         * knowledgetitle : jassss2222
         * tid : 1
         */

        private String knowledgecontent;
        private String knowledgedescribe;
        private int knowledgeid;
        private String knowledgetitle;
        private int tid;

        public String getKnowledgecontent() {
            return knowledgecontent;
        }

        public void setKnowledgecontent(String knowledgecontent) {
            this.knowledgecontent = knowledgecontent;
        }

        public String getKnowledgedescribe() {
            return knowledgedescribe;
        }

        public void setKnowledgedescribe(String knowledgedescribe) {
            this.knowledgedescribe = knowledgedescribe;
        }

        public int getKnowledgeid() {
            return knowledgeid;
        }

        public void setKnowledgeid(int knowledgeid) {
            this.knowledgeid = knowledgeid;
        }

        public String getKnowledgetitle() {
            return knowledgetitle;
        }

        public void setKnowledgetitle(String knowledgetitle) {
            this.knowledgetitle = knowledgetitle;
        }

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }
    }
}
