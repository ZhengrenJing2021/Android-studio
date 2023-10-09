package com.homework.myapplication.adapter

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
import android.view.View

class ResultAdapter(private val context: Context, private val scoreList: List<Score?>?) :
    RecyclerView.Adapter<ResultHolder>() {
    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(v: View?, postion: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_result, parent, false)
        return ResultHolder(view)
    }

    override fun onBindViewHolder(holder: ResultHolder, position: Int) {
        val score = scoreList!![position]
        val linearLayoutManager = LinearLayoutManager(context)
        holder.recyclerView.layoutManager = linearLayoutManager
        holder.recyclerView.setHasFixedSize(true)
        val questionAdapter = QuestionAdapter(context, score.getExamList())
        holder.recyclerView.adapter = questionAdapter
        if (score!!.isShow) {
            holder.recyclerView.visibility = View.VISIBLE
        } else {
            holder.recyclerView.visibility = View.GONE
        }
        holder.tvName.text = "Name: " + score.userName
        holder.tvResult.text = " Grade: " + score.score
        holder.tvDate.text = " Test Date: " + score.examDate
        holder.itemView.setOnClickListener { v -> onItemClickListener!!.onItemClick(v, position) }
    }

    override fun getItemCount(): Int {
        return scoreList!!.size
    }

    inner class ResultHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvResult: TextView
        val tvName: TextView
        val tvDate: TextView
        val recyclerView: RecyclerView

        init {
            tvResult = itemView.findViewById(R.id.tv_result)
            tvName = itemView.findViewById(R.id.tv_name)
            tvDate = itemView.findViewById(R.id.tv_date)
            recyclerView = itemView.findViewById(R.id.recycler_view)
        }
    }
}