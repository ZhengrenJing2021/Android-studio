package com.homework.myapplication.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.homework.myapplication.bean.Score;
import com.homework.myapplication.controller.ExamActivity;
import com.homework.myapplication.controller.ResultActivity;
import com.homework.myapplication.interfaces.CallBack;
import com.homework.myapplication.model.DaoManager;

public class MyApplication extends Application {
    private DaoManager daoManager;
    private static MyApplication myApplication;
    private CallBack callBack = new CallBack() {
        @Override
        public void scoreCallback(Score score, Activity activity) {
            Intent intent = new Intent(activity, ResultActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        daoManager = new DaoManager(this,callBack);
    }

    public DaoManager getDaoManager(){
            return daoManager;
    }

    public static MyApplication getCurrent() {
        if (myApplication == null) {
            myApplication = new MyApplication();
        }
        return myApplication;
    }
    /**
     *low memenory close database
     * */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        daoManager.getClose();
    }

}
