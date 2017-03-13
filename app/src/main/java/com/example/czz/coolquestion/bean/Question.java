package com.example.czz.coolquestion.bean;

import java.util.List;

/**
 * Created by dell on 2017/3/2.
 */

public class Question {

    /**
     * questionList : [{"ansA":"1995","ansB":"1996","ansC":"1997","ansD":"1998","answer":"A","deQuestion":"测试","questionContent":"java起源与多少年？","questionID":4,"tid":1},{"ansA":"rwer","ansB":"erwer","ansC":"fewrw","ansD":"fewrw","answer":"C","deQuestion":"rererer","questionContent":"ewrwerw11","questionID":7,"tid":1},{"ansA":"dsadsa","ansB":"sad","ansC":"sadas","ansD":"daa","answer":"D","deQuestion":"sdsd","questionContent":"dsada","questionID":8,"tid":1},{"ansA":"1111","ansB":"1111","ansC":"1111","ansD":"1111","answer":"A","deQuestion":"1212122","questionContent":"qqw","questionID":9,"tid":1},{"ansA":"222","ansB":"222","ansC":"222","ansD":"222","answer":"A","deQuestion":"323233","questionContent":"eqwe","questionID":10,"tid":1}]
     * result : success
     */

    private String result;
    private List<QuestionListBean> questionList;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<QuestionListBean> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionListBean> questionList) {
        this.questionList = questionList;
    }

    public static class QuestionListBean {
        /**
         * ansA : 1995
         * ansB : 1996
         * ansC : 1997
         * ansD : 1998
         * answer : A
         * deQuestion : 测试
         * questionContent : java起源与多少年？
         * questionID : 4
         * tid : 1
         */

        private String ansA;
        private String ansB;
        private String ansC;
        private String ansD;
        private String answer;
        private String deQuestion;
        private String questionContent;
        private int questionID;
        private int tid;

        public String getAnsA() {
            return ansA;
        }

        public void setAnsA(String ansA) {
            this.ansA = ansA;
        }

        public String getAnsB() {
            return ansB;
        }

        public void setAnsB(String ansB) {
            this.ansB = ansB;
        }

        public String getAnsC() {
            return ansC;
        }

        public void setAnsC(String ansC) {
            this.ansC = ansC;
        }

        public String getAnsD() {
            return ansD;
        }

        public void setAnsD(String ansD) {
            this.ansD = ansD;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getDeQuestion() {
            return deQuestion;
        }

        public void setDeQuestion(String deQuestion) {
            this.deQuestion = deQuestion;
        }

        public String getQuestionContent() {
            return questionContent;
        }

        public void setQuestionContent(String questionContent) {
            this.questionContent = questionContent;
        }

        public int getQuestionID() {
            return questionID;
        }

        public void setQuestionID(int questionID) {
            this.questionID = questionID;
        }

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }
    }
}
