package com.homework.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.homework.myapplication.R;
import com.homework.myapplication.bean.Answer;
import com.homework.myapplication.bean.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.AnswerViewHolder> {
    private Context context;
    private Question question  = new Question();
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int postion);
    }

    public OptionAdapter(Context context, Question question) {
        this.context = context;
        this.question = question;
    }

    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_option,parent,false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        List<Answer> answerList = question.getAnswerList();
        Answer answer = answerList.get(position);

        if (TextUtils.isEmpty(answer.getAnswerContent())){
            holder.tvAnswer.setVisibility(View.GONE);
            holder.ivAnswer.setVisibility(View.VISIBLE);
            InputStream inputStream = null;
            try {
                inputStream = context.getAssets().open(answer.getAnswerSrc());
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                holder.ivAnswer.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            holder.tvAnswer.setVisibility(View.VISIBLE);
            holder.ivAnswer.setVisibility(View.GONE);
            holder.tvAnswer.setText(answer.getAnswerContent()/*+"   " +answer.getIsCorrect()*/);
        }
        if (answer.getIsChecked()){
            holder.itemView.setBackground(context.getResources().getDrawable(R.drawable.check_bg));
        }else {
            holder.itemView.setBackground(context.getResources().getDrawable(R.drawable.uncheck_bg));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return question.getAnswerList()!=null?question.getAnswerList().size():0;
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAnswer;
        private ImageView ivAnswer;

        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAnswer = itemView.findViewById(R.id.tv_answer);
            ivAnswer = itemView.findViewById(R.id.iv_answer);
        }

    }
}
