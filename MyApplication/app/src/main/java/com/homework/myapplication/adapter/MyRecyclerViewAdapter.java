package com.homework.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.homework.myapplication.R;
import com.homework.myapplication.bean.Answer;
import com.homework.myapplication.bean.Question;

import java.util.List;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.AnswerViewHolder> {
    private Context context;
    private Question question;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int postion);
    }

    public MyRecyclerViewAdapter(Context context,Question question) {
        this.context = context;
        this.question = question;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview,parent,false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        List<Answer> answerList = question.getAnswerList();
        Answer answer = answerList.get(position);
        if (answer.getIsChecked()){
            holder.itemView.setBackground(context.getResources().getDrawable(R.drawable.check_bg));
        }else {
            holder.itemView.setBackground(context.getResources().getDrawable(R.drawable.uncheck_bg));
        }
        holder.tvAnswer.setText(answer.getAnswerContent()+"   iscorrect:" +answer.getIsCorrect());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return question.getAnswerList().size();
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAnswer;

        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAnswer = itemView.findViewById(R.id.tv_answer);
        }

    }
}
