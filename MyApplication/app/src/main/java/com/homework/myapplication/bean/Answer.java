package com.homework.myapplication.bean;

import java.io.Serializable;


public class Answer implements Serializable {
    private boolean isChecked;
    private int id;
    private int questionId;
    private String answerContent;
    private int isCorrect;
    private String answerSrc;



    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public int getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getAnswerSrc() {
        return answerSrc;
    }

    public void setAnswerSrc(String answerSrc) {
        this.answerSrc = answerSrc;
    }
}
