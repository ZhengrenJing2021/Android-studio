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
import com.homework.myapplication.adapter.OptionAdapter
import android.widget.EditText
import com.homework.myapplication.controller.ExamActivity
import com.homework.myapplication.adapter.ResultAdapter
import androidx.viewpager.widget.ViewPager
import android.view.MotionEvent

object SharePerferenceUtils {
    private var sp: SharedPreferences? = null

    // 1,存储boolean变量方法
    fun putBoolean(ctx: Context, key: String?, value: Boolean) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().putBoolean(key, value).commit()
    }

    // 2,读取boolean变量方法
    fun getBoolean(ctx: Context, key: String?, defValue: Boolean): Boolean {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sp!!.getBoolean(key, defValue)
    }

    fun putString(ctx: Context, key: String?, value: String?) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().putString(key, value).commit()
    }

    fun getString(ctx: Context, key: String?, defValue: String?): String? {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sp!!.getString(key, defValue)
    }

    /**
     * @param ctx
     * 上下文环境
     * @param key
     * 要从config.xml移除节点的name的名称
     */
    fun removeKey(ctx: Context, key: String?) {
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().remove(key).commit()
    }

    // 反射(扩展)
    //
    fun putInt(ctx: Context, key: String?, value: Int) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        sp!!.edit().putInt(key, value).commit()
    }

    fun getInt(ctx: Context, key: String?, defValue: Int): Int {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE)
        }
        return sp!!.getInt(key, defValue)
    }
}