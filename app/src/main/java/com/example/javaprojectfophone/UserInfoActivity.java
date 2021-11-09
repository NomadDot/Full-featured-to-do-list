package com.example.javaprojectfophone;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.javaprojectfophone.pojo.User;

public class UserInfoActivity extends AppCompatActivity {
    private User getUser() {
        return new User(
                1L,
                "http://i.imgur.com/DvpvklR.png",
                "Roman",
                "R");
    }

}
