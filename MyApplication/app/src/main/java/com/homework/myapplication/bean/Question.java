package com.homework.myapplication.bean;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {

    private int id;
    private String theme;
    private String questionName;
    private String questionSrc;
    private List<Answer> answerList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionSrc() {
        return questionSrc;
    }

    public void setQuestionSrc(String questionSrc) {
        this.questionSrc = questionSrc;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }


}

