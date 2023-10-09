package com.homework.myapplication.model;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.homework.myapplication.bean.Answer;
import com.homework.myapplication.bean.Exam;
import com.homework.myapplication.bean.Question;
import com.homework.myapplication.bean.Score;
import com.homework.myapplication.interfaces.CallBack;
import com.homework.myapplication.util.DataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class DaoManager {
    private DataBaseHelper db;
    private SQLiteDatabase sd;
    private CallBack callBack;

    public DaoManager(Context context,CallBack callBack) {
        db = new DataBaseHelper(context);
        sd = db.getWritableDatabase();
        this.callBack = callBack;
    }

    public void saveQuestionInfo(Question question) {
        db.getWritableDatabase().execSQL(
                "insert into question (_id,theme, question_name,question_src) values(?,?,?,?)",
                new Object[]{question.getId(), question.getTheme(), question.getQuestionName(),question.getQuestionSrc()});
    }

    public void saveAnswerInfo(Answer answer) {
        db.getWritableDatabase().execSQL(
                "insert into answer (_id,question_id,answer_content,answer_src,is_correct) values(?,?,?,?,?)",
                new Object[]{answer.getId(), answer.getQuestionId(), answer.getAnswerContent(),answer.getAnswerSrc(), answer.getIsCorrect()});
    }

    public void saveExamInfo(Exam exam) {
        db.getWritableDatabase().execSQL(
                "insert into exam (exam_id,user_name,question_id,question_content, is_correct,exam_date) values(?,?,?,?,?,?)",
                new Object[]{ exam.getExamId(), exam.getUserName(), exam.getQuestionId(), exam.getQuestionContent(), exam.getIsCorrect(),exam.getExamDate()});
    }

    public void saveScoreInfo(Score score, Activity activity) {
        db.getWritableDatabase().execSQL(
                "insert into score (exam_id,user_name,score,exam_date) values(?,?,?,?)",
                new Object[]{ score.getExamId(), score.getUserName(), score.getScore(), score.getExamDate()});
        callBack.scoreCallback(score,activity);
    }

    /**
     * add data
     */
    public void addData(String tableName, String[] key, String[] values) {
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < key.length; i++) {
            contentValues.put(key[i], values[i]);
        }
        sd.insert(tableName, null, contentValues);
        contentValues.clear();
    }

    /**
     * query question data
     */
    public List<Question> queryQuestionData() {
        List<Question> list = new ArrayList<>();
        Cursor cursor = sd.rawQuery("select _id,theme,question_name,question_src" +
                " from question", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String theme = cursor.getString(1);
            String questionName = cursor.getString(2);
            String questionSrc = cursor.getString(3);
            Question question = new Question();
            question.setId(id);
            question.setTheme(theme);
            question.setQuestionName(questionName);
            question.setQuestionSrc(questionSrc);
            list.add(question);
        }

        return list;
    }

    /**
     * query answer data
     */
    public List<Answer> queryAnswerData() {
        List<Answer> list = new ArrayList<>();
        Cursor cursor = sd.rawQuery("select _id,question_id,answer_content,answer_src, is_correct" +
                " from answer", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int questionId = cursor.getInt(1);
            String answerContent = cursor.getString(2);
            String answerSrc = cursor.getString(3);
            int isCorrect = cursor.getInt(4);
            Answer answer = new Answer();
            answer.setId(id);
            answer.setQuestionId(questionId);
            answer.setAnswerContent(answerContent);
            answer.setAnswerSrc(answerSrc);
            answer.setIsCorrect(isCorrect);
            list.add(answer);
        }
        return list;
    }

    /**
     * query exam data
     */
    public List<Exam> queryExamData(String id) {
        List<Exam> list = new ArrayList<>();
        Cursor cursor = sd.rawQuery("select exam_id,user_name,question_id,question_content,is_correct,exam_date" +
                " from exam where exam_id = '"+id+"'", null);
        while (cursor.moveToNext()) {
            String examId = cursor.getString(0);
            String userName = cursor.getString(1);
            int questionId = cursor.getInt(2);
            String questionContent = cursor.getString(3);
            int isCorrect = cursor.getInt(4);
            String examDate = cursor.getString(cursor.getColumnIndex("exam_date"));
            Exam exam = new Exam();
            exam.setExamId(examId);
            exam.setUserName(userName);
            exam.setQuestionId(questionId);
            exam.setIsCorrect(isCorrect);
            exam.setQuestionContent(questionContent);
            exam.setExamDate(examDate);
            list.add(exam);
        }
        return list;
    }

    /**
     * query score data
     */
    public List<Score> queryScoreData() {
        List<Score> list = new ArrayList<>();
        Cursor cursor = sd.rawQuery("select exam_id,user_name,score,exam_date" +
                " from score", null);
        while (cursor.moveToNext()) {
            String examId = cursor.getString(0);
            String userName = cursor.getString(1);
            String scoreData = cursor.getString(2);
            String examDate = cursor.getString(cursor.getColumnIndex("exam_date"));
            Score score = new Score();
            score.setExamId(examId);
            score.setUserName(userName);
            score.setScore(scoreData);
            score.setExamDate(examDate);
            list.add(score);
        }
        return list;
    }

    public Exam queryExam(String examId) {
        Exam exam = new Exam();
        Cursor cursor = sd.rawQuery("select exam_id,user_name,question_id,is_correct" +
                " from exam where exam_id = '"+examId+"'", null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String userName = cursor.getString(1);
            int questionId = cursor.getInt(2);
            int isCorrect = cursor.getInt(3);
            exam.setExamId(id);
            exam.setUserName(userName);
            exam.setQuestionId(questionId);
            exam.setIsCorrect(isCorrect);
        }
        return exam;
    }

    /**
     * close database
     */
    public void getClose() {
        if (db != null) {
            db.close();
        }
    }


    public List<Exam> queryExamDataById(String examId) {
        List<Exam> list = new ArrayList<>();
        Cursor cursor = sd.rawQuery("select exam_id,user_name,question_id,is_correct,exam_date" +
                " from exam where exam_id = '"+examId+"'", null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String userName = cursor.getString(1);
            int questionId = cursor.getInt(2);
            int isCorrect = cursor.getInt(3);
            String examDate = cursor.getString(cursor.getColumnIndex("exam_date"));
            Exam exam = new Exam();
            exam.setExamId(id);
            exam.setUserName(userName);
            exam.setQuestionId(questionId);
            exam.setIsCorrect(isCorrect);
            exam.setExamDate(examDate);
            list.add(exam);
        }
        return list;
    }
}
