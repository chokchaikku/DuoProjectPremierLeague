package com.pantip.dev.duoproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Score extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        Button button1 = findViewById(R.id.btn_insert_post);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMatchInsert();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycle_score);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("Score");

    }
    public void openMatchInsert(){
        Intent intent1 = new Intent(this, ScoreInsert.class);
        startActivity(intent1);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<DataFileScore> options = new FirebaseRecyclerOptions.Builder<DataFileScore>()
                .setQuery(mData,DataFileScore.class)
                .build();
        FirebaseRecyclerAdapter<DataFileScore, Score.Card_View> adapter = new FirebaseRecyclerAdapter<DataFileScore, Score.Card_View>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final Score.Card_View holder, int position, @NonNull DataFileScore model) {
                final String post_key = getRef(position).getKey();
                holder.setRank(model.getRank());
                holder.setClub(model.getClub());
                holder.setFigth(model.getFight());
                holder.setWin(model.getWin());
                holder.setEver(model.getEver());
                holder.setLose(model.getLose());
                holder.setTotal(model.getTotal());
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(Score.this, holder.cardView);
                        //Inflating the Popup using xml file
                        popup.getMenuInflater()
                                .inflate(R.menu.menu_setting, popup.getMenu());

                        //registering popup with OnMenuItemClickListener
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.edit_menu:
                                        Intent intent = new Intent(Score.this,ScoreEdit.class);
                                        intent.putExtra("PostKey",post_key);
                                        startActivity(intent);
                                        break;
                                    case R.id.delete_menu:
                                        FirebaseDatabase.getInstance().getReference("Score").child(post_key).removeValue();
                                        break;
                                }
                                return true;
                            }
                        });

                        popup.show(); //showing popup menu

                    }
                });
            }

            @NonNull
            @Override
            public Score.Card_View onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.content_card_score,viewGroup,false);
                return new Score.Card_View(view);
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    public static class Card_View extends RecyclerView.ViewHolder{
        View mView;
        CardView cardView;
        public Card_View(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            cardView = (CardView) mView.findViewById(R.id.card_view_score);
        }
        public void setRank(String rank){
            TextView setN = (TextView) mView.findViewById(R.id.rank_score);
            setN.setText(rank);
        }
        public void setClub(String club){
            TextView setC = (TextView) mView.findViewById(R.id.club_score);
            setC.setText(club);
        }
        public void setFigth(String fight){
            TextView setD = (TextView) mView.findViewById(R.id.fight_score);
            setD.setText(fight);
        }
        public void setWin(String win){
            TextView setD = (TextView) mView.findViewById(R.id.win_score);
            setD.setText(win);
        }
        public void setEver(String ever){
            TextView setD = (TextView) mView.findViewById(R.id.ever_score);
            setD.setText(ever);
        }
        public void setLose(String lose){
            TextView setD = (TextView) mView.findViewById(R.id.lose_score);
            setD.setText(lose);
        }
        public void setTotal(String total){
            TextView setD = (TextView) mView.findViewById(R.id.total_score);
            setD.setText(total);
        }
    }

}
