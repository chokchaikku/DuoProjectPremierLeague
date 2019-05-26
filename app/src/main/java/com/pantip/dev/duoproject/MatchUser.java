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

public class MatchUser extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_user);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_match);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("Match");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<DataFireMatch> options = new FirebaseRecyclerOptions.Builder<DataFireMatch>()
                .setQuery(mData, DataFireMatch.class)
                .build();
        FirebaseRecyclerAdapter<DataFireMatch, MatchUser.Card_View> adapter = new FirebaseRecyclerAdapter<DataFireMatch, MatchUser.Card_View>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MatchUser.Card_View holder, int position, @NonNull DataFireMatch model) {
                holder.setTime(model.getTime());
                holder.setVs(model.getVs());

            }

            @NonNull
            @Override
            public MatchUser.Card_View onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.content_card_match, viewGroup, false);
                return new MatchUser.Card_View(view);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public static class Card_View extends RecyclerView.ViewHolder {
        View mView;

        public Card_View(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTime(String time) {
            TextView setN = (TextView) mView.findViewById(R.id.match_time_team);
            setN.setText(time);
        }

        public void setVs(String vs) {
            TextView setC = (TextView) mView.findViewById(R.id.match_name_team);
            setC.setText(vs);
        }
    }
}
