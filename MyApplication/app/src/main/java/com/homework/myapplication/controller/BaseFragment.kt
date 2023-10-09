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
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

abstract class BaseFragment : Fragment() {
    var view: View? = null
    var mContext: Activity? = null
    protected var isViewInitiated = false
    protected var isVisibleToUser = false
    protected var isDataInitiated = false
    var mRootView: WeakReference<View?>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (mRootView == null || mRootView!!.get() == null) {
            val view = inflater.inflate(layoutId, null)
            mRootView = WeakReference(view)
        } else {
            val parent = mRootView!!.get()!!.parent as ViewGroup
            parent?.removeView(mRootView!!.get())
        }
        initView()
        mContext = activity
        initData()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewInitiated = true
        //加载数据
        prepareFetchData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        if (isVisibleToUser) {
            prepareFetchData()
        }
    }

    /**
     *
     *
     * @param forceUpdate
     */
    @JvmOverloads
    fun prepareFetchData(forceUpdate: Boolean = false) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            lazyLoad()
            isDataInitiated = true
        }
    }

    /**
     * lazyLoad
     */
    protected abstract fun lazyLoad()

    /**
     * getLayoutId
     *
     * @return
     */
    protected abstract val layoutId: Int
    protected abstract fun initView()
    protected abstract fun initData()
    override fun onDestroy() {
        super.onDestroy()
        view = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    /**
     *
     *
     * @param clz
     * @param bundle
     */
    /**
     *
     *
     * @param clz
     */
    @JvmOverloads
    fun startActivity(clz: Class<*>?, bundle: Bundle? = null) {
        val intent = Intent()
        intent.setClass(activity!!, clz!!)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     *
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    fun startActivityForResult(
        cls: Class<*>?, bundle: Bundle?,
        requestCode: Int
    ) {
        val intent = Intent()
        intent.setClass(activity!!, cls!!)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }
}