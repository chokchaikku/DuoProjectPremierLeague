package com.pantip.dev.duoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GoalInsert extends AppCompatActivity {
    EditText pl_ed;
    EditText cl_ed;
    EditText go_ed;
    Button post_btn;

    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_insert);
        pl_ed = (EditText) findViewById(R.id.edit_player);
        cl_ed = (EditText) findViewById(R.id.edit_club);
        go_ed = (EditText) findViewById(R.id.edit_goal);
        post_btn = (Button) findViewById(R.id.btn_post);


        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("Goal").push();
        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pl = pl_ed.getText().toString();
                String cl = cl_ed.getText().toString();
                String go = go_ed.getText().toString();
                mData.child("goalscore").setValue(go);
                mData.child("name").setValue(pl);
                mData.child("team").setValue(cl);
                Intent intent = new Intent(GoalInsert.this,Goal.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
