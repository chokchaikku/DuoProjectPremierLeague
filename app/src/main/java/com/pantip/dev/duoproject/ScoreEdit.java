package com.pantip.dev.duoproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ScoreEdit extends AppCompatActivity {
    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;

    Button btn_P;
    EditText edt_rank;
    EditText edt_club;
    EditText edt_fight;
    EditText edt_win;
    EditText edt_ever;
    EditText edt_lose;
    EditText edt_total;
    String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_edit);

        post_key = getIntent().getExtras().getString("PostKey");
        btn_P = (Button) findViewById(R.id.btn_postScore);
        edt_rank = (EditText) findViewById(R.id.edit_rank);
        edt_club = (EditText) findViewById(R.id.edit_club);
        edt_fight = (EditText) findViewById(R.id.edit_fight);
        edt_win = (EditText) findViewById(R.id.edit_win);
        edt_ever = (EditText) findViewById(R.id.edit_ever);
        edt_lose = (EditText) findViewById(R.id.edit_lose);
        edt_total = (EditText) findViewById(R.id.edit_total);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("Score").child(post_key);

        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataFileScore show = dataSnapshot.getValue(DataFileScore.class);
                edt_rank.setText(show.getRank());
                edt_club.setText(show.getClub());
                edt_fight.setText(show.getFight());
                edt_win.setText(show.getWin());
                edt_ever.setText(show.getEver());
                edt_lose.setText(show.getLose());
                edt_total.setText(show.getTotal());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a1 = edt_rank.getText().toString();
                String a2 = edt_club.getText().toString();
                String a3 = edt_fight.getText().toString();
                String a4 = edt_win.getText().toString();
                String a5 = edt_ever.getText().toString();
                String a6 = edt_lose.getText().toString();
                String a7 = edt_total.getText().toString();

                mData.child("rank").setValue(a1);
                mData.child("club").setValue(a2);
                mData.child("fight").setValue(a3);
                mData.child("win").setValue(a4);
                mData.child("ever").setValue(a5);
                mData.child("lose").setValue(a6);
                mData.child("total").setValue(a7);
                Intent intent = new Intent(ScoreEdit.this,Score.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
