package com.example.czz.coolquestion.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2017/3/14.
 */

public class ErrorQuestion {


    /**
     * errorQuestionlist : [{"eAnswer":"D","eContent":{"ansA":" sleep是线程类（Thread）的方法，wait是Object类的方法；","ansB":"sleep不释放对象锁，wait放弃对象锁；","ansC":"sleep暂停线程、但监控状态仍然保持，结束后会自动恢复；","ansD":"wait后进入等待锁定池，只有针对此对象发出notify方法后获得对象锁进入运行状态。","answer":"D","deQuestion":"sleep是线程类（Thread）的方法，导致此线程暂停执行指定时间，给执行机会给其他线程，但是监控状态依然保持，到时后会自动恢复。调用sleep不会释放对象锁。 wait是Object类的方法，对此对象调用wait方法导致本线程放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象发出notify方法（或notifyAll）后本线程才进入对象锁定池准备获得对象锁进入运行状态。","questionContent":"关于sleep()和wait()，以下描述错误的一项是","questionID":14,"tid":"JAVA"},"eid":1}]
     * result : success
     */

    private String result;
    private List<ErrorQuestionlistBean> errorQuestionlist;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ErrorQuestionlistBean> getErrorQuestionlist() {
        return errorQuestionlist;
    }

    public void setErrorQuestionlist(List<ErrorQuestionlistBean> errorQuestionlist) {
        this.errorQuestionlist = errorQuestionlist;
    }

    public static class ErrorQuestionlistBean implements Serializable {
        /**
         * eAnswer : D
         * eContent : {"ansA":" sleep是线程类（Thread）的方法，wait是Object类的方法；","ansB":"sleep不释放对象锁，wait放弃对象锁；","ansC":"sleep暂停线程、但监控状态仍然保持，结束后会自动恢复；","ansD":"wait后进入等待锁定池，只有针对此对象发出notify方法后获得对象锁进入运行状态。","answer":"D","deQuestion":"sleep是线程类（Thread）的方法，导致此线程暂停执行指定时间，给执行机会给其他线程，但是监控状态依然保持，到时后会自动恢复。调用sleep不会释放对象锁。 wait是Object类的方法，对此对象调用wait方法导致本线程放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象发出notify方法（或notifyAll）后本线程才进入对象锁定池准备获得对象锁进入运行状态。","questionContent":"关于sleep()和wait()，以下描述错误的一项是","questionID":14,"tid":"JAVA"}
         * eid : 1
         */

        private String eAnswer;
        private EContentBean eContent;
        private int eid;

        public String getEAnswer() {
            return eAnswer;
        }

        public void setEAnswer(String eAnswer) {
            this.eAnswer = eAnswer;
        }

        public EContentBean getEContent() {
            return eContent;
        }

        public void setEContent(EContentBean eContent) {
            this.eContent = eContent;
        }

        public int getEid() {
            return eid;
        }

        public void setEid(int eid) {
            this.eid = eid;
        }

        public static class EContentBean implements Serializable {
            /**
             * ansA :  sleep是线程类（Thread）的方法，wait是Object类的方法；
             * ansB : sleep不释放对象锁，wait放弃对象锁；
             * ansC : sleep暂停线程、但监控状态仍然保持，结束后会自动恢复；
             * ansD : wait后进入等待锁定池，只有针对此对象发出notify方法后获得对象锁进入运行状态。
             * answer : D
             * deQuestion : sleep是线程类（Thread）的方法，导致此线程暂停执行指定时间，给执行机会给其他线程，但是监控状态依然保持，到时后会自动恢复。调用sleep不会释放对象锁。 wait是Object类的方法，对此对象调用wait方法导致本线程放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象发出notify方法（或notifyAll）后本线程才进入对象锁定池准备获得对象锁进入运行状态。
             * questionContent : 关于sleep()和wait()，以下描述错误的一项是
             * questionID : 14
             * tid : JAVA
             */

            private String ansA;
            private String ansB;
            private String ansC;
            private String ansD;
            private String answer;
            private String deQuestion;
            private String questionContent;
            private int questionID;
            private String tid;

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

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }
        }
    }
}
