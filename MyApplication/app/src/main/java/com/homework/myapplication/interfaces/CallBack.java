package com.homework.myapplication.interfaces;

import android.app.Activity;

import com.homework.myapplication.bean.Score;

public interface CallBack {
    /**
     * @param score 请求结果返回的标识码
     */
    void scoreCallback(Score score, Activity activity);
}
