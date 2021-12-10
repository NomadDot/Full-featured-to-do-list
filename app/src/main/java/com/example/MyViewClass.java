package com.example;


import android.view.View;

import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaprojectfophone.R;

public class MyViewClass extends RecyclerView.ViewHolder {
    CheckBox task;
    MyViewClass(@NonNull View itemView){
        super(itemView);
        task = itemView.findViewById(R.id.taskCheckBox);
    }
}
