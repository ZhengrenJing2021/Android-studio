package com.homework.myapplication.util;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "exam.db";  //数据库名字
    private static final int DATABASE_VERSION = 1;         //数据库版本号


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 创建数据库表：question,answer,exam
     **/
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(this.getDatabaseName(), "onCreate database...");
        System.out.println("onCreate database...");
        db.execSQL("create table question (_id integer ,theme varchar(20),question_name varchar(200),question_src varchar(200))");
        //autoincrement
        db.execSQL("create table answer (_id integer,"
                + "question_id varchar(120),answer_content varchar(120),answer_src varchar(120),is_correct integer)");
        db.execSQL("create table exam (exam_id varchar(120),user_name varchar(120),"
                + "question_id varchar(120),question_content varchar(120),is_correct integer,exam_date date)");
        db.execSQL("create table score (exam_id varchar(120),user_name varchar(120),"
                + "score varchar(120),exam_date date)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase sqLiteDatabase) {
        super.onOpen(sqLiteDatabase);
    }
}
