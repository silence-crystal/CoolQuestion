package com.example.czz.coolquestion.bean;

/**
 * Created by dell on 2017/3/2.
 */

public class Question {
    private String question_language;//问题所属的计算机语言
    private String question_title;//问题题目
    private String answerA;//答案A
    private String answerB;//答案B
    private String answerC;//答案C
    private String answerD;//答案D
    private String answer_correct;//正确答案
    private String answer_description;//正确答案解释

    public String getQuestion_language() {
        return question_language;
    }

    public void setQuestion_language(String question_language) {
        this.question_language = question_language;
    }

    public String getQuestion_title() {
        return question_title;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getAnswer_correct() {
        return answer_correct;
    }

    public void setAnswer_correct(String answer_correct) {
        this.answer_correct = answer_correct;
    }

    public String getAnswer_description() {
        return answer_description;
    }

    public void setAnswer_description(String answer_description) {
        this.answer_description = answer_description;
    }

    public Question(String question_language, String question_title, String answerA, String answerB, String answerC, String answerD, String answer_correct, String answer_description) {
        this.question_language = question_language;
        this.question_title = question_title;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answer_correct = answer_correct;
        this.answer_description = answer_description;
    }
}
