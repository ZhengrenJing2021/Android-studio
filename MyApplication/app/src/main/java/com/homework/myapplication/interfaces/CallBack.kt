package com.homework.myapplication.interfaces

import android.app.Activity
import com.homework.myapplication.bean.Score

interface CallBack {
    /**
     * @param score 请求结果返回的标识码
     */
    fun scoreCallback(score: Score?, activity: Activity)
}