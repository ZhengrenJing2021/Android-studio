package com.homework.myapplication.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.homework.myapplication.R;
import com.homework.myapplication.adapter.ResultAdapter;
import com.homework.myapplication.app.MyApplication;
import com.homework.myapplication.bean.Exam;
import com.homework.myapplication.bean.Score;
import com.homework.myapplication.model.DaoManager;

import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private TextView tvScore;
    private RecyclerView recyclerView;
    private List<Exam> examList;
    private ResultAdapter resultAdapter;
    private Score score;
    private DaoManager daoManager;
    private List<Score> scoreList;
    private boolean isShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initData();
        initView();
    }

    private void initData() {
        daoManager = MyApplication.getCurrent().getDaoManager();
        scoreList = daoManager.queryScoreData();
        for (Score score : scoreList) {
            List<Exam> examList = daoManager.queryExamData(score.getExamId());
            score.setExamList(examList);
        }
    }


    private void initView() {
        tvScore = (TextView) findViewById(R.id.tv_score);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        resultAdapter = new ResultAdapter(getBaseContext(),scoreList);
        recyclerView.setAdapter(resultAdapter);
        resultAdapter.setOnItemClickListener(new ResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int postion) {
                isShow = !isShow;
                for (Score score : scoreList) {
                    score.setShow(false);
                }
                scoreList.get(postion).setShow(isShow);
                resultAdapter.notifyDataSetChanged();
            }
        });

    }

}
