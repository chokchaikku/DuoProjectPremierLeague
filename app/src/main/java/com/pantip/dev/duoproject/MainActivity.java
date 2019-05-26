package com.pantip.dev.duoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.btnMatch);
        Button button2 = findViewById(R.id.btnScore);
        Button button3 = findViewById(R.id.btnGoal);
        Button button4 = findViewById(R.id.button_login);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMatchUser();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScoreUser();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoalUser();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

    }
    public void openMatchUser(){
        Intent intent1 = new Intent(this, MatchUser.class);
        startActivity(intent1);
    }

    public void openScoreUser(){
        Intent intent2 = new Intent(this, ScoreUser.class);
        startActivity(intent2);
    }

    public void openGoalUser(){
        Intent intent3 = new Intent(this, GoalUser.class);
        startActivity(intent3);
    }
    public void openLogin(){
        Intent intent4 = new Intent(this, Login.class);
        startActivity(intent4);
    }

}
