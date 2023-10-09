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
import com.homework.myapplication.util.PecentUtil
import android.os.Build
import android.util.Log
import com.homework.myapplication.adapter.OptionAdapter
import com.homework.myapplication.controller.ExamActivity
import com.homework.myapplication.adapter.ResultAdapter
import androidx.viewpager.widget.ViewPager
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import java.io.IOException
import java.io.InputStream
import java.lang.ref.WeakReference
import java.time.LocalDate

class ExamFragment : Fragment() {
    private var tvQuestionContent: TextView? = null
    private var recyclerView: RecyclerView? = null
    private val btNext: Button? = null
    private var question: Question? = null
    private var myRecyclerViewAdapter: OptionAdapter? = null
    protected var mRootView: WeakReference<View?>? = null
    private var userName: String? = null
    private var examId: String? = null
    private var exam: Exam? = null
    private var ivQuestionContent: ImageView? = null
    private var tvTheme: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mRootView == null || mRootView!!.get() == null) {
            val view = inflater.inflate(R.layout.fragment_exam, null)
            mRootView = WeakReference(view)
        } else {
            val parent = mRootView!!.get()!!.parent as ViewGroup
            parent?.removeView(mRootView!!.get())
        }
        initView()
        return mRootView!!.get()
    }

    override fun onResume() {
        super.onResume()
        question = arguments!!.getSerializable("question") as Question?
        userName = arguments!!.getString("userName")
        examId = arguments!!.getString("examId")
        if (question == null) {
            question = arguments!!["question"] as Question?
        }
        tvQuestionContent.setText(question.getQuestionName())
        tvTheme.setText(question.getTheme())
        if (!TextUtils.isEmpty(question.getQuestionSrc())) {
            ivQuestionContent!!.visibility = View.VISIBLE
            var inputStream: InputStream? = null
            try {
                inputStream = context!!.assets.open(question.getQuestionSrc())
                Log.e("questionsrc", question.getQuestionSrc())
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val bitmap = BitmapFactory.decodeStream(inputStream)
            ivQuestionContent!!.setImageBitmap(bitmap)
        }
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = linearLayoutManager
        //Log.e("exam",question.getId()+"--------"+question.getAnswerList().size());
        myRecyclerViewAdapter = OptionAdapter(context, question)
        recyclerView!!.adapter = myRecyclerViewAdapter
        val answerList = question.getAnswerList()
        myRecyclerViewAdapter!!.setOnItemClickListener { v, postion ->
            for (answer in answerList!!) {
                answer.isChecked = false
            }
            answerList!![postion].isChecked = true
            myRecyclerViewAdapter!!.notifyDataSetChanged()
            val bundle = Bundle()
            exam = Exam()
            exam.setExamId(examId)
            exam.setUserName(userName)
            exam.setQuestionId(question.getId())
            exam.setQuestionContent(question.getQuestionName())
            if (answerList!![postion].isCorrect == 1) {
                exam.setIsCorrect(1)
            } else {
                exam.setIsCorrect(0)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                exam.setExamDate(LocalDate.now().toString())
            }
            bundle.putSerializable("data", exam)
            arguments = bundle
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("question", question)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            question = savedInstanceState.getSerializable("question") as Question?
        }
    }

    override fun onPause() {
        super.onPause()
        val bundle = Bundle()
        bundle.putSerializable("question", question)
        bundle.putSerializable("data", exam)
        arguments = bundle
    }

    private fun initView() {
        tvQuestionContent = mRootView!!.get()!!.findViewById<View>(R.id.tv_content) as TextView
        tvTheme = mRootView!!.get()!!.findViewById<View>(R.id.tv_theme) as TextView
        recyclerView = mRootView!!.get()!!.findViewById<View>(R.id.recycler_view) as RecyclerView
        ivQuestionContent = mRootView!!.get()!!.findViewById<View>(R.id.iv_content) as ImageView
    }
}