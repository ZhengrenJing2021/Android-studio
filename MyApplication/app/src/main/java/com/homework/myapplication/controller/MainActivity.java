package com.homework.myapplication.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.homework.myapplication.R;
import com.homework.myapplication.app.MyApplication;
import com.homework.myapplication.bean.Answer;
import com.homework.myapplication.bean.Question;
import com.homework.myapplication.model.DaoManager;
import com.homework.myapplication.util.DataUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private EditText tvName;
    private TextView tvQuestion;
    private Button btStart;
    private DaoManager daoManager;
    private List<Question> questionList;
    private List<Question> questions = new ArrayList<>();
    private List<Answer> answersList;
    private Button btQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daoManager = MyApplication.getCurrent().getDaoManager();
        initView();
        initData();
        btStart.setOnClickListener(v->{
            if (TextUtils.isEmpty(tvName.getText())){
                Toast.makeText(this,"Please enter your name",Toast.LENGTH_SHORT).show();
                return;
            }
            questions.clear();
            List<String> themeList = questionList.stream().map(q -> q.getTheme()).distinct().collect(Collectors.toList());
            for (String theme : themeList) {
                List<Question> themeQuestionList = questionList.stream().filter(q -> q.getTheme().trim().equals(theme)).collect(Collectors.toList());
                Collections.shuffle(themeQuestionList);
                List<Question> questionList2 = new ArrayList<>(themeQuestionList.subList(0, 2));
                questions.addAll(questionList2);
            }
            for (Question question : questions) {
                List<Answer> answers = new ArrayList<>();
                for (Answer answer : answersList) {
                    if (question.getId()==answer.getQuestionId()){
                        answers.add(answer);
                    }
                }
                Optional<Answer> first = answers.stream().filter(a -> a.getIsCorrect() == 1).findFirst();
                if (first!=null&&first.isPresent()){
                    Answer correctAnswer = first.get();
                    List<Answer> answerList = answers.stream().filter(a -> a.getIsCorrect() == 0).collect(Collectors.toList());
                    List<Answer> subList = new ArrayList<>(answerList.subList(0,4));
                    subList.add(correctAnswer);
                    Collections.shuffle(subList);
                    question.setAnswerList(subList);
                }
            }

            Intent intent = new Intent(this, ExamActivity.class);
            intent.putExtra("userName",tvName.getText().toString().trim());
            intent.putExtra("questions", (Serializable) questions);
            startActivity(intent);
            for (Question question : questions) {
                for (Answer answer : question.getAnswerList()) {
                    Log.e("question",question.getId()+"---"+question.getTheme()+"----"+question.getQuestionSrc()+"----"+answer.getAnswerContent()+"----"+answer.getIsCorrect());
                }
            }
        });
        btQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        DataUtil.getData(getBaseContext(),daoManager);
        questionList = daoManager.queryQuestionData();
        answersList = daoManager.queryAnswerData();
    }


    private void initView() {
        tvName = (EditText) findViewById(R.id.tv_name);
        btStart = (Button) findViewById(R.id.bt_start);
        btQuery = (Button) findViewById(R.id.bt_query);
        tvQuestion = (TextView) findViewById(R.id.tv_question);
    }
}