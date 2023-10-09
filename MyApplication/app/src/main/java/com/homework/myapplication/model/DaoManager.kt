package com.homework.myapplication.model

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
import com.homework.myapplication.adapter.OptionAdapter
import android.widget.EditText
import com.homework.myapplication.controller.ExamActivity
import com.homework.myapplication.adapter.ResultAdapter
import androidx.viewpager.widget.ViewPager
import android.view.MotionEvent
import java.util.ArrayList

class DaoManager(context: Context?, callBack: CallBack) {
    private val db: DataBaseHelper?
    private val sd: SQLiteDatabase
    private val callBack: CallBack
    fun saveQuestionInfo(question: Question) {
        db!!.writableDatabase.execSQL(
            "insert into question (_id,theme, question_name,question_src) values(?,?,?,?)",
            arrayOf<Any?>(question.id, question.theme, question.questionName, question.questionSrc)
        )
    }

    fun saveAnswerInfo(answer: Answer) {
        db!!.writableDatabase.execSQL(
            "insert into answer (_id,question_id,answer_content,answer_src,is_correct) values(?,?,?,?,?)",
            arrayOf<Any?>(
                answer.id,
                answer.questionId,
                answer.answerContent,
                answer.answerSrc,
                answer.isCorrect
            )
        )
    }

    fun saveExamInfo(exam: Exam) {
        db!!.writableDatabase.execSQL(
            "insert into exam (exam_id,user_name,question_id,question_content, is_correct,exam_date) values(?,?,?,?,?,?)",
            arrayOf<Any?>(
                exam.examId,
                exam.userName,
                exam.questionId,
                exam.questionContent,
                exam.isCorrect,
                exam.examDate
            )
        )
    }

    fun saveScoreInfo(score: Score, activity: Activity) {
        db!!.writableDatabase.execSQL(
            "insert into score (exam_id,user_name,score,exam_date) values(?,?,?,?)",
            arrayOf<Any?>(score.examId, score.userName, score.score, score.examDate)
        )
        callBack.scoreCallback(score, activity)
    }

    /**
     * add data
     */
    fun addData(tableName: String?, key: Array<String?>, values: Array<String?>) {
        val contentValues = ContentValues()
        for (i in key.indices) {
            contentValues.put(key[i], values[i])
        }
        sd.insert(tableName, null, contentValues)
        contentValues.clear()
    }

    /**
     * query question data
     */
    fun queryQuestionData(): List<Question> {
        val list: MutableList<Question> = ArrayList()
        val cursor = sd.rawQuery(
            "select _id,theme,question_name,question_src" +
                    " from question", null
        )
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val theme = cursor.getString(1)
            val questionName = cursor.getString(2)
            val questionSrc = cursor.getString(3)
            val question = Question()
            question.id = id
            question.theme = theme
            question.questionName = questionName
            question.questionSrc = questionSrc
            list.add(question)
        }
        return list
    }

    /**
     * query answer data
     */
    fun queryAnswerData(): List<Answer> {
        val list: MutableList<Answer> = ArrayList()
        val cursor = sd.rawQuery(
            "select _id,question_id,answer_content,answer_src, is_correct" +
                    " from answer", null
        )
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val questionId = cursor.getInt(1)
            val answerContent = cursor.getString(2)
            val answerSrc = cursor.getString(3)
            val isCorrect = cursor.getInt(4)
            val answer = Answer()
            answer.id = id
            answer.questionId = questionId
            answer.answerContent = answerContent
            answer.answerSrc = answerSrc
            answer.isCorrect = isCorrect
            list.add(answer)
        }
        return list
    }

    /**
     * query exam data
     */
    fun queryExamData(id: String?): List<Exam> {
        val list: MutableList<Exam> = ArrayList()
        val cursor = sd.rawQuery(
            "select exam_id,user_name,question_id,question_content,is_correct,exam_date" +
                    " from exam where exam_id = '" + id + "'", null
        )
        while (cursor.moveToNext()) {
            val examId = cursor.getString(0)
            val userName = cursor.getString(1)
            val questionId = cursor.getInt(2)
            val questionContent = cursor.getString(3)
            val isCorrect = cursor.getInt(4)
            val examDate = cursor.getString(cursor.getColumnIndex("exam_date"))
            val exam = Exam()
            exam.examId = examId
            exam.userName = userName
            exam.questionId = questionId
            exam.isCorrect = isCorrect
            exam.questionContent = questionContent
            exam.examDate = examDate
            list.add(exam)
        }
        return list
    }

    /**
     * query score data
     */
    fun queryScoreData(): List<Score> {
        val list: MutableList<Score> = ArrayList()
        val cursor = sd.rawQuery(
            "select exam_id,user_name,score,exam_date" +
                    " from score", null
        )
        while (cursor.moveToNext()) {
            val examId = cursor.getString(0)
            val userName = cursor.getString(1)
            val scoreData = cursor.getString(2)
            val examDate = cursor.getString(cursor.getColumnIndex("exam_date"))
            val score = Score()
            score.examId = examId
            score.userName = userName
            score.score = scoreData
            score.examDate = examDate
            list.add(score)
        }
        return list
    }

    fun queryExam(examId: String): Exam {
        val exam = Exam()
        val cursor = sd.rawQuery(
            "select exam_id,user_name,question_id,is_correct" +
                    " from exam where exam_id = '" + examId + "'", null
        )
        while (cursor.moveToNext()) {
            val id = cursor.getString(0)
            val userName = cursor.getString(1)
            val questionId = cursor.getInt(2)
            val isCorrect = cursor.getInt(3)
            exam.examId = id
            exam.userName = userName
            exam.questionId = questionId
            exam.isCorrect = isCorrect
        }
        return exam
    }

    /**
     * close database
     */
    val close: Unit
        get() {
            db?.close()
        }

    fun queryExamDataById(examId: String): List<Exam> {
        val list: MutableList<Exam> = ArrayList()
        val cursor = sd.rawQuery(
            "select exam_id,user_name,question_id,is_correct,exam_date" +
                    " from exam where exam_id = '" + examId + "'", null
        )
        while (cursor.moveToNext()) {
            val id = cursor.getString(0)
            val userName = cursor.getString(1)
            val questionId = cursor.getInt(2)
            val isCorrect = cursor.getInt(3)
            val examDate = cursor.getString(cursor.getColumnIndex("exam_date"))
            val exam = Exam()
            exam.examId = id
            exam.userName = userName
            exam.questionId = questionId
            exam.isCorrect = isCorrect
            exam.examDate = examDate
            list.add(exam)
        }
        return list
    }

    init {
        db = DataBaseHelper(context)
        sd = db.getWritableDatabase()
        this.callBack = callBack
    }
}