package com.example.basictack.TaskList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.basictack.R;

public class TaskListAct extends Fragment {

    private RecyclerView mTastListRV;

    public TaskListAct(String taskList){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_list_frgm, container, false);//fragment用这个layout来代替
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        show_task_list();

    }

    @Override
    public void onStart() {
        super.onStart();
        show_task_list();
    }

    private void show_task_list(){
//        mTastListRV = this.find
    }
}

