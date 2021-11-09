package com.example.javaprojectfophone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.javaprojectfophone.pojo.BoardView;

import java.util.ArrayList;
import java.util.List;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class PannerMainLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "CARD VIEW TEST";

    Button btnAddTask;
    LinearLayout taskLayout;

    // Context value will be using to do communication with classes
    Context ctxt;
    FloatingActionButton btnCreateCardView;

    EditText editTask;

    LinearLayout mainLayout;
    LinearLayout setTaskLayout;

    // Global layout for the unique
    LinearLayout layoutOfTasks;

    // Bad practice in programming, but I think it's ok for beginner
    // These 3 List<View> are using for unique the every table of plans
    List<CardView> listOfCardView = new ArrayList<>();;
    List<LinearLayout> listOfLinearLayout = new ArrayList<>();
    List<Button> listOfButton = new ArrayList<>();

    int id1 = 0;
    int id2 = -1;
    int idCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panner_main_layout);

        ctxt = this;
        BoardView brdView = new BoardView(ctxt);
        mainLayout = findViewById(R.id.main_layout);

       // btnAddTask = findViewById(R.id.btnAddTask);
        //taskLayout = findViewById(R.id.taskLayout);
        btnCreateCardView = findViewById(R.id.floatButton);
        View.OnClickListener createNewCardView = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView crdView = createCardView();
                mainLayout.addView(crdView);
            }
        };
        btnCreateCardView.setOnClickListener(createNewCardView);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public void onClick(View v) {
        int idOfView = v.getId();


        try {
            if(idOfView >= 0) {
                CardView cardView = listOfCardView.get(idOfView);
                idCounter = idOfView;
                layoutOfTasks = listOfLinearLayout.get(idOfView);
                layoutOfTasks.removeView(listOfButton.get(idOfView));
                setTaskLayout = createTaskLayout();
                layoutOfTasks.addView(setTaskLayout);

            }

            if(idOfView < 0) {
                CheckBox checkBox = createCheckBox(editTask.getText().toString());
                layoutOfTasks.removeView(setTaskLayout);
                layoutOfTasks.addView(checkBox);
                layoutOfTasks.addView(listOfButton.get(idCounter));
            }
        } catch (Exception e) {
            Log.i(TAG, "Many adds");
        }

    }

    Button createTaskButton() {
        Button btn = new Button(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);

        btn.setBackgroundResource(R.mipmap.add_new_task_foreground);
        params.gravity = CENTER;
        btn.setLayoutParams(params);

        btn.setId(id1);
        id1 += 1;
        btn.setOnClickListener(this);

        listOfButton.add(btn);
        return btn;
    }

    // These funcs are only for creating tasks in CardView
    LinearLayout createTaskLayout(){
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(params);

        EditText editText = createEditText();
        Button button = createButton();

        linearLayout.addView(editText);
        linearLayout.addView(button);

        return linearLayout;
    }

    EditText createEditText(){
        EditText editText = new EditText(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.setMarginStart(140);
        params.setMarginEnd(10);
        params.weight = 1;
        editText.setLayoutParams(params);
        editText.setTextSize(25);

        editText.setId(id2);

        editTask = editText;

        return editText;
    }

    Button createButton() {
        Button button = new Button(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, 100);
        params.weight = 7;
        button.setLayoutParams(params);
        button.setBackgroundResource(R.mipmap.add_new_task_foreground);

        button.setId(id2);
        id2 -= 1;
        button.setOnClickListener(this);
        return button;

    }

    CheckBox createCheckBox(String text) {
        CheckBox checkBox = new CheckBox(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.topMargin = 10;
        params.setMarginStart(80);
        params.setMarginEnd(80);
        checkBox.setTextSize(23);

        checkBox.setLayoutParams(params);
        checkBox.setText(text);
        return checkBox;
    }

    // Funs to create a new CardView
    TextView createHeading(String heading){
        TextView txtHeading = new TextView(this);
        txtHeading.setText(heading);
        txtHeading.setTextSize(30);
        txtHeading.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF000000")));
        txtHeading.setGravity(CENTER);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, 100);

        txtHeading.setLayoutParams(params);

        return txtHeading;
    }

    View createGreyLine() {
        View view = new View(this);
        view.setBackgroundResource(R.color.grey_500);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, 3);
        params.topMargin = 110;
        params.setMarginStart(25);
        params.setMarginEnd(25);

        view.setLayoutParams(params);

        return view;
    }

    LinearLayout createLinearLayout(){
        LinearLayout linearLayout = new LinearLayout(ctxt);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.topMargin = 120;

        Button btnAddTaskOnBoard = createTaskButton();
        linearLayout.setLayoutParams(params);

        linearLayout.addView(btnAddTaskOnBoard);

        listOfLinearLayout.add(linearLayout);
        return linearLayout;
    }

    CardView createCardView(){
        CardView cardView = new CardView(this);
        cardView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffcc80")));
        cardView.setRadius(10);;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.setMarginStart(50);
        params.setMarginEnd(50);
        params.topMargin = 15;

        Log.i(TAG, "Call method headingOfCardView");
        TextView headingOfCardView = createHeading("test heading");
        Log.i(TAG, "Call method createGreyLine");
        View greyLine = createGreyLine();
        Log.i(TAG, "Call method createLinearLayout");
        LinearLayout linearLayout = createLinearLayout();

        cardView.setLayoutParams(params);

        Log.i(TAG, "Adding views");
        cardView.addView(headingOfCardView);
        cardView.addView(greyLine);
        cardView.addView(linearLayout);

        listOfCardView.add(cardView);
        return cardView;

    }
}



