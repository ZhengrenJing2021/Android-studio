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
import com.homework.myapplication.adapter.OptionAdapter
import android.widget.EditText
import com.homework.myapplication.controller.ExamActivity
import com.homework.myapplication.adapter.ResultAdapter
import androidx.viewpager.widget.ViewPager
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import java.time.LocalDate
import java.util.ArrayList
import java.util.stream.Collectors

class ExamActivity : AppCompatActivity() {
    private var viewPager: NoScrollViewPager? = null
    private var btNext: Button? = null
    private var btBack: Button? = null
    private var questions: ArrayList<Question>? = null
    private var currentPosition = 0
    private var userName: String? = null
    private var daoManager: DaoManager? = null
    private val examList: MutableList<Exam> = ArrayList()
    private val activity: Activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        initData()
        initView()
    }

    private fun initData() {
        questions = intent.getSerializableExtra("questions") as ArrayList<Question>?
        userName = intent.getStringExtra("userName")
        daoManager = MyApplication.Companion.getCurrent().getDaoManager()
    }

    private fun initView() {
        viewPager = findViewById<View>(R.id.viewPager) as NoScrollViewPager
        btNext = findViewById<View>(R.id.bt_next) as Button
        btBack = findViewById<View>(R.id.bt_back) as Button
        val fragmentList: MutableList<Fragment> = ArrayList()
        val examId = GuidGeneratorUtil.newGuid()
        for (i in 0..13) {
            val examFragment = ExamFragment()
            val bundle = Bundle()
            bundle.putSerializable("question", questions!![i])
            bundle.putString("userName", userName)
            bundle.putString("examId", examId)
            examFragment.arguments = bundle
            fragmentList.add(examFragment)
        }
        val fragmentPagerAdapter = MyFragmentPagerAdapter(this.supportFragmentManager, fragmentList)
        viewPager!!.adapter = fragmentPagerAdapter
        viewPager!!.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                currentPosition = position
                if (position == fragmentList.size - 1) {
                    btNext!!.text = "Submit"
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        btBack!!.setOnClickListener {
            if (currentPosition != 0) {
                viewPager!!.currentItem = currentPosition - 1
            }
        }
        btNext!!.setOnClickListener(View.OnClickListener {
            val exam =
                fragmentPagerAdapter.getItem(currentPosition).arguments!!.getSerializable("data") as Exam?
            if (exam == null) {
                Toast.makeText(baseContext, "Be sure to select an option!", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }
            val first =
                examList.stream().filter { e: Exam -> e.questionId == exam.questionId }.findFirst()
            if (first != null && first.isPresent) {
                for (i in examList.indices) {
                    if (examList[i].questionId == exam.questionId) {
                        examList.remove(examList[i])
                        examList.add(exam)
                    }
                }
            } else {
                examList.add(exam)
            }
            if (currentPosition != fragmentList.size - 1) {
                viewPager!!.currentItem = currentPosition + 1
            } else {
                for (exam1 in examList) {
                    daoManager!!.saveExamInfo(exam1)
                }
                val correctNum = examList.stream().filter { e: Exam -> e.isCorrect == 1 }.collect(
                    Collectors.toList()
                ).size
                val score = Score()
                score.examId = examId
                score.userName = userName
                score.score = PecentUtil.percnet(correctNum / 14.0)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    score.examDate = LocalDate.now().toString()
                }
                daoManager!!.saveScoreInfo(score, activity)
            }
        })
    }
}