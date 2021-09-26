package com.example.ticketbooking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    ArrayList<Concert> list = new ArrayList<>();

    public RVadapter(Context ctx){
        this.context = ctx;
    }

    public void setItems(ArrayList<Concert> concert){
        list.addAll(concert);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
        return new ConcertVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ConcertVH vh = (ConcertVH)holder;
        Concert concert = list.get(position);
        vh.txt_name.setText(concert.getName());
        vh.txt_ticketType.setText(concert.getTicketType());
        vh.txt_ticketCount.setText(concert.getTicketCount());
        vh.txt_option.setOnClickListener(v ->
        {
            PopupMenu popupMenu = new PopupMenu(context,vh.txt_option);
            popupMenu.inflate(R.menu.option_menue);
            popupMenu.setOnMenuItemClickListener(item ->
            {
                switch (item.getItemId()){
                    case R.id.menu_edit:
                        Intent intent=new Intent(context,Activity_addConcert.class);
                        intent.putExtra("Edit",concert);
                        context.startActivity(intent);
                        break;

                    case R.id.menu_remove:
                        DataAccessObject dao = new DataAccessObject();

                        dao.remove(concert.getKey()).addOnSuccessListener(suc ->{

                            Toast.makeText(context, "Record is removed",Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                        }).addOnFailureListener(er->{

                            Toast.makeText(context,""+er.getMessage(), Toast.LENGTH_SHORT).show();
                        });



                        break;
                }

                return false;
            });

            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
