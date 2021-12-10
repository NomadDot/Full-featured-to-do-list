package com.example.javaprojectfophone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.CompoundButtonCompat;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

class CardViewBuilder {
    Context ctxt;

    // state is used to define the existence of the editText [if = false, editText isn't generated]
    private boolean state = false;

    CardViewBuilder(Context ctxt) {
        this.ctxt = ctxt;
    }

    EditText createEditText(){

        EditText editText = new EditText(ctxt);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT);

        params.setMarginStart(90);
        params.setMarginEnd(80);

        editText.setLayoutParams(params);
        editText.setTextSize(20);

        ColorStateList colorStateList = ColorStateList.valueOf(ContextCompat.getColor(ctxt, R.color.mainTheme));
        editText.setBackgroundTintList(colorStateList);

        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        setState(true);
        return editText;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }
}
