package com.homework.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.homework.myapplication.R;
import com.homework.myapplication.bean.Exam;

import java.util.ArrayList;
import java.util.List;


public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.AnswerViewHolder> {
    private Context context;
    private List<Exam> examList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int postion);
    }

    public QuestionAdapter(Context context, List<Exam> examList) {
        this.context = context;
        this.examList = examList;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_question,parent,false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        Exam exam = examList.get(position);
        //holder.tvQuestion.setText(exam.get);
        holder.tvQuestion.setText("Question: "+exam.getQuestionContent());
        holder.tvIscorrect.setText(exam.getIsCorrect()==1?"true":"false");
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIscorrect;
        private TextView tvQuestion;

        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIscorrect = itemView.findViewById(R.id.tv_iscorrect);
            tvQuestion = itemView.findViewById(R.id.tv_question);
        }

    }
}
