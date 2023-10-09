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

class ResultActivity : AppCompatActivity() {
    private var tvScore: TextView? = null
    private var recyclerView: RecyclerView? = null
    private val examList: List<Exam>? = null
    private var resultAdapter: ResultAdapter? = null
    private val score: Score? = null
    private var daoManager: DaoManager? = null
    private var scoreList: List<Score?>? = null
    private var isShow = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        initData()
        initView()
    }

    private fun initData() {
        daoManager = MyApplication.Companion.getCurrent().getDaoManager()
        scoreList = daoManager!!.queryScoreData()
        for (score in scoreList) {
            val examList = daoManager!!.queryExamData(score.getExamId())
            score.setExamList(examList)
        }
    }

    private fun initView() {
        tvScore = findViewById<View>(R.id.tv_score) as TextView
        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(baseContext)
        recyclerView!!.layoutManager = linearLayoutManager
        resultAdapter = ResultAdapter(baseContext, scoreList)
        recyclerView!!.adapter = resultAdapter
        resultAdapter!!.setOnItemClickListener { v, postion ->
            isShow = !isShow
            for (score in scoreList!!) {
                score.setShow(false)
            }
            scoreList!![postion].setShow(isShow)
            resultAdapter!!.notifyDataSetChanged()
        }
    }
}