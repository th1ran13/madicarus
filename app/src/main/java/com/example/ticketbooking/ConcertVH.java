package com.example.ticketbooking;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ConcertVH extends RecyclerView.ViewHolder {

    public TextView txt_name,txt_ticketType,txt_ticketCount,txt_option;
    public ConcertVH(@NonNull View itemView) {
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_ticketType = itemView.findViewById(R.id.txt_ticketType);
        txt_ticketCount = itemView.findViewById(R.id.txt_ticketCount);
        txt_option = itemView.findViewById(R.id.txt_option);
    }
}
