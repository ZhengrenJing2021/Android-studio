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
import android.widget.ImageView
import java.io.IOException
import java.io.InputStream

class OptionAdapter(private val context: Context?, question: Question?) :
    RecyclerView.Adapter<OptionAdapter.AnswerViewHolder>() {
    private val question: Question? = Question()
    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(v: View?, postion: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_option, parent, false)
        return AnswerViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val answerList = question.getAnswerList()
        val answer = answerList!![position]
        if (TextUtils.isEmpty(answer.answerContent)) {
            holder.tvAnswer.visibility = View.GONE
            holder.ivAnswer.visibility = View.VISIBLE
            var inputStream: InputStream? = null
            try {
                inputStream = context!!.assets.open(answer.answerSrc)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                holder.ivAnswer.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            holder.tvAnswer.visibility = View.VISIBLE
            holder.ivAnswer.visibility = View.GONE
            holder.tvAnswer.text = answer.answerContent
        }
        if (answer.isChecked) {
            holder.itemView.background = context!!.resources.getDrawable(R.drawable.check_bg)
        } else {
            holder.itemView.background = context!!.resources.getDrawable(R.drawable.uncheck_bg)
        }
        holder.itemView.setOnClickListener { v -> onItemClickListener!!.onItemClick(v, position) }
    }

    override fun getItemCount(): Int {
        return if (question.getAnswerList() != null) question.getAnswerList().size else 0
    }

    inner class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAnswer: TextView
        val ivAnswer: ImageView

        init {
            tvAnswer = itemView.findViewById(R.id.tv_answer)
            ivAnswer = itemView.findViewById(R.id.iv_answer)
        }
    }

    init {
        this.question = question
    }
}