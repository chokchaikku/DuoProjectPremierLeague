package com.pantip.dev.duoproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MatchInsert extends AppCompatActivity {
    EditText pl_ed;

    EditText go_ed;
    Button post_btn;

    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_insert);

        pl_ed = (EditText) findViewById(R.id.edit_time);

        go_ed = (EditText) findViewById(R.id.edit_vs);
        post_btn = (Button) findViewById(R.id.btn_postMatch);




        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("Match").push();
        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pl = pl_ed.getText().toString();
                String go = go_ed.getText().toString();
                mData.child("time").setValue(pl);
                mData.child("vs").setValue(go);

                Intent intent = new Intent(MatchInsert.this,Match.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
