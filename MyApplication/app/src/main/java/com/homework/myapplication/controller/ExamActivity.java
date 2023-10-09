package com.homework.myapplication.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.homework.myapplication.adapter.MyFragmentPagerAdapter;
import com.homework.myapplication.app.MyApplication;
import com.homework.myapplication.bean.Exam;
import com.homework.myapplication.bean.Question;
import com.homework.myapplication.bean.Score;
import com.homework.myapplication.interfaces.CallBack;
import com.homework.myapplication.model.DaoManager;
import com.homework.myapplication.util.GuidGeneratorUtil;
import com.homework.myapplication.util.PecentUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.homework.myapplication.R;

public class ExamActivity extends AppCompatActivity {

    private NoScrollViewPager viewPager;
    private Button btNext;
    private Button btBack;
    private ArrayList<Question> questions;
    private int currentPosition = 0;
    private String userName;
    private DaoManager daoManager;
    private List<Exam> examList = new ArrayList<>();
    private Activity activity = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        initData();
        initView();
    }

    private void initData() {
        questions = (ArrayList<Question>) getIntent().getSerializableExtra("questions");
        userName = getIntent().getStringExtra("userName");
        daoManager = MyApplication.getCurrent().getDaoManager();
    }


    private void initView() {
        viewPager = (NoScrollViewPager) findViewById(R.id.viewPager);
        btNext = (Button) findViewById(R.id.bt_next);
        btBack = (Button) findViewById(R.id.bt_back);
        List<Fragment> fragmentList = new ArrayList<>();
        String examId = GuidGeneratorUtil.newGuid();
        for (int i = 0; i <=13; i++) {
            ExamFragment examFragment = new ExamFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("question", questions.get(i));
            bundle.putString("userName",userName);
            bundle.putString("examId",examId);
            examFragment.setArguments(bundle);
            fragmentList.add(examFragment);
        }
        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(this.getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                if (position==fragmentList.size()-1){
                    btNext.setText("Submit");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition!=0){
                    viewPager.setCurrentItem(currentPosition-1);
                }
            }
        });
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exam exam = (Exam) fragmentPagerAdapter.getItem(currentPosition).getArguments().getSerializable("data");

                if (exam==null){
                    Toast.makeText(getBaseContext(),"Be sure to select an option!",Toast.LENGTH_SHORT).show();
                    return;
                }
                Optional<Exam> first = examList.stream().filter(e -> e.getQuestionId() == exam.getQuestionId()).findFirst();
                if (first!=null&&first.isPresent()){
                    for (int i = 0; i < examList.size(); i++) {
                        if (examList.get(i).getQuestionId()==exam.getQuestionId()){
                            examList.remove(examList.get(i));
                            examList.add(exam);
                        }
                    }
                }else {
                    examList.add(exam);
                }
                if (currentPosition!=fragmentList.size()-1){
                    viewPager.setCurrentItem(currentPosition+1);
                }else {
                    for (Exam exam1 : examList) {
                        daoManager.saveExamInfo(exam1);
                    }
                    int correctNum = examList.stream().filter(e -> e.getIsCorrect() == 1).collect(Collectors.toList()).size();
                    Score score = new Score();
                    score.setExamId(examId);
                    score.setUserName(userName);
                    score.setScore(PecentUtil.percnet(correctNum/(14.0)));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        score.setExamDate(LocalDate.now().toString());
                    }
                    daoManager.saveScoreInfo(score,activity);


                }
            }
        });
    }

}
