package com.homework.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.homework.myapplication.R;
import com.homework.myapplication.bean.Score;

import java.util.List;


public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultHolder> {
    private Context context;
    private List<Score> scoreList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int postion);
    }

    public ResultAdapter(Context context, List<Score> scoreList) {
        this.context = context;
        this.scoreList = scoreList;
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_result,parent,false);
        return new ResultHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, int position) {
        Score score = scoreList.get(position);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        holder.recyclerView.setHasFixedSize(true);
        QuestionAdapter questionAdapter = new QuestionAdapter(context,score.getExamList());
        holder.recyclerView.setAdapter(questionAdapter);
        if (score.isShow()){
            holder.recyclerView.setVisibility(View.VISIBLE);
        }else {
            holder.recyclerView.setVisibility(View.GONE);
        }
        holder.tvName.setText("Name: "+score.getUserName());
        holder.tvResult.setText(" Grade: "+score.getScore());
        holder.tvDate.setText(" Test Date: "+score.getExamDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    public class ResultHolder extends RecyclerView.ViewHolder {
        private TextView tvResult;
        private TextView tvName;
        private TextView tvDate;
        private RecyclerView recyclerView;

        public ResultHolder(@NonNull View itemView) {
            super(itemView);
            tvResult = itemView.findViewById(R.id.tv_result);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDate = itemView.findViewById(R.id.tv_date);
            recyclerView = itemView.findViewById(R.id.recycler_view);
        }

    }
}
