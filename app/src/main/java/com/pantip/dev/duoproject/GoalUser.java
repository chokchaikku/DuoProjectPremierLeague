package com.pantip.dev.duoproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GoalUser extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_user);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_goal);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("Goal");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<DataFire> options = new FirebaseRecyclerOptions.Builder<DataFire>()
                .setQuery(mData,DataFire.class)
                .build();
        FirebaseRecyclerAdapter<DataFire, GoalUser.Card_View> adapter = new FirebaseRecyclerAdapter<DataFire, GoalUser.Card_View>(options) {
            @Override
            protected void onBindViewHolder(@NonNull GoalUser.Card_View holder, int position, @NonNull DataFire model) {
                holder.setName(model.getName());
                holder.setClub(model.getTeam());
                holder.setDoor(model.getGoalscore());
            }

            @NonNull
            @Override
            public GoalUser.Card_View onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.content_card,viewGroup,false);
                return new GoalUser.Card_View(view);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    public static class Card_View extends RecyclerView.ViewHolder{
        View mView;
        public Card_View(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }
        public void setName(String name){
            TextView setN = (TextView) mView.findViewById(R.id.name_goal);
            setN.setText(name);
        }
        public void setClub(String club){
            TextView setC = (TextView) mView.findViewById(R.id.club_goal);
            setC.setText(club);
        }
        public void setDoor(String door){
            TextView setD = (TextView) mView.findViewById(R.id.goal_door);
            setD.setText(door);
        }
    }
}
