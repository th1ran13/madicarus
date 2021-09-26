package com.example.firebasecrud;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BusVH extends RecyclerView.ViewHolder {
    public TextView txt_whereto, txt_type, txt_count,txt_option;
    public BusVH(@NonNull View itemView) {
        super(itemView);
        txt_whereto = itemView.findViewById(R.id.txt_whereto);
        txt_type = itemView.findViewById(R.id.txt_type);
        txt_count = itemView.findViewById(R.id.txt_count);
        txt_option = itemView.findViewById(R.id.txt_option);
    }
}
