package com.pantip.dev.duoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScoreInsert extends AppCompatActivity {

    Button btn_P;
    EditText pst_rank;
    EditText pst_club;
    EditText pst_fight;
    EditText pst_win;
    EditText pst_ever;
    EditText pst_lose;
    EditText pst_total;

    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_insert);

        pst_rank = (EditText) findViewById(R.id.post_rank);
        pst_club = (EditText) findViewById(R.id.post_club);
        pst_fight = (EditText) findViewById(R.id.post_fight);
        pst_win = (EditText) findViewById(R.id.post_win);
        pst_ever = (EditText) findViewById(R.id.post_ever);
        pst_lose = (EditText) findViewById(R.id.post_lose);
        pst_total = (EditText) findViewById(R.id.post_total);
        btn_P = (Button) findViewById(R.id.btn_postScore);



        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("Score").push();
        btn_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String b1 = pst_rank.getText().toString();
                String b2 = pst_club.getText().toString();
                String b3 = pst_fight.getText().toString();
                String b4 = pst_win.getText().toString();
                String b5 = pst_ever.getText().toString();
                String b6 = pst_lose.getText().toString();
                String b7 = pst_total.getText().toString();

                mData.child("rank").setValue(b1);
                mData.child("club").setValue(b2);
                mData.child("fight").setValue(b3);
                mData.child("win").setValue(b4);
                mData.child("ever").setValue(b5);
                mData.child("lose").setValue(b6);
                mData.child("total").setValue(b7);
                Intent intent = new Intent(ScoreInsert.this,Score.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
