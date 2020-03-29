package com.example.basictack;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basictack.R;

import java.util.List;

public class TaskListAdapt extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    public TaskListAdapt(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //这里的R.Layout是指向item的，也就是整个list的xml，不是单个的
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.task_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((LinearViewHolder)viewHolder).mTaskName.setText("创建的task");
    }


    @Override
    public int getItemCount() {
        return 5;
    }

    static class LinearViewHolder extends RecyclerView.ViewHolder{

        private TextView mTaskName;

        public LinearViewHolder(View itemView) {
            super(itemView);
            mTaskName = itemView.<TextView>findViewById(R.id.tv_task_list_item);
            mTaskName.setText("test");
        }
    }
}
