package com.example.javaprojectfophone.pojo;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.javaprojectfophone.R;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class BoardView {

    public CardView cardView;
    Context ctxt;
    int id = 0;

    public BoardView(Context ctxt) {
        this.ctxt = ctxt;
        cardView = createCardView(ctxt);
    }

    LinearLayout createTaskLayout(){
        LinearLayout linearLayout = new LinearLayout(ctxt);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(params);
        EditText editText = createEditText(0);
        Button button = createButton(0);

        linearLayout.addView(editText);
        linearLayout.addView(button);
        return linearLayout;
    }

    EditText createEditText(int id){
        EditText editText = new EditText(ctxt);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.setMarginStart(140);
        params.setMarginEnd(10);
        params.weight = 1;
        editText.setLayoutParams(params);
        editText.setTextSize(25);

        editText.setId(id);


        return editText;
    }

    Button createButton(int id) {
        Button button = new Button(ctxt);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, 100);
        params.weight = 7;
        button.setLayoutParams(params);
        button.setBackgroundResource(R.mipmap.add_new_task_foreground);

        button.setId(id);
        button.setOnClickListener((View.OnClickListener) ctxt);
        return button;

    }

    CheckBox createCheckBox(String text,Context ctxt) {
        CheckBox checkBox = new CheckBox(ctxt);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.topMargin = 10;
        params.setMarginStart(80);
        params.setMarginEnd(80);
        checkBox.setTextSize(23);

        checkBox.setLayoutParams(params);
        checkBox.setText(text);
        return checkBox;
    }



    // CardView
    Button createTaskButton(Context ctx) {
        Button btn = new Button(ctx);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);

        btn.setBackgroundResource(R.mipmap.add_new_task_foreground);
        params.gravity = CENTER;
        btn.setLayoutParams(params);

        btn.setId(id);
        id += 1;

        btn.setOnClickListener((View.OnClickListener) ctxt);
        return btn;
    }

    TextView createHeading(String heading, Context ctxt){
        TextView txtHeading = new TextView(ctxt);
        txtHeading.setText(heading);
        txtHeading.setTextSize(30);
        txtHeading.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF000000")));
        txtHeading.setGravity(CENTER);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, 100);

        txtHeading.setLayoutParams(params);

        return txtHeading;
    }

    View createGreyLine(Context ctxt) {
        View view = new View(ctxt);
        view.setBackgroundResource(R.color.grey_500);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, 3);
        params.topMargin = 110;
        params.setMarginStart(25);
        params.setMarginEnd(25);

        view.setLayoutParams(params);

        return view;
    }

    LinearLayout createLinearLayout(Context ctxt){
        LinearLayout linearLayout = new LinearLayout(ctxt);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.topMargin = 120;

        CheckBox checkBox = createCheckBox("My text", ctxt);
        Button btnAddTaskOnBoard = createTaskButton(ctxt);

        linearLayout.setLayoutParams(params);

        linearLayout.addView(checkBox);
        linearLayout.addView(btnAddTaskOnBoard);
        return linearLayout;
    }

    CardView createCardView(Context ctxt){
        CardView cardView = new CardView(ctxt);
        cardView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffcc80")));
        cardView.setRadius(10);;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);
        params.setMarginStart(50);
        params.setMarginEnd(50);
        params.topMargin = 15;

        TextView headingOfCardView = createHeading("test", ctxt);
        View greyLine = createGreyLine(ctxt);
        LinearLayout linearLayout = createLinearLayout(ctxt);

        cardView.setLayoutParams(params);
        cardView.addView(headingOfCardView);
        cardView.addView(greyLine);
        cardView.addView(linearLayout);

        return cardView;
    }
}

