package com.example.ticketbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RvActivity extends AppCompatActivity {


    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    DataAccessObject dao;
    FirebaseRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        swipeRefreshLayout = findViewById(R.id.swip);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        dao = new DataAccessObject();
        FirebaseRecyclerOptions<Concert> option =
                new FirebaseRecyclerOptions.Builder<Concert>()
                        .setQuery(dao.get(), new SnapshotParser<Concert>() {
                            @NonNull
                            @Override
                            public Concert parseSnapshot(@NonNull DataSnapshot snapshot) {
                                Concert concert = snapshot.getValue(Concert.class);
                                concert.setKey(snapshot.getKey());
                                return concert;
                            }
                        }).build();

        adapter = new FirebaseRecyclerAdapter(option) {
            @Override
            protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i, @NonNull Object o) {

                ConcertVH vh = (ConcertVH)viewHolder;
                Concert concert = (Concert)o;
                vh.txt_name.setText(concert.getName());
                vh.txt_ticketType.setText(concert.getTicketType());
                vh.txt_ticketCount.setText(concert.getTicketCount());
                vh.txt_option.setOnClickListener(v ->
                {
                    PopupMenu popupMenu = new PopupMenu(RvActivity.this,vh.txt_option);
                    popupMenu.inflate(R.menu.option_menue);
                    popupMenu.setOnMenuItemClickListener(item ->
                    {
                        switch (item.getItemId()){
                            case R.id.menu_edit:
                                Intent intent=new Intent(RvActivity.this,Activity_addConcert.class);
                                intent.putExtra("Edit",concert);
                                startActivity(intent);
                                break;

                            case R.id.menu_remove:
                                DataAccessObject dao = new DataAccessObject();

                                dao.remove(concert.getKey()).addOnSuccessListener(suc ->{

                                    Toast.makeText(RvActivity.this, "Record is removed",Toast.LENGTH_SHORT).show();

                                }).addOnFailureListener(er->{

                                    Toast.makeText(RvActivity.this,""+er.getMessage(), Toast.LENGTH_SHORT).show();
                                });



                                break;
                        }

                        return false;
                    });

                    popupMenu.show();
                });
            }

            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(RvActivity.this).inflate(R.layout.layout_item,parent,false);
                return new ConcertVH(view);
            }

            @Override
            public void onDataChanged() {
                Toast.makeText(RvActivity.this, "Data Changed",Toast.LENGTH_SHORT).show();
            }
        };

        recyclerView.setAdapter((adapter));

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}