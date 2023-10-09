package com.homework.myapplication.controller

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
import android.view.View
import android.widget.Button
import java.io.Serializable
import java.util.*
import java.util.stream.Collectors

class MainActivity : AppCompatActivity() {
    private var tvName: EditText? = null
    private var tvQuestion: TextView? = null
    private var btStart: Button? = null
    private var daoManager: DaoManager? = null
    private var questionList: List<Question?>? = null
    private val questions: MutableList<Question?> = ArrayList()
    private var answersList: List<Answer?>? = null
    private var btQuery: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        daoManager = MyApplication.Companion.getCurrent().getDaoManager()
        initView()
        initData()
        btStart!!.setOnClickListener { v: View? ->
            if (TextUtils.isEmpty(tvName!!.text)) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            questions.clear()
            val themeList = questionList!!.stream().map { q: Question? -> q.getTheme() }
                .distinct().collect(Collectors.toList())
            for (theme in themeList) {
                val themeQuestionList = questionList!!.stream()
                    .filter { q: Question? -> q.getTheme().trim { it <= ' ' } == theme }
                    .collect(Collectors.toList())
                Collections.shuffle(themeQuestionList)
                val questionList2: List<Question?> = ArrayList(themeQuestionList.subList(0, 2))
                questions.addAll(questionList2)
            }
            for (question in questions) {
                val answers: MutableList<Answer?> = ArrayList()
                for (answer in answersList!!) {
                    if (question.getId() == answer.getQuestionId()) {
                        answers.add(answer)
                    }
                }
                val first =
                    answers.stream().filter { a: Answer? -> a.getIsCorrect() == 1 }.findFirst()
                if (first != null && first.isPresent) {
                    val correctAnswer = first.get()
                    val answerList =
                        answers.stream().filter { a: Answer? -> a.getIsCorrect() == 0 }.collect(
                            Collectors.toList()
                        )
                    val subList: MutableList<Answer?> = ArrayList(answerList.subList(0, 4))
                    subList.add(correctAnswer)
                    Collections.shuffle(subList)
                    question.setAnswerList(subList)
                }
            }
            val intent = Intent(this, ExamActivity::class.java)
            intent.putExtra("userName", tvName!!.text.toString().trim { it <= ' ' })
            intent.putExtra("questions", questions as Serializable)
            startActivity(intent)
            for (question in questions) {
                for (answer in question.getAnswerList()) {
                    Log.e("question",
                        question.getId()
                            .toString() + "---" + question.getTheme() + "----" + question.getQuestionSrc() + "----" + answer.answerContent + "----" + answer.isCorrect
                    )
                }
            }
        }
        btQuery!!.setOnClickListener {
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initData() {
        DataUtil.getData(baseContext, daoManager)
        questionList = daoManager!!.queryQuestionData()
        answersList = daoManager!!.queryAnswerData()
    }

    private fun initView() {
        tvName = findViewById<View>(R.id.tv_name) as EditText
        btStart = findViewById<View>(R.id.bt_start) as Button
        btQuery = findViewById<View>(R.id.bt_query) as Button
        tvQuestion = findViewById<View>(R.id.tv_question) as TextView
    }
}