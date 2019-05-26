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

public class GoalEdit extends AppCompatActivity {
    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;
    Button btn_P;
    EditText edt_pl;
    EditText edt_cl;
    EditText edt_go;
    String post_key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_edit);
        post_key = getIntent().getExtras().getString("PostKey");
        btn_P = (Button) findViewById(R.id.btn_post);
        edt_pl = (EditText) findViewById(R.id.edit_player);
        edt_cl = (EditText) findViewById(R.id.edit_club);
        edt_go = (EditText) findViewById(R.id.edit_goal);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("Goal").child(post_key);

        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataFire show = dataSnapshot.getValue(DataFire.class);
                edt_cl.setText(show.getTeam());
                edt_pl.setText(show.getName());
                edt_go.setText(show.getGoalscore());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pl = edt_pl.getText().toString();
                String cl = edt_cl.getText().toString();
                String go = edt_go.getText().toString();
                mData.child("goalscore").setValue(go);
                mData.child("name").setValue(pl);
                mData.child("team").setValue(cl);
                Intent intent = new Intent(GoalEdit.this,Goal.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
