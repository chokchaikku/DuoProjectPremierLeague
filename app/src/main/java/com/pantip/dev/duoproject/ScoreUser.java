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

public class ScoreUser extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_user);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_score);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("Score");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<DataFileScore> options = new FirebaseRecyclerOptions.Builder<DataFileScore>()
                .setQuery(mData, DataFileScore.class)
                .build();
        FirebaseRecyclerAdapter<DataFileScore, ScoreUser.Card_View> adapter = new FirebaseRecyclerAdapter<DataFileScore, ScoreUser.Card_View>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ScoreUser.Card_View holder, int position, @NonNull DataFileScore model) {
                holder.setRank(model.getRank());
                holder.setClub(model.getClub());
                holder.setFight(model.getFight());
                holder.setWin(model.getWin());
                holder.setEver(model.getEver());
                holder.setLose(model.getLose());
                holder.setTotal(model.getTotal());


            }

            @NonNull
            @Override
            public ScoreUser.Card_View onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.content_card_score, viewGroup, false);
                return new ScoreUser.Card_View(view);
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

        public void setRank(String rank) {
            TextView setA = (TextView) mView.findViewById(R.id.rank_score);
            setA.setText(rank);
        }

        public void setClub(String club) {
            TextView setB = (TextView) mView.findViewById(R.id.club_score);
            setB.setText(club);
        }
        public void setFight(String fight) {
            TextView setc = (TextView) mView.findViewById(R.id.fight_score);
            setc.setText(fight);
        }

        public void setWin(String win) {
            TextView setD = (TextView) mView.findViewById(R.id.win_score);
            setD.setText(win);
        }
        public void setLose(String lose) {
            TextView setE = (TextView) mView.findViewById(R.id.lose_score);
            setE.setText(lose);
        }
        public void setEver(String ever) {
            TextView setF = (TextView) mView.findViewById(R.id.ever_score);
            setF.setText(ever);
        }
        public void setTotal(String total) {
            TextView setG = (TextView) mView.findViewById(R.id.total_score);
            setG.setText(total);
        }
    }
}
