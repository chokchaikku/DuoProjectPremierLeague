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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;


public class Goal extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference mData;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        Button button1 = findViewById(R.id.btn_insert_post);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoalInsert();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycle_goal);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        mData = firebaseDatabase.getReference("Goal");

    }
    public void openGoalInsert(){
        Intent intent1 = new Intent(this, GoalInsert.class);
        startActivity(intent1);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<DataFire> options = new FirebaseRecyclerOptions.Builder<DataFire>()
                .setQuery(mData,DataFire.class)
                .build();
        FirebaseRecyclerAdapter<DataFire,Card_View> adapter = new FirebaseRecyclerAdapter<DataFire, Card_View>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final Card_View holder, int position, @NonNull DataFire model) {
                final String post_key = getRef(position).getKey();
                holder.setName(model.getName());
                holder.setClub(model.getTeam());
                holder.setDoor(model.getGoalscore());
                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu popup = new PopupMenu(Goal.this, holder.cardView);
                        //Inflating the Popup using xml file
                        popup.getMenuInflater()
                                .inflate(R.menu.menu_setting, popup.getMenu());

                        //registering popup with OnMenuItemClickListener
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()){
                                    case R.id.edit_menu:
                                        Intent intent = new Intent(Goal.this,GoalEdit.class);
                                        intent.putExtra("PostKey",post_key);
                                        startActivity(intent);
                                        break;
                                    case R.id.delete_menu:
                                        FirebaseDatabase.getInstance().getReference("Goal").child(post_key).removeValue();
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
            public Card_View onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.content_card,viewGroup,false);
                return new Card_View(view);
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
            cardView = (CardView) mView.findViewById(R.id.card_view_gold);
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
