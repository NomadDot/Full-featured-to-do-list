package com.example.javaprojectfophone;


import android.Manifest;
import android.app.Activity;
import android.content.Context;

import android.content.Intent;

import android.os.Bundle;
import com.example.HelperAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;

import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PannerMainLayoutActivity extends AppCompatActivity implements View.OnTouchListener {

    private static final String TAG = "CARD VIEW TEST";

    private final String FILE_NAME = "task.json";
    CardViewBuilder globalCardViewBuilder;

    Context ctxt;

    LinearLayout mainView;

    FloatingActionButton btnCreateCardView;

    RecyclerView recyclerView;

    HelperAdapter helperAdapter;

    ImageButton imgButton;

    NavigationView navgView;
    DrawerLayout drawerLayout;

    CoordinatorLayout coordinatorLayout;
    ArrayList<String> task = new ArrayList<>();

    TextView globalTextView;
    String globalData;
    WrittingAndReading writtingAndReading;

    Button btnAboutItem;
    Button btnTasksItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panner_main_layout);

        ctxt = getApplicationContext();

        mainView = findViewById(R.id.mainView);

        globalCardViewBuilder = new CardViewBuilder(ctxt);
        writtingAndReading = new WrittingAndReading(getApplicationContext());

        recyclerView = findViewById(R.id.recyclerView);

        navgView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        imgButton = findViewById(R.id.imbButton);

        btnAboutItem = findViewById(R.id.aboutItem);
        btnTasksItem = findViewById(R.id.tasksItem);

        btnCreateCardView = findViewById(R.id.floatButton);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        ScrollView scrollView = findViewById(R.id.scrollView1);

        scrollView.setOnTouchListener(this);

        // recyclerView start
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        fillRecycler();

        helperAdapter = new HelperAdapter(task, ctxt);
        recyclerView.setAdapter(helperAdapter);

        setItemTouchHelper();
        //recyclerView end

        // floatButton to create new task
        View.OnClickListener createNewCardView = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = globalCardViewBuilder.createEditText();
                editText.setOnEditorActionListener(editCheckBoxActionListener);

                btnCreateCardView.setVisibility(View.INVISIBLE);
                showKeyboard(editText);

                mainView.addView(editText);
                globalTextView = editText;

            }
        };
        btnCreateCardView.setOnClickListener(createNewCardView);

        // listener to open NavView
        View.OnClickListener openNavigationView = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navgView);
            }
        };
        imgButton.setOnClickListener(openNavigationView);

        // listener to open task activity
        View.OnClickListener openTasks = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(navgView);
            }
        };
        btnTasksItem.setOnClickListener(openTasks);

        // listener to open about activity
        View.OnClickListener openAbout = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(navgView);
                Intent intent = new Intent(PannerMainLayoutActivity.this, about_app.class);
                startActivity(intent);
                finish();
            }
        };
        btnAboutItem.setOnClickListener(openAbout);


    }

    public void setItemTouchHelper(){
        try {
            ItemTouchHelper.Callback callback =
                    new SimpleItemCallback(helperAdapter, getApplicationContext());
            ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
            touchHelper.attachToRecyclerView(recyclerView);
        } catch (Exception e) {

        }
    }

    public void fillRecycler(){
        try {
            JSONObject jsonObject = new JSONObject(writtingAndReading.openText());
            JSONArray jsonArray = jsonObject.getJSONArray("tasks");

            for(int i = 0; i < jsonArray.length();i++) {
                JSONObject taskData = jsonArray.getJSONObject(i);
                task.add(taskData.getString("task"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showKeyboard(EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService (Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput (InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        try {
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } else {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        }catch (Exception e) {}
    }

    CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked) {


            }
        }
    };

    TextView.OnEditorActionListener editCheckBoxActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                String data = v.getText().toString();
                if(data.length() != 0) {
                    mainView.removeView(v);
                    globalCardViewBuilder.setState(false);

                    task.add(data);
                    helperAdapter.notifyItemInserted(helperAdapter.getItemCount() + 1);

                    writtingAndReading.writeFile(data);

                    btnCreateCardView.setVisibility(View.VISIBLE);
                    hideKeyboard();
                }
                else {
                    mainView.removeView(v);
                    btnCreateCardView.setVisibility(View.VISIBLE);
                    hideKeyboard();
                }
            }
            return true;
        }
    };

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        // if state = 0, editText isn't generated
        if(globalCardViewBuilder.getState() == true) {
            globalData = globalTextView.getText().toString();
            Log.d(TAG, globalData);
            if(globalData.length() != 0) {
                mainView.removeView(v);

                task.add(globalData);
                helperAdapter.notifyItemInserted(helperAdapter.getItemCount() + 1);
                writtingAndReading.writeFile(globalData);

                mainView.removeView(globalTextView);
                globalCardViewBuilder.setState(false);

                btnCreateCardView.setVisibility(View.VISIBLE);
                hideKeyboard();
            }
            else {
                mainView.removeView(globalTextView);
                btnCreateCardView.setVisibility(View.VISIBLE);
                hideKeyboard();
            }
        }
        else {
            return false;
        }
        return false;
    }
}




