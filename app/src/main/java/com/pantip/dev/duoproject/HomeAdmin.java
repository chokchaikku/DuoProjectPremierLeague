package com.pantip.dev.duoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeAdmin extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        Button button1 = findViewById(R.id.btnMatch);
        Button button2 = findViewById(R.id.btnScore);
        Button button3 = findViewById(R.id.btnGoal);

        mAuth = FirebaseAuth.getInstance();


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMatch();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScore();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoal();
            }
        });

    }
    public void openMatch(){
        Intent intent = new Intent(this, Match.class);
        startActivity(intent);
    }

    public void openScore(){
        Intent intent = new Intent(this, Score.class);
        startActivity(intent);
    }

    public void openGoal(){
        Intent intent = new Intent(this, Goal.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_menu:
                mAuth.signOut();
                Intent intent = new Intent(HomeAdmin.this,MainActivity.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

