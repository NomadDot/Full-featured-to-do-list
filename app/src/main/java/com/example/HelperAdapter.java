package com.example;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaprojectfophone.ItemTouchHelperAdapter;
import com.example.javaprojectfophone.MainActivity;
import com.example.javaprojectfophone.PannerMainLayoutActivity;
import com.example.javaprojectfophone.R;
import com.example.javaprojectfophone.WrittingAndReading;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class HelperAdapter extends RecyclerView.Adapter<MyViewClass> implements ItemTouchHelperAdapter {
    private boolean[] currentValues;

    ArrayList<String> tasks;
    Context context;

    CompoundButton.OnCheckedChangeListener checkedChangeListener;
    String removedItem;


    public HelperAdapter(ArrayList<String> task, Context context){
        this.tasks = task;
        this.currentValues = new boolean[getItemCount()];

    }

    // array to save the states of checkboxes
    public boolean[] getCurrentValues() {
        return currentValues;
    }

    public void setCurrentValues(boolean[] currentValues) {
        this.currentValues = currentValues;
        notifyDataSetChanged();
    }



    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyViewClass holder, int position) {
        holder.task.setText(tasks.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        }});

    }

    public int getItemCount() {
        return tasks.size();
    }

    @NonNull
    @Override
    public MyViewClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("OnCreateViewHolder:", "WORK");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_row, parent, false);
        MyViewClass myViewClass = new MyViewClass(view);
        return myViewClass;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(tasks, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(tasks, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    // function is called when item (task) has swiped
    @Override
    public void onItemDismiss(int position) {
        WrittingAndReading writtingAndReading = new WrittingAndReading(context);
        removedItem = tasks.get(position);
        writtingAndReading.deleteTask(removedItem);
        tasks.remove(position);
        notifyItemRemoved(position);

    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }




}



