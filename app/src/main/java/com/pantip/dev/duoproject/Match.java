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

public class Match extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Button button1 = findViewById(R.id.btn_insert_post);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMatchInsert();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycle_match);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("Match");

    }
    public void openMatchInsert(){
        Intent intent = new Intent(this, MatchInsert.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<DataFireMatch> options = new FirebaseRecyclerOptions.Builder<DataFireMatch>()
                .setQuery(mData,DataFireMatch.class)
                .build();
        FirebaseRecyclerAdapter<DataFireMatch, Match.Card_View> adapter = new FirebaseRecyclerAdapter<DataFireMatch, Card_View>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final Match.Card_View holder, int position, @NonNull DataFireMatch model) {
                final String post_key = getRef(position).getKey();
                holder.setTime(model.getTime());
                holder.setVs(model.getVs());

                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(Match.this, holder.cardView);
                        //Inflating the Popup using xml file
                        popup.getMenuInflater()
                                .inflate(R.menu.menu_setting, popup.getMenu());

                        //registering popup with OnMenuItemClickListener
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.edit_menu:
                                        Intent intent = new Intent(Match.this,MatchEdit.class);
                                        intent.putExtra("PostKey",post_key);
                                        startActivity(intent);
                                        break;
                                    case R.id.delete_menu:
                                        FirebaseDatabase.getInstance().getReference("Match").child(post_key).removeValue();
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
            public Match.Card_View onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.content_card_match,viewGroup,false);
                return new Match.Card_View(view);
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
            cardView = (CardView) mView.findViewById(R.id.card_view_Match1);
        }
        public void setTime(String time){
            TextView setN = (TextView) mView.findViewById(R.id.match_time_team);
            setN.setText(time);
        }
        public void setVs(String vs){
            TextView setC = (TextView) mView.findViewById(R.id.match_name_team);
            setC.setText(vs);
        }

    }

}
