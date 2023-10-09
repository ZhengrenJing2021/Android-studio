package com.homework.myapplication.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.homework.myapplication.adapter.OptionAdapter;
import com.homework.myapplication.bean.Answer;
import com.homework.myapplication.bean.Exam;
import com.homework.myapplication.bean.Question;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.time.LocalDate;
import java.util.List;

import com.homework.myapplication.R;

public class ExamFragment extends Fragment {

    private TextView tvQuestionContent;
    private RecyclerView recyclerView;
    private Button btNext;
    private Question question;
    private OptionAdapter myRecyclerViewAdapter;
    protected WeakReference<View> mRootView;
    private String userName;
    private String examId;
    private Exam exam;
    private ImageView ivQuestionContent;
    private TextView tvTheme;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null || mRootView.get() == null) {
            View view=inflater.inflate(R.layout.fragment_exam, null);
            mRootView = new WeakReference<View>(view);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.get().getParent();
            if (parent != null) {
                parent.removeView(mRootView.get());
            }
        }
        initView();
        return mRootView.get();
    }



    @Override
    public void onResume() {
        super.onResume();
        question = (Question) getArguments().getSerializable("question");
        userName = getArguments().getString("userName");
        examId = getArguments().getString("examId");
        if (question==null){
            question = (Question) getArguments().get("question");
        }
        tvQuestionContent.setText(question.getQuestionName());
        tvTheme.setText(question.getTheme());
        if (!TextUtils.isEmpty(question.getQuestionSrc())){
            ivQuestionContent.setVisibility(View.VISIBLE);
            InputStream inputStream = null;
            try {
                inputStream = getContext().getAssets().open(question.getQuestionSrc());
                Log.e("questionsrc",question.getQuestionSrc());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ivQuestionContent.setImageBitmap(bitmap);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //Log.e("exam",question.getId()+"--------"+question.getAnswerList().size());
        myRecyclerViewAdapter = new OptionAdapter(getContext(),question);
        recyclerView.setAdapter(myRecyclerViewAdapter);
        final List<Answer> answerList = question.getAnswerList();
        myRecyclerViewAdapter.setOnItemClickListener((new OptionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int postion) {
                for (Answer answer : answerList) {
                    answer.setIsChecked(false);
                }
                answerList.get(postion).setIsChecked(true);
                myRecyclerViewAdapter.notifyDataSetChanged();

                Bundle bundle = new Bundle();
                exam = new Exam();
                exam.setExamId(examId);
                exam.setUserName(userName);
                exam.setQuestionId(question.getId());
                exam.setQuestionContent(question.getQuestionName());
                if (answerList.get(postion).getIsCorrect()==1){
                    exam.setIsCorrect(1);
                }else {
                    exam.setIsCorrect(0);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    exam.setExamDate(LocalDate.now().toString());
                }
                bundle.putSerializable("data", exam);
                setArguments(bundle);
            }
        }));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("question",question);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState!=null){
            question = (Question) savedInstanceState.getSerializable("question");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Bundle bundle = new Bundle();
        bundle.putSerializable("question",question);
        bundle.putSerializable("data",exam);
        setArguments(bundle);
    }

    private void initView() {
        tvQuestionContent = (TextView) mRootView.get().findViewById(R.id.tv_content);
        tvTheme = (TextView) mRootView.get().findViewById(R.id.tv_theme);
        recyclerView = (RecyclerView) mRootView.get().findViewById(R.id.recycler_view);
        ivQuestionContent = (ImageView) mRootView.get().findViewById(R.id.iv_content);
    }
}
