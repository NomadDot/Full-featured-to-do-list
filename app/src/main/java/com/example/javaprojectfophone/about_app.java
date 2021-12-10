package com.example.javaprojectfophone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class about_app extends AppCompatActivity {
    Button gitHubButton;
    Button btnAboutItem;
    Button btnTasksItem;
    Button telegramButton;
    Button instagramButton;
    ImageButton imgButton;
    DrawerLayout drawerLayout;
    NavigationView navgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        gitHubButton = findViewById(R.id.githubButton);
        telegramButton = findViewById(R.id.telegramButton);
        instagramButton = findViewById(R.id.instaButton);
        btnAboutItem = findViewById(R.id.aboutItemAboutApp);
        btnTasksItem = findViewById(R.id.tasksItemAboutApp);

        View.OnClickListener openGitHubPage = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://github.com/NomadDot");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        };
        gitHubButton.setOnClickListener(openGitHubPage);

        drawerLayout = findViewById(R.id.drawerLayoutAbout);
        navgView = findViewById(R.id.navigationViewR);



        View.OnClickListener openTasksS = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(navgView);
                Intent intent = new Intent(about_app.this, PannerMainLayoutActivity.class);
                startActivity(intent);
                finish();
            }
        };
        btnTasksItem.setOnClickListener(openTasksS);

        View.OnClickListener openAboutS = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(navgView);
            }
        };
        btnAboutItem.setOnClickListener(openAboutS);

        View.OnClickListener openTelegram = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://t.me/RomanVolosh");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        };
        telegramButton.setOnClickListener(openTelegram);

        View.OnClickListener openInstagram = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.linkedin.com/in/роман-волошин-82a246222");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        };
        instagramButton.setOnClickListener(openInstagram);

        imgButton = findViewById(R.id.imbButtonAbout);
        View.OnClickListener openNavigationView = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navgView);
            }
        };
        imgButton.setOnClickListener(openNavigationView);

    }
}