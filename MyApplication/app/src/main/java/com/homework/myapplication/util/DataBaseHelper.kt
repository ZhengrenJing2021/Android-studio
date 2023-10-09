package com.homework.myapplication.util

import android.app.Application
import com.homework.myapplication.model.DaoManager
import com.homework.myapplication.interfaces.CallBack
import com.homework.myapplication.bean.Score
import android.app.Activity
import android.content.Intent
import com.homework.myapplication.controller.ResultActivity
import com.homework.myapplication.app.MyApplication
import com.homework.myapplication.bean.Exam
import com.homework.myapplication.bean.Answer
import com.homework.myapplication.util.SharePerferenceUtils
import com.homework.myapplication.util.DataUtil
import com.homework.myapplication.bean.Question
import com.homework.myapplication.util.ListUtil
import android.database.sqlite.SQLiteOpenHelper
import com.homework.myapplication.util.DataBaseHelper
import android.database.sqlite.SQLiteDatabase
import android.content.SharedPreferences
import android.content.ContentValues
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.homework.myapplication.R
import android.text.TextUtils
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.TextView
import com.homework.myapplication.adapter.ResultAdapter.ResultHolder
import androidx.recyclerview.widget.LinearLayoutManager
import com.homework.myapplication.adapter.QuestionAdapter
import androidx.fragment.app.FragmentPagerAdapter
import android.os.Bundle
import kotlin.jvm.JvmOverloads
import androidx.appcompat.app.AppCompatActivity
import com.homework.myapplication.controller.NoScrollViewPager
import com.homework.myapplication.util.GuidGeneratorUtil
import com.homework.myapplication.controller.ExamFragment
import com.homework.myapplication.adapter.MyFragmentPagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import android.widget.Toast
import com.homework.myapplication.util.PecentUtil
import android.os.Build
import android.util.Log
import com.homework.myapplication.adapter.OptionAdapter
import android.widget.EditText
import com.homework.myapplication.controller.ExamActivity
import com.homework.myapplication.adapter.ResultAdapter
import androidx.viewpager.widget.ViewPager
import android.view.MotionEvent

class DataBaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    /**
     * 创建数据库表：question,answer,exam
     */
    override fun onCreate(db: SQLiteDatabase) {
        Log.i(this.databaseName, "onCreate database...")
        println("onCreate database...")
        db.execSQL("create table question (_id integer ,theme varchar(20),question_name varchar(200),question_src varchar(200))")
        //autoincrement
        db.execSQL(
            "create table answer (_id integer,"
                    + "question_id varchar(120),answer_content varchar(120),answer_src varchar(120),is_correct integer)"
        )
        db.execSQL(
            "create table exam (exam_id varchar(120),user_name varchar(120),"
                    + "question_id varchar(120),question_content varchar(120),is_correct integer,exam_date date)"
        )
        db.execSQL(
            "create table score (exam_id varchar(120),user_name varchar(120),"
                    + "score varchar(120),exam_date date)"
        )
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
    override fun onOpen(sqLiteDatabase: SQLiteDatabase) {
        super.onOpen(sqLiteDatabase)
    }

    companion object {
        private const val DATABASE_NAME = "exam.db" //数据库名字
        private const val DATABASE_VERSION = 1 //数据库版本号
    }
}