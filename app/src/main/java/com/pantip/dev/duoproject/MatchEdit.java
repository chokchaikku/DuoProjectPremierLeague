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

public class MatchEdit extends AppCompatActivity {
    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;
    Button btn_Post;
    EditText edt_pl;
    EditText edt_go;
    String post_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_edit);

        post_key = getIntent().getExtras().getString("PostKey");
        edt_pl = (EditText) findViewById(R.id.edit_time);

        edt_go = (EditText) findViewById(R.id.edit_vs);
        btn_Post = (Button) findViewById(R.id.btn_postMatch);


        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("Match").child(post_key);

        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataFireMatch show = dataSnapshot.getValue(DataFireMatch.class);

                edt_pl.setText(show.getTime());
                edt_go.setText(show.getVs());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pl = edt_pl.getText().toString();
                String go = edt_go.getText().toString();

                mData.child("time").setValue(pl);
                mData.child("vs").setValue(go);

                Intent intent = new Intent(MatchEdit.this,Match.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
